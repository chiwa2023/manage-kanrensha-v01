package mitei.mitei.political.balancesheet.manage.kanrensha.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.JwtTokenDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.LoginUserCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.LoginUserResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.security.GetLeastUserByMailService;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.security.JwtService;

/**
 * ログイン処理Controller ユーザ情報に基づき、JWTトークンとUser情報を変返却する
 */
@RestController
public class LoginUserOperatorController {

    /** 認証プロバイダ */
    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    /** JWTトークン生成Service */
    @Autowired
    private JwtService jwtService;

    /** ユーザ最低限Service */
    @Autowired
    private GetLeastUserByMailService getLeastUserByMailService;

    /**
     * 処理を行う
     *
     * @param capsuleDto ログイン情報Dto
     * @return ログイン結果Dto
     */
    @PostMapping("/login")
    public ResponseEntity<LoginUserResultDto> practice(final @RequestBody LoginUserCapsuleDto capsuleDto) {
        try {

            // ログイン処理
            Authentication authentication = daoAuthenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(capsuleDto.getUserId(), capsuleDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // トークン生成処理
            JwtTokenDto jwtToken = jwtService.generateToken(authentication);

            // ユーザ情報は別途取得する
            LoginUserResultDto resultDto = new LoginUserResultDto();
            resultDto.setJwtTokenDto(jwtToken);
            resultDto.setUserPersonLeastDto(getLeastUserByMailService.practice(capsuleDto.getUserId(), authentication));

            return ResponseEntity.status(HttpStatus.OK).body(resultDto);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

}
