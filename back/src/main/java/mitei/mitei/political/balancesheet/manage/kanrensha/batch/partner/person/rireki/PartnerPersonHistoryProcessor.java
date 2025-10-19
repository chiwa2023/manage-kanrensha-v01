package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.rireki;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人CsvからワークテーブルProcessor
 */
@Component
public class PartnerPersonHistoryProcessor
        implements ItemProcessor<PartnerPersonHistoryDto, WkTblPartnerPersonHistoryEntity> {

    /** 空白 */
    private static final String BLANK = "";

    /** 正常登録 */
    private static final String RIGHT = "正)";

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
    public WkTblPartnerPersonHistoryEntity process(final PartnerPersonHistoryDto item) throws Exception {

        WkTblPartnerPersonHistoryEntity entity = new WkTblPartnerPersonHistoryEntity();

        BeanUtils.copyProperties(item, entity);
        entity.setWkPartnerPersonHistoryId(0); // auto_increment明示

        return this.check(entity);
    }

    /**
     * チェック処理のみ行う
     *
     * @param entity ワークテーブルEnity
     * @return チェック処理
     */
    public WkTblPartnerPersonHistoryEntity check(final WkTblPartnerPersonHistoryEntity entity) {

        StringBuilder stringBuilder = this.createCheckMessage(entity);

        if (stringBuilder.isEmpty()) {
            entity.setIsAffected(true);
            entity.setJudgeReason(RIGHT);
        } else {
            // 何らかの未登録メッセージが入っている場合は判定対象外を登録
            entity.setIsAffected(false);
            entity.setJudgeReason(stringBuilder.toString());
        }

        return entity;
    }

    private StringBuilder createCheckMessage(final WkTblPartnerPersonHistoryEntity entity) {

        // どれかの値が入力していなければ情報不足として処理対象外
        StringBuilder stringBuilder = new StringBuilder();
        if (BLANK.equals(entity.getPartnerName())) {
            stringBuilder.append("名称が入力されていません;");
        }
        if (BLANK.equals(entity.getAllAddress())) {
            stringBuilder.append("住所が入力されていません;");
        }
        // if (BLANK.equals(item.getPersonShokugyou())) {
        // stringBuilder.append("職業が入力されていません;");
        // }
        if (BLANK.equals(entity.getPersonKanrenshaCode())) {
            stringBuilder.append("関連者コードが入力されていません;");
        }

        if (stringBuilder.isEmpty()) {

            // 少なくとも団体名と関連者コードが同一でない場合は未登録とみなす
            List<MasterPersonEntity> listMaster = masterPersonRepository
                    .findByPersonKanrenshaCodeAndCompareNameTextAndIsLatest(entity.getPersonKanrenshaCode(),
                            formatNaturalSearchTextUtil.practice(entity.getPartnerName()),
                            SetTableDataHistoryUtil.INSERT_STATE);
            if (listMaster.isEmpty()) {
                stringBuilder.append("コードと名称に合致する関連者が存在しません;");
            }
        }

        return stringBuilder;
    }

}
