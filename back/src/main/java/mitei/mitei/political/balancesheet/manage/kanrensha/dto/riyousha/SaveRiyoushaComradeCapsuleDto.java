package mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;

/**
 * APIユーザ格納Dto
 */
public class SaveRiyoushaComradeCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 利用者API接続Dto */
    private RiyoushaComradeDto riyoushaComradeDto = new RiyoushaComradeDto();

    /**
     * 利用者API接続Dtoを取得する
     *
     * @return 利用者API接続Dto
     */
    public RiyoushaComradeDto getRiyoushaComradeDto() {
        return riyoushaComradeDto;
    }

    /**
     * 利用者API接続Dtoを設定する
     *
     * @param riyoushaComradeDto 利用者API接続Dto
     */
    public void setRiyoushaComradeDto(final RiyoushaComradeDto riyoushaComradeDto) {
        this.riyoushaComradeDto = riyoushaComradeDto;
    }

}
