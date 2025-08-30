package mitei.mitei.political.balancesheet.manage.kanrensha.controller.search_code;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.http.HttpResponseStatus;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.partner.PartnerCommonInfoDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.partner.SearchPartnerHistoryCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.partner.SearchPartnerHistoryResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.SearchPartnerAllByHistoryNameAddressService;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.SearchPartnerAllByHistoryService;

/**
 * 検索条件から履歴を取得する
 */
@RestController
@RequestMapping("/kanrenssha-list")
public class SearchKanrenshaCodeByHistoryController {

    /** 名称・住所・認識キー全条件で検索する場合 */
    @Autowired
    private SearchPartnerAllByHistoryService searchPartnerAllByHistoryService;

    /** 名称・住所条件で検索する場合 */
    @Autowired
    private SearchPartnerAllByHistoryNameAddressService searchPartnerAllByHistoryNameAddressService;

    /** 空白文字 */
    private static final String BLANK = "";

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果
     */
    @PostMapping("/search")
    public ResponseEntity<SearchPartnerHistoryResultDto> practice(
            final @RequestBody SearchPartnerHistoryCapsuleDto capsuleDto) {

        List<PartnerCommonInfoDto> listAll = new ArrayList<>();

        boolean hasConditionKey = this.isInput(capsuleDto.getRecognizedKey());
        // 検索条件が名称・住所・認識キーの場合
        if (hasConditionKey) {
            listAll.addAll(searchPartnerAllByHistoryService.practice(capsuleDto.getPartnerName(),
                    capsuleDto.getAllAddress(), capsuleDto.getRecognizedKey()));
        }

        // 検索条件が名称・住所の場合または3要素で取得できなかった場合
        if (listAll.isEmpty() || !hasConditionKey) {
            // TODO 名前と住所だけで検索
            listAll.addAll(searchPartnerAllByHistoryNameAddressService.practice(capsuleDto.getPartnerName(),
                    capsuleDto.getAllAddress()));
        }

        List<PartnerCommonInfoDto> list = new ArrayList<>();
        // 関連者区分に入力がある場合はフィルタ
        if (this.isInput(capsuleDto.getKanrenshaKbn())) {
            list = listAll.stream().filter(e -> capsuleDto.getKanrenshaKbn().equals(e.getKanrenshaKbn())).toList();
        } else {
            list = listAll;
        }

        // 基本的にリストしか返す予定はないが、何かの時のためにカプセル化しておく
        SearchPartnerHistoryResultDto resultDto = new SearchPartnerHistoryResultDto();
        resultDto.setListHistoryInfo(list);

        return ResponseEntity.status(HttpResponseStatus.OK.code()).body(resultDto);
    }

    private boolean isInput(final String data) {

        if (Objects.isNull(data)) {
            return false;
        }
        if (BLANK.equals(data)) {
            return false;
        }
        return true;
    }

    private boolean isInput(final Short data) {

        if (Objects.isNull(data)) {
            return false;
        }
        if (0 == (short) data) {
            return false;
        }
        return true;
    }

}
