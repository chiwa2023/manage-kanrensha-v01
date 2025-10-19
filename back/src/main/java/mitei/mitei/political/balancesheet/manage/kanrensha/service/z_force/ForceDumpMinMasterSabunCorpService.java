package mitei.mitei.political.balancesheet.manage.kanrensha.service.z_force;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.sabun.DumpSabunMasterCorporationBatchConfiguration;
import mitei.mitei.political.balancesheet.manage.kanrensha.constants.GetCurrentResourcePath;
import mitei.mitei.political.balancesheet.manage.kanrensha.constants.MasterCsvFileNameConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.constants.MasterCsvFileNameConstants.SabunMasterMin;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.util.WriteLogService;

/**
 * 企業／団体マスタ最小差分データをダンプする非同期Service
 */
@Service
@ConfigurationProperties(prefix = "mitei.mitei.political.balancesheet.manage.kanrensha")
public class ForceDumpMinMasterSabunCorpService {

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** 起動をするJob */
    @Qualifier(DumpSabunMasterCorporationBatchConfiguration.JOB_NAME)
    @Autowired
    private Job dumpSabunMasterCorporation;

    /** propertiesからインジェクションされたフロントの共通ダンプCSV保存先 */
    private String frontDumpFolder;

    /**
     * フロントの共通ダンプCSV保存先を取得する
     *
     * @return フロントの共通ダンプCSV保存先
     */
    public String getFrontDumpFolder() {
        return frontDumpFolder;
    }

    /**
     * フロントの共通ダンプCSV保存先を設定する
     *
     * @param frontDumpFolder フロントの共通ダンプCSV保存先
     */
    public void setFrontDumpFolder(final String frontDumpFolder) {
        this.frontDumpFolder = frontDumpFolder;
    }

    /** ログ書き出しService */
    @Autowired
    private WriteLogService writeLogService;

    /**
     * 非同期処理を行う
     *
     * @param startDate 抽出開始時間
     * @param endDate   抽出終了時間
     */
    public void practice(final LocalDate startDate, final LocalDate endDate) {

        final String pathSaved = Paths.get(GetCurrentResourcePath.getBackSrcPath("")).getParent().getParent()
                .toString();
        final String folder = frontDumpFolder + MasterCsvFileNameConstants.FOLDER_MASTER_SABUN;

        JobParameters jobParameters = new JobParametersBuilder(
                dumpSabunMasterCorporation.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now())
                .addLocalDateTime("datetimeStart", LocalDateTime.of(startDate, LocalTime.MIN))
                .addLocalDateTime("datetimeEnd", LocalDateTime.of(endDate.plusDays(1L), LocalTime.MIN))
                .addString("writeFilePath", Paths.get(pathSaved, folder, SabunMasterMin.SABUN_MIN_CORP).toString())
                .toJobParameters();

        try {
            jobLauncher.run(dumpSabunMasterCorporation, jobParameters);

        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException exception) {
            // TODO: handle exception
            writeLogService.practiceError(exception);
        }

    }

}
