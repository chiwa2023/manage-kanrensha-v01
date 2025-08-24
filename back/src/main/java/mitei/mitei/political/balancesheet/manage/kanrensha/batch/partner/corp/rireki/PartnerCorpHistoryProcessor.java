package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.rireki;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業・団体CsvからワークテーブルProcessor
 */
@Component
public class PartnerCorpHistoryProcessor
        implements ItemProcessor<PartnerCorpHistoryDto, WkTblPartnerCorpHistoryEntity> {

    /** 空白 */
    private static final String BLANK = "";

    /** 正常登録 */
    private static final String RIGHT = "正)";

    /** 関連者企業・団体マスタRepository */
    @Autowired
    private MasterCorporationRepository masterCorporationRepository;

    /** 自然検索フォーマットUtil */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblPartnerCorpHistoryEntity process(final PartnerCorpHistoryDto item) throws Exception {

        WkTblPartnerCorpHistoryEntity entity = new WkTblPartnerCorpHistoryEntity();
        BeanUtils.copyProperties(item, entity);

        return this.check(entity);
    }

    /**
     * チェック処理のみ行う
     *
     * @param entity ワークテーブルEnity
     * @return チェック処理
     */
    public WkTblPartnerCorpHistoryEntity check(final WkTblPartnerCorpHistoryEntity entity) {

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

    private StringBuilder createCheckMessage(final WkTblPartnerCorpHistoryEntity entity) {

        // どれかの値が入力していなければ情報不足として処理対象外
        StringBuilder stringBuilder = new StringBuilder();
        if (BLANK.equals(entity.getPartnerName())) {
            stringBuilder.append("名称が入力されていません;");
        }
        if (BLANK.equals(entity.getAllAddress())) {
            stringBuilder.append("住所が入力されていません;");
        }
        // if (BLANK.equals(item.getCorpDelegate())) {
        // stringBuilder.append("代表者が入力されていません;");
        // }
        if (BLANK.equals(entity.getCorpKanrenshaCode())) {
            stringBuilder.append("関連者コードが入力されていません;");
        }

        if (stringBuilder.isEmpty()) {
            // 少なくとも団体名と関連者コードが同一でない場合は未登録とみなす
            List<MasterCorporationEntity> listMaster = masterCorporationRepository
                    .findByCorpKanrenshaCodeAndCompareNameTextAndIsLatest(entity.getCorpKanrenshaCode(),
                            formatNaturalSearchTextUtil.practice(entity.getPartnerName()),
                            SetTableDataHistoryUtil.INSERT_STATE);
            if (listMaster.isEmpty()) {
                stringBuilder.append("コードと名称に合致する関連者が存在しません;");
            }
        }

        return stringBuilder;
    }

}
