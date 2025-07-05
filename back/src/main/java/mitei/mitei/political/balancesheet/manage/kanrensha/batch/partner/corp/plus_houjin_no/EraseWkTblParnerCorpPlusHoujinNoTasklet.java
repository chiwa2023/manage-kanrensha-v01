package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.plus_houjin_no;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCorpPlusHojinNoRepository;

/**
 * 企業法人番号追加ワークテーブルから該当ユーザデータ消去Taskelt
 */
@Component
public class EraseWkTblParnerCorpPlusHoujinNoTasklet implements Tasklet, StepExecutionListener {

    /** 企業法人番号追加ワークテーブルRepository */
    @Autowired
    private WkTblPartnerCorpPlusHojinNoRepository wkTblPartnerCorpPlusHojinNoRepository;

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

        wkTblPartnerCorpPlusHojinNoRepository.deleteByInsertUserCode(userCode);

        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
