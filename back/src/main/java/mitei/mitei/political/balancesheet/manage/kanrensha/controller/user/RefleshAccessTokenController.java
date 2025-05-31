package mitei.mitei.political.balancesheet.manage.kanrensha.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.JwtTokenDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.security.CustomUserDetailsManager;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.security.JwtService;

/**
 * アクセストークンを更新Controller
 */
@RestController
public class RefleshAccessTokenController {

    /** JwtDecoder */
    @Autowired
    private JwtDecoder jwtDecoder;

    /** CustomUserDetailsManager */
    @Autowired
    private CustomUserDetailsManager customUserDetailsManager;

    /** JwtService */
    @Autowired
    private JwtService jwtService;

    /**
     * 処理を行う
     *
     * @param tokenDto トークンDto
     * @return 更新されたToken
     */
    @PostMapping("/reflesh-token")
    public ResponseEntity<JwtTokenDto> practice(final @RequestBody JwtTokenDto tokenDto) {

        Jwt jwt = jwtDecoder.decode(tokenDto.getRefreshToken());

        // サブジェクトからユーザー名を取得
        String username = jwt.getSubject();

        // ユーザー情報の取得
        UserDetails userDetails = customUserDetailsManager.loadUserByUsername(username);

        // 新しいトークンの生成
        JwtTokenDto jwtToken = jwtService.generateToken(userDetails);

        return ResponseEntity.status(HttpStatus.OK).body(jwtToken);
    }

}
