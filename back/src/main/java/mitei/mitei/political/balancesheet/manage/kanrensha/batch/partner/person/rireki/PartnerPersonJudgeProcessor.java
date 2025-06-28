package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.rireki;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人ワークテーブルから判定結果を作成する
 */
@Component
public class PartnerPersonJudgeProcessor
        implements ItemProcessor<WkTblPartnerPersonHistoryEntity, WkTblPartnerPersonJudgeEntity> {

    /** 空白 */
    private static final String BLANK = "";

    /** 関連者個人マスタRepository */
    @Autowired
    private MasterPersonRepository masterPersonRepository;

    /** 自然検索フォーマットUtil */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblPartnerPersonJudgeEntity process(final WkTblPartnerPersonHistoryEntity item) throws Exception {

        WkTblPartnerPersonJudgeEntity entity = new WkTblPartnerPersonJudgeEntity();
        BeanUtils.copyProperties(item, entity);

        // どれかの値が入力していなければ情報不足として処理対象外
        StringBuilder stringBuilder = new StringBuilder();
        if (BLANK.equals(item.getPartnerName())) {
            stringBuilder.append("名称が入力されていません;");
        }
        if (BLANK.equals(item.getAllAddress())) {
            stringBuilder.append("住所が入力されていません;");
        }
        if (BLANK.equals(item.getPersonShokugyou())) {
            stringBuilder.append("職業が入力されていません;");
        }
        if (BLANK.equals(item.getPersonKanrenshaCode())) {
            stringBuilder.append("関連者コードが入力されていません;");
        }

        if (stringBuilder.isEmpty()) {

            // 少なくとも団体名と関連者コードが同一でない場合は未登録とみなす
            List<MasterPersonEntity> listMaster = masterPersonRepository
                    .findByPersonKanrenshaCodeAndCompareNameTextAndIsLatest(item.getPersonKanrenshaCode(),
                            formatNaturalSearchTextUtil.practice(item.getPartnerName()),
                            SetTableDataHistoryUtil.INSERT_STATE);
            if (listMaster.isEmpty()) {
                stringBuilder.append("コードと名称に合致する関連者が存在しません;");
            }
        }

        if (stringBuilder.isEmpty()) {
            entity.setIsAffected(true);
        } else {
            // 何らかの未登録メッセージが入っている場合は判定対象外を登録
            entity.setIsAffected(false);
            entity.setJudgeReason(stringBuilder.toString());
        }

        return entity;
    }

}
