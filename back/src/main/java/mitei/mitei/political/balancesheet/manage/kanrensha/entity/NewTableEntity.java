package mitei.mitei.political.balancesheet.manage.kanrensha.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * データベース接続テスト(TODO 後で削除)
 */
@Entity
@Table(name = "new_table")
public class NewTableEntity implements Serializable { // NOPMD DataClas

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "new_table_id")
    private Integer newTableId = INIT_Integer;

    /** 適当テキスト */
    @Column(name = "text_etc")
    private String textEtc = INIT_String;

    /**
     * テーブルId
     *
     * @return テーブルId
     */
    public Integer getNewTableId() {
        return newTableId;
    }

    /**
     * テーブルId
     *
     * @param newTableId テーブルId
     */
    public void setNewTableId(final Integer newTableId) {
        this.newTableId = newTableId;
    }

    /**
     * 適当テキスト
     *
     * @return 適当テキスト
     */
    public String getTextEtc() {
        return textEtc;
    }

    /**
     * 適当テキスト
     *
     * @param textEtc 適当テキスト
     */
    public void setTextEtc(final String textEtc) {
        this.textEtc = textEtc;
    }
}
