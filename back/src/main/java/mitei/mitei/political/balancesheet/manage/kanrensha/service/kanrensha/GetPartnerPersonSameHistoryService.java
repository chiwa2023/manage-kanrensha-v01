package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPersonHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory01Repository;

/**
 * 関連者個人の同属性リスト取得Service
 */
@Service
public class GetPartnerPersonSameHistoryService {

    /** 関連者個人履歴Repository(01) */
    @Autowired
    private PartnerPersonHistory01Repository partnerPersonHistory01Repository;

    /**
     * 処理を行う
     *
     * @param name     個人名
     * @param address  住所
     * @param shokugyou 個人職業
     * @return 検索結果
     */
    public List<PartnerPersonHistoryBaseEntity> practice(final String name, final String address, final String shokugyou) {

        return partnerPersonHistory01Repository.selectByProperty(name, address, shokugyou);
    }

}
