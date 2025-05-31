package mitei.mitei.political.balancesheet.manage.kanrensha.batch.houjin_no;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.HoujinNoHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.HoujinNoLatestEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.HoujinNoHistoryRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.HoujinNoLatestRepository;

/**
 * 法人番号登録Itemreader(全体登録用)
 */
@Component
public class HoujinNoTableItemWriter extends JpaItemWriter<HoujinNoLatestEntity> {

    /** 法人番号最新Repository */
    @Autowired
    private HoujinNoLatestRepository houjinNoLatestRepository;

    /** 法人番号履歴Repository */
    @Autowired
    private HoujinNoHistoryRepository houjinNoHistoryRepository;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public HoujinNoTableItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends HoujinNoLatestEntity> items) {

        // 最新データと履歴データは分けて保存
        for (HoujinNoLatestEntity entity : items) {
            if (entity.getLatest()) {
                houjinNoLatestRepository.save(entity);
            } else {
                houjinNoHistoryRepository.save(this.convertHistory(entity));
            }
        }

        houjinNoLatestRepository.flush();
        houjinNoHistoryRepository.flush();
    }

    private HoujinNoHistoryEntity convertHistory(final HoujinNoLatestEntity entity) {

        HoujinNoHistoryEntity entityHistory = new HoujinNoHistoryEntity();
        BeanUtils.copyProperties(entity, entityHistory);

        return entityHistory;
    }

}
