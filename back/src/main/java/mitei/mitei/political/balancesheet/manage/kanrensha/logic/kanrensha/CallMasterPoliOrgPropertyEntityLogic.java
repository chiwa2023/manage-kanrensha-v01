package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationPropertyRepository;

/**
 * MasterPoliticalOrganizationPropertyEntityをDBから呼び出して返却するLogic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallMasterPoliOrgPropertyEntityLogic {

    /** 関連者政治団体住所リポジトリ */
    @Autowired
    private MasterPoliticalOrganizationPropertyRepository masterPoliticalOrganizationPropertyRepository;

    /**
     * MasterPoliticalOrganizationPropertyEntityをDBから呼び出して返却する
     *
     * @param poliOrgKanrenshaCode 関連者政治団体コード
     * @return MasterPoliticalOrganizationPropertyEntity
     */
    public MasterPoliticalOrganizationPropertyEntity practice(final String poliOrgKanrenshaCode) //
            throws DataRetrievalFailureException { // NOPMD UnchekedException
        List<MasterPoliticalOrganizationPropertyEntity> propertyList = masterPoliticalOrganizationPropertyRepository
                .findByPoliOrgKanrenshaCodeOrderByMasterPoliticalOrganizationPropertyIdDesc(poliOrgKanrenshaCode);

        if (propertyList.isEmpty()) {
            MasterPoliticalOrganizationPropertyEntity newEntity = new MasterPoliticalOrganizationPropertyEntity();
            newEntity.setPoliOrgKanrenshaCode(poliOrgKanrenshaCode);
            return newEntity;
        }

        List<MasterPoliticalOrganizationPropertyEntity> latestList = propertyList.stream()
                .filter(MasterPoliticalOrganizationPropertyEntity::getIsLatest).collect(Collectors.toList());

        // 通常最新取得件数は1件
        final int nomalCnt = 1;

        if (nomalCnt == latestList.size()) {
            return latestList.get(0);
        } else if (nomalCnt < latestList.size()) {
            throw new DataRetrievalFailureException(
                    "Latest data is duplicated. poliOrgKanrenshaCode: " + poliOrgKanrenshaCode);
        } else { // latestList.size() == 0
            return propertyList.get(0);
        }
    }
}
