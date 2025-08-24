package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_min;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPersonHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.GetPartnerPersonSameHistoryService;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人CsvからワークテーブルProcessor
 */
@Component
public class PartnerPersonAddMiniCsvProcessor
        implements ItemProcessor<PartnerPersonAddMiniDto, WkTblPartnerPersonAddMinEntity> {

    /** 空文字 */
    private static final String BLANK = "";

    /** 正常登録 */
    private static final String RIGHT = "正)";

    /** 関連者個人同属性取得Service */
    @Autowired
    private GetPartnerPersonSameHistoryService getPartnerPersonSameHistoryService;

    /** 関連者個人マスタRepository */
    @Autowired
    private MasterPersonRepository masterPersonRepository;

    /** 全文検索検索語フォーマットUtil */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblPartnerPersonAddMinEntity process(final PartnerPersonAddMiniDto item) throws Exception {

        WkTblPartnerPersonAddMinEntity entity = new WkTblPartnerPersonAddMinEntity();
        BeanUtils.copyProperties(item, entity);

        return this.check(entity);
    }

    /**
     * チェック処理のみ行う
     *
     * @param entity ワークテーブルEntity
     * @return 処理後Entity
     */
    public WkTblPartnerPersonAddMinEntity check(final WkTblPartnerPersonAddMinEntity entity) {

        StringBuilder stringBuilder = new StringBuilder();
        if (BLANK.equals(entity.getPartnerName())) {
            stringBuilder.append("名称が入力されていません;");
        }
        if (BLANK.equals(entity.getAllAddress())) {
            stringBuilder.append("住所が入力されていません;");
        }
        // if (BLANK.equals(entity.getPersonShokugyou())) {
        // stringBuilder.append("職業が入力されていません;");
        // }

        // 全く同じ履歴があるかどうか確認する
        List<PartnerPersonHistoryBaseEntity> listHistory = this.selectSameRirekiList(entity.getPartnerName(),
                entity.getAllAddress(), entity.getPersonShokugyou());
        if (listHistory.isEmpty()) {
            // マスタに同名の団体があるかどうか確認する
            if (!entity.getIsAffected()) {
                // マスタに同名の団体があるかどうか確認する
                List<MasterPersonEntity> listMaster = masterPersonRepository.findByCompareNameTextAndIsLatest(
                        formatNaturalSearchTextUtil.practice(entity.getPartnerName()),
                        SetTableDataHistoryUtil.INSERT_STATE);
                if (!listMaster.isEmpty()) { // SUPPRESS CHECKSTYLE NestedIf
                    stringBuilder.append("同名の個人があります。確認調査の上、必要に応じて追加してください;");
                }
            }

        } else {
            stringBuilder.append("すでに登録が存在します(").append(listHistory.get(0).getPersonKanrenshaCode()).append(");");
        }

        // 入力に問題がある場合は記録だけ残して処理中断
        if (stringBuilder.isEmpty()) {
            entity.setIsAffected(true);
            entity.setIsFinish(false);
            entity.setJudgeReason(RIGHT);
        } else {
            entity.setIsAffected(false);
            entity.setJudgeReason(stringBuilder.toString());
            entity.setIsFinish(false);
        }

        return entity;
    }

    /*
     * 同属性リストを取得する
     *
     * @param name 団体名称
     * 
     * @param address 全住所
     * 
     * @param delegate 代表者名
     * 
     * @return 検索結果
     */
    private List<PartnerPersonHistoryBaseEntity> selectSameRirekiList(final String name, final String address,
            final String delegate) {

        return getPartnerPersonSameHistoryService.practice(name, address, delegate);
    }

}
