package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base.repair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalIrregularEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalIrregularRepository;

/**
 * 不規則未編集郵便番号データ抽出itemreader
 */
@Component
public class ChoicePostalCodeIrregularItemReader extends RepositoryItemReader<AddressPostalIrregularEntity> {

    /**
     * コンストラクタ
     *
     * @param addressPostalIrregularRepository 郵便番号不規則データRespository
     */
    public ChoicePostalCodeIrregularItemReader(
            final @Autowired AddressPostalIrregularRepository addressPostalIrregularRepository) {
        super();
        super.setRepository(addressPostalIrregularRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findByLgCodeStartingWithAndAddressOrgLikeAndAddressOrgNotLike");

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

        String lgCodePref = stepExecution.getJobParameters().getString("lgCodePref");

        List<Object> list = new ArrayList<>();
        list.add(lgCodePref);
        list.add("%〜%");
        list.add("%、%");
        super.setArguments(list);
    }

}
