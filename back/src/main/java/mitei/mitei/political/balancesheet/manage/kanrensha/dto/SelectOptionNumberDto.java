package mitei.mitei.political.balancesheet.manage.kanrensha.dto;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * 選択肢値数値Dto
 */
@Entity
public class SelectOptionNumberDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Long) */
    private static final Long INIT_Long = 0L;

    /** 選択肢値 */
    @Id
    @Column(name = "value")
    private Long value = INIT_Long;

    /** 選択肢表示テキスト */
    @Column(name = "text")
    private String text = INIT_String;

    /**
     * 値を取得する
     *
     * @return 値
     */
    public Long getValue() {
        return value;
    }

    /**
     * 値を設定する
     *
     * @param value 値
     */
    public void setValue(final Long value) {
        this.value = value;
    }

    /**
     * 表示テキストを取得する
     *
     * @return 表示テキスト
     */
    public String getText() {
        return text;
    }

    /**
     * 表示テキストを設定する
     *
     * @param text 表示テキスト
     */
    public void setText(final String text) {
        this.text = text;
    }

}
