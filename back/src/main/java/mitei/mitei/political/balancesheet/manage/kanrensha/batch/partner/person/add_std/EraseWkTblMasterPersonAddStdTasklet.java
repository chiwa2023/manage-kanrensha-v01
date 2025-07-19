package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_std;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPersonJudgeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPersonRepository;

/**
 * 関連者個人マスタ標準登録初期化Tasklet
 */
@Component
public class EraseWkTblMasterPersonAddStdTasklet implements Tasklet, StepExecutionListener {

    /** 関連者個人マスタワークテーブルRepository */
    @Autowired
    private WkTblMasterPersonRepository wkTblMasterPersonRepository;

    /** 関連者個人マスタワークテーブル判定Repository */
    @Autowired
    private WkTblMasterPersonJudgeRepository wkTblMasterPersonJudgeRepository;

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

        wkTblMasterPersonRepository.deleteByInsertUserCode(userCode);
        wkTblMasterPersonJudgeRepository.deleteByInsertUserCode(userCode);

        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
