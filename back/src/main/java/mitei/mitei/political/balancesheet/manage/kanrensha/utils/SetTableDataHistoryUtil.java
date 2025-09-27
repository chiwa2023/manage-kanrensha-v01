package mitei.mitei.political.balancesheet.manage.kanrensha.utils;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AllTabeDataHistoryInterface;

/**
 * テーブルの更新履歴に必要なログインユーザ、更新時間情報をセットする
 */
@Component
public class SetTableDataHistoryUtil {

    /** 最新状態値 */
    public static final boolean INSERT_STATE = true;

    /** 履歴状態値 */
    public static final boolean DELETE_STATE = false;

    /**
     * データ履歴カラムにデータを入力する
     *
     * @param userDto        ユーザDto
     * @param interfaceImple データ履歴カラムInterface
     */
    public void practiceInsert(final UserPersonLeastDto userDto, final AllTabeDataHistoryInterface interfaceImple) {

        LocalDateTime timestamp = LocalDateTime.now();

        // Insert(初回)データセット

        interfaceImple.setIsLatest(INSERT_STATE);
        interfaceImple.setInsertUserId(userDto.getUserPersonId());
        interfaceImple.setInsertUserCode(userDto.getUserPersonCode());
        interfaceImple.setInsertUserName(userDto.getUserPersonName());
        interfaceImple.setInsertTimestamp(timestamp);
    }

    /**
     * データ履歴カラムにデータを入力する
     *
     * @param userDto        ユーザDto
     * @param interfaceImple データ履歴カラムInterface
     */
    public void practiceDelete(final UserPersonLeastDto userDto, final AllTabeDataHistoryInterface interfaceImple) {

        LocalDateTime timestamp = LocalDateTime.now();

        // Delete(無効)データセット
        // NOTE どうしても更新してもInsertを維持したい場合は別メソッドとする
        interfaceImple.setIsLatest(DELETE_STATE);
        interfaceImple.setDeleteUserId(userDto.getUserPersonId());
        interfaceImple.setDeleteUserCode(userDto.getUserPersonCode());
        interfaceImple.setDeleteUserName(userDto.getUserPersonName());
        interfaceImple.setDeleteTimestamp(timestamp);
    }
}
