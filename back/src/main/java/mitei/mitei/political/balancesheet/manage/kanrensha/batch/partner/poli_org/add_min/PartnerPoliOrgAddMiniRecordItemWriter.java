package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgAddMinResultEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgAddMinResultRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory01Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateDokujiCodeForPoliOrgUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateUserLeastDtoByBatchParamUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業団体マスタ履歴最小登録IterWriter
 */
@Component
public class PartnerPoliOrgAddMiniRecordItemWriter extends JpaItemWriter<WkTblPartnerPoliOrgAddMinEntity> {

    /** 関連者政治団体履歴(01)Repository */
    @Autowired
    private PartnerPoliOrgHistory01Repository partnerPoliOrgHistory01Repository;

    /** 関連者政治団体マスタRepository */
    @Autowired
    private MasterPoliticalOrganizationRepository masterPoliticalOrganizationRepository;

    /** 関連者政治団体マスタ履歴処理結果Repository */
    @Autowired
    private WkTblPartnerPoliOrgAddMinResultRepository wkTblPartnerPoliOrgAddMinResultRepository;

    /** 全文検索用フォーマットUtility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者コード政治団体用発行Utility */
    @Autowired
    private CreateDokujiCodeForPoliOrgUtil createDokujiCodeForPoliOrgUtil;

    /** ユーザ最低限Dto */
    private UserPersonLeastDto userDto;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public PartnerPoliOrgAddMiniRecordItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }

    /**
     * BeforeStep(読み取りファイル指定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        userDto = createUserLeastDtoByBatchParamUtil.practice(stepExecution);
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends WkTblPartnerPoliOrgAddMinEntity> items) {

        final List<WkTblPartnerPoliOrgAddMinResultEntity> list = new ArrayList<>();

        // 編集処理
        for (WkTblPartnerPoliOrgAddMinEntity entity : items) {

            String kanrenshaCode = createDokujiCodeForPoliOrgUtil.practice("");

            int masterId = this.insertMaster(entity, kanrenshaCode);
            int historyId = this.insertHistory(entity, kanrenshaCode);

            // 両方間違いなく更新できたら結果に残す
            if (masterId != 0 && historyId != 0) {
                list.add(this.createResult(entity));
                wkTblPartnerPoliOrgAddMinResultRepository.flush();
            }
        }

        wkTblPartnerPoliOrgAddMinResultRepository.saveAllAndFlush(list);
    }

    private int insertMaster(final WkTblPartnerPoliOrgAddMinEntity entityWkTbl, final String kanrenshaCode) {

        MasterPoliticalOrganizationEntity entity = new MasterPoliticalOrganizationEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setPoliOrgKanrenshaCode(kanrenshaCode);
        entity.setCompareNameText(formatNaturalSearchTextUtil.practice(entity.getPartnerName()));

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setMasterPoliticalOrganizationId(0); // auto_increment明示

        return masterPoliticalOrganizationRepository.save(entity).getMasterPoliticalOrganizationId();

    }

    private int insertHistory(final WkTblPartnerPoliOrgAddMinEntity entityWkTbl, final String kanrenshaCode) {

        // TODO 47都道府県とそれ以外に分割して登録する
        PartnerPoliOrgHistory01Entity entity = new PartnerPoliOrgHistory01Entity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setPoliOrgKanrenshaCode(kanrenshaCode);

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示

        return partnerPoliOrgHistory01Repository.save(entity).getPartnerPoliOrgHistoryId();

    }

    private WkTblPartnerPoliOrgAddMinResultEntity createResult(final WkTblPartnerPoliOrgAddMinEntity entityWkTbl) {
        WkTblPartnerPoliOrgAddMinResultEntity entity = new WkTblPartnerPoliOrgAddMinResultEntity();
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setWkTblPartnerPoliOrgAddMinId(entityWkTbl.getWkTblPartnerPoliOrgAddMinId());

        return entity;
    }

}
