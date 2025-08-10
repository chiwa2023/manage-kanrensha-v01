package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_min;

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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPersonAddMinRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateUserLeastDtoByBatchParamUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * ファイル内抽出処理を削除する
 */
@Component
public class SuspendDuplicateWkTblParnerPersonAddMinTasklet implements Tasklet, StepExecutionListener {

    /** 関連者個人登録最小限Repository */
    @Autowired
    private WkTblPartnerPersonAddMinRepository wkTblPartnerPersonAddMinRepository;

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

        List<PartnerPersonMasterUniquekeyDto> listKeyGroup = wkTblPartnerPersonAddMinRepository
                .findDuplicateUniqueKey(userCode);

        for (PartnerPersonMasterUniquekeyDto uniqueDto : listKeyGroup) {
            List<WkTblPartnerPersonAddMinEntity> list = wkTblPartnerPersonAddMinRepository
                    .findByPartnerNameAndAllAddressAndPersonShokugyouAndInsertUserCodeOrderByWkTblPartnerPersonAddMinIdAsc(
                            uniqueDto.getPartnerName(), uniqueDto.getAllAddress(), uniqueDto.getPersonShokugyou(),
                            userCode);
            list.remove(0); // 1行だけは処理実行行として残す
            for (WkTblPartnerPersonAddMinEntity entity : list) {
                setTableDataHistoryUtil.practiceDelete(userDto, entity); // 削除
                entity.setIsFinish(true);
                entity.setJudgeReason("アップロードファイル内で重複しているデータです");
            }
            wkTblPartnerPersonAddMinRepository.saveAllAndFlush(list);
        }

        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
