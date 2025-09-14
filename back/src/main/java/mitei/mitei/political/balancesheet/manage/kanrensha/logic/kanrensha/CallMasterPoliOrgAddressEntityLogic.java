package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationAddressRepository;

/**
 * MasterPoliticalOrganizationAddressEntityをDBから呼び出して返却するLogic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallMasterPoliOrgAddressEntityLogic {

    /** 関連者政治団体住所リポジトリ */
    @Autowired
    private MasterPoliticalOrganizationAddressRepository masterPoliticalOrganizationAddressRepository;

    /**
     * MasterPoliticalOrganizationAddressEntityをDBから呼び出して返却する
     *
     * @param poliOrgKanrenshaCode 関連者政治団体コード
     * @return MasterPoliticalOrganizationAddressEntity
     */
    public MasterPoliticalOrganizationAddressEntity practice(final String poliOrgKanrenshaCode) // 
            throws DataRetrievalFailureException { // NOPMD UncheckedException
        List<MasterPoliticalOrganizationAddressEntity> addressList = masterPoliticalOrganizationAddressRepository
                .findByPoliOrgKanrenshaCodeOrderByMasterPoliticalOrganizationAddressIdDesc(poliOrgKanrenshaCode);

        if (addressList.isEmpty()) {
            MasterPoliticalOrganizationAddressEntity newEntity = new MasterPoliticalOrganizationAddressEntity();
            newEntity.setPoliOrgKanrenshaCode(poliOrgKanrenshaCode);
            return newEntity;
        }

        List<MasterPoliticalOrganizationAddressEntity> latestList = addressList.stream().filter(MasterPoliticalOrganizationAddressEntity::getIsLatest)
                .collect(Collectors.toList());

        // 最新は標準取得件数1件
        final int nomalCnt = 1;

        if (nomalCnt == latestList.size()) {
            return latestList.get(0);
        } else if (nomalCnt < latestList.size()) {
            throw new DataRetrievalFailureException(
                    "Latest data is duplicated. poliOrgKanrenshaCode: " + poliOrgKanrenshaCode);
        } else { // latestList.size() == 0
            return addressList.get(0);
        }
    }
}
