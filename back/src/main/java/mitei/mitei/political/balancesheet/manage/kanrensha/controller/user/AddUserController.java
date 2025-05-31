package mitei.mitei.political.balancesheet.manage.kanrensha.controller.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.JwtTokenDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.LoginUserResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.NewComerDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.UserPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.UserPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.security.AddUserService;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.security.JwtService;

/**
 * ユーザ追加Controller
 */
@RestController
@RequestMapping("/add-user")
public class AddUserController {

    /** 認証プロバイダ */
    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    /** JWTトークン生成Service */
    @Autowired
    private JwtService jwtService;

    /** ユーザRepository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** ユーザ追加サービス */
    @Autowired
    private AddUserService addUserService;

    /**
     * 処理を行う
     *
     * @param newComerDto 新規ユーザDto
     * @return ログインDto
     */
    @PostMapping("/user")
    public ResponseEntity<LoginUserResultDto> practice(final @RequestBody NewComerDto newComerDto) {

        NewComerDto responseDto = addUserService.practice(newComerDto);

        try {
            // ログイン処理
            Authentication authentication = daoAuthenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(responseDto.getMailAddress(), responseDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // トークン生成処理
            JwtTokenDto jwtToken = jwtService.generateToken(authentication);
            
            LoginUserResultDto resultDto = new LoginUserResultDto();
            resultDto.setJwtTokenDto(jwtToken);

            // ユーザ情報は別途取得する
            Optional<UserPersonEntity> optional = userPersonRepository.findLatestByMail(responseDto.getMailAddress());
            UserPersonLeastDto personDto = new UserPersonLeastDto();
            if (!optional.isEmpty()) {
                BeanUtils.copyProperties(optional.get(), personDto);
            }
            
            List<String> listAuh = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            personDto.setListRoles(listAuh);

            resultDto.setUserPersonLeastDto(personDto);

            return ResponseEntity.status(HttpStatus.OK).body(resultDto);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
