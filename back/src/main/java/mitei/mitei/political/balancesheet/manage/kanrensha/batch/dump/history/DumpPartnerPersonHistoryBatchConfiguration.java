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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory02Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory03Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory04Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory05Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory06Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory07Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory08Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory09Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory10Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory11Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory12Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory13Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory14Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory15Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory16Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory17Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory18Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory19Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory20Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory21Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory22Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory23Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory24Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory25Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory26Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory27Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory28Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory29Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory30Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory31Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory32Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory33Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory34Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory35Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory36Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory37Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory38Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory39Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory40Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory41Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory42Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory43Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory44Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory45Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory46Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory47Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory99Entity;

/**
 * 関連者個人履歴csv作成BatchConfiguration
 */
@Configuration
public class DumpPartnerPersonHistoryBatchConfiguration {

    /** 機能名 */
    private static final String FUNCTION_NAME = "dumpPartnerPersonHistory";

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
    private DumpPartnerPersonHistory01ItemReader dumpPartnerPersonHistory01ItemReader;
    /** 関連者企業・団体履歴(02)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory02ItemReader dumpPartnerPersonHistory02ItemReader;
    /** 関連者企業・団体履歴(03)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory03ItemReader dumpPartnerPersonHistory03ItemReader;
    /** 関連者企業・団体履歴(04)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory04ItemReader dumpPartnerPersonHistory04ItemReader;
    /** 関連者企業・団体履歴(05)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory05ItemReader dumpPartnerPersonHistory05ItemReader;
    /** 関連者企業・団体履歴(06)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory06ItemReader dumpPartnerPersonHistory06ItemReader;
    /** 関連者企業・団体履歴(07)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory07ItemReader dumpPartnerPersonHistory07ItemReader;
    /** 関連者企業・団体履歴(08)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory08ItemReader dumpPartnerPersonHistory08ItemReader;
    /** 関連者企業・団体履歴(09)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory09ItemReader dumpPartnerPersonHistory09ItemReader;
    /** 関連者企業・団体履歴(10)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory10ItemReader dumpPartnerPersonHistory10ItemReader;
    /** 関連者企業・団体履歴(11)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory11ItemReader dumpPartnerPersonHistory11ItemReader;
    /** 関連者企業・団体履歴(12)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory12ItemReader dumpPartnerPersonHistory12ItemReader;
    /** 関連者企業・団体履歴(13)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory13ItemReader dumpPartnerPersonHistory13ItemReader;
    /** 関連者企業・団体履歴(14)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory14ItemReader dumpPartnerPersonHistory14ItemReader;
    /** 関連者企業・団体履歴(15)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory15ItemReader dumpPartnerPersonHistory15ItemReader;
    /** 関連者企業・団体履歴(16)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory16ItemReader dumpPartnerPersonHistory16ItemReader;
    /** 関連者企業・団体履歴(17)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory17ItemReader dumpPartnerPersonHistory17ItemReader;
    /** 関連者企業・団体履歴(18)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory18ItemReader dumpPartnerPersonHistory18ItemReader;
    /** 関連者企業・団体履歴(19)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory19ItemReader dumpPartnerPersonHistory19ItemReader;
    /** 関連者企業・団体履歴(20)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory20ItemReader dumpPartnerPersonHistory20ItemReader;
    /** 関連者企業・団体履歴(21)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory21ItemReader dumpPartnerPersonHistory21ItemReader;
    /** 関連者企業・団体履歴(22)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory22ItemReader dumpPartnerPersonHistory22ItemReader;
    /** 関連者企業・団体履歴(23)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory23ItemReader dumpPartnerPersonHistory23ItemReader;
    /** 関連者企業・団体履歴(24)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory24ItemReader dumpPartnerPersonHistory24ItemReader;
    /** 関連者企業・団体履歴(25)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory25ItemReader dumpPartnerPersonHistory25ItemReader;
    /** 関連者企業・団体履歴(26)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory26ItemReader dumpPartnerPersonHistory26ItemReader;
    /** 関連者企業・団体履歴(27)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory27ItemReader dumpPartnerPersonHistory27ItemReader;
    /** 関連者企業・団体履歴(28)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory28ItemReader dumpPartnerPersonHistory28ItemReader;
    /** 関連者企業・団体履歴(29)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory29ItemReader dumpPartnerPersonHistory29ItemReader;
    /** 関連者企業・団体履歴(30)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory30ItemReader dumpPartnerPersonHistory30ItemReader;
    /** 関連者企業・団体履歴(31)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory31ItemReader dumpPartnerPersonHistory31ItemReader;
    /** 関連者企業・団体履歴(32)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory32ItemReader dumpPartnerPersonHistory32ItemReader;
    /** 関連者企業・団体履歴(33)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory33ItemReader dumpPartnerPersonHistory33ItemReader;
    /** 関連者企業・団体履歴(34)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory34ItemReader dumpPartnerPersonHistory34ItemReader;
    /** 関連者企業・団体履歴(35)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory35ItemReader dumpPartnerPersonHistory35ItemReader;
    /** 関連者企業・団体履歴(36)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory36ItemReader dumpPartnerPersonHistory36ItemReader;
    /** 関連者企業・団体履歴(37)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory37ItemReader dumpPartnerPersonHistory37ItemReader;
    /** 関連者企業・団体履歴(38)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory38ItemReader dumpPartnerPersonHistory38ItemReader;
    /** 関連者企業・団体履歴(39)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory39ItemReader dumpPartnerPersonHistory39ItemReader;
    /** 関連者企業・団体履歴(40)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory40ItemReader dumpPartnerPersonHistory40ItemReader;
    /** 関連者企業・団体履歴(41)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory41ItemReader dumpPartnerPersonHistory41ItemReader;
    /** 関連者企業・団体履歴(42)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory42ItemReader dumpPartnerPersonHistory42ItemReader;
    /** 関連者企業・団体履歴(43)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory43ItemReader dumpPartnerPersonHistory43ItemReader;
    /** 関連者企業・団体履歴(44)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory44ItemReader dumpPartnerPersonHistory44ItemReader;
    /** 関連者企業・団体履歴(45)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory45ItemReader dumpPartnerPersonHistory45ItemReader;
    /** 関連者企業・団体履歴(46)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory46ItemReader dumpPartnerPersonHistory46ItemReader;
    /** 関連者企業・団体履歴(47)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory47ItemReader dumpPartnerPersonHistory47ItemReader;
    /** 関連者企業・団体履歴(99)CsvItemReader */
    @Autowired
    private DumpPartnerPersonHistory99ItemReader dumpPartnerPersonHistory99ItemReader;
    /** 関連者企業・団体履歴(01)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory01ItemWriter dumpPartnerPersonHistory01ItemWriter;
    /** 関連者企業・団体履歴(02)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory02ItemWriter dumpPartnerPersonHistory02ItemWriter;
    /** 関連者企業・団体履歴(03)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory03ItemWriter dumpPartnerPersonHistory03ItemWriter;
    /** 関連者企業・団体履歴(04)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory04ItemWriter dumpPartnerPersonHistory04ItemWriter;
    /** 関連者企業・団体履歴(05)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory05ItemWriter dumpPartnerPersonHistory05ItemWriter;
    /** 関連者企業・団体履歴(06)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory06ItemWriter dumpPartnerPersonHistory06ItemWriter;
    /** 関連者企業・団体履歴(07)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory07ItemWriter dumpPartnerPersonHistory07ItemWriter;
    /** 関連者企業・団体履歴(08)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory08ItemWriter dumpPartnerPersonHistory08ItemWriter;
    /** 関連者企業・団体履歴(09)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory09ItemWriter dumpPartnerPersonHistory09ItemWriter;
    /** 関連者企業・団体履歴(10)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory10ItemWriter dumpPartnerPersonHistory10ItemWriter;
    /** 関連者企業・団体履歴(11)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory11ItemWriter dumpPartnerPersonHistory11ItemWriter;
    /** 関連者企業・団体履歴(12)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory12ItemWriter dumpPartnerPersonHistory12ItemWriter;
    /** 関連者企業・団体履歴(13)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory13ItemWriter dumpPartnerPersonHistory13ItemWriter;
    /** 関連者企業・団体履歴(14)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory14ItemWriter dumpPartnerPersonHistory14ItemWriter;
    /** 関連者企業・団体履歴(15)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory15ItemWriter dumpPartnerPersonHistory15ItemWriter;
    /** 関連者企業・団体履歴(16)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory16ItemWriter dumpPartnerPersonHistory16ItemWriter;
    /** 関連者企業・団体履歴(17)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory17ItemWriter dumpPartnerPersonHistory17ItemWriter;
    /** 関連者企業・団体履歴(18)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory18ItemWriter dumpPartnerPersonHistory18ItemWriter;
    /** 関連者企業・団体履歴(19)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory19ItemWriter dumpPartnerPersonHistory19ItemWriter;
    /** 関連者企業・団体履歴(20)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory20ItemWriter dumpPartnerPersonHistory20ItemWriter;
    /** 関連者企業・団体履歴(21)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory21ItemWriter dumpPartnerPersonHistory21ItemWriter;
    /** 関連者企業・団体履歴(22)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory22ItemWriter dumpPartnerPersonHistory22ItemWriter;
    /** 関連者企業・団体履歴(23)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory23ItemWriter dumpPartnerPersonHistory23ItemWriter;
    /** 関連者企業・団体履歴(24)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory24ItemWriter dumpPartnerPersonHistory24ItemWriter;
    /** 関連者企業・団体履歴(25)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory25ItemWriter dumpPartnerPersonHistory25ItemWriter;
    /** 関連者企業・団体履歴(26)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory26ItemWriter dumpPartnerPersonHistory26ItemWriter;
    /** 関連者企業・団体履歴(27)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory27ItemWriter dumpPartnerPersonHistory27ItemWriter;
    /** 関連者企業・団体履歴(28)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory28ItemWriter dumpPartnerPersonHistory28ItemWriter;
    /** 関連者企業・団体履歴(29)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory29ItemWriter dumpPartnerPersonHistory29ItemWriter;
    /** 関連者企業・団体履歴(30)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory30ItemWriter dumpPartnerPersonHistory30ItemWriter;
    /** 関連者企業・団体履歴(31)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory31ItemWriter dumpPartnerPersonHistory31ItemWriter;
    /** 関連者企業・団体履歴(32)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory32ItemWriter dumpPartnerPersonHistory32ItemWriter;
    /** 関連者企業・団体履歴(33)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory33ItemWriter dumpPartnerPersonHistory33ItemWriter;
    /** 関連者企業・団体履歴(34)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory34ItemWriter dumpPartnerPersonHistory34ItemWriter;
    /** 関連者企業・団体履歴(35)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory35ItemWriter dumpPartnerPersonHistory35ItemWriter;
    /** 関連者企業・団体履歴(36)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory36ItemWriter dumpPartnerPersonHistory36ItemWriter;
    /** 関連者企業・団体履歴(37)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory37ItemWriter dumpPartnerPersonHistory37ItemWriter;
    /** 関連者企業・団体履歴(38)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory38ItemWriter dumpPartnerPersonHistory38ItemWriter;
    /** 関連者企業・団体履歴(39)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory39ItemWriter dumpPartnerPersonHistory39ItemWriter;
    /** 関連者企業・団体履歴(40)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory40ItemWriter dumpPartnerPersonHistory40ItemWriter;
    /** 関連者企業・団体履歴(41)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory41ItemWriter dumpPartnerPersonHistory41ItemWriter;
    /** 関連者企業・団体履歴(42)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory42ItemWriter dumpPartnerPersonHistory42ItemWriter;
    /** 関連者企業・団体履歴(43)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory43ItemWriter dumpPartnerPersonHistory43ItemWriter;
    /** 関連者企業・団体履歴(44)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory44ItemWriter dumpPartnerPersonHistory44ItemWriter;
    /** 関連者企業・団体履歴(45)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory45ItemWriter dumpPartnerPersonHistory45ItemWriter;
    /** 関連者企業・団体履歴(46)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory46ItemWriter dumpPartnerPersonHistory46ItemWriter;
    /** 関連者企業・団体履歴(47)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory47ItemWriter dumpPartnerPersonHistory47ItemWriter;
    /** 関連者企業・団体履歴(99)ItemWriter */
    @Autowired
    private DumpPartnerPersonHistory99ItemWriter dumpPartnerPersonHistory99ItemWriter;

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
                .<PartnerPersonHistory01Entity, PartnerPersonHistory01Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory01ItemReader).writer(dumpPartnerPersonHistory01ItemWriter).build();
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
                .<PartnerPersonHistory02Entity, PartnerPersonHistory02Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory02ItemReader).writer(dumpPartnerPersonHistory02ItemWriter).build();
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
                .<PartnerPersonHistory03Entity, PartnerPersonHistory03Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory03ItemReader).writer(dumpPartnerPersonHistory03ItemWriter).build();
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
                .<PartnerPersonHistory04Entity, PartnerPersonHistory04Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory04ItemReader).writer(dumpPartnerPersonHistory04ItemWriter).build();
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
                .<PartnerPersonHistory05Entity, PartnerPersonHistory05Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory05ItemReader).writer(dumpPartnerPersonHistory05ItemWriter).build();
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
                .<PartnerPersonHistory06Entity, PartnerPersonHistory06Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory06ItemReader).writer(dumpPartnerPersonHistory06ItemWriter).build();
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
                .<PartnerPersonHistory07Entity, PartnerPersonHistory07Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory07ItemReader).writer(dumpPartnerPersonHistory07ItemWriter).build();
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
                .<PartnerPersonHistory08Entity, PartnerPersonHistory08Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory08ItemReader).writer(dumpPartnerPersonHistory08ItemWriter).build();
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
                .<PartnerPersonHistory09Entity, PartnerPersonHistory09Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory09ItemReader).writer(dumpPartnerPersonHistory09ItemWriter).build();
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
                .<PartnerPersonHistory10Entity, PartnerPersonHistory10Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory10ItemReader).writer(dumpPartnerPersonHistory10ItemWriter).build();
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
                .<PartnerPersonHistory11Entity, PartnerPersonHistory11Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory11ItemReader).writer(dumpPartnerPersonHistory11ItemWriter).build();
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
                .<PartnerPersonHistory12Entity, PartnerPersonHistory12Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory12ItemReader).writer(dumpPartnerPersonHistory12ItemWriter).build();
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
                .<PartnerPersonHistory13Entity, PartnerPersonHistory13Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory13ItemReader).writer(dumpPartnerPersonHistory13ItemWriter).build();
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
                .<PartnerPersonHistory14Entity, PartnerPersonHistory14Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory14ItemReader).writer(dumpPartnerPersonHistory14ItemWriter).build();
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
                .<PartnerPersonHistory15Entity, PartnerPersonHistory15Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory15ItemReader).writer(dumpPartnerPersonHistory15ItemWriter).build();
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
                .<PartnerPersonHistory16Entity, PartnerPersonHistory16Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory16ItemReader).writer(dumpPartnerPersonHistory16ItemWriter).build();
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
                .<PartnerPersonHistory17Entity, PartnerPersonHistory17Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory17ItemReader).writer(dumpPartnerPersonHistory17ItemWriter).build();
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
                .<PartnerPersonHistory18Entity, PartnerPersonHistory18Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory18ItemReader).writer(dumpPartnerPersonHistory18ItemWriter).build();
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
                .<PartnerPersonHistory19Entity, PartnerPersonHistory19Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory19ItemReader).writer(dumpPartnerPersonHistory19ItemWriter).build();
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
                .<PartnerPersonHistory20Entity, PartnerPersonHistory20Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory20ItemReader).writer(dumpPartnerPersonHistory20ItemWriter).build();
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
                .<PartnerPersonHistory21Entity, PartnerPersonHistory21Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory21ItemReader).writer(dumpPartnerPersonHistory21ItemWriter).build();
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
                .<PartnerPersonHistory22Entity, PartnerPersonHistory22Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory22ItemReader).writer(dumpPartnerPersonHistory22ItemWriter).build();
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
                .<PartnerPersonHistory23Entity, PartnerPersonHistory23Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory23ItemReader).writer(dumpPartnerPersonHistory23ItemWriter).build();
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
                .<PartnerPersonHistory24Entity, PartnerPersonHistory24Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory24ItemReader).writer(dumpPartnerPersonHistory24ItemWriter).build();
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
                .<PartnerPersonHistory25Entity, PartnerPersonHistory25Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory25ItemReader).writer(dumpPartnerPersonHistory25ItemWriter).build();
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
                .<PartnerPersonHistory26Entity, PartnerPersonHistory26Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory26ItemReader).writer(dumpPartnerPersonHistory26ItemWriter).build();
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
                .<PartnerPersonHistory27Entity, PartnerPersonHistory27Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory27ItemReader).writer(dumpPartnerPersonHistory27ItemWriter).build();
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
                .<PartnerPersonHistory28Entity, PartnerPersonHistory28Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory28ItemReader).writer(dumpPartnerPersonHistory28ItemWriter).build();
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
                .<PartnerPersonHistory29Entity, PartnerPersonHistory29Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory29ItemReader).writer(dumpPartnerPersonHistory29ItemWriter).build();
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
                .<PartnerPersonHistory30Entity, PartnerPersonHistory30Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory30ItemReader).writer(dumpPartnerPersonHistory30ItemWriter).build();
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
                .<PartnerPersonHistory31Entity, PartnerPersonHistory31Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory31ItemReader).writer(dumpPartnerPersonHistory31ItemWriter).build();
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
                .<PartnerPersonHistory32Entity, PartnerPersonHistory32Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory32ItemReader).writer(dumpPartnerPersonHistory32ItemWriter).build();
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
                .<PartnerPersonHistory33Entity, PartnerPersonHistory33Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory33ItemReader).writer(dumpPartnerPersonHistory33ItemWriter).build();
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
                .<PartnerPersonHistory34Entity, PartnerPersonHistory34Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory34ItemReader).writer(dumpPartnerPersonHistory34ItemWriter).build();
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
                .<PartnerPersonHistory35Entity, PartnerPersonHistory35Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory35ItemReader).writer(dumpPartnerPersonHistory35ItemWriter).build();
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
                .<PartnerPersonHistory36Entity, PartnerPersonHistory36Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory36ItemReader).writer(dumpPartnerPersonHistory36ItemWriter).build();
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
                .<PartnerPersonHistory37Entity, PartnerPersonHistory37Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory37ItemReader).writer(dumpPartnerPersonHistory37ItemWriter).build();
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
                .<PartnerPersonHistory38Entity, PartnerPersonHistory38Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory38ItemReader).writer(dumpPartnerPersonHistory38ItemWriter).build();
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
                .<PartnerPersonHistory39Entity, PartnerPersonHistory39Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory39ItemReader).writer(dumpPartnerPersonHistory39ItemWriter).build();
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
                .<PartnerPersonHistory40Entity, PartnerPersonHistory40Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory40ItemReader).writer(dumpPartnerPersonHistory40ItemWriter).build();
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
                .<PartnerPersonHistory41Entity, PartnerPersonHistory41Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory41ItemReader).writer(dumpPartnerPersonHistory41ItemWriter).build();
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
                .<PartnerPersonHistory42Entity, PartnerPersonHistory42Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory42ItemReader).writer(dumpPartnerPersonHistory42ItemWriter).build();
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
                .<PartnerPersonHistory43Entity, PartnerPersonHistory43Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory43ItemReader).writer(dumpPartnerPersonHistory43ItemWriter).build();
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
                .<PartnerPersonHistory44Entity, PartnerPersonHistory44Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory44ItemReader).writer(dumpPartnerPersonHistory44ItemWriter).build();
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
                .<PartnerPersonHistory45Entity, PartnerPersonHistory45Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory45ItemReader).writer(dumpPartnerPersonHistory45ItemWriter).build();
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
                .<PartnerPersonHistory46Entity, PartnerPersonHistory46Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory46ItemReader).writer(dumpPartnerPersonHistory46ItemWriter).build();
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
                .<PartnerPersonHistory47Entity, PartnerPersonHistory47Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory47ItemReader).writer(dumpPartnerPersonHistory47ItemWriter).build();
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
                .<PartnerPersonHistory99Entity, PartnerPersonHistory99Entity>chunk(CHUNK_SIZE, transactionManager)
                .reader(dumpPartnerPersonHistory99ItemReader).writer(dumpPartnerPersonHistory99ItemWriter).build();
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
