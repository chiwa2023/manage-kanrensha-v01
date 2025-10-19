package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_std;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPoliOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPoliOrgRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人標準登録マスタ複写ItemReader
 */
@Component
public class MasterPoliticalOrganizationAddStdRecordItemReader extends RepositoryItemReader<WkTblMasterPoliOrgEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblMasterPoliOrgRepository 関連者政治団体標準登録ワークテーブルRepository
     */
    public MasterPoliticalOrganizationAddStdRecordItemReader(
            final @Autowired WkTblMasterPoliOrgRepository wkTblMasterPoliOrgRepository) {

        super();
        super.setRepository(wkTblMasterPoliOrgRepository);
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
