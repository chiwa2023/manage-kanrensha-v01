package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.rireki;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgHistoryRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgJudgeRepository;

/**
 * 関連者政治団体履歴登録処理ワークテーブルデータを操作者で消去する
 */
@Component
public class EraseWkTblParnerPoliOrgHistoryTasklet implements Tasklet ,StepExecutionListener{

    /** 関連者政治団体履歴Repository */
    @Autowired
    private WkTblPartnerPoliOrgHistoryRepository wkTblPartnerPoliOrgHistoryRepository;

    /** 関連者政治団体本登録判断Repository */
    @Autowired
    private WkTblPartnerPoliOrgJudgeRepository wkTblPartnerPoliOrgJudgeRepository;

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

        wkTblPartnerPoliOrgHistoryRepository.deleteByInsertUserCode(userCode);

        wkTblPartnerPoliOrgJudgeRepository.deleteByInsertUserCode(userCode);

        // 処理終了
        return RepeatStatus.FINISHED;
    }
}
