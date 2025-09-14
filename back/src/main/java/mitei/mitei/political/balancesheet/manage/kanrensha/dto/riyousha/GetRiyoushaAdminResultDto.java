package mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;

/**
 * 利用者管理者格納Dto
 */
public class GetRiyoushaAdminResultDto extends FrameworkMessageAndResultDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 利用者管理者Dto */
    private RiyoushaAdminDto riyoushaAdminDto = new RiyoushaAdminDto();

    /**
     * 利用者管理者Dtoを取得する
     *
     * @return 利用者管理者Dto
     */
    public RiyoushaAdminDto getRiyoushaAdminDto() {
        return riyoushaAdminDto;
    }

    /**
     * 利用者管理者Dtoを設定する
     *
     * @param riyoushaAdminDto 利用者管理者Dto
     */
    public void setRiyoushaAdminDto(final RiyoushaAdminDto riyoushaAdminDto) {
        this.riyoushaAdminDto = riyoushaAdminDto;
    }

}
