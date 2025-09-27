package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_min;

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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpAddMinResultEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCorpAddMinResultRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory01Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateDokujiCodeForCorpUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateUserLeastDtoByBatchParamUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業団体マスタ履歴最小登録IterWriter
 */
@Component
public class PartnerCorpAddMiniRecordItemWriter extends JpaItemWriter<WkTblPartnerCorpAddMinEntity> {

    /** 関連者企業・団体履歴(01)Repository */
    @Autowired
    private PartnerCorpHistory01Repository partnerCorpHistory01Repository;

    /** 関連者企業・団体マスタRepository */
    @Autowired
    private MasterCorporationRepository masterCorporationRepository;

    /** 関連者企業・団体マスタ履歴処理結果Repository */
    @Autowired
    private WkTblPartnerCorpAddMinResultRepository wkTblPartnerCorpAddMinResultRepository;

    /** 全文検索用フォーマットUtility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者コード企業・団体用発行Utility */
    @Autowired
    private CreateDokujiCodeForCorpUtil createDokujiCodeForCorpUtil;

    /** ユーザ最低限Dto */
    private UserPersonLeastDto userDto;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public PartnerCorpAddMiniRecordItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblPartnerCorpAddMinEntity> items) {

        final List<WkTblPartnerCorpAddMinResultEntity> list = new ArrayList<>();

        // 編集処理
        for (WkTblPartnerCorpAddMinEntity entity : items) {

            String kanrenshaCode = createDokujiCodeForCorpUtil.practice(entity.getHoujinNo());

            int masterId = this.insertMaster(entity, kanrenshaCode);
            int historyId = this.insertHistory(entity, kanrenshaCode);

            // 両方間違いなく更新できたら結果に残す
            if (masterId != 0 && historyId != 0) {
                list.add(this.createResult(entity));
                wkTblPartnerCorpAddMinResultRepository.flush();
            }
        }

        wkTblPartnerCorpAddMinResultRepository.saveAllAndFlush(list);
    }

    private int insertMaster(final WkTblPartnerCorpAddMinEntity entityWkTbl, final String kanrenshaCode) {

        MasterCorporationEntity entity = new MasterCorporationEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setCorpKanrenshaCode(kanrenshaCode);
        entity.setCompareNameText(formatNaturalSearchTextUtil.practice(entity.getPartnerName()));

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setMasterCorporationId(0); // auto_increment明示

        return masterCorporationRepository.save(entity).getMasterCorporationId();

    }

    private int insertHistory(final WkTblPartnerCorpAddMinEntity entityWkTbl, final String kanrenshaCode) {

        // TODO 47都道府県とそれ以外に分割して登録する
        PartnerCorpHistory01Entity entity = new PartnerCorpHistory01Entity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setCorpKanrenshaCode(kanrenshaCode);

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示

        return partnerCorpHistory01Repository.save(entity).getPartnerCorpHistoryId();

    }

    private WkTblPartnerCorpAddMinResultEntity createResult(final WkTblPartnerCorpAddMinEntity entityWkTbl) {
        WkTblPartnerCorpAddMinResultEntity entity = new WkTblPartnerCorpAddMinResultEntity();
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setWkTblPartnerCorpAddMinId(entityWkTbl.getWkTblPartnerCorpAddMinId());

        return entity;
    }

}
