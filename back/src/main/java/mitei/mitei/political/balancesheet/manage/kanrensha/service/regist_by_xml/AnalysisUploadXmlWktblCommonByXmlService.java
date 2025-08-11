package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_by_xml;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;

/**
 * アップロード済XMLファイル解析ワークテーブル複写Service
 */
@Service
public class AnalysisUploadXmlWktblCommonByXmlService {

    /**
     * 処理を行う
     *
     * @throws JsonProcessingException JSOn変換例外
     */
    public void practice() throws JsonProcessingException {
        // 公式XML読み取り
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setSerializationInclusion(Include.ALWAYS);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        AllBookDto allBookDto = xmlMapper.readValue("", new TypeReference<>() {
        });

        // 様式7の3:備考欄に取引相手の記載がある可能性がある

        // 様式7の4:わかるのは借入先(備考に住所の記載があればラッキー)

        // 様式7の5:支部の名称とに事務所の所在地の

        // 様式7の6:備考欄に取引相手の記載がある可能性がある

        // 様式7の7-1:寄付者の氏名、住所、職業
        // 様式7の7-2:寄付者の団体名称、住所、代表者名
        // 様式7の7-3:寄付者の団体名称、住所、代表者名
        // 様式7の8-1:寄付者の氏名、住所、職業
        // 様式7の8-2:寄付者の団体名称、住所、代表者名
        // 様式7の8-3:寄付者の団体名称、住所、代表者名

        // 様式7の9:相手が匿名であることが大前提である様式であるので、処理としては無視してよい
        // 様式7の10:パーティ券購入者の明細を記載しないで良い様式

        // 様式7の11-1:パーティ券の氏名、住所、職業
        // 様式7の11-2:パーティ券の団体名称、住所、代表者名
        // 様式7の11-3:パーティ券の団体名称、住所、代表者名
        // 様式7の12-1:パーティ券の氏名、住所、職業
        // 様式7の12-2:パーティ券の団体名称、住所、代表者名
        // 様式7の12-3:パーティ券の団体名称、住所、代表者名

        // 様式7の14(1-3):氏名と事務所の所在地
        // 様式7の15(1-13):氏名と事務所の所在地
        // 様式7の16:氏名と事務所の所在地

    }

}
