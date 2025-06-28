package mitei.mitei.political.balancesheet.manage.kanrensha.service.postal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.SearchPostalCodeCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.SearchPostalCodeResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalRepository;

/**
 * 郵便番号検索Service
 */
@Service
public class SearchPostalCodeService {

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public SearchPostalCodeResultDto practice(final SearchPostalCodeCapsuleDto capsuleDto) {

        SearchPostalCodeResultDto resultDto = new SearchPostalCodeResultDto();

        final Integer zero = 0;
        String words = capsuleDto.getAddressWords();
        if (zero.equals(capsuleDto.getAllCount())) {
            resultDto.setAllCount(addressPostalRepository.countByAddressNameContaining(words));
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

        resultDto.setListItem(addressPostalRepository.findByAddressNameContaining(words, pageable));

        return resultDto;
    }
}
