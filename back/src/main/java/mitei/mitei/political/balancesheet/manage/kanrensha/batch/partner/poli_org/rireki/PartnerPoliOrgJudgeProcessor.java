package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.rireki;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者政治団体ワークテーブルから判定結果を作成する
 */
@Component
public class PartnerPoliOrgJudgeProcessor
        implements ItemProcessor<WkTblPartnerPoliOrgHistoryEntity, WkTblPartnerPoliOrgJudgeEntity> {

    /** 空白 */
    private static final String BLANK = "";

    /** 正常登録 */
    private static final String RIGHT = "正)";

    /** 関連者政治団体マスタRepository */
    @Autowired
    private MasterPoliticalOrganizationRepository masterPoliticalOrganizationRepository;

    /** 自然検索フォーマットUtil */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 変換処理を実行する
     */
    @Override
    public WkTblPartnerPoliOrgJudgeEntity process(final WkTblPartnerPoliOrgHistoryEntity item) throws Exception {

        WkTblPartnerPoliOrgJudgeEntity entity = new WkTblPartnerPoliOrgJudgeEntity();
        BeanUtils.copyProperties(item, entity);

        StringBuilder stringBuilder = this.createCheckMessage(item);

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

    /**
     * チェック処理のみ行う
     *
     * @param entity ワークテーブルEnity
     * @return チェック処理
     */
    public WkTblPartnerPoliOrgHistoryEntity check(final WkTblPartnerPoliOrgHistoryEntity entity) {

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

    private StringBuilder createCheckMessage(final WkTblPartnerPoliOrgHistoryEntity entity) {

        // どれかの値が入力していなければ情報不足として処理対象外
        StringBuilder stringBuilder = new StringBuilder();
        if (BLANK.equals(entity.getPartnerName())) {
            stringBuilder.append("名称が入力されていません;");
        }
        if (BLANK.equals(entity.getAllAddress())) {
            stringBuilder.append("住所が入力されていません;");
        }
        // if (BLANK.equals(item.getPoliOrgDelegate())) {
        // stringBuilder.append("代表者が入力されていません;");
        // }
        if (BLANK.equals(entity.getPoliOrgKanrenshaCode())) {
            stringBuilder.append("関連者コードが入力されていません;");
        }
        
        if (stringBuilder.isEmpty()) {
            // 少なくとも団体名と関連者コードが同一でない場合は未登録とみなす
            List<MasterPoliticalOrganizationEntity> listMaster = masterPoliticalOrganizationRepository
                    .findByPoliOrgKanrenshaCodeAndCompareNameTextAndIsLatest(entity.getPoliOrgKanrenshaCode(),
                            formatNaturalSearchTextUtil.practice(entity.getPartnerName()),
                            SetTableDataHistoryUtil.INSERT_STATE);
            if (listMaster.isEmpty()) {
                stringBuilder.append("コードと名称に合致する関連者が存在しません;");
            }
        }

        return stringBuilder;
    }

}
