package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationBaseRepository;

/**
 * MasterPoliticalOrganizationBaseEntityをDBから呼び出して返却するLogic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallMasterPoliOrgBaseEntityLogic {

    /** 関連者政治団体住所リポジトリ */
    @Autowired
    private MasterPoliticalOrganizationBaseRepository masterPoliticalOrganizationBaseRepository;

    /**
     * MasterPoliticalOrganizationBaseEntityをDBから呼び出して返却する
     *
     * @param poliOrgKanrenshaCode 関連者政治団体コード
     * @return MasterPoliticalOrganizationBaseEntity
     */
    public MasterPoliticalOrganizationBaseEntity practice(final String poliOrgKanrenshaCode) // 
            throws DataRetrievalFailureException { // NOPMD UncheckedException
        List<MasterPoliticalOrganizationBaseEntity> baseList = masterPoliticalOrganizationBaseRepository
                .findByPoliOrgKanrenshaCodeOrderByMasterPoliticalOrganizationBaseIdDesc(poliOrgKanrenshaCode);

        if (baseList.isEmpty()) {
            MasterPoliticalOrganizationBaseEntity newEntity = new MasterPoliticalOrganizationBaseEntity();
            newEntity.setPoliOrgKanrenshaCode(poliOrgKanrenshaCode);
            return newEntity;
        }

        List<MasterPoliticalOrganizationBaseEntity> latestList = baseList.stream().filter(MasterPoliticalOrganizationBaseEntity::getIsLatest)
                .collect(Collectors.toList());

        // 通常最新取得件数は1件
        final int nomalCnt = 1;

        if (nomalCnt == latestList.size()) {
            return latestList.get(0);
        } else if (nomalCnt < latestList.size()) {
            throw new DataRetrievalFailureException(
                    "Latest data is duplicated. poliOrgKanrenshaCode: " + poliOrgKanrenshaCode);
        } else { // latestList.size() == 0
            return baseList.get(0);
        }
    }
}
