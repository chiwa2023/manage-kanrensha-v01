package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.combine_org;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCombineOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCombineOrgRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateUserLeastDtoByBatchParamUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * ファイル内同データを削除する
 */
@Component
public class SuspendDuplicateWkTblCombineOrgTasklet implements Tasklet, StepExecutionListener {

    /** 個人団体紐づけワークテーブルRepository */
    @Autowired
    private WkTblPartnerCombineOrgRepository wkTblPartnerCombineOrgRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** バッチ用ユーザ最低限作成Util */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** ユーザ最小限Dto */
    private UserPersonLeastDto userDto;

    /**
     * 起動条件を設定する
     *
     * @param stepExecution StepExecution
     */
    @BeforeStep
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        userDto = createUserLeastDtoByBatchParamUtil.practice(stepExecution);
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        Integer userCode = userDto.getUserPersonCode();

        List<PartnerCombineOrgUniquekeyDto> listKeyGroup = wkTblPartnerCombineOrgRepository
                .findDuplicateUniqueKey(userCode);

        for (PartnerCombineOrgUniquekeyDto uniqueDto : listKeyGroup) {
            List<WkTblPartnerCombineOrgEntity> list = wkTblPartnerCombineOrgRepository
                    .findByPersonKanrenshaCodeAndOrgKanrenshaCodeAndYearArrayTextAndKanrenshaKbnAndInsertUserCodeOrderByWkTblPartnerCombineOrgIdAsc(
                            uniqueDto.getPersonKanrenshaCode(), uniqueDto.getOrgKanrenshaCode(), uniqueDto.getYearArrayText(),
                            uniqueDto.getKanrenshaKbn(), userCode);
            list.remove(0); // 1行だけは処理実行行として残す
            for (WkTblPartnerCombineOrgEntity entity : list) {
                setTableDataHistoryUtil.practiceDelete(userDto, entity); // 削除
                entity.setIsLatest(SetTableDataHistoryUtil.DELETE_STATE);
                entity.setIsFinish(true);
                entity.setJudgeReason("アップロードファイル内で重複しているデータです");
            }
            wkTblPartnerCombineOrgRepository.saveAllAndFlush(list);
        }

        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
