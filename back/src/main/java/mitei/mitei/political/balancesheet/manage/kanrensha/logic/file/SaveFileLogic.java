package mitei.mitei.political.balancesheet.manage.kanrensha.logic.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.stereotype.Component;

/**
 * ファイル保存処理Logic
 */
@Component
public class SaveFileLogic {

    /** Base64ヘッダ */
    private static final String BASE64_HEADER = ";base64,";

    /**
     * 処理を行う
     *
     * @param saveFolder  保存フォルダ
     * @param fileName    ファイル名
     * @param fileContent BASE64Encodeされたファイル内容
     * @return ファイル作成結果
     * @throws IOException ファイル書き込み例外
     */
    public boolean practice(final Path saveFolder, final String fileName, final String fileContent) throws IOException {

        // Webから取得したデータの場合はMymeTypeのヘッダがついているので発見したら除去
        int pos = fileContent.indexOf(BASE64_HEADER);
        String content;
        if (-1 == pos) {
            content = fileContent;
        } else {
            content = fileContent.substring(pos + BASE64_HEADER.length(), fileContent.length());
        }

        // 実際のファイル書き込み
        Path allPath = Paths.get(saveFolder.toString(), fileName);
        return Files.write(allPath, Base64.getDecoder().decode(content)).toFile().exists();
    }

}
