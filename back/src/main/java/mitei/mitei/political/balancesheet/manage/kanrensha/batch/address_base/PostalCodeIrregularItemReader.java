package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalRepository;

/**
 * 郵便番号不規則データ抽出ItemReader
 */
@Component
public class PostalCodeIrregularItemReader extends RepositoryItemReader<AddressPostalEntity> {

    /**
     * コンストラクタ
     *
     * @param addressPostalRepository 郵便番号Repository
     */
    public PostalCodeIrregularItemReader(final @Autowired AddressPostalRepository addressPostalRepository) {

        super();
        super.setRepository(addressPostalRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findByIsGyoseikuData");

        List<Object> list = new ArrayList<>();
        list.add(false);
        super.setArguments(list); // NOPMD
    }
}
