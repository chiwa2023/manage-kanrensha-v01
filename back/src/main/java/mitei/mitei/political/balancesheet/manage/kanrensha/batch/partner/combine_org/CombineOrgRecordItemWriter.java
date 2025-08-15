package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.combine_org;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCombineOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCombineOrgJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCombineOrgJudgeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.year.SwitchYearInsertCombineOrgService;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateUserLeastDtoByBatchParamUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 個人団体紐づけ記録ItemWriter
 */
@Service
public class CombineOrgRecordItemWriter extends JpaItemWriter<WkTblPartnerCombineOrgEntity> {

    /** 関連者企業・団体履歴(01)Repository */
    @Autowired
    private WkTblPartnerCombineOrgJudgeRepository wkTblPartnerCombineOrgJudgeRepository;

    /** 個人団体紐づけ年切り替えService */
    @Autowired
    private SwitchYearInsertCombineOrgService switchYearInsertCombineOrgService;

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
    public CombineOrgRecordItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblPartnerCombineOrgEntity> items) {

        final List<WkTblPartnerCombineOrgJudgeEntity> list = new ArrayList<>();

        // 編集処理
        for (WkTblPartnerCombineOrgEntity entity : items) {

            // すべて登録できたら判定テーブルに処理成功と記録する
            if (switchYearInsertCombineOrgService.practice(entity, userDto)) {
                list.add(this.createJudge(entity));
            }
        }

        wkTblPartnerCombineOrgJudgeRepository.saveAllAndFlush(list);
    }

    private WkTblPartnerCombineOrgJudgeEntity createJudge(final WkTblPartnerCombineOrgEntity entityWkTbl) {
        WkTblPartnerCombineOrgJudgeEntity entity = new WkTblPartnerCombineOrgJudgeEntity();
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setWkTblPartnerCombineOrgId(entityWkTbl.getWkTblPartnerCombineOrgId());
        return entity;
    }

}
