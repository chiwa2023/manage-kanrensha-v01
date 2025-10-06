package mitei.mitei.political.balancesheet.manage.kanrensha.service.address_registory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressRsdtTemplateEntity;

/**
 * 同一コード住所を抽出する
 */
@Service
public class ChoiceAddressRegistoryByCodeService {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /**
     * 処理を行う
     *
     * @param entity 検索条件格納Entity
     * @return 検索結果
     */
    @SuppressWarnings("unchecked")
    public List<AddressRsdtTemplateEntity> practice(final AddressRsdtTemplateEntity entity){

        // コード指定による検索。基本的に1件あるいは該当0件しか返さない
        Query query = entityManager.createNativeQuery("SELECT * FROM address_rsdt_" + entity.getLgCode()
                + this.createCondition(entity) ,AddressRsdtTemplateEntity.class);

        return (List<AddressRsdtTemplateEntity>) query.getResultList();
    }
    
    private String createCondition(final AddressRsdtTemplateEntity entity) {
        
        StringBuilder builder = new StringBuilder();
        
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        builder // 
        .append(" WHERE machiaza_id = '").append(entity.getMachiazaId())
        .append("' AND parcel_rsdt_id = '").append(entity.getParcelRsdtId())
        .append("' AND effect_date <= '").append(now.format(formatter)).append("'");
        
        return builder.toString();
    }
    
    
}
