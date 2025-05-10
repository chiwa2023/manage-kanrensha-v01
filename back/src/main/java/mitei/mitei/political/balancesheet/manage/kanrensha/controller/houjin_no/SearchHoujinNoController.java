package mitei.mitei.political.balancesheet.manage.kanrensha.controller.houjin_no;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.houjin_no.HoujinNoCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.houjin_no.HoujinNoResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.houjin_no.SearchHoujinNoByNaturalSearchSearvice;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.houjin_no.SearchHoujinNoByNoService;

/**
 * 法人番号検索Controller
 */
@RestController
@RequestMapping("/houjin-no")
public class SearchHoujinNoController {

    /** 法人番号前方一致による法人番号検索Service */
    @Autowired
    private SearchHoujinNoByNoService searchHoujinNoByNoService;

    /** 法人番号前方一致による法人番号検索Service */
    @Autowired
    private SearchHoujinNoByNaturalSearchSearvice searchHoujinNoByNaturalSearchSearvice;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    @PostMapping("/search")
    public HoujinNoResultDto practice(final HoujinNoCapsuleDto capsuleDto) {

        if (capsuleDto.getIsNaturalSearch()) {
            return searchHoujinNoByNaturalSearchSearvice.practice(capsuleDto);
        } else {
            return searchHoujinNoByNoService.practice(capsuleDto);
        }

    }

}
