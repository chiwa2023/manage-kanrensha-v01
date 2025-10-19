package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_std;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人標準登録マスタ複写ItemReader
 */
@Component
public class MasterPersonAddStdRecordItemReader extends RepositoryItemReader<WkTblMasterPersonEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblMasterPersonRepository 関連者個人標準登録ワークテーブルRepository
     */
    public MasterPersonAddStdRecordItemReader(
            final @Autowired WkTblMasterPersonRepository wkTblMasterPersonRepository) {

        super();
        super.setRepository(wkTblMasterPersonRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findByInsertUserCodeAndIsLatestAndIsAffected");

        List<Object> list = new ArrayList<>();
        super.setArguments(list); // NOPMD
    }

    /**
     * BeforeStep(読み取りファイル指定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        Integer userCode = Math.toIntExact(stepExecution.getJobParameters().getLong("userCode"));

        List<Object> list = new ArrayList<>();
        list.add(userCode);
        list.add(SetTableDataHistoryUtil.INSERT_STATE);
        list.add(true);

        super.setArguments(list); // NOPMD
    }

}
