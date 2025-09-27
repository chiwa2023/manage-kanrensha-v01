package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationAddressRepository;

/**
 * MasterCorporationAddressEntityをDBから呼び出して返却するLogic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallMasterCorpAddressEntityLogic {

    /** 関連者企業団体住所リポジトリ */
    @Autowired
    private MasterCorporationAddressRepository masterCorporationAddressRepository;

    /**
     * MasterCorporationAddressEntityをDBから呼び出して返却する
     *
     * @param corpKanrenshaCode 関連者企業団体コード
     * @return MasterCorporationAddressEntity
     */
    public MasterCorporationAddressEntity practice(final String corpKanrenshaCode) // 
            throws DataRetrievalFailureException { // NOPMD UncheckedException
        List<MasterCorporationAddressEntity> addressList = masterCorporationAddressRepository
                .findByCorpKanrenshaCodeOrderByMasterCorporationAddressIdDesc(corpKanrenshaCode);

        if (addressList.isEmpty()) {
            MasterCorporationAddressEntity newEntity = new MasterCorporationAddressEntity();
            newEntity.setCorpKanrenshaCode(corpKanrenshaCode);
            return newEntity;
        }

        List<MasterCorporationAddressEntity> latestList = addressList.stream().filter(MasterCorporationAddressEntity::getIsLatest)
                .collect(Collectors.toList());

        // 最新は標準取得件数1件
        final int nomalCnt = 1;

        if (nomalCnt == latestList.size()) {
            return latestList.get(0);
        } else if (nomalCnt < latestList.size()) {
            throw new DataRetrievalFailureException(
                    "Latest data is duplicated. corpKanrenshaCode: " + corpKanrenshaCode);
        } else { // latestList.size() == 0
            return addressList.get(0);
        }
    }
}
