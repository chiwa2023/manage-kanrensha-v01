package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.plus_houjin_no;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_min.PartnerCorpAddMiniDto;

/**
 * 法人番号追加Csv出力Dto
 */
public class ParterCorpPlusHoujinNoDto extends PartnerCorpAddMiniDto implements Serializable {

    /** serialId */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 外国籍企業該当非該当テキスト */
    private String isForeignCorpText = INIT_String; // NOPMD

    /**
     * 外国籍企業該当非該当テキストを取得する
     *
     * @return 外国籍企業該当非該当テキスト
     */
    public String getIsForeignCorpText() {
        return isForeignCorpText;
    }

    /**
     * 外国籍企業該当非該当テキストを設定する
     *
     * @param isForeignCorpText 外国籍企業該当非該当テキスト
     */
    public void setIsForeignCorpText(final String isForeignCorpText) {
        this.isForeignCorpText = isForeignCorpText;
    }

}
