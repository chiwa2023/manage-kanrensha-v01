package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationBaseRepository;

/**
 * MasterCorporationBaseEntityをDBから呼び出して返却するLogic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallMasterCorpBaseEntityLogic {

    /** 関連者企業団体住所リポジトリ */
    @Autowired
    private MasterCorporationBaseRepository masterCorpolationBaseRepository;

    /**
     * MasterCorporationBaseEntityをDBから呼び出して返却する
     *
     * @param corpKanrenshaCode 関連者企業団体コード
     * @return MasterCorporationBaseEntity
     */
    public MasterCorporationBaseEntity practice(final String corpKanrenshaCode) // 
            throws DataRetrievalFailureException { // NOPMD UncheckedException
        List<MasterCorporationBaseEntity> baseList = masterCorpolationBaseRepository
                .findByCorpKanrenshaCodeOrderByMasterCorporationBaseIdDesc(corpKanrenshaCode);

        if (baseList.isEmpty()) {
            MasterCorporationBaseEntity newEntity = new MasterCorporationBaseEntity();
            newEntity.setCorpKanrenshaCode(corpKanrenshaCode);
            return newEntity;
        }

        List<MasterCorporationBaseEntity> latestList = baseList.stream().filter(MasterCorporationBaseEntity::getIsLatest)
                .collect(Collectors.toList());

        // 通常最新取得件数は1件
        final int nomalCnt = 1;

        if (nomalCnt == latestList.size()) {
            return latestList.get(0);
        } else if (nomalCnt < latestList.size()) {
            throw new DataRetrievalFailureException(
                    "Latest data is duplicated. corpKanrenshaCode: " + corpKanrenshaCode);
        } else { // latestList.size() == 0
            return baseList.get(0);
        }
    }
}
