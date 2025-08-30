package mitei.mitei.political.balancesheet.manage.kanrensha.dto;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

/**
 * 選択肢値数値Dto
 */
public class SelectOptionNumberDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /**
     * コンストラクタ
     *
     * @param value 選択肢の値
     * @param text  選択肢表示テキスト
     */
    public SelectOptionNumberDto(final Long value, final String text) {
        super();
        this.value = Math.toIntExact(value); // 本当にLongが来ないか監視は必要
        this.text = text;
    }

    /** 選択肢値 */
    @Id
    @Column(name = "value")
    private Integer value = INIT_Integer;

    /** 選択肢表示テキスト */
    @Column(name = "text")
    private String text = INIT_String;

    /**
     * 値を取得する
     *
     * @return 値
     */
    public Integer getValue() {
        return value;
    }

    /**
     * 値を設定する
     *
     * @param value 値
     */
    public void setValue(final Integer value) {
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
