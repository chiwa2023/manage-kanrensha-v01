package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_min;

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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonAddMinResultEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPersonAddMinResultRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory01Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateDokujiCodeForPersonUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateUserLeastDtoByBatchParamUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業団体マスタ履歴最小登録IterWriter
 */
@Component
public class PartnerPersonAddMiniRecordItemWriter extends JpaItemWriter<WkTblPartnerPersonAddMinEntity> {

    /** 関連者個人履歴(01)Repository */
    @Autowired
    private PartnerPersonHistory01Repository partnerPersonHistory01Repository;

    /** 関連者個人マスタRepository */
    @Autowired
    private MasterPersonRepository masterPersonRepository;

    /** 関連者個人マスタ履歴処理結果Repository */
    @Autowired
    private WkTblPartnerPersonAddMinResultRepository wkTblPartnerPersonAddMinResultRepository;

    /** 全文検索用フォーマットUtility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者コード個人用発行Utility */
    @Autowired
    private CreateDokujiCodeForPersonUtil createDokujiCodeForPersonUtil;

    /** ユーザ最低限Dto */
    private UserPersonLeastDto userDto;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public PartnerPersonAddMiniRecordItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblPartnerPersonAddMinEntity> items) {

        final List<WkTblPartnerPersonAddMinResultEntity> list = new ArrayList<>();

        // 編集処理
        for (WkTblPartnerPersonAddMinEntity entity : items) {

            String kanrenshaCode = createDokujiCodeForPersonUtil.practice("");

            int masterId = this.insertMaster(entity, kanrenshaCode);
            int historyId = this.insertHistory(entity, kanrenshaCode);

            // 両方間違いなく更新できたら結果に残す
            if (masterId != 0 && historyId != 0) {
                list.add(this.createResult(entity));
                wkTblPartnerPersonAddMinResultRepository.flush();
            }
        }

        wkTblPartnerPersonAddMinResultRepository.saveAllAndFlush(list);
    }

    private int insertMaster(final WkTblPartnerPersonAddMinEntity entityWkTbl, final String kanrenshaCode) {

        MasterPersonEntity entity = new MasterPersonEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setPersonKanrenshaCode(kanrenshaCode);
        entity.setCompareNameText(formatNaturalSearchTextUtil.practice(entity.getPartnerName()));

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setMasterPersonId(0); // auto_increment明示

        return masterPersonRepository.save(entity).getMasterPersonId();

    }

    private int insertHistory(final WkTblPartnerPersonAddMinEntity entityWkTbl, final String kanrenshaCode) {

        // TODO 47都道府県とそれ以外に分割して登録する
        PartnerPersonHistory01Entity entity = new PartnerPersonHistory01Entity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setPersonKanrenshaCode(kanrenshaCode);

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示

        return partnerPersonHistory01Repository.save(entity).getPartnerPersonHistoryId();

    }

    private WkTblPartnerPersonAddMinResultEntity createResult(final WkTblPartnerPersonAddMinEntity entityWkTbl) {
        WkTblPartnerPersonAddMinResultEntity entity = new WkTblPartnerPersonAddMinResultEntity();
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setWkTblPartnerPersonAddMinId(entityWkTbl.getWkTblPartnerPersonAddMinId());

        return entity;
    }

}
