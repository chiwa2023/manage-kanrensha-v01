package mitei.mitei.political.balancesheet.manage.kanrensha.dto.user;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;

/**
 * 関連者企業団体格納Dto
 */
public class GetKanrenshaCorpCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者企業団体マスタEntity */
    private MasterCorporationEntity masterCorporationEntity = new MasterCorporationEntity();

    /**
     * 関連者企業団体マスタEntityを取得する
     *
     * @return 関連者企業団体マスタEntity
     */
    public MasterCorporationEntity getMasterCorporationEntity() {
        return masterCorporationEntity;
    }

    /**
     * 関連者企業団体マスタEntityを設定する
     *
     * @param masterCorporationEntity 関連者企業団体マスタEntity
     */
    public void setMasterCorporationEntity(final MasterCorporationEntity masterCorporationEntity) {
        this.masterCorporationEntity = masterCorporationEntity;
    }

}
