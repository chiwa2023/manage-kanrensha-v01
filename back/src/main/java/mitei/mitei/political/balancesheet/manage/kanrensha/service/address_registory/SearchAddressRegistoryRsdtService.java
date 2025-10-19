package mitei.mitei.political.balancesheet.manage.kanrensha.service.address_registory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address_registory.SearchAddressRegistoryCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address_registory.SearchAddressRegistoryResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressRsdtTemplateEntity;

/**
 * アドレス・ベース・レジストリ住居Service
 */
@Service
public class SearchAddressRegistoryRsdtService {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    @SuppressWarnings("unchecked")
    public SearchAddressRegistoryResultDto practice(final @RequestBody SearchAddressRegistoryCapsuleDto capsuleDto) {

        SearchAddressRegistoryResultDto resultDto = new SearchAddressRegistoryResultDto();
        try {
            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // 全検索件数0の時は検索件数を設定
            final Integer zero = 0;
            if (zero.equals(capsuleDto.getAllCount())) {
                Query queryCount = entityManager.createNativeQuery("SELECT count(*) FROM address_rsdt_"
                        + capsuleDto.getLgCode() + " WHERE effect_date < '" + now.format(formatter) + "'",
                        Integer.class);
                List<Integer> listCount = queryCount.getResultList();
                resultDto.setAllCount(listCount.get(0));
            }

            resultDto.setLimit(capsuleDto.getLimit());
            resultDto.setPageNumber(capsuleDto.getPageNumber());

            // 件数0件の場合は実検索をしない
            if(zero.equals(resultDto.getAllCount())) {
                resultDto.setIsFailure(true);
                resultDto.setMessage("テーブルにデータが存在しません");
                return resultDto;
            }
            
            // ページングによる検索
            Query query = entityManager.createNativeQuery("SELECT * FROM address_rsdt_" + capsuleDto.getLgCode()
                    + " WHERE effect_date < '" + now.format(formatter) + "' limit " + capsuleDto.getLimit() + " OFFSET "
                    + capsuleDto.getPageNumber(), AddressRsdtTemplateEntity.class);

            List<AddressRsdtTemplateEntity> list = (List<AddressRsdtTemplateEntity>) query.getResultList();
            resultDto.setListRsdt(list);

        } catch (SQLGrammarException exception) {

            resultDto.setIsFailure(true);
            resultDto.setMessage("存在しない地方自治体コードが設定されています");
        }

        return resultDto;
    }

}
