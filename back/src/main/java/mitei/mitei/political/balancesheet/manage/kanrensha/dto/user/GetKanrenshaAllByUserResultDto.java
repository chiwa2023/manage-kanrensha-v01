package mitei.mitei.political.balancesheet.manage.kanrensha.dto.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;

/**
 * ユーザから関連者をすべて取得した結果Dto
 */
public class GetKanrenshaAllByUserResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者個人マスタ */
    private MasterPersonEntity masterPersonEntity = new MasterPersonEntity();

    /** 関連者企業団体マスタリスト */
    private List<MasterCorporationEntity> listCorpEntity = new ArrayList<>();

    /** 関連者政治団体マスタリスト */
    private List<MasterPoliticalOrganizationEntity> listPoliOrgEntity = new ArrayList<>();

    /**
     * 関連者個人マスタを取得する
     *
     * @return 関連者個人マスタ
     */
    public MasterPersonEntity getMasterPersonEntity() {
        return masterPersonEntity;
    }

    /**
     * 関連者個人マスタを設定する
     *
     * @param masterPersonEntity 関連者個人マスタ
     */
    public void setMasterPersonEntity(final MasterPersonEntity masterPersonEntity) {
        this.masterPersonEntity = masterPersonEntity;
    }

    /**
     * 関連者企業団体マスタリストを取得する
     *
     * @return 関連者企業団体マスタリスト
     */
    public List<MasterCorporationEntity> getListCorpEntity() {
        return listCorpEntity;
    }

    /**
     * 関連者企業団体マスタリストを設定する
     *
     * @param listCorpEntity 関連者企業団体マスタリスト
     */
    public void setListCorpEntity(final List<MasterCorporationEntity> listCorpEntity) {
        this.listCorpEntity = listCorpEntity;
    }

    /**
     * 関連者政治団体マスタリストを取得する
     *
     * @return 関連者政治団体マスタリスト
     */
    public List<MasterPoliticalOrganizationEntity> getListPoliOrgEntity() {
        return listPoliOrgEntity;
    }

    /**
     * 関連者政治団体マスタリストを設定する
     *
     * @param listPoliOrgEntity 関連者政治団体マスタリスト
     */
    public void setListPoliOrgEntity(final List<MasterPoliticalOrganizationEntity> listPoliOrgEntity) {
        this.listPoliOrgEntity = listPoliOrgEntity;
    }

}
