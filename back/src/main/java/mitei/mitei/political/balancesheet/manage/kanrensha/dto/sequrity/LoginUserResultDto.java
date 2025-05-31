package mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity;

import java.io.Serializable;

/**
 * ログイン結果Dto
 */
public class LoginUserResultDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** Jwtトークン */
    private JwtTokenDto jwtTokenDto;

    /** ユーザ最低限Dto */
    private UserPersonLeastDto userPersonLeastDto = new UserPersonLeastDto();

    /**
     * ユーザ最低限Dtoを取得する
     *
     * @return ユーザ最低限Dto
     */
    public UserPersonLeastDto getUserPersonLeastDto() {
        return userPersonLeastDto;
    }

    /**
     * ユーザ最低限Dtoを設定する
     *
     * @param userPersonLeastDto ユーザ最低限Dto
     */
    public void setUserPersonLeastDto(final UserPersonLeastDto userPersonLeastDto) {
        this.userPersonLeastDto = userPersonLeastDto;
    }

    /**
     * Jwtトークンを取得する
     *
     * @return Jwtトークン
     */
    public JwtTokenDto getJwtTokenDto() {
        return jwtTokenDto;
    }

    /**
     * Jwtトークンを設定する
     *
     * @param jwtTokenDto Jwtトークン
     */
    public void setJwtTokenDto(final JwtTokenDto jwtTokenDto) {
        this.jwtTokenDto = jwtTokenDto;
    }

}
