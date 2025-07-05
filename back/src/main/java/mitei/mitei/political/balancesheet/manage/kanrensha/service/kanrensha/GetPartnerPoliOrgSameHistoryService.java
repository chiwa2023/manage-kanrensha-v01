package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPoliOrgHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory01Repository;

/**
 * 関連者政治団体の同属性リスト取得Service
 */
@Service
public class GetPartnerPoliOrgSameHistoryService {

    /** 関連者政治団体履歴Repository(01) */
    @Autowired
    private PartnerPoliOrgHistory01Repository partnerPoliOrgHistory01Repository;

    /**
     * 処理を行う
     *
     * @param name     団体名
     * @param address  住所
     * @param delegate 代表者名
     * @return 検索結果
     */
    public List<PartnerPoliOrgHistoryBaseEntity> practice(final String name, final String address,
            final String delegate) {

        return partnerPoliOrgHistory01Repository.selectByProperty(name, address, delegate);
    }

}
