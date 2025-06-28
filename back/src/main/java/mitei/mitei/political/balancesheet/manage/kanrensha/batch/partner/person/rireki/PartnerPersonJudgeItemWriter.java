package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.rireki;


import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPersonJudgeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateUserLeastDtoByBatchParamUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人ワークテーブル判定ItemWriter
 */
@Component
public class PartnerPersonJudgeItemWriter extends JpaItemWriter<WkTblPartnerPersonJudgeEntity> {


    /** 関連者個人ワークテーブル判定Repository */
    @Autowired
    private WkTblPartnerPersonJudgeRepository wkTblPartnerPersonJudgeRepository;

    
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
    public PartnerPersonJudgeItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblPartnerPersonJudgeEntity> items) {

        for (WkTblPartnerPersonJudgeEntity entity : items) {
            setTableDataHistoryUtil.practiceInsert(userDto, entity);
        }

        wkTblPartnerPersonJudgeRepository.saveAll(items);
    }
}
