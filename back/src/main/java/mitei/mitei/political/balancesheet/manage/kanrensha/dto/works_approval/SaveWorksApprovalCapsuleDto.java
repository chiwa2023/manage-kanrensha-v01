package mitei.mitei.political.balancesheet.manage.kanrensha.dto.works_approval;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterKanrenshaAddressBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonBaseEntity;

/**
 * 作業承認内容保存Dto
 */
public class SaveWorksApprovalCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 住所承認作業リスト */
    private List<MasterKanrenshaAddressBaseEntity> listAddress = new ArrayList<>();

    /** 承認作業用職業リスト */
    private List<MasterPersonBaseEntity> listShokugyou = new ArrayList<>();

    /**
     * 住所承認作業リストを取得する
     *
     * @return 住所承認作業リスト
     */
    public List<MasterKanrenshaAddressBaseEntity> getListAddress() {
        return listAddress;
    }

    /**
     * 住所承認作業リストを設定する
     *
     * @param listAddress 住所承認作業リスト
     */
    public void setListAddress(final List<MasterKanrenshaAddressBaseEntity> listAddress) {
        this.listAddress = listAddress;
    }

    /**
     * 承認作業用職業リストを取得する
     *
     * @return 承認作業用職業リスト
     */
    public List<MasterPersonBaseEntity> getListShokugyou() {
        return listShokugyou;
    }

    /**
     * 承認作業用職業リストを設定する
     *
     * @param listShokugyou 承認作業用職業リスト
     */
    public void setListShokugyou(final List<MasterPersonBaseEntity> listShokugyou) {
        this.listShokugyou = listShokugyou;
    }

}
