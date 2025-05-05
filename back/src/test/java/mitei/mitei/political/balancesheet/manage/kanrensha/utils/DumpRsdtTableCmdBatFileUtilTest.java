package mitei.mitei.political.balancesheet.manage.kanrensha.utils;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * DumpRsdtTableCmdBatFileUtil単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class DumpRsdtTableCmdBatFileUtilTest {

    /** テスト対象 */
    @Autowired
    private DumpRsdtTableCmdBatFileUtil dumpRsdtTableCmdBatFileUtil;
    
    @Test
    void test()throws  Exception {
        
        dumpRsdtTableCmdBatFileUtil.practice("13","c:/temp/dump_rsdt.bat");
        
        fail("Not yet implemented");
    }

}
