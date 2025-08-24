package mitei.mitei.political.balancesheet.manage.kanrensha.dto.add_xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;

/**
 * XML一括登録最小マスタ編集リストワークテーブル更新CapsuleDto
 */
public class UpdateWkTblAddByXmlTableListCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象リスト */
    private List<WkTblMasterAllByXmlEntity> listWkTblByXml = new ArrayList<>();

    /**
     * 編集対象リストを取得する
     *
     * @return 編集対象リスト
     */
    public List<WkTblMasterAllByXmlEntity> getListWkTblByXml() {
        return listWkTblByXml;
    }

    /**
     * 編集対象リストを設定する
     *
     * @param listWkTblByXml 編集対象リスト
     */
    public void setListWkTblByXml(final List<WkTblMasterAllByXmlEntity> listWkTblByXml) {
        this.listWkTblByXml = listWkTblByXml;
    }

}
