package mitei.mitei.political.balancesheet.manage.kanrensha.service.address_registory;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address_registory.SearchLocalGovernmentCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address_registory.SearchLocalGovernmentResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressAllCityRepository;

/**
 * 地方公共団体検索Service
 */
@Service
public class SearchLocalGovService {

    /** 地方自治体コードRepository */
    @Autowired
    private AddressAllCityRepository addressAllCityRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public SearchLocalGovernmentResultDto practice(final @RequestBody SearchLocalGovernmentCapsuleDto capsuleDto) {

        SearchLocalGovernmentResultDto resultDto = new SearchLocalGovernmentResultDto();

        LocalDate now = LocalDate.now();

        // 全検索件数0の時は検索件数を設定
        final Integer zero = 0;
        String words = capsuleDto.getAddressWords();
        if (zero.equals(capsuleDto.getAllCount())) {
            resultDto.setAllCount(
                    addressAllCityRepository.countByAddressNameContainingAndEffectDateLessThanEqual(words, now));
        }
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        // 件数0件の場合は実検索をしない
        if (zero.equals(resultDto.getAllCount())) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("検索件数が0件です");
            return resultDto;
        }

        Pageable pageable = Pageable.ofSize(capsuleDto.getLimit()).withPage(capsuleDto.getPageNumber());

        resultDto.setListAllCity(
                addressAllCityRepository.findByAddressNameContainingAndEffectDateLessThanEqual(words, now, pageable));

        return resultDto;
    }

}
