package mitei.mitei.political.balancesheet.manage.kanrensha.service.postal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.SearchPostalIllegularCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.SearchPostalIllegularResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalIrregularRepository;

/**
 * 郵便番号不規則検索Service
 */
@Service
public class SearchPostalIrregularBuildingDelegateService {

    /** 郵便番号不規則Repository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public SearchPostalIllegularResultDto practice(final SearchPostalIllegularCapsuleDto capsuleDto) {
        SearchPostalIllegularResultDto resultDto = new SearchPostalIllegularResultDto();

        resultDto.setListItem(
                addressPostalIrregularRepository.findByAddressOrgContainingAndIsRepairRsdt("（地階・階層不明）", false));

        return resultDto;
    }

}
