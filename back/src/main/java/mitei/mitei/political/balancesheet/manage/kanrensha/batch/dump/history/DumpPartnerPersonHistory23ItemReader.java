package mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory23Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory23Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 基準時間より前の関連者個人最新履歴を抽出する
 */
@Component
public class DumpPartnerPersonHistory23ItemReader extends RepositoryItemReader<PartnerPersonHistory23Entity> {

    /**
     * コンストラクタ
     *
     * @param partnerPersonHistory23Repository 関連者企業履歴23Repository
     */
    public DumpPartnerPersonHistory23ItemReader(
            final @Autowired PartnerPersonHistory23Repository partnerPersonHistory23Repository) {
        super();
        super.setRepository(partnerPersonHistory23Repository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findByInsertTimestampLessThanAndIsLatest");

        List<Object> list = new ArrayList<>();
        super.setArguments(list); // NOPMD
    }

    /**
     * 起動条件を設定する
     *
     * @param stepExecution StepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        LocalDateTime datetimeEnd = stepExecution.getJobParameters().getLocalDateTime("datetimeEnd");

        List<Object> list = new ArrayList<>();
        list.add(datetimeEnd);
        list.add(SetTableDataHistoryUtil.INSERT_STATE);

        super.setArguments(list);
    }

}
