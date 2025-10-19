package mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_std;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPoliOrgEntity;

/**
 * 政治団体一括登録マスタ標準ワークテーブル更新CapsuleDto
 */
public class UpdateWkTblStdPoliOrgCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象Entity */
    private WkTblMasterPoliOrgEntity wkTblMasterPoliOrgEntity = new WkTblMasterPoliOrgEntity();

    /**
     * 編集対象Entityを取得する
     *
     * @return 編集対象Entity
     */
    public WkTblMasterPoliOrgEntity getWkTblMasterPoliOrgEntity() {
        return wkTblMasterPoliOrgEntity;
    }

    /**
     * 編集対象Entityを設定する
     *
     * @param wkTblMasterPoliOrgEntity 編集対象Entity
     */
    public void setWkTblMasterPoliOrgEntity(final WkTblMasterPoliOrgEntity wkTblMasterPoliOrgEntity) {
        this.wkTblMasterPoliOrgEntity = wkTblMasterPoliOrgEntity;
    }

}
