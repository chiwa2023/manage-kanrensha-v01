package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_std;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterCorpJudgeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterCorpRepository;

/**
 * 関連者企業・団体マスタ標準登録初期化Tasklet
 */
@Component
public class EraseWkTblMasterCorporationAddStdTasklet implements Tasklet, StepExecutionListener {

    /** 関連者企業・団体マスタワークテーブルRepository */
    @Autowired
    private WkTblMasterCorpRepository wkTblMasterCorporationRepository;

    /** 関連者企業・団体マスタワークテーブル判定Repository */
    @Autowired
    private WkTblMasterCorpJudgeRepository wkTblMasterCorporationJudgeRepository;

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

        wkTblMasterCorporationRepository.deleteByInsertUserCode(userCode);
        wkTblMasterCorporationJudgeRepository.deleteByInsertUserCode(userCode);

        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
