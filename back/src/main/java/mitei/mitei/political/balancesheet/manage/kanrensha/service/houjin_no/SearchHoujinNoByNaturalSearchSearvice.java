package mitei.mitei.political.balancesheet.manage.kanrensha.service.houjin_no;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.houjin_no.HoujinNoCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.houjin_no.HoujinNoResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.HoujinNoLatestEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.natural_search.CreateSerachWordsBooleanModeLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.HoujinNoLatestRepository;

/**
 * 全文検索法人番号Service
 */
@Service
public class SearchHoujinNoByNaturalSearchSearvice {

    /** 検索上限件数 */
    private static final int LIMIT = 30;

    /** 法人番号最新Repository */
    @Autowired
    private HoujinNoLatestRepository houjinNoLatestRepository;

    /** 検索作成Logic */
    @Autowired
    private CreateSerachWordsBooleanModeLogic createSerachWordsBooleanModeLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件格納Dto
     * @return 検索結果
     */
    public HoujinNoResultDto practice(final HoujinNoCapsuleDto capsuleDto) {

        String words = createSerachWordsBooleanModeLogic.practice(capsuleDto.getSearchNaturaloWords());

        HoujinNoResultDto resultDto = new HoujinNoResultDto();

        final Long ZERO = 0L;

        if (ZERO.equals(capsuleDto.getAllCount())) {
            resultDto.setAllCount(houjinNoLatestRepository.getCountByFullText(words));
        }

        resultDto.setLimit(LIMIT);
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        Pageable pageable = Pageable.ofSize(LIMIT).withPage(capsuleDto.getPageNumber());
        List<HoujinNoLatestEntity> list = houjinNoLatestRepository.findFullText(words, pageable);
        resultDto.setListHoujinNo(list);

        return resultDto;
    }

}
