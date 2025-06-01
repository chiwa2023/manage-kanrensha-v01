package mitei.mitei.political.balancesheet.manage.kanrensha.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

/**
 * user_new接続用Entity
 */
@Entity
@Table(name = "user_new")
public class UserNewEntity  implements Serializable{ // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948,7,29);

    /** 初期データ(Timestamp) */
    private static final LocalDateTime INIT_Timestamp = INIT_LocalDate.atTime(0, 0, 0);

    /** email */
    @Id
    @Column(name = "email")
    private String email = INIT_String;

    /**
     * emailを取得する
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * emailを設定する
     *
     * @param email email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /** email */
    @Column(name = "regist_code")
    private String registCode = INIT_String;

    /**
     * emailを取得する
     *
     * @return email
     */
    public String getRegistCode() {
        return registCode;
    }

    /**
     * emailを設定する
     *
     * @param registCode email
     */
    public void setRegistCode(final String registCode) {
        this.registCode = registCode;
    }

    /** 有効期限日時 */
    @Column(name = "limit_datetime")
    private LocalDateTime limitDatetime = INIT_Timestamp;

    /**
     * 有効期限日時を取得する
     *
     * @return 有効期限日時
     */
    public LocalDateTime getLimitDatetime() {
        return limitDatetime;
    }

    /**
     * 有効期限日時を設定する
     *
     * @param limitDatetime 有効期限日時
     */
    public void setLimitDatetime(final LocalDateTime limitDatetime) {
        this.limitDatetime = limitDatetime;
    }

}
