package mitei.mitei.political.balancesheet.manage.kanrensha.dto.user;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;

/**
 * 関連者政治団体格納Dto
 */
public class GetKanrenshaPoliOrgCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者政治団体マスタEntity */
    private MasterPoliticalOrganizationEntity masterPoliticalOrganizationEntity = new MasterPoliticalOrganizationEntity();

    /**
     * 関連者政治団体マスタEntityを取得する
     *
     * @return 関連者政治団体マスタEntity
     */
    public MasterPoliticalOrganizationEntity getMasterPoliticalOrganizationEntity() {
        return masterPoliticalOrganizationEntity;
    }

    /**
     * 関連者政治団体マスタEntityを設定する
     *
     * @param masterPoliticalOrganizationEntity 関連者政治団体マスタEntity
     */
    public void setMasterPoliticalOrganizationEntity(
            final MasterPoliticalOrganizationEntity masterPoliticalOrganizationEntity) {
        this.masterPoliticalOrganizationEntity = masterPoliticalOrganizationEntity;
    }

}
