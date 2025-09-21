package mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

/**
 * マスタId格納Dto
 */
public class GetRiyoushaByMasterIdCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** マスタId */
    private Integer masterId = INIT_Integer;

    /**
     * マスタIdを取得する
     *
     * @return マスタId
     */
    public Integer getMasterId() {
        return masterId;
    }

    /**
     * マスタIdを設定する
     *
     * @param masterId マスタId
     */
    public void setMasterId(final Integer masterId) {
        this.masterId = masterId;
    }

}
