package mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;

/**
 * 運営者ユーザー格納Dto
 */
public class SaveRiyoushaManagerCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 利用者運営者Dto */
    private RiyoushaManagerDto riyoushaManagerDto = new RiyoushaManagerDto();

    /**
     * 利用者運営者Dtoを設定する
     *
     * @return 利用者運営者Dto
     */
    public RiyoushaManagerDto getRiyoushaManagerDto() {
        return riyoushaManagerDto;
    }

    /**
     * 利用者運営者Dtoを取得する
     *
     * @param riyoushaManagerDto 利用者運営者Dto
     */
    public void setRiyoushaManagerDto(final RiyoushaManagerDto riyoushaManagerDto) {
        this.riyoushaManagerDto = riyoushaManagerDto;
    }

}
