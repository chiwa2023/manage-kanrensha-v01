package mitei.mitei.political.balancesheet.manage.kanrensha.logic.postalcode;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalWorksEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressRsdtBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalRepository;

/**
 * 住居テーブルに(その他)データが存在するか確認する
 */
@Component
public class CheckExistPostalCodeByOtherLogic {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /**
     * 処理を行う
     *
     * @param worksEntity 郵便番号作業Entity
     * @return 郵便番号リスト
     */
    public List<AddressPostalEntity> practice(final AddressPostalWorksEntity worksEntity) {

        // (その他)の前までの住所が共通である住所が存在すればOK
        String sql = "SELECT * FROM address_rsdt_" + worksEntity.getLgCode() + " WHERE address_block LIKE '"
                + worksEntity.getAddressName() + "%' LIMIT 2";
        Query query = entityManager.createNativeQuery(sql, AddressRsdtBaseEntity.class);

        List<AddressPostalEntity> list = new ArrayList<>();
        if (query.getResultList().isEmpty()) {
            // 住居テーブルにデータが存在しないときは、本来起きないことが起きているとして空リスト
            return list;
        } else {
            return addressPostalRepository.findByPostal1OrderByAddressNameAsc(worksEntity.getPostal1());
        }

    }
}
