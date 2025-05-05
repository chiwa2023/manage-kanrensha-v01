package mitei.mitei.political.balancesheet.manage.kanrensha.logic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressAllCityEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressAllCityRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.util.WriteLogService;

/**
 * ファイルダウンロード(アドレス・べース・レジストリから地番ファイルを県ごとに抜き出し)Logic
 */
@Component
public class GetChibanCsvLogic {

    /** 市区町村Repository */
    @Autowired
    private AddressAllCityRepository addressAllCityRepository;

    /** Logger */
    private final Logger log = LoggerFactory.getLogger(WriteLogService.class);

    /**
     * 処理を行う
     *
     * @throws Exception 例外(取得できなかったらしょうがない)
     */
    public void practice(final String lgCodePref,final String storeDir) {

        List<AddressAllCityEntity> list = addressAllCityRepository.findByLgCodeStartingWith(lgCodePref);
        for (AddressAllCityEntity entity : list) {
            String lgCode = entity.getLgCode();

            try {
                URI uri = new URI( // NOPMD
                        "https://catalog.registries.digital.go.jp/rsc/address/mt_parcel_city" + lgCode + ".csv.zip");
                URL url = uri.toURL();

                // URLに紐づいたURLConnectionインスタンスを生成
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                // メソッドを設定
                conn.setRequestMethod("GET");
                // 接続を確立する
                conn.connect();

                // HTTP(S)接続の応答メッセージのステータスコードを返す
                int statusCode = conn.getResponseCode();
                log.info("HTTP Status Code: " + statusCode + "===" + lgCode);

                this.saveFile(conn, lgCode,storeDir);

                Thread.sleep(1000); // SUPPRESS CHECKSTYLE MagicNumber // NOPMD

            } catch (Exception e) { // NOPMD
                log.error(lgCode + "not access!");
            }
        }

    }

    private void saveFile(final HttpsURLConnection conn, final String lgCode,final String storeDir) {

        try (InputStream input = conn.getInputStream()) {

            File targetFile = new File(storeDir + lgCode + ".csv.zip");
            Files.copy(input, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException exception) { // NOPMD
            log.error(lgCode + "not save!");
        }

    }

}
