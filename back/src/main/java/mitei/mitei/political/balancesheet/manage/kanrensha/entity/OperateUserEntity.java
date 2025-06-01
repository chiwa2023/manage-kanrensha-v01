package mitei.mitei.political.balancesheet.manage.kanrensha.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 操作ユーザEntity
 */
@Entity
@Table(name = "operate_user")
public class OperateUserEntity implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operate_user_id")
    private Integer operateUserId = INIT_Integer;

    /** ユーザ名 */
    @Column(name = "user_name")
    private String userName = INIT_String;

    /** パスワード */
    @Column(name = "password")
    private String password = INIT_String;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getOperateUserId() {
        return operateUserId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param operateUserId テーブルId
     */
    public void setOperateUserId(final Integer operateUserId) {
        this.operateUserId = operateUserId;
    }

    /**
     * ユーザ名を取得する
     *
     * @return ユーザ名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * ユーザ名を設定する
     *
     * @param userName ユーザ名
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * パスワードを取得する
     *
     * @return パスワード
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワードを設定する
     *
     * @param password パスワード
     */
    public void setPassword(final String password) {
        this.password = password;
    }

}
