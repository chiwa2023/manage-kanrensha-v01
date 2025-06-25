package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCorpJudgeRepository;

/**
 * 関連者企業・団体ワークテーブル修正ItemReader
 */
@Component
public class PartnerCorpWkTblFixItemReader extends RepositoryItemReader<WkTblPartnerCorpJudgeEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblPartnerCorpJudgeRepository 関連者企業・団体ワークテーブルRepository
     */
    public PartnerCorpWkTblFixItemReader(
            final @Autowired WkTblPartnerCorpJudgeRepository wkTblPartnerCorpJudgeRepository) {

        super();
        super.setRepository(wkTblPartnerCorpJudgeRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findByInsertUserCode");

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

        super.setArguments(list); // NOPMD
    }

}
