package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.rireki;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPersonHistoryRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPersonJudgeRepository;

/**
 * 関連者個人履歴登録処理ワークテーブルデータを操作者で消去する
 */
@Component
public class EraseWkTblParnerPersonHistoryTasklet implements Tasklet ,StepExecutionListener{

    /** 関連者個人履歴Repository */
    @Autowired
    private WkTblPartnerPersonHistoryRepository wkTblPartnerPersonHistoryRepository;

    /** 関連者個人本登録判断Repository */
    @Autowired
    private WkTblPartnerPersonJudgeRepository wkTblPartnerPersonJudgeRepository;

    /** 操作者ユーザコード */
    private Integer userCode;

    /**
     * 起動条件を設定する
     *
     * @param stepExecution StepExecution
     */
    @BeforeStep
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        userCode = Math.toIntExact(stepExecution.getJobParameters().getLong("userCode"));
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        wkTblPartnerPersonHistoryRepository.deleteByInsertUserCode(userCode);

        wkTblPartnerPersonJudgeRepository.deleteByInsertUserCode(userCode);

        // 処理終了
        return RepeatStatus.FINISHED;
    }
}
