package mitei.mitei.political.balancesheet.manage.kanrensha.logic.postalcode;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalWorksEntity;

/**
 * 郵便番号不規則データのうち１種類の範囲データを自動で修正する
 */
@Component
public class CopyPostalCodeByRangeNameLogic {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /** 数字漢数字変換Utility */
    @Autowired
    private ConvertNumberToKansujiUtil convertNumberToKansujiUtil;

    /** 住所丁目 */
    private static final String CHOUME_TEXT = "丁目";

    /** 住所番地 */
    private static final String BANCHI_TEXT = "番地";

    /** から記号 */
    private static final String NAMI_DASH = "〜";

    /**
     * 処理を行う
     *
     * @param irregularEntity 郵便番号不規則データ
     * @return 郵便番号(正規)データ
     */
    public List<AddressPostalEntity> practice( // SUPPRESS CHECKSTYLE Return Number NOPMD
            final AddressPostalWorksEntity irregularEntity) {

        String postalAddressName = irregularEntity.getAddressOrg();
        int posNamiDash = postalAddressName.indexOf(NAMI_DASH);

        List<AddressPostalEntity> list = new ArrayList<>();
        int posNamiDash2 = postalAddressName.indexOf(NAMI_DASH, posNamiDash + 1);
        if (posNamiDash2 != -1) {
            // ～が2件入っているデータはPG実装では無理(現実と照らし合わせながら修正が必要)
            return list;
        }

        int editCount = 0;
        String key = "";
        if (postalAddressName.indexOf(CHOUME_TEXT) != -1) {
            editCount++;
            key = CHOUME_TEXT;
        }
        if (postalAddressName.indexOf(BANCHI_TEXT) != -1) {
            editCount++;
            key = BANCHI_TEXT;
        }

        final int limitCount = 1;
        if (limitCount < editCount) {
            // 丁目も番地も入っている複雑なパターンは自動処理しない
            return list;
        }

        int posStartParenthese = postalAddressName.indexOf("（");
        int posEndParenthese = postalAddressName.indexOf("）");
        String startAddress = postalAddressName.substring(posStartParenthese + 1, posNamiDash);
        String endAddress = postalAddressName.substring(posNamiDash + 1, posEndParenthese);

        int startIndex;
        try {
            startIndex = Integer.parseInt(startAddress);
        } catch (NumberFormatException exception) {
            // 数字が取得できない場合は空リストを返却
            return list;
        }

        int endIndex;
        try {
            endIndex = Integer.parseInt(endAddress.replace(key, ""));
        } catch (NumberFormatException exception) {
            // 数字が取得できない場合は空リストを返却
            return list;
        }

        String table = "address_rsdt_" + irregularEntity.getLgCode();
        String address = irregularEntity.getAddressName();
        for (int index = startIndex; index <= endIndex; index++) {

            String newAddress = this.getNewAddress(address, index, key);
            String sql = "SELECT COUNT(*) FROM " + table + " WHERE address_block LIKE '" + newAddress + "%'";
            Query query = entityManager.createNativeQuery(sql, Integer.class);
            Integer count = (Integer) query.getResultList().get(0);

            if (count > 0) {
                list.add(this.createEntity(irregularEntity, newAddress));
            }
        }

        return list;
    }

    private AddressPostalEntity createEntity(final AddressPostalWorksEntity irregularEntity,
            final String newAddress) {

        AddressPostalEntity postalEntity = new AddressPostalEntity();

        BeanUtils.copyProperties(irregularEntity, postalEntity);

        postalEntity.setAddressName(newAddress);
        postalEntity.setIsGyoseikuData(true);
        postalEntity.setAddressPostalId(0); // auto_increment明示

        return postalEntity;
    }

    private String getNewAddress( // SUPPRESS CHECKSTYLE ReturnNumber
            final String address, final int index, final String key) {

        // 番地の場合は算用数字をそのまま出していい
        if (BANCHI_TEXT.equals(key)) {
            return address + index + key;
        }

        // 丁目の場合は漢数字に変換必要
        if (CHOUME_TEXT.equals(key)) {
            return address + convertNumberToKansujiUtil.practice(index) + key;
        }

        return "該当なし"; // 現時点では入りようのない値だが、今後その他のKeyが発生したとき用の実装
    }

}
