package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.PoliOrgDantaiKbnConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPoliOrgHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.GetPartnerPoliOrgSameHistoryService;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者政治団体CsvからワークテーブルProcessor
 */
@Component
public class PartnerPoliOrgAddMiniCsvProcessor
        implements ItemProcessor<PartnerPoliOrgAddMiniDto, WkTblPartnerPoliOrgAddMinEntity> {

    /** 空文字 */
    private static final String BLANK = "";

    /** 関連者政治団体同属性取得Service */
    @Autowired
    private GetPartnerPoliOrgSameHistoryService getPartnerPoliOrgSameHistoryService;

    /** 関連者政治団体マスタRepository */
    @Autowired
    private MasterPoliticalOrganizationRepository masterPoliticalOrganizationRepository;

    /** 全文検索検索語フォーマットUtil */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblPartnerPoliOrgAddMinEntity process(final PartnerPoliOrgAddMiniDto item) throws Exception {

        WkTblPartnerPoliOrgAddMinEntity entity = new WkTblPartnerPoliOrgAddMinEntity();
        BeanUtils.copyProperties(item, entity);

        return this.check(entity);
    }

    /**
     * チェック処理のみ行う
     *
     * @param entity ワークテーブルEntity
     * @return 処理後Entity
     */
    public WkTblPartnerPoliOrgAddMinEntity check(final WkTblPartnerPoliOrgAddMinEntity entity) {

        StringBuilder stringBuilder = new StringBuilder();
        if (BLANK.equals(entity.getPartnerName())) {
            stringBuilder.append("名称が入力されていません;");
        }
        if (BLANK.equals(entity.getAllAddress())) {
            stringBuilder.append("住所が入力されていません;");
        }
        if (BLANK.equals(entity.getPoliOrgDelegate())) {
            stringBuilder.append("代表者が入力されていません;");
        }
        String dantaiKbn = entity.getDantaiKbn();
        List<String> listDantaiKbn = PoliOrgDantaiKbnConstants.getList();
        if (BLANK.equals(dantaiKbn)) {
            stringBuilder.append("政治団体区分が入力されていません;");
        } else {
            if (!listDantaiKbn.contains(dantaiKbn)) {
                stringBuilder.append("政治団体区分の値が不正です;");
            }
        }

        // 全く同じ履歴があるかどうか確認する
        List<PartnerPoliOrgHistoryBaseEntity> listHistory = this.selectSameRirekiList(entity.getPartnerName(),
                entity.getAllAddress(), entity.getPoliOrgDelegate());
        if (listHistory.isEmpty()) {
            if (!entity.getIsAffected()) {
                // マスタに同名の団体があるかどうか確認する
                List<MasterPoliticalOrganizationEntity> listMaster = masterPoliticalOrganizationRepository
                        .findByCompareNameTextAndIsLatest(formatNaturalSearchTextUtil.practice(entity.getPartnerName()),
                                SetTableDataHistoryUtil.INSERT_STATE);
                if (!listMaster.isEmpty()) { // SUPPRESS CHECKSTYLE NestedIf
                    stringBuilder.append("同名の団体があります。確認調査の上、必要に応じて追加してください;");
                }
            }

        } else {
            stringBuilder.append("すでに登録が存在します(").append(listHistory.get(0).getPoliOrgKanrenshaCode()).append(");");
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
    private List<PartnerPoliOrgHistoryBaseEntity> selectSameRirekiList(final String name, final String address,
            final String delegate) {

        return getPartnerPoliOrgSameHistoryService.practice(name, address, delegate);
    }

}
