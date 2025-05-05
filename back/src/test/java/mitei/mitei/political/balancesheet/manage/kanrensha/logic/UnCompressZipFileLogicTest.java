package mitei.mitei.political.balancesheet.manage.kanrensha.logic;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * UnCompressZipFileLogic単体テスト
 */
class UnCompressZipFileLogicTest {

    @Test
    void test() throws Exception {

        String compressPath = "C:/temp/address/";
        String unCompressPath = "C:/temp/uncompress/address/";

        UnCompressZipFileLogic unCompressZipFileLogic = new UnCompressZipFileLogic();

        unCompressZipFileLogic.practice(compressPath, unCompressPath);

        fail("Not yet implemented");
    }

}
