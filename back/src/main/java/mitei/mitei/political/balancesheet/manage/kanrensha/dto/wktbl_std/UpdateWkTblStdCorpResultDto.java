package mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_std;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterCorpEntity;

/**
 * 企業／団体一括登録マスタ標準ワークテーブル更新CapsuleDto
 */
public class UpdateWkTblStdCorpResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集対象Entity */
    private WkTblMasterCorpEntity wkTblMasterCorpEntity = new WkTblMasterCorpEntity();

    /**
     * 編集対象Entityを取得する
     *
     * @return 編集対象Entity
     */
    public WkTblMasterCorpEntity getWkTblMasterCorpEntity() {
        return wkTblMasterCorpEntity;
    }

    /**
     * 編集対象Entityを設定する
     *
     * @param wkTblMasterCorpEntity 編集対象Entity
     */
    public void setWkTblMasterCorpEntity(final WkTblMasterCorpEntity wkTblMasterCorpEntity) {
        this.wkTblMasterCorpEntity = wkTblMasterCorpEntity;
    }

}
