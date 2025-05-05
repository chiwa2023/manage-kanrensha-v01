package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressRsdtBaseEntity;

/**
 * アドレス・ベース・レジストリ住居ItemWriter
 */
@Component
public class RsdtAddressItemWriter extends JpaItemWriter<AddressRsdtBaseEntity> {

    /** entityManager */
    private final EntityManager entityManager;

    /** カンマ */
    private static final String COMMA = ",";

    /** シングルクォーテーション */
    private static final String QUOTE_SINGLE = "'";

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public RsdtAddressItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
        entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * 書き込み処理
     */
    @Override
    @Transactional
    public void write(final Chunk<? extends AddressRsdtBaseEntity> items) {

        final String blank = "";
        // ローカル専用のトランザクションを設定しそこにJoinせよ、とのこと
        // 参考
        // https://stackoverflow.com/questions/25821579/transactionrequiredexception-executing-an-update-delete-query
        entityManager.joinTransaction();
        for (AddressRsdtBaseEntity entity : items) {
            // 2重登録になるケースを避けるために、条件によっては空Entityを渡すことがあるので、その場合は処理を回避する
            if(!blank.equals(entity.getLgCode())) {
                Query query = entityManager.createNativeQuery("INSERT INTO address_rsdt_" + entity.getLgCode()
                        + " (address_rsdt_id,postal_code,lg_code,machiaza_id,parcel_rsdt_id,address_block"
                        + ",address_building,effect_date) VALUES" + " (0,''," + this.createInsertParameter(entity) + ")");
                query.executeUpdate();
            }
        }
        entityManager.flush();
    }

    private String createInsertParameter(final AddressRsdtBaseEntity entity) {
        StringBuilder builder = new StringBuilder();

        builder.append(QUOTE_SINGLE).append(entity.getLgCode()).append(QUOTE_SINGLE).append(COMMA).append(QUOTE_SINGLE)
                .append(entity.getMachiazaId()).append(QUOTE_SINGLE).append(COMMA).append(QUOTE_SINGLE)
                .append(entity.getParcelRsdtId()).append(QUOTE_SINGLE).append(COMMA).append(QUOTE_SINGLE)
                .append(entity.getAddressBlock()).append(QUOTE_SINGLE).append(COMMA).append(QUOTE_SINGLE)
                .append(entity.getAddressBuilding()).append(QUOTE_SINGLE).append(COMMA).append(QUOTE_SINGLE)
                .append(entity.getEffectDate()).append(QUOTE_SINGLE);

        return builder.toString();
    }

}
