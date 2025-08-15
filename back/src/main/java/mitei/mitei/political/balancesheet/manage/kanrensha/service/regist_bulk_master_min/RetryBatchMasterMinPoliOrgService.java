package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_master_min;

import java.time.LocalDateTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min.RetryMinKanrenshaPoliOrgMasterBatchConfiguration;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.util.WriteLogService;

/**
 * Csv読み取り政治団体マスタ最小登録Service
 */
@Service
public class RetryBatchMasterMinPoliOrgService {

    /** 起動をするJob */
    @Qualifier(RetryMinKanrenshaPoliOrgMasterBatchConfiguration.JOB_NAME)
    @Autowired
    private Job retryMinKanrenshaPoliOrgMaster;

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** ログ書き出しService */
    @Autowired
    private WriteLogService writeLogService;

    /**
     * 処理を行う
     *
     * @param userDto ユーザDto
     */
    @Async
    public void practice(final UserPersonLeastDto userDto) {

        JobParameters jobParameters = new JobParametersBuilder(
                retryMinKanrenshaPoliOrgMaster.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now())
                .addLong("userId", Long.parseLong(userDto.getUserPersonId().toString()))
                .addLong("userCode", Long.parseLong(userDto.getUserPersonCode().toString()))
                .addString("userName", userDto.getUserPersonName()).toJobParameters();

        try {
            jobLauncher.run(retryMinKanrenshaPoliOrgMaster, jobParameters);

        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException exception) {
            // TODO: handle exception
            writeLogService.practiceError(exception);
        }

    }

}
