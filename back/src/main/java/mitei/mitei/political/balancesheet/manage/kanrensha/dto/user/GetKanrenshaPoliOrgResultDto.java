package mitei.mitei.political.balancesheet.manage.kanrensha.dto.user;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;

/**
 * 関連者政治団体格納Dto
 */
public class GetKanrenshaPoliOrgResultDto extends FrameworkMessageAndResultDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者政治団体Dto */
    private KanrenshaPoliOrgDto kanrenshaPoliOrgDto = new KanrenshaPoliOrgDto();

    /**
     * 関連者政治団体Dtoを取得する
     *
     * @return 関連者政治団体Dto
     */
    public KanrenshaPoliOrgDto getKanrenshaPoliOrgDto() {
        return kanrenshaPoliOrgDto;
    }

    /**
     * 関連者政治団体Dtoを設定する
     *
     * @param kanrenshaPoliOrgDto 関連者政治団体Dto
     */
    public void setKanrenshaPoliOrgDto(final KanrenshaPoliOrgDto kanrenshaPoliOrgDto) {
        this.kanrenshaPoliOrgDto = kanrenshaPoliOrgDto;
    }

}
