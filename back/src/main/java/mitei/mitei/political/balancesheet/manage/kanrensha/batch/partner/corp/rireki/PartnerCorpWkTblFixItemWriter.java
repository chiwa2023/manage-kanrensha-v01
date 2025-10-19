package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.rireki;

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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerCorpHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCorpHistoryRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.InsertPartnerCorpHistoryService;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateUserLeastDtoByBatchParamUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業・団体最終書き込み処理
 */
@Component
public class PartnerCorpWkTblFixItemWriter extends JpaItemWriter<WkTblPartnerCorpHistoryEntity> {

    /** 関連者企業・団体ワークテーブルRepository */
    @Autowired
    private WkTblPartnerCorpHistoryRepository wkTbPartnerCorpHistoryRepository;

    /** 関連者企業・団体履歴新規挿入Service */
    @Autowired
    private InsertPartnerCorpHistoryService insertPartnerCorpHistoryService;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** ユーザ最低限Dto */
    private UserPersonLeastDto userDto;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public PartnerCorpWkTblFixItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblPartnerCorpHistoryEntity> items) {

        final Integer zero = 0;

        final List<WkTblPartnerCorpHistoryEntity> list = new ArrayList<>();

        // 編集処理
        for (WkTblPartnerCorpHistoryEntity entity : items) {
            if (!zero.equals(entity.getWkPartnerCorpHistoryId())) {
                if (entity.getIsAffected()) {
                    // 判定が影響させるの場合は本体に書き込み
                    this.insertHistoryTable(entity);
                    entity.setIsFinish(true);
                    entity.setJudgeReason("正常保存");
                } else {
                    entity.setIsFinish(false);
                }
                entity.setWkPartnerCorpHistoryCode(entity.getWkPartnerCorpHistoryId());
                setTableDataHistoryUtil.practiceInsert(userDto, entity);
                list.add(entity);
            }
            // ワークうテーブルから呼び出しできなかったテーブルIdが0のデータは更新対象外
        }

        wkTbPartnerCorpHistoryRepository.saveAll(list);
    }

    /* 履歴テーブル本体に保存する */
    private void insertHistoryTable(final WkTblPartnerCorpHistoryEntity entityWkTbl) {

        PartnerCorpHistoryBaseEntity entity = new PartnerCorpHistoryBaseEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        insertPartnerCorpHistoryService.practice(userDto, entity);
    }

}
