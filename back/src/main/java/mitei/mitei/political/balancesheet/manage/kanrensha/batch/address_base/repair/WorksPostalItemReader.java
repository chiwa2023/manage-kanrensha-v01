package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base.repair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalWorksEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalWorksRepository;

/**
 * 登録された郵便番号作業を全抽出する
 */
@Component
public class WorksPostalItemReader extends RepositoryItemReader<AddressPostalWorksEntity> {

    /**
     * コンストラクタ
     *
     * @param addressPostalWorksRepository 郵便番号作業Respository
     */
    public WorksPostalItemReader(final @Autowired AddressPostalWorksRepository addressPostalWorksRepository) {
        super();
        super.setRepository(addressPostalWorksRepository);
        super.setSort(new HashMap<String, Direction>()); // NOPMD
        super.setMethodName("findAll");

        List<Object> list = new ArrayList<>();
        super.setArguments(list); // NOPMD
    }

}
