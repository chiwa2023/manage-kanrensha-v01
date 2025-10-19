package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgAddMinRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者政治団体最小登録実登録ItemReader
 */
@Component
public class PartnerPoliOrgAddMiniRecordItemReader extends RepositoryItemReader<WkTblPartnerPoliOrgAddMinEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblPartnerPoliOrgAddMinRepository 関連者政治団体最小登録ワークテーブルRepository
     */
    public PartnerPoliOrgAddMiniRecordItemReader(
            final @Autowired WkTblPartnerPoliOrgAddMinRepository wkTblPartnerPoliOrgAddMinRepository) {

        super();
        super.setRepository(wkTblPartnerPoliOrgAddMinRepository);
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
