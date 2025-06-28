package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.rireki;

import java.util.Optional;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCorpHistoryRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateUserLeastDtoByBatchParamUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業・団体ワークテーブルItemWriter
 */
@Component
public class PartnerCorpHistoryItemWriter extends JpaItemWriter<WkTblPartnerCorpHistoryEntity> {

    /** 関連者企業・団体ワークテーブルRepository */
    @Autowired
    private WkTblPartnerCorpHistoryRepository wkTbPartnerCorpHistoryRepository;

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
    public PartnerCorpHistoryItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblPartnerCorpHistoryEntity> items) {

        int code = 0;

        Optional<WkTblPartnerCorpHistoryEntity> optional = wkTbPartnerCorpHistoryRepository
                .findFirstByOrderByWkPartnerCorpHistoryCodeDesc();
        if (!optional.isEmpty()) {
            code = optional.get().getWkPartnerCorpHistoryCode();
        }

        for (WkTblPartnerCorpHistoryEntity entity : items) {
            code++;
            setTableDataHistoryUtil.practiceInsert(userDto, entity);
            entity.setWkPartnerCorpHistoryCode(code);
        }

        wkTbPartnerCorpHistoryRepository.saveAll(items);
    }

}
