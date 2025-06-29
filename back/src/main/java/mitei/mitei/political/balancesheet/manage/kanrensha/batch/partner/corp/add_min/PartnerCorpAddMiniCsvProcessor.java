package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_min;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerCorpHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.GetPartnerCorpSameHistoryService;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業・団体CsvからワークテーブルProcessor
 */
@Component
public class PartnerCorpAddMiniCsvProcessor
        implements ItemProcessor<PartnerCorpAddMiniDto, WkTblPartnerCorpAddMinEntity> {

    /** 空文字 */
    private static final String BLANK = "";

    /** 法人番号正規表現 */
    private final Pattern patternHoujinNo = Pattern.compile("[0-9]{13}");

    /** 関連者企業・団体同属性取得Service */
    @Autowired
    private GetPartnerCorpSameHistoryService getPartnerCorpSameHistoryService;

    /** 関連者企業・団体マスタRepository */
    @Autowired
    private MasterCorporationRepository masterCorporationRepository;

    /** 全文検索検索語フォーマットUtil */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblPartnerCorpAddMinEntity process(final PartnerCorpAddMiniDto item) throws Exception {

        WkTblPartnerCorpAddMinEntity entity = new WkTblPartnerCorpAddMinEntity();
        BeanUtils.copyProperties(item, entity);

        StringBuilder stringBuilder = new StringBuilder();
        if (BLANK.equals(item.getPartnerName())) {
            stringBuilder.append("名称が入力されていません;");
        }
        if (BLANK.equals(item.getAllAddress())) {
            stringBuilder.append("住所が入力されていません;");
        }
        if (BLANK.equals(item.getCorpDelegate())) {
            stringBuilder.append("代表者が入力されていません;");
        }
        String houjinNo = item.getHoujinNo();
        if (BLANK.equals(houjinNo)) {
            stringBuilder.append("法人番号が入力されていません;");
        } else {
            Matcher matcher = patternHoujinNo.matcher(houjinNo);
            if (!matcher.find()) {
                stringBuilder.append("法人番号の形式ではありません(数字13桁);");
            }
        }

        // 全く同じ履歴があるかどうか確認する
        List<PartnerCorpHistoryBaseEntity> listHistory = this.selectSameRirekiList(entity.getPartnerName(),
                entity.getAllAddress(), entity.getCorpDelegate());
        if (listHistory.isEmpty()) {
            // マスタに同名の団体があるかどうか確認する
            // TODO このデータは追加処理しなければならないケース(同名団体)と、してはいけないケースがあるので
            // バッチ処理後に選択して処理できないと問題がある
            List<MasterCorporationEntity> listMaster = masterCorporationRepository.findByCompareNameTextAndIsLatest(
                    formatNaturalSearchTextUtil.practice(entity.getPartnerName()),
                    SetTableDataHistoryUtil.INSERT_STATE);
            if (!listMaster.isEmpty()) {
                stringBuilder.append("同名の団体があります。確認調査の上、必要に応じて追加してください;");
            }

        } else {
            stringBuilder.append("すでに登録が存在します(").append(listHistory.get(0).getCorpKanrenshaCode()).append(");");
        }

        // 入力に問題がある場合は記録だけ残して処理中断
        if (stringBuilder.isEmpty()) {
            entity.setIsAffected(true);
            entity.setIsFinish(false);
        } else {
            entity.setIsAffected(false);
            entity.setJudgeReason(stringBuilder.toString());
            entity.setIsFinish(true);
        }

        return entity;
    }

    /*
     * 同属性リストを取得する
     *
     * @param name 団体名称
     * @param address 全住所
     * @param delegate 代表者名
     * @return 検索結果
     */
    private List<PartnerCorpHistoryBaseEntity> selectSameRirekiList(final  String name, final String address,final  String delegate) {

        return getPartnerCorpSameHistoryService.practice(name, address, delegate);
    }

}
