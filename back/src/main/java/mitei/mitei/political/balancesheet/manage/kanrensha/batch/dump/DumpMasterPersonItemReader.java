package mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人マスタCsv出力ItemReader
 */
@Component
public class DumpMasterPersonItemReader extends RepositoryItemReader<MasterPersonEntity> {

    /**
     * コンストラクタ
     *
     * @param masterPersonRepository 関連者企業・団体Repository
     */
    public DumpMasterPersonItemReader(final @Autowired MasterPersonRepository masterPersonRepository) {
        super();
        super.setRepository(masterPersonRepository);
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
