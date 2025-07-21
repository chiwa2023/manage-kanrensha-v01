package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_std;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPoliOrgHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPoliOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.GetPartnerPoliOrgSameHistoryService;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人標準登録Processor
 */
@Component
public class PartnerPoliOrgAddStdCsvProcessor implements ItemProcessor<PartnerPoliOrgAddStdDto, WkTblMasterPoliOrgEntity> {

    /** 空文字 */
    private static final String BLANK = "";

    /** 関連者個人同属性取得Service */
    @Autowired
    private GetPartnerPoliOrgSameHistoryService getPartnerPoliOrgSameHistoryService;

    /** 関連者個人マスタRepository */
    @Autowired
    private MasterPoliticalOrganizationRepository masterPoliticalOrganizationRepository;

    /** 全文検索検索語フォーマットUtil */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /** 電話番号最大桁数 */
    private static final int LIMIT_DIGIT_PHON = 10;

    /** 郵便番号最大桁数 */
    private static final int LIMIT_DIGIT_POSTAL = 6;
    
    /** 地方自治体コード最大桁数 */
    private static final int LIMIT_LGCODE = 8;

    /** 町字コード最大桁数 */
    private static final int LIMIT_MACHIAZA = 9;

    /** 街区コード最大桁数 */
    private static final int LIMIT_BLK = 5;

    /** 住居コード最大桁数 */
    private static final int LIMIT_RSDT = 5;

    /** 住居2コード最大桁数 */
    private static final int LIMIT_RSDT2 = 7;

    /**
     * 変換処理を実行する
     */
    @Override // SUPPRESS CHECKSTYLE NCSS
    public WkTblMasterPoliOrgEntity process(final PartnerPoliOrgAddStdDto item) throws Exception { // NOPMD

        WkTblMasterPoliOrgEntity entity = new WkTblMasterPoliOrgEntity();
        BeanUtils.copyProperties(item, entity);
        StringBuilder stringBuilder = new StringBuilder();
        //  未入力
        if (BLANK.equals(item.getPartnerName())) {
            stringBuilder.append("名称が入力されていません;");
        }
        if (BLANK.equals(item.getAllAddress())) {
            stringBuilder.append("住所が入力されていません;");
        }
        if (BLANK.equals(item.getPoliOrgDelegate())) {
            stringBuilder.append("代表者が入力されていません;");
        }
        if (BLANK.equals(item.getDantaiKbn())) {
            stringBuilder.append("団体区分が入力されていません;");
        }
        if (BLANK.equals(item.getAddressPostal())) {
            stringBuilder.append("住所郵便番号までが入力されていません;");
        }
        if (BLANK.equals(item.getAddressBlock())) {
            stringBuilder.append("住所番地までが入力されていません;");
        }
        if (BLANK.equals(item.getAddressBuilding())) {
            stringBuilder.append("住所建物までが入力されていません;");
        }
        if (BLANK.equals(item.getPhon1())) {
            stringBuilder.append("電話番号市外局番が入力されていません;");
        }
        if (BLANK.equals(item.getPhon2())) {
            stringBuilder.append("電話番号局番が入力されていません;");
        }
        if (BLANK.equals(item.getPhon3())) {
            stringBuilder.append("電話番号番号が入力されていません;");
        }
        if (BLANK.equals(item.getEmail())) {
            stringBuilder.append("メールアドレスが入力されていません;");
        }

        // 文字数制限
        if (LIMIT_DIGIT_PHON < item.getPhon1().length()) {
            stringBuilder.append("電話番号市外局番が10文字以上です;");
        }
        if (LIMIT_DIGIT_PHON < item.getPhon2().length()) {
            stringBuilder.append("電話番号局番が10文字以上です;");
        }
        if (LIMIT_DIGIT_PHON < item.getPhon1().length()) {
            stringBuilder.append("電話番号番号が10文字以上です;");
        }
        if (LIMIT_DIGIT_POSTAL < item.getPostal1().length()) {
            stringBuilder.append("郵便番号1が6文字以上です;");
        }
        if (LIMIT_DIGIT_POSTAL < item.getPostal2().length()) {
            stringBuilder.append("郵便番号2が6文字以上です;");
        }
        if (LIMIT_LGCODE < item.getLgCode().length()) {
            stringBuilder.append("地方自治体コードが8文字以上です;");
        }
        if (LIMIT_MACHIAZA < item.getMachiazaId().length()) {
            stringBuilder.append("町字コードが9文字以上です;");
        }
        if (LIMIT_BLK < item.getBlkId().length()) {
            stringBuilder.append("街区コードが5文字以上です;");
        }
        if (LIMIT_RSDT < item.getRsdtId().length()) {
            stringBuilder.append("住居コードが5文字以上です;");
        }
        if (LIMIT_RSDT2 < item.getRsdt2Id().length()) {
            stringBuilder.append("住居2コードが7文字以上です;");
        }

        // 全く同じ履歴があるかどうか確認する
        List<PartnerPoliOrgHistoryBaseEntity> listHistory = this.selectSameRirekiList(entity.getPartnerName(),
                entity.getAllAddress(), entity.getPoliOrgDelegate());
        if (listHistory.isEmpty()) {
            // マスタに同名の団体があるかどうか確認する
            // TODO このデータは追加処理しなければならないケース(同名団体)と、してはいけないケースがあるので
            // バッチ処理後に選択して処理できないと問題がある
            List<MasterPoliticalOrganizationEntity> listMaster = masterPoliticalOrganizationRepository.findByCompareNameTextAndIsLatest(
                    formatNaturalSearchTextUtil.practice(entity.getPartnerName()),
                    SetTableDataHistoryUtil.INSERT_STATE);
            if (!listMaster.isEmpty()) {
                stringBuilder.append("同名の団体があります。確認調査の上、必要に応じて追加してください;");
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
            final String shokugyou) {
        return getPartnerPoliOrgSameHistoryService.practice(name, address, shokugyou);
    }

}
