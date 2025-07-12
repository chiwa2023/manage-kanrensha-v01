package mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.sabun.history;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory44Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory44Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 開始日時以上かつ終了日時より小さい関連者政治団体最新履歴差分データを取得する44
 */
@Component
public class DumpSabunPartnerPoliOrgHistory44ItemReader extends RepositoryItemReader<PartnerPoliOrgHistory44Entity> {

    /**
     * コンストラクタ
     *
     * @param partnerPoliOrgHistory44Repository 関連者企業履歴44Repository
     */
    public DumpSabunPartnerPoliOrgHistory44ItemReader(
            final @Autowired PartnerPoliOrgHistory44Repository partnerPoliOrgHistory44Repository) {
        super();
        super.setRepository(partnerPoliOrgHistory44Repository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findByInsertTimestampGreaterThanEqualAndInsertTimestampLessThanAndIsLatest");

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

        LocalDateTime datetimeStart = stepExecution.getJobParameters().getLocalDateTime("datetimeStart");
        LocalDateTime datetimeEnd = stepExecution.getJobParameters().getLocalDateTime("datetimeEnd");

        List<Object> list = new ArrayList<>();
        list.add(datetimeStart);
        list.add(datetimeEnd);
        list.add(SetTableDataHistoryUtil.INSERT_STATE);
        super.setArguments(list);
    }

}
