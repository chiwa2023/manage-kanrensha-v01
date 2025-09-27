package mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;

/**
 * 利用者API接続者格納Dto
 */
public class GetRiyoushaComradeResultDto extends FrameworkMessageAndResultDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 利用者API接続者Dto */
    private RiyoushaComradeDto riyoushaComradeDto = new RiyoushaComradeDto();

    /**
     * 利用者API接続者Dtoを取得する
     *
     * @return 利用者API接続者Dto
     */
    public RiyoushaComradeDto getRiyoushaComradeDto() {
        return riyoushaComradeDto;
    }

    /**
     * 利用者API接続者Dtoを設定する
     *
     * @param riyoushaComradeDto 利用者API接続者Dto
     */
    public void setRiyoushaComradeDto(final RiyoushaComradeDto riyoushaComradeDto) {
        this.riyoushaComradeDto = riyoushaComradeDto;
    }

}
