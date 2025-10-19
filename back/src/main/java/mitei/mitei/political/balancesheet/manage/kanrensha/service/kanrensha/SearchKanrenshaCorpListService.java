package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.NaturalTextSearchPagingCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SearchKanrenshaCorpResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationRepository;

/**
 * 関連者個人を検索する
 */
@Service
public class SearchKanrenshaCorpListService {

    /** 関連者個人マスタRespoitory */
    @Autowired
    private MasterCorporationRepository masterCorporationRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件(ページング含む)格納Dto
     * @return 検索結果
     */
    public SearchKanrenshaCorpResultDto practice(final @RequestBody NaturalTextSearchPagingCapsuleDto capsuleDto) {

        SearchKanrenshaCorpResultDto resultDto = new SearchKanrenshaCorpResultDto();

        // TODO 検索語は次バージョン対応。現在は検索語なしの全検索

        // 件数を取得して、全件数よりページング×1件件数が小さいときは、すくなくとも検索条件が変更しているのでページ番号を初期化
        Integer allCount = masterCorporationRepository.countByIsLatest(true);
        if (allCount < capsuleDto.getLimit() * capsuleDto.getPageNumber()) {
            capsuleDto.setPageNumber(0);
        }

        resultDto.setAllCount(allCount);
        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setPageNumber(capsuleDto.getPageNumber());

        resultDto.setListMasterCorp(masterCorporationRepository.findByIsLatest(true,
                Pageable.ofSize(capsuleDto.getLimit()).withPage(capsuleDto.getPageNumber())));

        return resultDto;
    }
}
