package mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump.history; // NOPMD

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

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.zip.CompressZipFileTasklet;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory02Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory03Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory04Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory05Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory06Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory07Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory08Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory09Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory10Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory11Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory12Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory13Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory14Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory15Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory16Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory17Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory18Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory19Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory20Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory21Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory22Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory23Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory24Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory25Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory26Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory27Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory28Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory29Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory30Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory31Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory32Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory33Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory34Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory35Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory36Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory37Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory38Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory39Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory40Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory41Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory42Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory43Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory44Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory45Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory46Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory47Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory99Entity;

/**
 * 関連者企業・団体履歴csv作成BatchConfiguration
 */
@Configuration
public class DumpPartnerCorpHistoryBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "dumpPartnerCorpHistory";

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

    /** 関連者企業・団体履歴(01)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory01ItemReader dumpPartnerCorpHistory01ItemReader;
    /** 関連者企業・団体履歴(02)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory02ItemReader dumpPartnerCorpHistory02ItemReader;
    /** 関連者企業・団体履歴(03)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory03ItemReader dumpPartnerCorpHistory03ItemReader;
    /** 関連者企業・団体履歴(04)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory04ItemReader dumpPartnerCorpHistory04ItemReader;
    /** 関連者企業・団体履歴(05)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory05ItemReader dumpPartnerCorpHistory05ItemReader;
    /** 関連者企業・団体履歴(06)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory06ItemReader dumpPartnerCorpHistory06ItemReader;
    /** 関連者企業・団体履歴(07)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory07ItemReader dumpPartnerCorpHistory07ItemReader;
    /** 関連者企業・団体履歴(08)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory08ItemReader dumpPartnerCorpHistory08ItemReader;
    /** 関連者企業・団体履歴(09)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory09ItemReader dumpPartnerCorpHistory09ItemReader;
    /** 関連者企業・団体履歴(10)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory10ItemReader dumpPartnerCorpHistory10ItemReader;
    /** 関連者企業・団体履歴(11)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory11ItemReader dumpPartnerCorpHistory11ItemReader;
    /** 関連者企業・団体履歴(12)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory12ItemReader dumpPartnerCorpHistory12ItemReader;
    /** 関連者企業・団体履歴(13)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory13ItemReader dumpPartnerCorpHistory13ItemReader;
    /** 関連者企業・団体履歴(14)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory14ItemReader dumpPartnerCorpHistory14ItemReader;
    /** 関連者企業・団体履歴(15)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory15ItemReader dumpPartnerCorpHistory15ItemReader;
    /** 関連者企業・団体履歴(16)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory16ItemReader dumpPartnerCorpHistory16ItemReader;
    /** 関連者企業・団体履歴(17)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory17ItemReader dumpPartnerCorpHistory17ItemReader;
    /** 関連者企業・団体履歴(18)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory18ItemReader dumpPartnerCorpHistory18ItemReader;
    /** 関連者企業・団体履歴(19)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory19ItemReader dumpPartnerCorpHistory19ItemReader;
    /** 関連者企業・団体履歴(20)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory20ItemReader dumpPartnerCorpHistory20ItemReader;
    /** 関連者企業・団体履歴(21)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory21ItemReader dumpPartnerCorpHistory21ItemReader;
    /** 関連者企業・団体履歴(22)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory22ItemReader dumpPartnerCorpHistory22ItemReader;
    /** 関連者企業・団体履歴(23)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory23ItemReader dumpPartnerCorpHistory23ItemReader;
    /** 関連者企業・団体履歴(24)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory24ItemReader dumpPartnerCorpHistory24ItemReader;
    /** 関連者企業・団体履歴(25)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory25ItemReader dumpPartnerCorpHistory25ItemReader;
    /** 関連者企業・団体履歴(26)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory26ItemReader dumpPartnerCorpHistory26ItemReader;
    /** 関連者企業・団体履歴(27)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory27ItemReader dumpPartnerCorpHistory27ItemReader;
    /** 関連者企業・団体履歴(28)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory28ItemReader dumpPartnerCorpHistory28ItemReader;
    /** 関連者企業・団体履歴(29)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory29ItemReader dumpPartnerCorpHistory29ItemReader;
    /** 関連者企業・団体履歴(30)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory30ItemReader dumpPartnerCorpHistory30ItemReader;
    /** 関連者企業・団体履歴(31)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory31ItemReader dumpPartnerCorpHistory31ItemReader;
    /** 関連者企業・団体履歴(32)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory32ItemReader dumpPartnerCorpHistory32ItemReader;
    /** 関連者企業・団体履歴(33)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory33ItemReader dumpPartnerCorpHistory33ItemReader;
    /** 関連者企業・団体履歴(34)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory34ItemReader dumpPartnerCorpHistory34ItemReader;
    /** 関連者企業・団体履歴(35)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory35ItemReader dumpPartnerCorpHistory35ItemReader;
    /** 関連者企業・団体履歴(36)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory36ItemReader dumpPartnerCorpHistory36ItemReader;
    /** 関連者企業・団体履歴(37)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory37ItemReader dumpPartnerCorpHistory37ItemReader;
    /** 関連者企業・団体履歴(38)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory38ItemReader dumpPartnerCorpHistory38ItemReader;
    /** 関連者企業・団体履歴(39)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory39ItemReader dumpPartnerCorpHistory39ItemReader;
    /** 関連者企業・団体履歴(40)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory40ItemReader dumpPartnerCorpHistory40ItemReader;
    /** 関連者企業・団体履歴(41)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory41ItemReader dumpPartnerCorpHistory41ItemReader;
    /** 関連者企業・団体履歴(42)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory42ItemReader dumpPartnerCorpHistory42ItemReader;
    /** 関連者企業・団体履歴(43)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory43ItemReader dumpPartnerCorpHistory43ItemReader;
    /** 関連者企業・団体履歴(44)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory44ItemReader dumpPartnerCorpHistory44ItemReader;
    /** 関連者企業・団体履歴(45)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory45ItemReader dumpPartnerCorpHistory45ItemReader;
    /** 関連者企業・団体履歴(46)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory46ItemReader dumpPartnerCorpHistory46ItemReader;
    /** 関連者企業・団体履歴(47)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory47ItemReader dumpPartnerCorpHistory47ItemReader;
    /** 関連者企業・団体履歴(99)CsvItemReader */
    @Autowired
    private DumpPartnerCorpHistory99ItemReader dumpPartnerCorpHistory99ItemReader;
    /** 関連者企業・団体履歴(01)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory01ItemWriter dumpPartnerCorpHistory01ItemWriter;
    /** 関連者企業・団体履歴(02)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory02ItemWriter dumpPartnerCorpHistory02ItemWriter;
    /** 関連者企業・団体履歴(03)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory03ItemWriter dumpPartnerCorpHistory03ItemWriter;
    /** 関連者企業・団体履歴(04)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory04ItemWriter dumpPartnerCorpHistory04ItemWriter;
    /** 関連者企業・団体履歴(05)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory05ItemWriter dumpPartnerCorpHistory05ItemWriter;
    /** 関連者企業・団体履歴(06)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory06ItemWriter dumpPartnerCorpHistory06ItemWriter;
    /** 関連者企業・団体履歴(07)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory07ItemWriter dumpPartnerCorpHistory07ItemWriter;
    /** 関連者企業・団体履歴(08)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory08ItemWriter dumpPartnerCorpHistory08ItemWriter;
    /** 関連者企業・団体履歴(09)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory09ItemWriter dumpPartnerCorpHistory09ItemWriter;
    /** 関連者企業・団体履歴(10)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory10ItemWriter dumpPartnerCorpHistory10ItemWriter;
    /** 関連者企業・団体履歴(11)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory11ItemWriter dumpPartnerCorpHistory11ItemWriter;
    /** 関連者企業・団体履歴(12)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory12ItemWriter dumpPartnerCorpHistory12ItemWriter;
    /** 関連者企業・団体履歴(13)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory13ItemWriter dumpPartnerCorpHistory13ItemWriter;
    /** 関連者企業・団体履歴(14)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory14ItemWriter dumpPartnerCorpHistory14ItemWriter;
    /** 関連者企業・団体履歴(15)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory15ItemWriter dumpPartnerCorpHistory15ItemWriter;
    /** 関連者企業・団体履歴(16)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory16ItemWriter dumpPartnerCorpHistory16ItemWriter;
    /** 関連者企業・団体履歴(17)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory17ItemWriter dumpPartnerCorpHistory17ItemWriter;
    /** 関連者企業・団体履歴(18)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory18ItemWriter dumpPartnerCorpHistory18ItemWriter;
    /** 関連者企業・団体履歴(19)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory19ItemWriter dumpPartnerCorpHistory19ItemWriter;
    /** 関連者企業・団体履歴(20)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory20ItemWriter dumpPartnerCorpHistory20ItemWriter;
    /** 関連者企業・団体履歴(21)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory21ItemWriter dumpPartnerCorpHistory21ItemWriter;
    /** 関連者企業・団体履歴(22)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory22ItemWriter dumpPartnerCorpHistory22ItemWriter;
    /** 関連者企業・団体履歴(23)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory23ItemWriter dumpPartnerCorpHistory23ItemWriter;
    /** 関連者企業・団体履歴(24)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory24ItemWriter dumpPartnerCorpHistory24ItemWriter;
    /** 関連者企業・団体履歴(25)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory25ItemWriter dumpPartnerCorpHistory25ItemWriter;
    /** 関連者企業・団体履歴(26)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory26ItemWriter dumpPartnerCorpHistory26ItemWriter;
    /** 関連者企業・団体履歴(27)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory27ItemWriter dumpPartnerCorpHistory27ItemWriter;
    /** 関連者企業・団体履歴(28)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory28ItemWriter dumpPartnerCorpHistory28ItemWriter;
    /** 関連者企業・団体履歴(29)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory29ItemWriter dumpPartnerCorpHistory29ItemWriter;
    /** 関連者企業・団体履歴(30)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory30ItemWriter dumpPartnerCorpHistory30ItemWriter;
    /** 関連者企業・団体履歴(31)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory31ItemWriter dumpPartnerCorpHistory31ItemWriter;
    /** 関連者企業・団体履歴(32)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory32ItemWriter dumpPartnerCorpHistory32ItemWriter;
    /** 関連者企業・団体履歴(33)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory33ItemWriter dumpPartnerCorpHistory33ItemWriter;
    /** 関連者企業・団体履歴(34)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory34ItemWriter dumpPartnerCorpHistory34ItemWriter;
    /** 関連者企業・団体履歴(35)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory35ItemWriter dumpPartnerCorpHistory35ItemWriter;
    /** 関連者企業・団体履歴(36)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory36ItemWriter dumpPartnerCorpHistory36ItemWriter;
    /** 関連者企業・団体履歴(37)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory37ItemWriter dumpPartnerCorpHistory37ItemWriter;
    /** 関連者企業・団体履歴(38)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory38ItemWriter dumpPartnerCorpHistory38ItemWriter;
    /** 関連者企業・団体履歴(39)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory39ItemWriter dumpPartnerCorpHistory39ItemWriter;
    /** 関連者企業・団体履歴(40)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory40ItemWriter dumpPartnerCorpHistory40ItemWriter;
    /** 関連者企業・団体履歴(41)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory41ItemWriter dumpPartnerCorpHistory41ItemWriter;
    /** 関連者企業・団体履歴(42)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory42ItemWriter dumpPartnerCorpHistory42ItemWriter;
    /** 関連者企業・団体履歴(43)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory43ItemWriter dumpPartnerCorpHistory43ItemWriter;
    /** 関連者企業・団体履歴(44)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory44ItemWriter dumpPartnerCorpHistory44ItemWriter;
    /** 関連者企業・団体履歴(45)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory45ItemWriter dumpPartnerCorpHistory45ItemWriter;
    /** 関連者企業・団体履歴(46)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory46ItemWriter dumpPartnerCorpHistory46ItemWriter;
    /** 関連者企業・団体履歴(47)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory47ItemWriter dumpPartnerCorpHistory47ItemWriter;
    /** 関連者企業・団体履歴(99)ItemWriter */
    @Autowired
    private DumpPartnerCorpHistory99ItemWriter dumpPartnerCorpHistory99ItemWriter;

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
                .<PartnerCorpHistory01Entity, PartnerCorpHistory01Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory01ItemReader).writer(dumpPartnerCorpHistory01ItemWriter).build();
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
                .<PartnerCorpHistory02Entity, PartnerCorpHistory02Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory02ItemReader).writer(dumpPartnerCorpHistory02ItemWriter).build();
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
                .<PartnerCorpHistory03Entity, PartnerCorpHistory03Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory03ItemReader).writer(dumpPartnerCorpHistory03ItemWriter).build();
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
                .<PartnerCorpHistory04Entity, PartnerCorpHistory04Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory04ItemReader).writer(dumpPartnerCorpHistory04ItemWriter).build();
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
                .<PartnerCorpHistory05Entity, PartnerCorpHistory05Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory05ItemReader).writer(dumpPartnerCorpHistory05ItemWriter).build();
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
                .<PartnerCorpHistory06Entity, PartnerCorpHistory06Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory06ItemReader).writer(dumpPartnerCorpHistory06ItemWriter).build();
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
                .<PartnerCorpHistory07Entity, PartnerCorpHistory07Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory07ItemReader).writer(dumpPartnerCorpHistory07ItemWriter).build();
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
                .<PartnerCorpHistory08Entity, PartnerCorpHistory08Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory08ItemReader).writer(dumpPartnerCorpHistory08ItemWriter).build();
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
                .<PartnerCorpHistory09Entity, PartnerCorpHistory09Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory09ItemReader).writer(dumpPartnerCorpHistory09ItemWriter).build();
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
                .<PartnerCorpHistory10Entity, PartnerCorpHistory10Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory10ItemReader).writer(dumpPartnerCorpHistory10ItemWriter).build();
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
                .<PartnerCorpHistory11Entity, PartnerCorpHistory11Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory11ItemReader).writer(dumpPartnerCorpHistory11ItemWriter).build();
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
                .<PartnerCorpHistory12Entity, PartnerCorpHistory12Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory12ItemReader).writer(dumpPartnerCorpHistory12ItemWriter).build();
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
                .<PartnerCorpHistory13Entity, PartnerCorpHistory13Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory13ItemReader).writer(dumpPartnerCorpHistory13ItemWriter).build();
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
                .<PartnerCorpHistory14Entity, PartnerCorpHistory14Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory14ItemReader).writer(dumpPartnerCorpHistory14ItemWriter).build();
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
                .<PartnerCorpHistory15Entity, PartnerCorpHistory15Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory15ItemReader).writer(dumpPartnerCorpHistory15ItemWriter).build();
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
                .<PartnerCorpHistory16Entity, PartnerCorpHistory16Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory16ItemReader).writer(dumpPartnerCorpHistory16ItemWriter).build();
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
                .<PartnerCorpHistory17Entity, PartnerCorpHistory17Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory17ItemReader).writer(dumpPartnerCorpHistory17ItemWriter).build();
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
                .<PartnerCorpHistory18Entity, PartnerCorpHistory18Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory18ItemReader).writer(dumpPartnerCorpHistory18ItemWriter).build();
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
                .<PartnerCorpHistory19Entity, PartnerCorpHistory19Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory19ItemReader).writer(dumpPartnerCorpHistory19ItemWriter).build();
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
                .<PartnerCorpHistory20Entity, PartnerCorpHistory20Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory20ItemReader).writer(dumpPartnerCorpHistory20ItemWriter).build();
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
                .<PartnerCorpHistory21Entity, PartnerCorpHistory21Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory21ItemReader).writer(dumpPartnerCorpHistory21ItemWriter).build();
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
                .<PartnerCorpHistory22Entity, PartnerCorpHistory22Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory22ItemReader).writer(dumpPartnerCorpHistory22ItemWriter).build();
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
                .<PartnerCorpHistory23Entity, PartnerCorpHistory23Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory23ItemReader).writer(dumpPartnerCorpHistory23ItemWriter).build();
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
                .<PartnerCorpHistory24Entity, PartnerCorpHistory24Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory24ItemReader).writer(dumpPartnerCorpHistory24ItemWriter).build();
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
                .<PartnerCorpHistory25Entity, PartnerCorpHistory25Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory25ItemReader).writer(dumpPartnerCorpHistory25ItemWriter).build();
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
                .<PartnerCorpHistory26Entity, PartnerCorpHistory26Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory26ItemReader).writer(dumpPartnerCorpHistory26ItemWriter).build();
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
                .<PartnerCorpHistory27Entity, PartnerCorpHistory27Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory27ItemReader).writer(dumpPartnerCorpHistory27ItemWriter).build();
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
                .<PartnerCorpHistory28Entity, PartnerCorpHistory28Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory28ItemReader).writer(dumpPartnerCorpHistory28ItemWriter).build();
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
                .<PartnerCorpHistory29Entity, PartnerCorpHistory29Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory29ItemReader).writer(dumpPartnerCorpHistory29ItemWriter).build();
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
                .<PartnerCorpHistory30Entity, PartnerCorpHistory30Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory30ItemReader).writer(dumpPartnerCorpHistory30ItemWriter).build();
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
                .<PartnerCorpHistory31Entity, PartnerCorpHistory31Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory31ItemReader).writer(dumpPartnerCorpHistory31ItemWriter).build();
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
                .<PartnerCorpHistory32Entity, PartnerCorpHistory32Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory32ItemReader).writer(dumpPartnerCorpHistory32ItemWriter).build();
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
                .<PartnerCorpHistory33Entity, PartnerCorpHistory33Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory33ItemReader).writer(dumpPartnerCorpHistory33ItemWriter).build();
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
                .<PartnerCorpHistory34Entity, PartnerCorpHistory34Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory34ItemReader).writer(dumpPartnerCorpHistory34ItemWriter).build();
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
                .<PartnerCorpHistory35Entity, PartnerCorpHistory35Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory35ItemReader).writer(dumpPartnerCorpHistory35ItemWriter).build();
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
                .<PartnerCorpHistory36Entity, PartnerCorpHistory36Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory36ItemReader).writer(dumpPartnerCorpHistory36ItemWriter).build();
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
                .<PartnerCorpHistory37Entity, PartnerCorpHistory37Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory37ItemReader).writer(dumpPartnerCorpHistory37ItemWriter).build();
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
                .<PartnerCorpHistory38Entity, PartnerCorpHistory38Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory38ItemReader).writer(dumpPartnerCorpHistory38ItemWriter).build();
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
                .<PartnerCorpHistory39Entity, PartnerCorpHistory39Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory39ItemReader).writer(dumpPartnerCorpHistory39ItemWriter).build();
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
                .<PartnerCorpHistory40Entity, PartnerCorpHistory40Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory40ItemReader).writer(dumpPartnerCorpHistory40ItemWriter).build();
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
                .<PartnerCorpHistory41Entity, PartnerCorpHistory41Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory41ItemReader).writer(dumpPartnerCorpHistory41ItemWriter).build();
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
                .<PartnerCorpHistory42Entity, PartnerCorpHistory42Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory42ItemReader).writer(dumpPartnerCorpHistory42ItemWriter).build();
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
                .<PartnerCorpHistory43Entity, PartnerCorpHistory43Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory43ItemReader).writer(dumpPartnerCorpHistory43ItemWriter).build();
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
                .<PartnerCorpHistory44Entity, PartnerCorpHistory44Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory44ItemReader).writer(dumpPartnerCorpHistory44ItemWriter).build();
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
                .<PartnerCorpHistory45Entity, PartnerCorpHistory45Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory45ItemReader).writer(dumpPartnerCorpHistory45ItemWriter).build();
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
                .<PartnerCorpHistory46Entity, PartnerCorpHistory46Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory46ItemReader).writer(dumpPartnerCorpHistory46ItemWriter).build();
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
                .<PartnerCorpHistory47Entity, PartnerCorpHistory47Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory47ItemReader).writer(dumpPartnerCorpHistory47ItemWriter).build();
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
                .<PartnerCorpHistory99Entity, PartnerCorpHistory99Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerCorpHistory99ItemReader).writer(dumpPartnerCorpHistory99ItemWriter).build();
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
