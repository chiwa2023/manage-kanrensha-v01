package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonPropertyRepository;

/**
 * MasterPersonPropertyEntityをDBから呼び出して返却するLogic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallMasterPersonPropertyEntityLogic {

    /** 関連者個人住所リポジトリ */
    @Autowired
    private MasterPersonPropertyRepository masterPersonPropertyRepository;

    /**
     * MasterPersonPropertyEntityをDBから呼び出して返却する
     *
     * @param personKanrenshaCode 関連者個人コード
     * @return MasterPersonPropertyEntity
     */
    public MasterPersonPropertyEntity practice(final String personKanrenshaCode) //
            throws DataRetrievalFailureException { // NOPMD UnchekedException
        List<MasterPersonPropertyEntity> propertyList = masterPersonPropertyRepository
                .findByPersonKanrenshaCodeOrderByMasterPersonPropertyIdDesc(personKanrenshaCode);

        if (propertyList.isEmpty()) {
            MasterPersonPropertyEntity newEntity = new MasterPersonPropertyEntity();
            newEntity.setPersonKanrenshaCode(personKanrenshaCode);
            return newEntity;
        }

        List<MasterPersonPropertyEntity> latestList = propertyList.stream()
                .filter(MasterPersonPropertyEntity::getIsLatest).collect(Collectors.toList());

        // 通常最新取得件数は1件
        final int nomalCnt = 1;

        if (nomalCnt == latestList.size()) {
            return latestList.get(0);
        } else if (nomalCnt < latestList.size()) {
            throw new DataRetrievalFailureException(
                    "Latest data is duplicated. personKanrenshaCode: " + personKanrenshaCode);
        } else { // latestList.size() == 0
            return propertyList.get(0);
        }
    }
}
