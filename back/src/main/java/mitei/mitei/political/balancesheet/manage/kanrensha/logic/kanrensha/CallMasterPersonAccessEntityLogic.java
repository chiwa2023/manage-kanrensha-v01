package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonAccessRepository;

/**
 * 関連者個人コードに紐づく個人連絡先取得Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallMasterPersonAccessEntityLogic {

    /** マスタ個人連絡先Repository */
    @Autowired
    private MasterPersonAccessRepository repository;

    /**
     * 処理を行う
     *
     * @param personKanrenshaCode 関連者個人コード
     * @return 個人連絡先情報
     */
    public MasterPersonAccessEntity practice(final String personKanrenshaCode) {
        // データベースよりpersonKanrenshaCodeが一致し、かつテーブルIdの降順で、リストを取得
        List<MasterPersonAccessEntity> entities = repository
                .findByPersonKanrenshaCodeOrderByMasterPersonAccessIdDesc(personKanrenshaCode);

        // 取得したリストが空の場合、新たにEntityのインスタンスを作成し、personKanrenshaCodeだけを設定して返却
        if (entities.isEmpty()) {
            MasterPersonAccessEntity newEntity = new MasterPersonAccessEntity();
            newEntity.setPersonKanrenshaCode(personKanrenshaCode);
            return newEntity;
        }

        // 取得したリストをisLatestがtrueでフィルタ
        List<MasterPersonAccessEntity> latestEntities = entities.stream().filter(MasterPersonAccessEntity::getIsLatest)
                .collect(Collectors.toList());

        // 最新は標準取得件数1件
        final int nomalCnt = 1;

        // isLatestがtrueのものが1件の場合、その1件のEntityを返却
        if (nomalCnt == latestEntities.size()) {
            return latestEntities.get(0);
        }

        // isLatestがtrueのものが複数の場合、例外を投げる
        if (nomalCnt < latestEntities.size()) {
            throw new DataRetrievalFailureException(
                    "Multiple latest records found for personKanrenshaCode: " + personKanrenshaCode);
        }

        // isLatestがtrueのものが0件の場合、フィルタ前の最初の1件(isLatestがfalseでテーブルIdが最も大きい1件)を返却
        return entities.get(0);
    }
}
