package mitei.mitei.political.balancesheet.manage.kanrensha.logic.operate_user;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.OperateUserEntity;

@Component
public class InsertUserOperatorLogic {

    
    public void practice(final UserDetails user) {
        
        OperateUserEntity operateUserEntity = new OperateUserEntity();
        BeanUtils.copyProperties(user, operateUserEntity);
        
        
    }
}
