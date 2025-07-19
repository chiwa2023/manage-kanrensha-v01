package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_std;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateUserLeastDtoByBatchParamUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人マスタ標準登録処理結果登録ItemWriter
 */
@Component
public class MasterPersonAddStdWkTblFixItemWriter extends JpaItemWriter<WkTblMasterPersonEntity> {

    /** 関連者個人マスタ標準ワークテーブルRepository */
    @Autowired
    private WkTblMasterPersonRepository wkTblMasterPersonRepository;

    /** テーブル履歴設定Util */
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
    public MasterPersonAddStdWkTblFixItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblMasterPersonEntity> items) {

        for (WkTblMasterPersonEntity entity : items) {
            setTableDataHistoryUtil.practiceInsert(userDto, entity);
        }
        wkTblMasterPersonRepository.saveAll(items);
    }

}
