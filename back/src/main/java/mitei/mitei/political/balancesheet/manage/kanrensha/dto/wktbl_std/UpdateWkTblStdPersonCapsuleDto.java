package mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_std;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPersonEntity;

/**
 * 個人一括登録マスタ標準ワークテーブル更新CapsuleDto
 */
public class UpdateWkTblStdPersonCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象Entity */
    private WkTblMasterPersonEntity wkTblMasterPersonEntity = new WkTblMasterPersonEntity();

    /**
     * 編集対象Entityを取得する
     *
     * @return 編集対象Entity
     */
    public WkTblMasterPersonEntity getWkTblMasterPersonEntity() {
        return wkTblMasterPersonEntity;
    }

    /**
     * 編集対象Entityを設定する
     *
     * @param wkTblMasterPersonEntity 編集対象Entity
     */
    public void setWkTblMasterPersonEntity(final WkTblMasterPersonEntity wkTblMasterPersonEntity) {
        this.wkTblMasterPersonEntity = wkTblMasterPersonEntity;
    }

}
