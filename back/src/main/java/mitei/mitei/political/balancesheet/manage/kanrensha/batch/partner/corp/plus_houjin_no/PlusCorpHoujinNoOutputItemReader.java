package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.plus_houjin_no;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpPlusHojinNoEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCorpPlusHojinNoRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 企業・団体法人番号追加ワークテーブルItemReader
 */
@Component
public class PlusCorpHoujinNoOutputItemReader extends RepositoryItemReader<WkTblPartnerCorpPlusHojinNoEntity> {

    /**
     * コンストラクタ
     *
     * @param wkTblPartnerCorpPlusHojinNoRepository 関連者企業・団体法人番号追加ワークテーブルRepository
     */
    public PlusCorpHoujinNoOutputItemReader(
            final @Autowired WkTblPartnerCorpPlusHojinNoRepository wkTblPartnerCorpPlusHojinNoRepository) {

        super();
        super.setRepository(wkTblPartnerCorpPlusHojinNoRepository);
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
