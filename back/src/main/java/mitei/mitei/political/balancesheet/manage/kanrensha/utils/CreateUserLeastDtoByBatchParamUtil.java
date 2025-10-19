package mitei.mitei.political.balancesheet.manage.kanrensha.utils;

import org.springframework.batch.core.StepExecution;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;

/**
 * バッチ起動条件からユーザ最低限を作成する
 */
@Component
public class CreateUserLeastDtoByBatchParamUtil {

    /**
     * 処理を行う
     *
     * @param stepExecution StepExecution
     * @return ユーザ最低限Dto
     */
    public UserPersonLeastDto practice(final StepExecution stepExecution) {
        UserPersonLeastDto dto = new UserPersonLeastDto();

        dto.setUserPersonId(Math.toIntExact(stepExecution.getJobParameters().getLong("userId")));
        dto.setUserPersonCode(Math.toIntExact(stepExecution.getJobParameters().getLong("userCode")));
        dto.setUserPersonName(stepExecution.getJobParameters().getString("userName"));

        return dto;
    }

}
