package mitei.mitei.political.balancesheet.manage.kanrensha.service.address_registory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address_registory.SaveAddressRegistoryCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressRsdtTemplateEntity;

/**
 * アドレス・ベース・レジストリ住居更新Service
 */
@Service
public class SaveAddressRegistoryRsdtService {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /** カンマ */
    private static final String COMMA = ",";

    /** シングルクォーテーション */
    private static final String QUOTE_SINGLE = "'";

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集Entity格納Dto
     * @return 処理結果
     */
    public FrameworkMessageAndResultDto practice(final SaveAddressRegistoryCapsuleDto capsuleDto) {

        // TODO このメソッドは削除予定。下記practiceByEntityをpracticeとする
        // 追加処理
        // AddressRsdtTemplateEntity entity = capsuleDto.getAddressRsdtTemplateEntity();
        // entityManager.joinTransaction();
        // Query query = entityManager.createNativeQuery("INSERT INTO address_rsdt_" +
        // entity.getLgCode()
        // + "
        // (address_rsdt_id,postal_code,lg_code,machiaza_id,parcel_rsdt_id,address_block"
        // + ",address_building,effect_date) VALUES" + " (0," +
        // this.createInsertParameter(entity) + ")");
        // query.executeUpdate();
        //
        // entityManager.flush();

        // TODO 編集処理はテーブル構造が確定次第作成

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        if (0 < this.practiceByEntity(capsuleDto.getAddressRsdtTemplateEntity())) {
            resultDto.setMessage("保存できました");
        } else {
            resultDto.setIsFailure(true);
            resultDto.setMessage("保存できませんでした");
        }

        return resultDto;
    }

    /**
     * 処理を行う(引数Entity)
     *
     * @param entity アドレス・ベース・レジストリ共通Entity
     * @return 更新行数
     */
    public Integer practiceByEntity(final AddressRsdtTemplateEntity entity) {

        entityManager.joinTransaction();
        Query query = entityManager.createNativeQuery("INSERT INTO address_rsdt_" + entity.getLgCode()
                + " (address_rsdt_id,postal_code,lg_code,machiaza_id,parcel_rsdt_id,address_block"
                + ",address_building,effect_date) VALUES" + " (0," + this.createInsertParameter(entity) + ")");
        return query.executeUpdate();
    }

    private String createInsertParameter(final AddressRsdtTemplateEntity entity) {
        StringBuilder builder = new StringBuilder();

        builder //
                .append(QUOTE_SINGLE).append(entity.getPostalCode()).append(QUOTE_SINGLE).append(COMMA)
                .append(QUOTE_SINGLE).append(entity.getLgCode()).append(QUOTE_SINGLE).append(COMMA).append(QUOTE_SINGLE)
                .append(entity.getMachiazaId()).append(QUOTE_SINGLE).append(COMMA).append(QUOTE_SINGLE)
                .append(entity.getParcelRsdtId()).append(QUOTE_SINGLE).append(COMMA).append(QUOTE_SINGLE)
                .append(entity.getAddressBlock()).append(QUOTE_SINGLE).append(COMMA).append(QUOTE_SINGLE)
                .append(entity.getAddressBuilding()).append(QUOTE_SINGLE).append(COMMA).append(QUOTE_SINGLE)
                .append(entity.getEffectDate()).append(QUOTE_SINGLE);

        return builder.toString();
    }

}
