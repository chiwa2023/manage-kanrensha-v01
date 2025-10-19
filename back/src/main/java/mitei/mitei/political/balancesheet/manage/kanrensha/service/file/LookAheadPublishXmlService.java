package mitei.mitei.political.balancesheet.manage.kanrensha.service.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookHeaderDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070100CoverAndOrganizationDetailsDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.storage_file.LookAheadPublishXmlResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.storage_file.StorageFileDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.storage_file.UploadFileDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.file.GetAbsolutePathLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.file.GetTempFilePathLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.file.SaveFileLogic;

/**
 * アップロードされたXMLファイル文書種類先読みService
 */
@Service
@ConfigurationProperties(prefix = "mitei.mitei.political.balancesheet.manage.kanrensha")
public class LookAheadPublishXmlService {

    /** 一時ファイルパス取得Logic */
    @Autowired
    private GetTempFilePathLogic getTempFilePathLogic;

    /** ファイル絶対パス取得Logic */
    @Autowired
    private GetAbsolutePathLogic getAbsolutePathLogic;

    /** ファイル保存Logic */
    @Autowired
    private SaveFileLogic saveFileLogic;

    /**
     * 処理を行う
     *
     * @param month         処理月(=保存場所)
     * @param uploadFileDto アップロードファイル内容
     * @return 頭出し結果
     * @throws IOException ファイルに関する例外
     */
    public LookAheadPublishXmlResultDto practice(final int month, final UploadFileDto uploadFileDto)
            throws IOException, DatabindException {

        // 保存場所の設定
        LookAheadPublishXmlResultDto resultDto = new LookAheadPublishXmlResultDto();
        StorageFileDto storageFileDto = getTempFilePathLogic.practice(month, uploadFileDto.getFileName());
        resultDto.setStorageFileDto(storageFileDto);

        Path path = getAbsolutePathLogic.practice(storageFileDto.getSavedDir(), storageFileDto.getFileName());

        if (saveFileLogic.practice(path, uploadFileDto.getFileContent())) {

            // 公式XML読み取り
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.setSerializationInclusion(Include.ALWAYS);
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

            // byteで渡せば基本的には良しなにしてくれそう
            AllBookDto allBookDto = xmlMapper.readValue(Files.readAllBytes(path), new TypeReference<>() {
            });

            // アプリ名とバージョン設定
            AllBookHeaderDto headerDto = allBookDto.getAllBookHeaderDto();
            Sheet070100CoverAndOrganizationDetailsDto coverDto = allBookDto
                    .getAllSheet0701CoverAndOrganizationDetailsDto().getSheet070100CoverAndOrganizationDetailsDto();

            resultDto.setApp(headerDto.getAppName());
            resultDto.setVersion(headerDto.getVersion());
            resultDto.setDantaiName(coverDto.getDantaiName01());
            resultDto.setHoukokuNen(coverDto.getHoukokuNen());
            resultDto.setMessage("政治資金収支報告書V5が正常に読み込みできました");
        }

        return resultDto;
    }

}
