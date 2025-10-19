package mitei.mitei.political.balancesheet.manage.kanrensha.dto.user;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;

/**
 * 関連者個人格納Dto
 */
public class GetKanrenshaPersonCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者個人マスタEntity */
    private MasterPersonEntity masterPersonEntity = new MasterPersonEntity();

    /**
     * 関連者個人マスタEntityを取得する
     *
     * @return 関連者個人マスタEntity
     */
    public MasterPersonEntity getMasterPersonEntity() {
        return masterPersonEntity;
    }

    /**
     * 関連者個人マスタEntityを設定する
     *
     * @param masterPersonEntity 関連者個人マスタEntity
     */
    public void setMasterPersonEntity(final MasterPersonEntity masterPersonEntity) {
        this.masterPersonEntity = masterPersonEntity;
    }

}
