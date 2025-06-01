package mitei.mitei.political.balancesheet.manage.kanrensha.service.houjin_no;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.houjin_no.HoujinNoCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.houjin_no.HoujinNoResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.HoujinNoLatestEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.HoujinNoLatestRepository;

/**
 * 法人番号前方一致検索Service
 */
@Service
public class SearchHoujinNoByNoService {

    /** 検索上限件数 */
    private static final int LIMIT = 30;

    /** 法人番号最新Repository */
    @Autowired
    private HoujinNoLatestRepository houjinNoLatestRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件格納Dto
     * @return 検索結果Dto
     */
    public HoujinNoResultDto practice(final HoujinNoCapsuleDto capsuleDto) {

        String words = capsuleDto.getSearchNoWords() + '%';

        HoujinNoResultDto resultDto = new HoujinNoResultDto();

        final Long ZERO = 0L;

        if (ZERO.equals(capsuleDto.getAllCount())) {
            resultDto.setAllCount(houjinNoLatestRepository.getCountByHoujinNumber(words));
        }

        resultDto.setLimit(LIMIT);
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        Pageable pageable = Pageable.ofSize(LIMIT).withPage(capsuleDto.getPageNumber());
        List<HoujinNoLatestEntity> list = houjinNoLatestRepository.findByHoujinNumber(words, pageable);
        resultDto.setListHoujinNo(list);

        return resultDto;
    }
}
