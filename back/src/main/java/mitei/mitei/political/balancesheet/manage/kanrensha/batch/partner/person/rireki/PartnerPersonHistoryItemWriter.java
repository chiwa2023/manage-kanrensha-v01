package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.rireki;

import java.util.Optional;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPersonHistoryRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateUserLeastDtoByBatchParamUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人ワークテーブルItemWriter
 */
@Component
public class PartnerPersonHistoryItemWriter extends JpaItemWriter<WkTblPartnerPersonHistoryEntity> {

    /** 関連者個人ワークテーブルRepository */
    @Autowired
    private WkTblPartnerPersonHistoryRepository wkTbPartnerPersonHistoryRepository;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** ユーザ最低限Dto */
    private UserPersonLeastDto userDto;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public PartnerPersonHistoryItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }

    /**
     * BeforeStep(読み取りファイル指定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        userDto = createUserLeastDtoByBatchParamUtil.practice(stepExecution);
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends WkTblPartnerPersonHistoryEntity> items) {

        int code = 0;

        Optional<WkTblPartnerPersonHistoryEntity> optional = wkTbPartnerPersonHistoryRepository
                .findFirstByOrderByWkPartnerPersonHistoryCodeDesc();
        if (!optional.isEmpty()) {
            code = optional.get().getWkPartnerPersonHistoryCode();
        }

        for (WkTblPartnerPersonHistoryEntity entity : items) {
            code++;
            setTableDataHistoryUtil.practiceInsert(userDto, entity);
            entity.setWkPartnerPersonHistoryCode(code);
        }

        wkTbPartnerPersonHistoryRepository.saveAll(items);
    }

}
