package mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.SelectOptionIntegerDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaComradeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerEntity;

/**
 * 利用者所属組織選択肢取得結果Dto
 */
public class PickupOrgSelectOptionResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 組織セレクト項目選択肢リスト */
    private List<SelectOptionIntegerDto> listOrgOptions = new ArrayList<>();

    /**
     * 組織セレクト項目選択肢リスト
     *
     * @return 組織セレクト項目選択肢リスト
     */
    public List<SelectOptionIntegerDto> getListOrgOptions() {
        return listOrgOptions;
    }

    /**
     * 組織セレクト項目選択肢リスト
     *
     * @param listOrgOptions 組織セレクト項目選択肢リスト
     */
    public void setListOrgOptions(final List<SelectOptionIntegerDto> listOrgOptions) {
        this.listOrgOptions = listOrgOptions;
    }

    /** 呼び出し個人APIユーザEntity */
    private RiyoushaComradeEntity riyoushaComradeEntity = new RiyoushaComradeEntity();

    /**
     * 呼び出し個人APIユーザEntityを取得する
     *
     * @return 呼び出し個人APIユーザEntity
     */
    public RiyoushaComradeEntity getRiyoushaComradeEntity() {
        return riyoushaComradeEntity;
    }

    /**
     * 呼び出し個人APIユーザEntityを設定する
     *
     * @param riyoushaComradeEntity 呼び出し個人APIユーザEntity
     */
    public void setRiyoushaComradeEntity(final RiyoushaComradeEntity riyoushaComradeEntity) {
        this.riyoushaComradeEntity = riyoushaComradeEntity;
    }

    /** 呼び出し個人運営者Entity */
    private RiyoushaManagerEntity riyoushaManagerEntity = new RiyoushaManagerEntity();

    /**
     * 呼び出し個人運営者Entityを取得する
     *
     * @return 呼び出し個人運営者Entity
     */
    public RiyoushaManagerEntity getRiyoushaManagerEntity() {
        return riyoushaManagerEntity;
    }

    /**
     * 呼び出し個人運営者Entityを設定する
     *
     * @param riyoushaManagerEntity 呼び出し個人運営者Entity
     */
    public void setRiyoushaManagerEntity(final RiyoushaManagerEntity riyoushaManagerEntity) {
        this.riyoushaManagerEntity = riyoushaManagerEntity;
    }

}
