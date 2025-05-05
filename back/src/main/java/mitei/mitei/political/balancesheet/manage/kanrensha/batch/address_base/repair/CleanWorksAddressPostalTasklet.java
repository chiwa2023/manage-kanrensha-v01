package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base.repair;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalWorksRepository;

/**
 * 処理の終わった作業テーブルを全クリアする
 */
@Component
public class CleanWorksAddressPostalTasklet implements Tasklet {

    /** 郵便番号作業Repository */
    @Autowired
    private AddressPostalWorksRepository addressPostalWorksRepository;

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        // 全データ消去
        addressPostalWorksRepository.deleteAll();

        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
