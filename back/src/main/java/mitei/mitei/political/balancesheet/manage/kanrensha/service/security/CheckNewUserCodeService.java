package mitei.mitei.political.balancesheet.manage.kanrensha.service.security;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.NewComerDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.UserNewEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.UserNewRepository;

/**
 * 新規コードチェックService
 */
@Service
public class CheckNewUserCodeService {

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

        Optional<UserNewEntity> optional = userNewRepository.findById(newComerDto.getMailAddress());

        NewComerDto responseDto = new NewComerDto();
        if (optional.isEmpty()) {

            responseDto.setIsSuccess(false);
            responseDto.setMessage("データが見つかりませんでした");

        } else {
            UserNewEntity entity = optional.get();

            if (newComerDto.getMailAddress().equals(entity.getEmail())) {
                responseDto.setMailAddress(entity.getEmail());
            }
            if (newComerDto.getRegistCode().equals(entity.getRegistCode())) {
                responseDto.setRegistCode(entity.getRegistCode());
            }

            // 現在時刻が有効期限より前
            if (LocalDateTime.now().isBefore(entity.getLimitDatetime())) {
                responseDto.setLimitDateTime(newComerDto.getLimitDateTime());
            }

            responseDto.setIsSuccess(true);
            responseDto.setMessage("コードを確認できました");
            final String empty = "";
            if (empty.equals(responseDto.getRegistCode())) {
                responseDto.setIsSuccess(false);
                responseDto.setMessage("コードが一致しません");
            }
            if (LocalDateTime.of(1948, 7, 28, 0, 0, 0). // SUPPRESS CHECKSTYLE MagicNumber
                    equals(responseDto.getLimitDateTime())) {
                responseDto.setIsSuccess(false);
                responseDto.setMessage("期限切れです");
            }
        }

        return responseDto;
    }
}
