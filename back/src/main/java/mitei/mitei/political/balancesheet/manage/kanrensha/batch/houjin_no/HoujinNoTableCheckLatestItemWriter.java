package mitei.mitei.political.balancesheet.manage.kanrensha.batch.houjin_no;

import java.util.List;

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
 * 法人番号更新(最新にバッティングするデータがないかを確認しながら)ItemWriter
 */
@Component
public class HoujinNoTableCheckLatestItemWriter extends JpaItemWriter<HoujinNoLatestEntity> {

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
    public HoujinNoTableCheckLatestItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends HoujinNoLatestEntity> items) {

        for (HoujinNoLatestEntity entity : items) {
            if (entity.getLatest()) {

                List<HoujinNoLatestEntity> list = houjinNoLatestRepository
                        .findByCorporateNumberAndLatest(entity.getCorporateNumber(), true);
                // 同じ法人番号かつ最新のデータが存在する場合はそのデータを履歴に複写
                if (!list.isEmpty()) {
                    for (HoujinNoLatestEntity entityOld : list) {
                        houjinNoHistoryRepository.save(this.convertHistory(entityOld));
                    }
                }

                // 履歴のあるなしに関係なく新たに入ってきたデータは登録
                houjinNoLatestRepository.save(entity);

            } else {
                houjinNoHistoryRepository.save(this.convertHistory(entity));
            }
        }
    }

    private HoujinNoHistoryEntity convertHistory(final HoujinNoLatestEntity entity) {

        HoujinNoHistoryEntity entityHistory = new HoujinNoHistoryEntity();
        BeanUtils.copyProperties(entity, entityHistory);

        return entityHistory;
    }

}
