package mitei.mitei.political.balancesheet.manage.kanrensha.dto.user;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;

/**
 * 関連者企業団体格納Dto
 */
public class GetKanrenshaCorpResultDto extends FrameworkMessageAndResultDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者企業団体Dto */
    private KanrenshaCorpDto kanrenshaCorpDto = new KanrenshaCorpDto();

    /**
     * 関連者企業団体Dtoを取得する
     *
     * @return 関連者企業団体Dto
     */
    public KanrenshaCorpDto getKanrenshaCorpDto() {
        return kanrenshaCorpDto;
    }

    /**
     * 関連者企業団体Dtoを設定する
     *
     * @param kanrenshaCorpDto 関連者企業団体Dto
     */
    public void setKanrenshaCorpDto(final KanrenshaCorpDto kanrenshaCorpDto) {
        this.kanrenshaCorpDto = kanrenshaCorpDto;
    }

}
