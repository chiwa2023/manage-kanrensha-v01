package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonBaseRepository;

/**
 * MasterPersonBaseEntityをDBから呼び出して返却するLogic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallMasterPersonBaseEntityLogic {

    /** 関連者個人住所リポジトリ */
    @Autowired
    private MasterPersonBaseRepository masterPersonBaseRepository;

    /**
     * MasterPersonBaseEntityをDBから呼び出して返却する
     *
     * @param personKanrenshaCode 関連者個人コード
     * @return MasterPersonBaseEntity
     */
    public MasterPersonBaseEntity practice(final String personKanrenshaCode) // 
            throws DataRetrievalFailureException { // NOPMD UncheckedException
        List<MasterPersonBaseEntity> baseList = masterPersonBaseRepository
                .findByPersonKanrenshaCodeOrderByMasterPersonBaseIdDesc(personKanrenshaCode);

        if (baseList.isEmpty()) {
            MasterPersonBaseEntity newEntity = new MasterPersonBaseEntity();
            newEntity.setPersonKanrenshaCode(personKanrenshaCode);
            return newEntity;
        }

        List<MasterPersonBaseEntity> latestList = baseList.stream().filter(MasterPersonBaseEntity::getIsLatest)
                .collect(Collectors.toList());

        // 通常最新取得件数は1件
        final int nomalCnt = 1;

        if (nomalCnt == latestList.size()) {
            return latestList.get(0);
        } else if (nomalCnt < latestList.size()) {
            throw new DataRetrievalFailureException(
                    "Latest data is duplicated. personKanrenshaCode: " + personKanrenshaCode);
        } else { // latestList.size() == 0
            return baseList.get(0);
        }
    }
}
