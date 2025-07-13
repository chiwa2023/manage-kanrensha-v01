package mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.sabun.history; // NOPMD

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory01ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory02ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory03ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory04ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory05ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory06ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory07ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory08ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory09ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory10ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory11ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory12ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory13ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory14ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory15ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory16ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory17ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory18ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory19ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory20ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory21ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory22ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory23ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory24ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory25ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory26ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory27ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory28ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory29ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory30ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory31ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory32ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory33ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory34ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory35ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory36ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory37ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory38ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory39ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory40ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory41ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory42ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory43ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory44ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory45ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory46ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory47ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history.DumpPartnerPoliOrgHistory99ItemWriter;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.zip.CompressZipFileTasklet;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory02Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory03Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory04Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory05Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory06Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory07Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory08Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory09Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory10Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory11Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory12Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory13Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory14Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory15Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory16Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory17Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory18Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory19Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory20Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory21Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory22Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory23Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory24Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory25Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory26Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory27Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory28Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory29Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory30Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory31Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory32Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory33Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory34Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory35Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory36Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory37Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory38Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory39Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory40Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory41Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory42Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory43Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory44Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory45Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory46Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory47Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory99Entity;

/**
 * 関連者政治団体履歴差分csv作成BatchConfiguration
 */
@Configuration
public class DumpSabunPartnerPoliOrgHistoryBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "dumpSabunPartnerPoliOrgHistory";

    /** Step(接尾語) */
    private static final String STEP = "Step";

    /** Job(接尾語) */
    private static final String JOB = "Job";

    /** Job名 */
    public static final String JOB_NAME = FUNCTION_NAME + JOB;

    /** 処理単位数 */
    private static final int CHUNK_SIZE = 100;

    /** Step名zip圧縮 */
    public static final String STEP_COMPRESS = FUNCTION_NAME + "Compress" + STEP;

    /** Step名(01) */
    public static final String STEP_OUTPUT01 = FUNCTION_NAME + "Output01" + STEP;
    /** Step名(02) */
    public static final String STEP_OUTPUT02 = FUNCTION_NAME + "Output02" + STEP;
    /** Step名(03) */
    public static final String STEP_OUTPUT03 = FUNCTION_NAME + "Output03" + STEP;
    /** Step名(04) */
    public static final String STEP_OUTPUT04 = FUNCTION_NAME + "Output04" + STEP;
    /** Step名(05) */
    public static final String STEP_OUTPUT05 = FUNCTION_NAME + "Output05" + STEP;
    /** Step名(06) */
    public static final String STEP_OUTPUT06 = FUNCTION_NAME + "Output06" + STEP;
    /** Step名(07) */
    public static final String STEP_OUTPUT07 = FUNCTION_NAME + "Output07" + STEP;
    /** Step名(08) */
    public static final String STEP_OUTPUT08 = FUNCTION_NAME + "Output08" + STEP;
    /** Step名(09) */
    public static final String STEP_OUTPUT09 = FUNCTION_NAME + "Output09" + STEP;
    /** Step名(10) */
    public static final String STEP_OUTPUT10 = FUNCTION_NAME + "Output10" + STEP;
    /** Step名(11) */
    public static final String STEP_OUTPUT11 = FUNCTION_NAME + "Output11" + STEP;
    /** Step名(12) */
    public static final String STEP_OUTPUT12 = FUNCTION_NAME + "Output12" + STEP;
    /** Step名(13) */
    public static final String STEP_OUTPUT13 = FUNCTION_NAME + "Output13" + STEP;
    /** Step名(14) */
    public static final String STEP_OUTPUT14 = FUNCTION_NAME + "Output14" + STEP;
    /** Step名(15) */
    public static final String STEP_OUTPUT15 = FUNCTION_NAME + "Output15" + STEP;
    /** Step名(16) */
    public static final String STEP_OUTPUT16 = FUNCTION_NAME + "Output16" + STEP;
    /** Step名(17) */
    public static final String STEP_OUTPUT17 = FUNCTION_NAME + "Output17" + STEP;
    /** Step名(18) */
    public static final String STEP_OUTPUT18 = FUNCTION_NAME + "Output18" + STEP;
    /** Step名(19) */
    public static final String STEP_OUTPUT19 = FUNCTION_NAME + "Output19" + STEP;
    /** Step名(20) */
    public static final String STEP_OUTPUT20 = FUNCTION_NAME + "Output20" + STEP;
    /** Step名(21) */
    public static final String STEP_OUTPUT21 = FUNCTION_NAME + "Output21" + STEP;
    /** Step名(22) */
    public static final String STEP_OUTPUT22 = FUNCTION_NAME + "Output22" + STEP;
    /** Step名(23) */
    public static final String STEP_OUTPUT23 = FUNCTION_NAME + "Output23" + STEP;
    /** Step名(24) */
    public static final String STEP_OUTPUT24 = FUNCTION_NAME + "Output24" + STEP;
    /** Step名(25) */
    public static final String STEP_OUTPUT25 = FUNCTION_NAME + "Output25" + STEP;
    /** Step名(26) */
    public static final String STEP_OUTPUT26 = FUNCTION_NAME + "Output26" + STEP;
    /** Step名(27) */
    public static final String STEP_OUTPUT27 = FUNCTION_NAME + "Output27" + STEP;
    /** Step名(28) */
    public static final String STEP_OUTPUT28 = FUNCTION_NAME + "Output28" + STEP;
    /** Step名(29) */
    public static final String STEP_OUTPUT29 = FUNCTION_NAME + "Output29" + STEP;
    /** Step名(30) */
    public static final String STEP_OUTPUT30 = FUNCTION_NAME + "Output30" + STEP;
    /** Step名(31) */
    public static final String STEP_OUTPUT31 = FUNCTION_NAME + "Output31" + STEP;
    /** Step名(32) */
    public static final String STEP_OUTPUT32 = FUNCTION_NAME + "Output32" + STEP;
    /** Step名(33) */
    public static final String STEP_OUTPUT33 = FUNCTION_NAME + "Output33" + STEP;
    /** Step名(34) */
    public static final String STEP_OUTPUT34 = FUNCTION_NAME + "Output34" + STEP;
    /** Step名(35) */
    public static final String STEP_OUTPUT35 = FUNCTION_NAME + "Output35" + STEP;
    /** Step名(36) */
    public static final String STEP_OUTPUT36 = FUNCTION_NAME + "Output36" + STEP;
    /** Step名(37) */
    public static final String STEP_OUTPUT37 = FUNCTION_NAME + "Output37" + STEP;
    /** Step名(38) */
    public static final String STEP_OUTPUT38 = FUNCTION_NAME + "Output38" + STEP;
    /** Step名(39) */
    public static final String STEP_OUTPUT39 = FUNCTION_NAME + "Output39" + STEP;
    /** Step名(40) */
    public static final String STEP_OUTPUT40 = FUNCTION_NAME + "Output40" + STEP;
    /** Step名(41) */
    public static final String STEP_OUTPUT41 = FUNCTION_NAME + "Output41" + STEP;
    /** Step名(42) */
    public static final String STEP_OUTPUT42 = FUNCTION_NAME + "Output42" + STEP;
    /** Step名(43) */
    public static final String STEP_OUTPUT43 = FUNCTION_NAME + "Output43" + STEP;
    /** Step名(44) */
    public static final String STEP_OUTPUT44 = FUNCTION_NAME + "Output44" + STEP;
    /** Step名(45) */
    public static final String STEP_OUTPUT45 = FUNCTION_NAME + "Output45" + STEP;
    /** Step名(46) */
    public static final String STEP_OUTPUT46 = FUNCTION_NAME + "Output46" + STEP;
    /** Step名(47) */
    public static final String STEP_OUTPUT47 = FUNCTION_NAME + "Output47" + STEP;
    /** Step名(48) */
    public static final String STEP_OUTPUT99 = FUNCTION_NAME + "Output99" + STEP;

    /** フォルダZip圧縮Tasklet */
    @Autowired
    private CompressZipFileTasklet compressZipFileTasklet;

    /** 関連者政治団体履歴(01)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory01ItemReader dumpSabunPartnerPoliOrgHistory01ItemReader;
    /** 関連者政治団体履歴(02)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory02ItemReader dumpSabunPartnerPoliOrgHistory02ItemReader;
    /** 関連者政治団体履歴(03)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory03ItemReader dumpSabunPartnerPoliOrgHistory03ItemReader;
    /** 関連者政治団体履歴(04)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory04ItemReader dumpSabunPartnerPoliOrgHistory04ItemReader;
    /** 関連者政治団体履歴(05)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory05ItemReader dumpSabunPartnerPoliOrgHistory05ItemReader;
    /** 関連者政治団体履歴(06)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory06ItemReader dumpSabunPartnerPoliOrgHistory06ItemReader;
    /** 関連者政治団体履歴(07)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory07ItemReader dumpSabunPartnerPoliOrgHistory07ItemReader;
    /** 関連者政治団体履歴(08)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory08ItemReader dumpSabunPartnerPoliOrgHistory08ItemReader;
    /** 関連者政治団体履歴(09)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory09ItemReader dumpSabunPartnerPoliOrgHistory09ItemReader;
    /** 関連者政治団体履歴(10)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory10ItemReader dumpSabunPartnerPoliOrgHistory10ItemReader;
    /** 関連者政治団体履歴(11)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory11ItemReader dumpSabunPartnerPoliOrgHistory11ItemReader;
    /** 関連者政治団体履歴(12)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory12ItemReader dumpSabunPartnerPoliOrgHistory12ItemReader;
    /** 関連者政治団体履歴(13)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory13ItemReader dumpSabunPartnerPoliOrgHistory13ItemReader;
    /** 関連者政治団体履歴(14)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory14ItemReader dumpSabunPartnerPoliOrgHistory14ItemReader;
    /** 関連者政治団体履歴(15)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory15ItemReader dumpSabunPartnerPoliOrgHistory15ItemReader;
    /** 関連者政治団体履歴(16)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory16ItemReader dumpSabunPartnerPoliOrgHistory16ItemReader;
    /** 関連者政治団体履歴(17)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory17ItemReader dumpSabunPartnerPoliOrgHistory17ItemReader;
    /** 関連者政治団体履歴(18)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory18ItemReader dumpSabunPartnerPoliOrgHistory18ItemReader;
    /** 関連者政治団体履歴(19)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory19ItemReader dumpSabunPartnerPoliOrgHistory19ItemReader;
    /** 関連者政治団体履歴(20)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory20ItemReader dumpSabunPartnerPoliOrgHistory20ItemReader;
    /** 関連者政治団体履歴(21)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory21ItemReader dumpSabunPartnerPoliOrgHistory21ItemReader;
    /** 関連者政治団体履歴(22)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory22ItemReader dumpSabunPartnerPoliOrgHistory22ItemReader;
    /** 関連者政治団体履歴(23)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory23ItemReader dumpSabunPartnerPoliOrgHistory23ItemReader;
    /** 関連者政治団体履歴(24)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory24ItemReader dumpSabunPartnerPoliOrgHistory24ItemReader;
    /** 関連者政治団体履歴(25)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory25ItemReader dumpSabunPartnerPoliOrgHistory25ItemReader;
    /** 関連者政治団体履歴(26)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory26ItemReader dumpSabunPartnerPoliOrgHistory26ItemReader;
    /** 関連者政治団体履歴(27)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory27ItemReader dumpSabunPartnerPoliOrgHistory27ItemReader;
    /** 関連者政治団体履歴(28)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory28ItemReader dumpSabunPartnerPoliOrgHistory28ItemReader;
    /** 関連者政治団体履歴(29)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory29ItemReader dumpSabunPartnerPoliOrgHistory29ItemReader;
    /** 関連者政治団体履歴(30)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory30ItemReader dumpSabunPartnerPoliOrgHistory30ItemReader;
    /** 関連者政治団体履歴(31)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory31ItemReader dumpSabunPartnerPoliOrgHistory31ItemReader;
    /** 関連者政治団体履歴(32)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory32ItemReader dumpSabunPartnerPoliOrgHistory32ItemReader;
    /** 関連者政治団体履歴(33)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory33ItemReader dumpSabunPartnerPoliOrgHistory33ItemReader;
    /** 関連者政治団体履歴(34)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory34ItemReader dumpSabunPartnerPoliOrgHistory34ItemReader;
    /** 関連者政治団体履歴(35)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory35ItemReader dumpSabunPartnerPoliOrgHistory35ItemReader;
    /** 関連者政治団体履歴(36)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory36ItemReader dumpSabunPartnerPoliOrgHistory36ItemReader;
    /** 関連者政治団体履歴(37)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory37ItemReader dumpSabunPartnerPoliOrgHistory37ItemReader;
    /** 関連者政治団体履歴(38)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory38ItemReader dumpSabunPartnerPoliOrgHistory38ItemReader;
    /** 関連者政治団体履歴(39)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory39ItemReader dumpSabunPartnerPoliOrgHistory39ItemReader;
    /** 関連者政治団体履歴(40)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory40ItemReader dumpSabunPartnerPoliOrgHistory40ItemReader;
    /** 関連者政治団体履歴(41)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory41ItemReader dumpSabunPartnerPoliOrgHistory41ItemReader;
    /** 関連者政治団体履歴(42)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory42ItemReader dumpSabunPartnerPoliOrgHistory42ItemReader;
    /** 関連者政治団体履歴(43)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory43ItemReader dumpSabunPartnerPoliOrgHistory43ItemReader;
    /** 関連者政治団体履歴(44)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory44ItemReader dumpSabunPartnerPoliOrgHistory44ItemReader;
    /** 関連者政治団体履歴(45)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory45ItemReader dumpSabunPartnerPoliOrgHistory45ItemReader;
    /** 関連者政治団体履歴(46)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory46ItemReader dumpSabunPartnerPoliOrgHistory46ItemReader;
    /** 関連者政治団体履歴(47)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory47ItemReader dumpSabunPartnerPoliOrgHistory47ItemReader;
    /** 関連者政治団体履歴(99)CsvItemReader */
    @Autowired
    private DumpSabunPartnerPoliOrgHistory99ItemReader dumpSabunPartnerPoliOrgHistory99ItemReader;
    /** 関連者政治団体履歴(01)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory01ItemWriter dumpPartnerPoliOrgHistory01ItemWriter;
    /** 関連者政治団体履歴(02)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory02ItemWriter dumpPartnerPoliOrgHistory02ItemWriter;
    /** 関連者政治団体履歴(03)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory03ItemWriter dumpPartnerPoliOrgHistory03ItemWriter;
    /** 関連者政治団体履歴(04)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory04ItemWriter dumpPartnerPoliOrgHistory04ItemWriter;
    /** 関連者政治団体履歴(05)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory05ItemWriter dumpPartnerPoliOrgHistory05ItemWriter;
    /** 関連者政治団体履歴(06)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory06ItemWriter dumpPartnerPoliOrgHistory06ItemWriter;
    /** 関連者政治団体履歴(07)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory07ItemWriter dumpPartnerPoliOrgHistory07ItemWriter;
    /** 関連者政治団体履歴(08)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory08ItemWriter dumpPartnerPoliOrgHistory08ItemWriter;
    /** 関連者政治団体履歴(09)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory09ItemWriter dumpPartnerPoliOrgHistory09ItemWriter;
    /** 関連者政治団体履歴(10)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory10ItemWriter dumpPartnerPoliOrgHistory10ItemWriter;
    /** 関連者政治団体履歴(11)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory11ItemWriter dumpPartnerPoliOrgHistory11ItemWriter;
    /** 関連者政治団体履歴(12)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory12ItemWriter dumpPartnerPoliOrgHistory12ItemWriter;
    /** 関連者政治団体履歴(13)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory13ItemWriter dumpPartnerPoliOrgHistory13ItemWriter;
    /** 関連者政治団体履歴(14)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory14ItemWriter dumpPartnerPoliOrgHistory14ItemWriter;
    /** 関連者政治団体履歴(15)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory15ItemWriter dumpPartnerPoliOrgHistory15ItemWriter;
    /** 関連者政治団体履歴(16)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory16ItemWriter dumpPartnerPoliOrgHistory16ItemWriter;
    /** 関連者政治団体履歴(17)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory17ItemWriter dumpPartnerPoliOrgHistory17ItemWriter;
    /** 関連者政治団体履歴(18)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory18ItemWriter dumpPartnerPoliOrgHistory18ItemWriter;
    /** 関連者政治団体履歴(19)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory19ItemWriter dumpPartnerPoliOrgHistory19ItemWriter;
    /** 関連者政治団体履歴(20)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory20ItemWriter dumpPartnerPoliOrgHistory20ItemWriter;
    /** 関連者政治団体履歴(21)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory21ItemWriter dumpPartnerPoliOrgHistory21ItemWriter;
    /** 関連者政治団体履歴(22)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory22ItemWriter dumpPartnerPoliOrgHistory22ItemWriter;
    /** 関連者政治団体履歴(23)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory23ItemWriter dumpPartnerPoliOrgHistory23ItemWriter;
    /** 関連者政治団体履歴(24)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory24ItemWriter dumpPartnerPoliOrgHistory24ItemWriter;
    /** 関連者政治団体履歴(25)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory25ItemWriter dumpPartnerPoliOrgHistory25ItemWriter;
    /** 関連者政治団体履歴(26)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory26ItemWriter dumpPartnerPoliOrgHistory26ItemWriter;
    /** 関連者政治団体履歴(27)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory27ItemWriter dumpPartnerPoliOrgHistory27ItemWriter;
    /** 関連者政治団体履歴(28)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory28ItemWriter dumpPartnerPoliOrgHistory28ItemWriter;
    /** 関連者政治団体履歴(29)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory29ItemWriter dumpPartnerPoliOrgHistory29ItemWriter;
    /** 関連者政治団体履歴(30)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory30ItemWriter dumpPartnerPoliOrgHistory30ItemWriter;
    /** 関連者政治団体履歴(31)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory31ItemWriter dumpPartnerPoliOrgHistory31ItemWriter;
    /** 関連者政治団体履歴(32)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory32ItemWriter dumpPartnerPoliOrgHistory32ItemWriter;
    /** 関連者政治団体履歴(33)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory33ItemWriter dumpPartnerPoliOrgHistory33ItemWriter;
    /** 関連者政治団体履歴(34)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory34ItemWriter dumpPartnerPoliOrgHistory34ItemWriter;
    /** 関連者政治団体履歴(35)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory35ItemWriter dumpPartnerPoliOrgHistory35ItemWriter;
    /** 関連者政治団体履歴(36)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory36ItemWriter dumpPartnerPoliOrgHistory36ItemWriter;
    /** 関連者政治団体履歴(37)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory37ItemWriter dumpPartnerPoliOrgHistory37ItemWriter;
    /** 関連者政治団体履歴(38)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory38ItemWriter dumpPartnerPoliOrgHistory38ItemWriter;
    /** 関連者政治団体履歴(39)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory39ItemWriter dumpPartnerPoliOrgHistory39ItemWriter;
    /** 関連者政治団体履歴(40)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory40ItemWriter dumpPartnerPoliOrgHistory40ItemWriter;
    /** 関連者政治団体履歴(41)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory41ItemWriter dumpPartnerPoliOrgHistory41ItemWriter;
    /** 関連者政治団体履歴(42)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory42ItemWriter dumpPartnerPoliOrgHistory42ItemWriter;
    /** 関連者政治団体履歴(43)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory43ItemWriter dumpPartnerPoliOrgHistory43ItemWriter;
    /** 関連者政治団体履歴(44)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory44ItemWriter dumpPartnerPoliOrgHistory44ItemWriter;
    /** 関連者政治団体履歴(45)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory45ItemWriter dumpPartnerPoliOrgHistory45ItemWriter;
    /** 関連者政治団体履歴(46)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory46ItemWriter dumpPartnerPoliOrgHistory46ItemWriter;
    /** 関連者政治団体履歴(47)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory47ItemWriter dumpPartnerPoliOrgHistory47ItemWriter;
    /** 関連者政治団体履歴(99)ItemWriter */
    @Autowired
    private DumpPartnerPoliOrgHistory99ItemWriter dumpPartnerPoliOrgHistory99ItemWriter;

    /**
     * Jobを返却する
     *
     * @param jobRepository ジョブレポジトリ
     * @param step          このConfigureで設定したステップ
     * @return Job
     */
    @Bean(JOB_NAME)
    protected Job getJob(final JobRepository jobRepository, @Qualifier(STEP_OUTPUT01) final Step stepOutput01,
            @Qualifier(STEP_OUTPUT02) final Step stepOutput02, @Qualifier(STEP_OUTPUT03) final Step stepOutput03,
            @Qualifier(STEP_OUTPUT04) final Step stepOutput04, @Qualifier(STEP_OUTPUT05) final Step stepOutput05,
            @Qualifier(STEP_OUTPUT06) final Step stepOutput06, @Qualifier(STEP_OUTPUT07) final Step stepOutput07,
            @Qualifier(STEP_OUTPUT08) final Step stepOutput08, @Qualifier(STEP_OUTPUT09) final Step stepOutput09,
            @Qualifier(STEP_OUTPUT10) final Step stepOutput10, @Qualifier(STEP_OUTPUT11) final Step stepOutput11,
            @Qualifier(STEP_OUTPUT12) final Step stepOutput12, @Qualifier(STEP_OUTPUT13) final Step stepOutput13,
            @Qualifier(STEP_OUTPUT14) final Step stepOutput14, @Qualifier(STEP_OUTPUT15) final Step stepOutput15,
            @Qualifier(STEP_OUTPUT16) final Step stepOutput16, @Qualifier(STEP_OUTPUT17) final Step stepOutput17,
            @Qualifier(STEP_OUTPUT18) final Step stepOutput18, @Qualifier(STEP_OUTPUT19) final Step stepOutput19,
            @Qualifier(STEP_OUTPUT20) final Step stepOutput20, @Qualifier(STEP_OUTPUT21) final Step stepOutput21,
            @Qualifier(STEP_OUTPUT22) final Step stepOutput22, @Qualifier(STEP_OUTPUT23) final Step stepOutput23,
            @Qualifier(STEP_OUTPUT24) final Step stepOutput24, @Qualifier(STEP_OUTPUT25) final Step stepOutput25,
            @Qualifier(STEP_OUTPUT26) final Step stepOutput26, @Qualifier(STEP_OUTPUT27) final Step stepOutput27,
            @Qualifier(STEP_OUTPUT28) final Step stepOutput28, @Qualifier(STEP_OUTPUT29) final Step stepOutput29,
            @Qualifier(STEP_OUTPUT30) final Step stepOutput30, @Qualifier(STEP_OUTPUT31) final Step stepOutput31,
            @Qualifier(STEP_OUTPUT32) final Step stepOutput32, @Qualifier(STEP_OUTPUT33) final Step stepOutput33,
            @Qualifier(STEP_OUTPUT34) final Step stepOutput34, @Qualifier(STEP_OUTPUT35) final Step stepOutput35,
            @Qualifier(STEP_OUTPUT36) final Step stepOutput36, @Qualifier(STEP_OUTPUT37) final Step stepOutput37,
            @Qualifier(STEP_OUTPUT38) final Step stepOutput38, @Qualifier(STEP_OUTPUT39) final Step stepOutput39,
            @Qualifier(STEP_OUTPUT40) final Step stepOutput40, @Qualifier(STEP_OUTPUT41) final Step stepOutput41,
            @Qualifier(STEP_OUTPUT42) final Step stepOutput42, @Qualifier(STEP_OUTPUT43) final Step stepOutput43,
            @Qualifier(STEP_OUTPUT44) final Step stepOutput44, @Qualifier(STEP_OUTPUT45) final Step stepOutput45,
            @Qualifier(STEP_OUTPUT46) final Step stepOutput46, @Qualifier(STEP_OUTPUT47) final Step stepOutput47,
            @Qualifier(STEP_OUTPUT99) final Step stepOutput99, @Qualifier(STEP_COMPRESS) final Step stepCompress) {

        return new JobBuilder(JOB_NAME, jobRepository).incrementer(new RunIdIncrementer()).flow(stepOutput01)
                .next(stepOutput02).next(stepOutput03).next(stepOutput04).next(stepOutput05).next(stepOutput06)
                .next(stepOutput07).next(stepOutput08).next(stepOutput09).next(stepOutput10).next(stepOutput11)
                .next(stepOutput12).next(stepOutput13).next(stepOutput14).next(stepOutput15).next(stepOutput16)
                .next(stepOutput17).next(stepOutput18).next(stepOutput19).next(stepOutput20).next(stepOutput21)
                .next(stepOutput22).next(stepOutput23).next(stepOutput24).next(stepOutput25).next(stepOutput26)
                .next(stepOutput27).next(stepOutput28).next(stepOutput29).next(stepOutput30).next(stepOutput31)
                .next(stepOutput32).next(stepOutput33).next(stepOutput34).next(stepOutput35).next(stepOutput36)
                .next(stepOutput37).next(stepOutput38).next(stepOutput39).next(stepOutput40).next(stepOutput41)
                .next(stepOutput42).next(stepOutput43).next(stepOutput44).next(stepOutput45).next(stepOutput46)
                .next(stepOutput47).next(stepOutput99).next(stepCompress).end().build();
    }

    /**
     * Step01を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT01)
    protected Step getStep01(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT01, jobRepository)
                .<PartnerPoliOrgHistory01Entity, PartnerPoliOrgHistory01Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory01ItemReader).writer(dumpPartnerPoliOrgHistory01ItemWriter).build();
    }

    /**
     * Step02を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT02)
    protected Step getStep02(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT02, jobRepository)
                .<PartnerPoliOrgHistory02Entity, PartnerPoliOrgHistory02Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory02ItemReader).writer(dumpPartnerPoliOrgHistory02ItemWriter).build();
    }

    /**
     * Step03を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT03)
    protected Step getStep03(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT03, jobRepository)
                .<PartnerPoliOrgHistory03Entity, PartnerPoliOrgHistory03Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory03ItemReader).writer(dumpPartnerPoliOrgHistory03ItemWriter).build();
    }

    /**
     * Step04を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT04)
    protected Step getStep04(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT04, jobRepository)
                .<PartnerPoliOrgHistory04Entity, PartnerPoliOrgHistory04Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory04ItemReader).writer(dumpPartnerPoliOrgHistory04ItemWriter).build();
    }

    /**
     * Step05を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT05)
    protected Step getStep05(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT05, jobRepository)
                .<PartnerPoliOrgHistory05Entity, PartnerPoliOrgHistory05Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory05ItemReader).writer(dumpPartnerPoliOrgHistory05ItemWriter).build();
    }

    /**
     * Step06を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT06)
    protected Step getStep06(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT06, jobRepository)
                .<PartnerPoliOrgHistory06Entity, PartnerPoliOrgHistory06Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory06ItemReader).writer(dumpPartnerPoliOrgHistory06ItemWriter).build();
    }

    /**
     * Step07を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT07)
    protected Step getStep07(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT07, jobRepository)
                .<PartnerPoliOrgHistory07Entity, PartnerPoliOrgHistory07Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory07ItemReader).writer(dumpPartnerPoliOrgHistory07ItemWriter).build();
    }

    /**
     * Step08を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT08)
    protected Step getStep08(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT01, jobRepository)
                .<PartnerPoliOrgHistory08Entity, PartnerPoliOrgHistory08Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory08ItemReader).writer(dumpPartnerPoliOrgHistory08ItemWriter).build();
    }

    /**
     * Step09を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT09)
    protected Step getStep09(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT09, jobRepository)
                .<PartnerPoliOrgHistory09Entity, PartnerPoliOrgHistory09Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory09ItemReader).writer(dumpPartnerPoliOrgHistory09ItemWriter).build();
    }

    /**
     * Step10を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT10)
    protected Step getStep10(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT10, jobRepository)
                .<PartnerPoliOrgHistory10Entity, PartnerPoliOrgHistory10Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory10ItemReader).writer(dumpPartnerPoliOrgHistory10ItemWriter).build();
    }

    /**
     * Step11を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT11)
    protected Step getStep11(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT11, jobRepository)
                .<PartnerPoliOrgHistory11Entity, PartnerPoliOrgHistory11Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory11ItemReader).writer(dumpPartnerPoliOrgHistory11ItemWriter).build();
    }

    /**
     * Step12を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT12)
    protected Step getStep12(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT12, jobRepository)
                .<PartnerPoliOrgHistory12Entity, PartnerPoliOrgHistory12Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory12ItemReader).writer(dumpPartnerPoliOrgHistory12ItemWriter).build();
    }

    /**
     * Step13を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT13)
    protected Step getStep13(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT13, jobRepository)
                .<PartnerPoliOrgHistory13Entity, PartnerPoliOrgHistory13Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory13ItemReader).writer(dumpPartnerPoliOrgHistory13ItemWriter).build();
    }

    /**
     * Step14を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT14)
    protected Step getStep14(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT14, jobRepository)
                .<PartnerPoliOrgHistory14Entity, PartnerPoliOrgHistory14Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory14ItemReader).writer(dumpPartnerPoliOrgHistory14ItemWriter).build();
    }

    /**
     * Step15を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT15)
    protected Step getStep15(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT15, jobRepository)
                .<PartnerPoliOrgHistory15Entity, PartnerPoliOrgHistory15Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory15ItemReader).writer(dumpPartnerPoliOrgHistory15ItemWriter).build();
    }

    /**
     * Step16を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT16)
    protected Step getStep16(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT16, jobRepository)
                .<PartnerPoliOrgHistory16Entity, PartnerPoliOrgHistory16Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory16ItemReader).writer(dumpPartnerPoliOrgHistory16ItemWriter).build();
    }

    /**
     * Step17を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT17)
    protected Step getStep17(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT17, jobRepository)
                .<PartnerPoliOrgHistory17Entity, PartnerPoliOrgHistory17Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory17ItemReader).writer(dumpPartnerPoliOrgHistory17ItemWriter).build();
    }

    /**
     * Step18を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT18)
    protected Step getStep18(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT18, jobRepository)
                .<PartnerPoliOrgHistory18Entity, PartnerPoliOrgHistory18Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory18ItemReader).writer(dumpPartnerPoliOrgHistory18ItemWriter).build();
    }

    /**
     * Step19を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT19)
    protected Step getStep19(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT19, jobRepository)
                .<PartnerPoliOrgHistory19Entity, PartnerPoliOrgHistory19Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory19ItemReader).writer(dumpPartnerPoliOrgHistory19ItemWriter).build();
    }

    /**
     * Step20を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT20)
    protected Step getStep20(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT20, jobRepository)
                .<PartnerPoliOrgHistory20Entity, PartnerPoliOrgHistory20Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory20ItemReader).writer(dumpPartnerPoliOrgHistory20ItemWriter).build();
    }

    /**
     * Step21を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT21)
    protected Step getStep21(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT21, jobRepository)
                .<PartnerPoliOrgHistory21Entity, PartnerPoliOrgHistory21Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory21ItemReader).writer(dumpPartnerPoliOrgHistory21ItemWriter).build();
    }

    /**
     * Step22を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT22)
    protected Step getStep22(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT22, jobRepository)
                .<PartnerPoliOrgHistory22Entity, PartnerPoliOrgHistory22Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory22ItemReader).writer(dumpPartnerPoliOrgHistory22ItemWriter).build();
    }

    /**
     * Step23を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT23)
    protected Step getStep23(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT23, jobRepository)
                .<PartnerPoliOrgHistory23Entity, PartnerPoliOrgHistory23Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory23ItemReader).writer(dumpPartnerPoliOrgHistory23ItemWriter).build();
    }

    /**
     * Step24を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT24)
    protected Step getStep24(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT24, jobRepository)
                .<PartnerPoliOrgHistory24Entity, PartnerPoliOrgHistory24Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory24ItemReader).writer(dumpPartnerPoliOrgHistory24ItemWriter).build();
    }

    /**
     * Step25を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT25)
    protected Step getStep25(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT25, jobRepository)
                .<PartnerPoliOrgHistory25Entity, PartnerPoliOrgHistory25Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory25ItemReader).writer(dumpPartnerPoliOrgHistory25ItemWriter).build();
    }

    /**
     * Step26を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT26)
    protected Step getStep26(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT26, jobRepository)
                .<PartnerPoliOrgHistory26Entity, PartnerPoliOrgHistory26Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory26ItemReader).writer(dumpPartnerPoliOrgHistory26ItemWriter).build();
    }

    /**
     * Step27を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT27)
    protected Step getStep27(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT27, jobRepository)
                .<PartnerPoliOrgHistory27Entity, PartnerPoliOrgHistory27Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory27ItemReader).writer(dumpPartnerPoliOrgHistory27ItemWriter).build();
    }

    /**
     * Step28を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT28)
    protected Step getStep28(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT28, jobRepository)
                .<PartnerPoliOrgHistory28Entity, PartnerPoliOrgHistory28Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory28ItemReader).writer(dumpPartnerPoliOrgHistory28ItemWriter).build();
    }

    /**
     * Step29を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT29)
    protected Step getStep29(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT29, jobRepository)
                .<PartnerPoliOrgHistory29Entity, PartnerPoliOrgHistory29Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory29ItemReader).writer(dumpPartnerPoliOrgHistory29ItemWriter).build();
    }

    /**
     * Step30を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT30)
    protected Step getStep30(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT30, jobRepository)
                .<PartnerPoliOrgHistory30Entity, PartnerPoliOrgHistory30Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory30ItemReader).writer(dumpPartnerPoliOrgHistory30ItemWriter).build();
    }

    /**
     * Step31を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT31)
    protected Step getStep31(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT31, jobRepository)
                .<PartnerPoliOrgHistory31Entity, PartnerPoliOrgHistory31Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory31ItemReader).writer(dumpPartnerPoliOrgHistory31ItemWriter).build();
    }

    /**
     * Step32を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT32)
    protected Step getStep32(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT32, jobRepository)
                .<PartnerPoliOrgHistory32Entity, PartnerPoliOrgHistory32Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory32ItemReader).writer(dumpPartnerPoliOrgHistory32ItemWriter).build();
    }

    /**
     * Step33を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT33)
    protected Step getStep33(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT33, jobRepository)
                .<PartnerPoliOrgHistory33Entity, PartnerPoliOrgHistory33Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory33ItemReader).writer(dumpPartnerPoliOrgHistory33ItemWriter).build();
    }

    /**
     * Step34を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT34)
    protected Step getStep34(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT34, jobRepository)
                .<PartnerPoliOrgHistory34Entity, PartnerPoliOrgHistory34Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory34ItemReader).writer(dumpPartnerPoliOrgHistory34ItemWriter).build();
    }

    /**
     * Step35を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT35)
    protected Step getStep35(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT35, jobRepository)
                .<PartnerPoliOrgHistory35Entity, PartnerPoliOrgHistory35Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory35ItemReader).writer(dumpPartnerPoliOrgHistory35ItemWriter).build();
    }

    /**
     * Step36を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT36)
    protected Step getStep36(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT36, jobRepository)
                .<PartnerPoliOrgHistory36Entity, PartnerPoliOrgHistory36Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory36ItemReader).writer(dumpPartnerPoliOrgHistory36ItemWriter).build();
    }

    /**
     * Step37を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT37)
    protected Step getStep37(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT37, jobRepository)
                .<PartnerPoliOrgHistory37Entity, PartnerPoliOrgHistory37Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory37ItemReader).writer(dumpPartnerPoliOrgHistory37ItemWriter).build();
    }

    /**
     * Step38を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT38)
    protected Step getStep38(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT38, jobRepository)
                .<PartnerPoliOrgHistory38Entity, PartnerPoliOrgHistory38Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory38ItemReader).writer(dumpPartnerPoliOrgHistory38ItemWriter).build();
    }

    /**
     * Step39を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT39)
    protected Step getStep39(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT39, jobRepository)
                .<PartnerPoliOrgHistory39Entity, PartnerPoliOrgHistory39Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory39ItemReader).writer(dumpPartnerPoliOrgHistory39ItemWriter).build();
    }

    /**
     * Step40を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT40)
    protected Step getStep40(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT40, jobRepository)
                .<PartnerPoliOrgHistory40Entity, PartnerPoliOrgHistory40Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory40ItemReader).writer(dumpPartnerPoliOrgHistory40ItemWriter).build();
    }

    /**
     * Step41を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT41)
    protected Step getStep41(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT41, jobRepository)
                .<PartnerPoliOrgHistory41Entity, PartnerPoliOrgHistory41Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory41ItemReader).writer(dumpPartnerPoliOrgHistory41ItemWriter).build();
    }

    /**
     * Step42を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT42)
    protected Step getStep42(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT42, jobRepository)
                .<PartnerPoliOrgHistory42Entity, PartnerPoliOrgHistory42Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory42ItemReader).writer(dumpPartnerPoliOrgHistory42ItemWriter).build();
    }

    /**
     * Step43を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT43)
    protected Step getStep43(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT43, jobRepository)
                .<PartnerPoliOrgHistory43Entity, PartnerPoliOrgHistory43Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory43ItemReader).writer(dumpPartnerPoliOrgHistory43ItemWriter).build();
    }

    /**
     * Step44を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT44)
    protected Step getStep44(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT44, jobRepository)
                .<PartnerPoliOrgHistory44Entity, PartnerPoliOrgHistory44Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory44ItemReader).writer(dumpPartnerPoliOrgHistory44ItemWriter).build();
    }

    /**
     * Step45を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT45)
    protected Step getStep45(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT45, jobRepository)
                .<PartnerPoliOrgHistory45Entity, PartnerPoliOrgHistory45Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory45ItemReader).writer(dumpPartnerPoliOrgHistory45ItemWriter).build();
    }

    /**
     * Step46を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT46)
    protected Step getStep46(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT46, jobRepository)
                .<PartnerPoliOrgHistory46Entity, PartnerPoliOrgHistory46Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory46ItemReader).writer(dumpPartnerPoliOrgHistory46ItemWriter).build();
    }

    /**
     * Step47を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT47)
    protected Step getStep47(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT47, jobRepository)
                .<PartnerPoliOrgHistory47Entity, PartnerPoliOrgHistory47Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory47ItemReader).writer(dumpPartnerPoliOrgHistory47ItemWriter).build();
    }

    /**
     * Step99を返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_OUTPUT99)
    protected Step getStep99(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
        return new StepBuilder(STEP_OUTPUT99, jobRepository)
                .<PartnerPoliOrgHistory99Entity, PartnerPoliOrgHistory99Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpSabunPartnerPoliOrgHistory99ItemReader).writer(dumpPartnerPoliOrgHistory99ItemWriter).build();
    }

    /**
     * StepCleanを返却する
     *
     * @param jobRepository      jobRepository
     * @param transactionManager transactionManager
     * @return step
     */
    @Bean(STEP_COMPRESS)
    protected Step getStepCompress(final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager) {

        return new StepBuilder(STEP_COMPRESS, jobRepository).tasklet(compressZipFileTasklet, transactionManager)
                .build();
    }

}
