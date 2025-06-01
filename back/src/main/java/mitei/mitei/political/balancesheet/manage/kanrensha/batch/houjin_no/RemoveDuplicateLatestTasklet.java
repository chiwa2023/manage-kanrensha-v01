package mitei.mitei.political.balancesheet.manage.kanrensha.batch.houjin_no;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.HoujinNoLatestEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.HoujinNoLatestRepository;

/**
 * 履歴と最新の両方にの存在するデータを最新から削除するTasklet
 */
@Component
public class RemoveDuplicateLatestTasklet implements Tasklet { 

    /** 法人番号最新Repository */
    @Autowired
    private HoujinNoLatestRepository houjinNoLatestRepository;

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        List<HoujinNoLatestEntity> list = houjinNoLatestRepository.findRestLatestData();
        houjinNoLatestRepository.deleteAll(list);
        
        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
