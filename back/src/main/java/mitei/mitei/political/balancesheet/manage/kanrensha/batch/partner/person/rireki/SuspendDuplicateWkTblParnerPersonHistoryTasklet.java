package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.rireki;

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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPersonHistoryRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateUserLeastDtoByBatchParamUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * アップロードファイル内で重複している行を処理対象外とするTasklet
 */
@Component
public class SuspendDuplicateWkTblParnerPersonHistoryTasklet implements Tasklet,StepExecutionListener {

    /** 関連者個人履歴Repository */
    @Autowired
    private WkTblPartnerPersonHistoryRepository wkTblPartnerPersonHistoryRepository;

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
        List<PartnerPersonUniquekeyDto> listKeyGroup = wkTblPartnerPersonHistoryRepository.findDuplicateUniqueKey(userCode);

        for (PartnerPersonUniquekeyDto uniqueDto : listKeyGroup) {
            List<WkTblPartnerPersonHistoryEntity> list = wkTblPartnerPersonHistoryRepository
                    .findByPartnerNameAndAllAddressAndPersonShokugyouAndPersonKanrenshaCodeAndInsertUserCodeOrderByWkPartnerPersonHistoryIdAsc(
                            uniqueDto.getPartnerName(), uniqueDto.getAllAddress(), uniqueDto.getPersonShokugyou(),
                            uniqueDto.getPersonKanrenshaCode(), userCode);
            list.remove(0); // 1行だけは処理実行行として残す
            for (WkTblPartnerPersonHistoryEntity entity : list) {
                setTableDataHistoryUtil.practiceDelete(userDto, entity); // 削除
                entity.setIsLatest(SetTableDataHistoryUtil.DELETE_STATE);
                entity.setIsFinish(true);
                entity.setJudgeReason("アップロードファイル内で重複しているデータです");
            }
            wkTblPartnerPersonHistoryRepository.saveAllAndFlush(list);
        }

        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
