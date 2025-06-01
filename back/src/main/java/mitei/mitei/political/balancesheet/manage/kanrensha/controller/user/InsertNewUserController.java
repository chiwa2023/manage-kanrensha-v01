package mitei.mitei.political.balancesheet.manage.kanrensha.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.LoginUserResultDto;

@RestController
@RequestMapping("/add-user")
public class InsertNewUserController {

    @PostMapping("/new-comer")
    public ResponseEntity<LoginUserResultDto> practice() {
        
        
        
        
        return null;
    }

}
