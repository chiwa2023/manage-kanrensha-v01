package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonAddressRepository;

/**
 * MasterPersonAddressEntityをDBから呼び出して返却するLogic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallMasterPersonAddressEntityLogic {

    /** 関連者個人住所リポジトリ */
    @Autowired
    private MasterPersonAddressRepository masterPersonAddressRepository;

    /**
     * MasterPersonAddressEntityをDBから呼び出して返却する
     *
     * @param personKanrenshaCode 関連者個人コード
     * @return MasterPersonAddressEntity
     */
    public MasterPersonAddressEntity practice(final String personKanrenshaCode) // 
            throws DataRetrievalFailureException { // NOPMD UncheckedException
        List<MasterPersonAddressEntity> addressList = masterPersonAddressRepository
                .findByPersonKanrenshaCodeOrderByMasterPersonAddressIdDesc(personKanrenshaCode);

        if (addressList.isEmpty()) {
            MasterPersonAddressEntity newEntity = new MasterPersonAddressEntity();
            newEntity.setPersonKanrenshaCode(personKanrenshaCode);
            return newEntity;
        }

        List<MasterPersonAddressEntity> latestList = addressList.stream().filter(MasterPersonAddressEntity::getIsLatest)
                .collect(Collectors.toList());

        // 最新は標準取得件数1件
        final int nomalCnt = 1;

        if (nomalCnt == latestList.size()) {
            return latestList.get(0);
        } else if (nomalCnt < latestList.size()) {
            throw new DataRetrievalFailureException(
                    "Latest data is duplicated. personKanrenshaCode: " + personKanrenshaCode);
        } else { // latestList.size() == 0
            return addressList.get(0);
        }
    }
}
