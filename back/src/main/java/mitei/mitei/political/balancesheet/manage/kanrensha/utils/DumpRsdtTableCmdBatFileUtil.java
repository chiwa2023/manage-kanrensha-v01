package mitei.mitei.political.balancesheet.manage.kanrensha.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

/**
 * 住居テーブルダンプバッチ作成Util
 */
@Component
public class DumpRsdtTableCmdBatFileUtil {

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    /**
     * 処理行う
     *
     * @throws IOException ファイル例外
     */
    @SuppressWarnings("unchecked")
    public void practice(final String lgCodePref,final String pathCmdBat) throws IOException {

        String sql = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'test_manage_kanrensha' AND table_name LIKE 'address_rsdt_"
                + lgCodePref + "%' ";

        Query queryCount = entityManager.createNativeQuery(sql, String.class);
        List<String> listTable = (List<String>) queryCount.getResultList();

        List<String> list = new ArrayList<>();
        list.add("cd MysqlインストールDir");
        for (String table : listTable) {
            list.add("mysqldump -u root -p test_manage_kanrensha " + table + " > c:\\temp\\" + table + ".sql");
        }
        list.add("PAUSE");

        Path path = Paths.get(pathCmdBat);
        Files.write(path, list, Charset.forName("cp932"));

    }

}
