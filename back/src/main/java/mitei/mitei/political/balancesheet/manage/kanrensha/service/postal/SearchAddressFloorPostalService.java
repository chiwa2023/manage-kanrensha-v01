package mitei.mitei.political.balancesheet.manage.kanrensha.service.postal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.SelectOptionStringDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.PostalCodeBuildingResultDto;

/**
 * 郵便番号を階ごとに割り振られている場合の住所(建物と部屋番号)を検索する
 */
@Service
public class SearchAddressFloorPostalService {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /**
     * 処理を行う
     *
     * @param lgCode 地方自治体コード
     * @param postalCode 郵便番号
     * @return 検索結果
     */
    @SuppressWarnings("unchecked")
    public PostalCodeBuildingResultDto practice(final String lgCode, final String postalCode) {
        
        // 行政区住居を検索しないの場合、郵便番号で該当データを取得する
        String sql = "SELECT address_building AS value,address_building AS text" + " FROM address_rsdt_" + lgCode
                + "  WHERE postal_code = '" + postalCode + "' AND address_building <> ''";
        Query query = entityManager.createNativeQuery(sql, SelectOptionStringDto.class);

        List<SelectOptionStringDto> list = (List<SelectOptionStringDto>) query.getResultList();

        PostalCodeBuildingResultDto resultDto = new PostalCodeBuildingResultDto();
        resultDto.setListOptions(list);
        resultDto.setLgCode(lgCode);
        return resultDto;
    }
}
