package mitei.mitei.political.balancesheet.manage.kanrensha.service.postal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.SelectOptionStringDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.PostalCodeBlockResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalIrregularEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalIrregularRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalRepository;

/**
 * 住所番地まで検索Service
 */
@Service
public class SearchAddressBlockService {

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /**
     * 処理を行う
     *
     * @param tableid テーブルId
     * @param isGyouseikuData 自治体住居検索
     * @return 検索結果
     */
    @SuppressWarnings("unchecked")
    public PostalCodeBlockResultDto practice(final Integer tableid, final boolean isGyouseikuData) {

        if (isGyouseikuData) {

            // 自治体住居を検索する場合、自治体住居を住居の前方一致で取得する
            AddressPostalEntity postalEntity = addressPostalRepository.findById(tableid).get();

            PostalCodeBlockResultDto resultDto = new PostalCodeBlockResultDto();
            String lgCode = postalEntity.getLgCode();
            resultDto.setLgCode(lgCode);

            String name = postalEntity.getAddressName();

            String sql = "SELECT DISTINCT address_block AS value, RIGHT(address_block, CHAR_LENGTH(address_block)-"
                    + name.length() + ") AS text" + " FROM address_rsdt_" + lgCode + "  WHERE address_block LIKE '"
                    + name + "%'";
            Query query = entityManager.createNativeQuery(sql, SelectOptionStringDto.class);

            List<SelectOptionStringDto> list = (List<SelectOptionStringDto>) query.getResultList();

            resultDto.setListOptions(list);

            return resultDto;
            
        } else {

            // 自治体住居を検索しない場合不規則に郵便番号でアクセスして取得
            AddressPostalIrregularEntity irregularEntity = addressPostalIrregularRepository
                    .findById(Math.toIntExact(tableid)).get();

            SelectOptionStringDto dto = new SelectOptionStringDto();
            dto.setValue(irregularEntity.getAddressPostal() + irregularEntity.getAddressBlock());
            dto.setText(irregularEntity.getAddressBlock());

            List<SelectOptionStringDto> list = new ArrayList<>();
            list.add(dto);

            PostalCodeBlockResultDto resultDto = new PostalCodeBlockResultDto();
            resultDto.setLgCode(irregularEntity.getLgCode());
            resultDto.setListOptions(list);

            return resultDto;
        }

    }

}
