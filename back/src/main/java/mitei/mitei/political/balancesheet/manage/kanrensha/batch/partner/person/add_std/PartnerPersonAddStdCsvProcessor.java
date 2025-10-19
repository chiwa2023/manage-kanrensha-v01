package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_std;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPersonHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.GetPartnerPersonSameHistoryService;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人標準登録Processor
 */
@Component
public class PartnerPersonAddStdCsvProcessor implements ItemProcessor<PartnerPersonAddStdDto, WkTblMasterPersonEntity> {

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
    @Override
    public WkTblMasterPersonEntity process(final PartnerPersonAddStdDto item) throws Exception { // NOPMD

        WkTblMasterPersonEntity entity = new WkTblMasterPersonEntity();
        BeanUtils.copyProperties(item, entity);

        return this.check(entity);
    }

    /**
     * チェック処理のみ行う
     *
     * @param entity ワークテーブルEntity
     * @return 処理後Entity
     */
    public WkTblMasterPersonEntity check(final WkTblMasterPersonEntity entity) { // SUPPRESS CHECKSTYLE NCSS NOPMD
        StringBuilder stringBuilder = new StringBuilder();
        // 未入力
        if (BLANK.equals(entity.getPartnerName())) {
            stringBuilder.append("名称が入力されていません;");
        }
        if (BLANK.equals(entity.getAllAddress())) {
            stringBuilder.append("住所が入力されていません;");
        }
        // if (BLANK.equals(item.getPersonShokugyou())) {
        // stringBuilder.append("職業が入力されていません;");
        // }
        if (BLANK.equals(entity.getAddressPostal())) {
            stringBuilder.append("住所郵便番号までが入力されていません;");
        }
        if (BLANK.equals(entity.getAddressBlock())) {
            stringBuilder.append("住所番地までが入力されていません;");
        }
        // if (BLANK.equals(entity.getAddressBuilding())) {
        // stringBuilder.append("住所建物までが入力されていません;");
        // }
        if (BLANK.equals(entity.getPhon1())) {
            stringBuilder.append("電話番号市外局番が入力されていません;");
        }
        if (BLANK.equals(entity.getPhon2())) {
            stringBuilder.append("電話番号局番が入力されていません;");
        }
        if (BLANK.equals(entity.getPhon3())) {
            stringBuilder.append("電話番号番号が入力されていません;");
        }
        if (BLANK.equals(entity.getEmail())) {
            stringBuilder.append("メールアドレスが入力されていません;");
        }

        // 文字数制限
        if (LIMIT_DIGIT_PHON < entity.getPhon1().length()) {
            stringBuilder.append("電話番号市外局番が10文字以上です;");
        }
        if (LIMIT_DIGIT_PHON < entity.getPhon2().length()) {
            stringBuilder.append("電話番号局番が10文字以上です;");
        }
        if (LIMIT_DIGIT_PHON < entity.getPhon1().length()) {
            stringBuilder.append("電話番号番号が10文字以上です;");
        }
        if (LIMIT_DIGIT_POSTAL < entity.getPostal1().length()) {
            stringBuilder.append("郵便番号1が6文字以上です;");
        }
        if (LIMIT_DIGIT_POSTAL < entity.getPostal2().length()) {
            stringBuilder.append("郵便番号2が6文字以上です;");
        }
        if (LIMIT_LGCODE < entity.getLgCode().length()) {
            stringBuilder.append("地方自治体コードが8文字以上です;");
        }
        if (LIMIT_MACHIAZA < entity.getMachiazaId().length()) {
            stringBuilder.append("町字コードが9文字以上です;");
        }
        if (LIMIT_BLK < entity.getBlkId().length()) {
            stringBuilder.append("街区コードが5文字以上です;");
        }
        if (LIMIT_RSDT < entity.getRsdtId().length()) {
            stringBuilder.append("住居コードが5文字以上です;");
        }
        if (LIMIT_RSDT2 < entity.getRsdt2Id().length()) {
            stringBuilder.append("住居2コードが7文字以上です;");
        }

        // 全く同じ履歴があるかどうか確認する
        List<PartnerPersonHistoryBaseEntity> listHistory = this.selectSameRirekiList(entity.getPartnerName(),
                entity.getAllAddress(), entity.getPersonShokugyou());
        if (listHistory.isEmpty()) {
            // 初回はすべてチェック対象だが、自動・人為的に作業対象にした場合は同名チェックを行わない(同名でも登録できる)
            // マスタに同名の団体があるかどうか確認する
            if (!entity.getIsAffected()) {
                List<MasterPersonEntity> listMaster = masterPersonRepository.findByCompareNameTextAndIsLatest(
                        formatNaturalSearchTextUtil.practice(entity.getPartnerName()),
                        SetTableDataHistoryUtil.INSERT_STATE);
                // 初回はすでにチェック対象だが
                if (!listMaster.isEmpty()) { // SUPPRESS CHECKSTYLE NestedIf
                    stringBuilder.append("同名の団体があります。確認調査の上、必要に応じて追加してください;");
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
            final String shokugyou) {
        return getPartnerPersonSameHistoryService.practice(name, address, shokugyou);
    }

}
