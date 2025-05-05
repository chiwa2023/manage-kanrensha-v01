package mitei.mitei.political.balancesheet.manage.kanrensha.utils;

import java.time.LocalDateTime;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.commom_check.CheckPrivilegeDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AllTabeDataHistoryInterface;

/**
 * テーブルの更新履歴に必要なログインユーザ、更新時間情報をセットする
 */
public final class SetTableDataHistoryUtil {

    /** 最新状態値 */
    public static final short INSERT_STATE = 1;

    /** 履歴状態値 */
    public static final short UPDATE_STATE = 0;

    // インスタンス生成よけ
    private SetTableDataHistoryUtil() {

    }

    /**
     * データ履歴カラムにデータを入力する
     *
     * @param checkPrivilegeDto 権限チェックDto
     * @param interfaceImple    データ履歴カラムInterface
     * @param status            データ履歴ステータス
     */
    public static void practice(final CheckPrivilegeDto checkPrivilegeDto,
            final AllTabeDataHistoryInterface interfaceImple, final int status) {

        LocalDateTime timestamp = LocalDateTime.now();

        // Insert(初回)データセット
        if (INSERT_STATE == status) {

            interfaceImple.setLatestKbn(INSERT_STATE);
            interfaceImple.setInsertUserId(checkPrivilegeDto.getLoginUserId());
            interfaceImple.setInsertUserCode(checkPrivilegeDto.getLoginUserCode());
            interfaceImple.setInsertUserName(checkPrivilegeDto.getLoginUserName());
            interfaceImple.setInsertTimestamp(timestamp);
        }

        // Update(更新)データセット
        if (UPDATE_STATE == status) {
            // NOTE どうしても更新してもInsertを維持したい場合は別メソッドとする
            interfaceImple.setLatestKbn(UPDATE_STATE);
            interfaceImple.setUpdateUserId(checkPrivilegeDto.getLoginUserId());
            interfaceImple.setUpdateUserCode(checkPrivilegeDto.getLoginUserCode());
            interfaceImple.setUpdateUserName(checkPrivilegeDto.getLoginUserName());
            interfaceImple.setUpdateTimestamp(timestamp);
        }

    }
}
