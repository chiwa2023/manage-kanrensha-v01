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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPersonJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPersonJudgeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人マスタ標準登録処理結果登録ItemReader
 */
@Component
public class MasterPersonAddStdWkTblFixItemReader extends RepositoryItemReader<WkTblMasterPersonJudgeEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblMasterPersonJudgeRepository 関連者個人マスタ標準登録処理結果ワークテーブルRepository
     */
    public MasterPersonAddStdWkTblFixItemReader(
            final @Autowired WkTblMasterPersonJudgeRepository wkTblMasterPersonJudgeRepository) {

        super();
        super.setRepository(wkTblMasterPersonJudgeRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findByInsertUserCodeAndIsLatest");

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

        super.setArguments(list); // NOPMD
    }

}
