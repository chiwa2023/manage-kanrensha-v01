package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_std;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min.PartnerPoliOrgMasterUniquekeyDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPoliOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPoliOrgRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateUserLeastDtoByBatchParamUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人マスタ標準登録同一データ削除Tasklet
 */
@Component
public class SuspendDuplicateWkTblMasterPoliOrgAddStdTasklet implements Tasklet, StepExecutionListener {

    /** 関連者個人マスタ標準ワークテーブル */
    @Autowired
    private WkTblMasterPoliOrgRepository wkTblMasterPoliOrgRepository;

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

        List<PartnerPoliOrgMasterUniquekeyDto> listKeyGroup = wkTblMasterPoliOrgRepository
                .findDuplicateUniqueKey(userCode);
        
        for (PartnerPoliOrgMasterUniquekeyDto uniqueDto : listKeyGroup) {
            List<WkTblMasterPoliOrgEntity> list = wkTblMasterPoliOrgRepository
                    .findByPartnerNameAndAllAddressAndPoliOrgDelegateAndInsertUserCodeOrderByWkTblMasterPoliOrgIdAsc(
                            uniqueDto.getPartnerName(), uniqueDto.getAllAddress(), uniqueDto.getPoliOrgDelegate(),
                            userCode);

            list.remove(0); // 1行だけは処理実行行として残す
            for (WkTblMasterPoliOrgEntity entity : list) {
                setTableDataHistoryUtil.practiceDelete(userDto, entity); // 削除
                entity.setIsFinish(true);
                entity.setJudgeReason("アップロードファイル内で重複しているデータです");
            }
            wkTblMasterPoliOrgRepository.saveAllAndFlush(list);

        }

        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
