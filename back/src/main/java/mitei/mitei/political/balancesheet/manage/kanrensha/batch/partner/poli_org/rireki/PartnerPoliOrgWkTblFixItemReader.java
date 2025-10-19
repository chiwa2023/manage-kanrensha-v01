package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.rireki;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgJudgeRepository;

/**
 * 関連者政治団体ワークテーブル修正ItemReader
 */
@Component
public class PartnerPoliOrgWkTblFixItemReader extends RepositoryItemReader<WkTblPartnerPoliOrgJudgeEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblPartnerPoliOrgJudgeRepository 関連者政治団体ワークテーブルRepository
     */
    public PartnerPoliOrgWkTblFixItemReader(
            final @Autowired WkTblPartnerPoliOrgJudgeRepository wkTblPartnerPoliOrgJudgeRepository) {

        super();
        super.setRepository(wkTblPartnerPoliOrgJudgeRepository);
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
