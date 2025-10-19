package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.partner.PartnerCommonInfoDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationRepository;

/**
 * 関連者コードから該当関連者を検索する 検索結果をリストを返しているが必ず1項目のみ戻る(はず)
 */
@Service
public class SearchPartnerAllByKanrenshaCodeService {

    /** 関連者政治団体マスタRepository */
    @Autowired
    private MasterPoliticalOrganizationRepository masterPoliticalOrganizationRepository;

    /**
     * 処理を行う
     *
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    public List<PartnerCommonInfoDto> practice(final String kanrenshaCode) {

        return masterPoliticalOrganizationRepository.findKanrenshaCode(kanrenshaCode);
    }

}
