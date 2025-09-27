package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.rireki;

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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPersonHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPersonHistoryRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.InsertPartnerPersonHistoryService;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateUserLeastDtoByBatchParamUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人最終書き込み処理
 */
@Component
public class PartnerPersonWkTblFixItemWriter extends JpaItemWriter<WkTblPartnerPersonHistoryEntity> {

    /** 関連者個人ワークテーブルRepository */
    @Autowired
    private WkTblPartnerPersonHistoryRepository wkTbPartnerPersonHistoryRepository;

    /** 関連者個人挿入サービス */
    @Autowired
    private InsertPartnerPersonHistoryService insertPartnerPersonHistoryService;

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
    public PartnerPersonWkTblFixItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblPartnerPersonHistoryEntity> items) {

        final Integer zero = 0;

        final List<WkTblPartnerPersonHistoryEntity> list = new ArrayList<>();

        // 編集処理
        for (WkTblPartnerPersonHistoryEntity entity : items) {
            if (!zero.equals(entity.getWkPartnerPersonHistoryId())) {
                if (entity.getIsAffected()) {
                    // 判定が影響させるの場合は本体に書き込み
                    this.insertHistoryTable(entity);
                    entity.setIsFinish(true);
                    entity.setJudgeReason("正常保存");
                } else {
                    entity.setIsFinish(false);
                }
                entity.setWkPartnerPersonHistoryCode(entity.getWkPartnerPersonHistoryId());
                setTableDataHistoryUtil.practiceInsert(userDto, entity);
                list.add(entity);
            }
            // ワークうテーブルから呼び出しできなかったテーブルIdが0のデータは更新対象外
        }

        wkTbPartnerPersonHistoryRepository.saveAll(list);
    }

    /* 履歴テーブル本体に保存する */
    private void insertHistoryTable(final WkTblPartnerPersonHistoryEntity entityWkTbl) {

        PartnerPersonHistoryBaseEntity entity = new PartnerPersonHistoryBaseEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        insertPartnerPersonHistoryService.practice(userDto, entity);
    }

}
