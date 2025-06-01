package mitei.mitei.political.balancesheet.manage.kanrensha.service.security;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.NewComerDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.UserNewEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.UserNewRepository;

/**
 * 新規ユーザ用仮コード発行Service
 */
@Service
public class PublishNewUserCodeService {

    /** Logger */
    private final Logger log = LoggerFactory.getLogger(PublishNewUserCodeService.class);
    
    
    /** 新規登録中ユーザRepository */
    @Autowired
    private UserNewRepository userNewRepository;

    /**
     * 処理を行う
     *
     * @param newComerDto 新規ユーザDto
     * @return コード入力したDto
     */
    public NewComerDto practice(final NewComerDto newComerDto) {
        
        log.info("有効期限"+newComerDto.getLimitDateTime());
        
        // エンティティにセットして登録
        UserNewEntity userNewEntity = new UserNewEntity();
        userNewEntity.setEmail(newComerDto.getMailAddress());
        userNewEntity.setRegistCode(UUID.randomUUID().toString());
        userNewEntity.setLimitDatetime(newComerDto.getLimitDateTime());
        userNewRepository.save(userNewEntity);

        // 返却Dtoに複写
        NewComerDto responseDto = new NewComerDto();
        responseDto.setMailAddress(userNewEntity.getEmail());
        responseDto.setRegistCode(userNewEntity.getRegistCode());
        responseDto.setLimitDateTime(userNewEntity.getLimitDatetime());
        
        return responseDto;
    }
}
