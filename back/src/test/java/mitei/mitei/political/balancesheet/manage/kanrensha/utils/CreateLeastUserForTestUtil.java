package mitei.mitei.political.balancesheet.manage.kanrensha.utils;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;

/**
 * テスト専用操作者ユーザ最小限を生成する
 */
public final class CreateLeastUserForTestUtil {

    /**
     * インスタンス生成よけ
     */
    private CreateLeastUserForTestUtil() {

    }

    /**
     * 処理を行う
     *
     * @return テスト専用操作者ユーザ最小限
     */
    public static UserPersonLeastDto practice() {

        UserPersonLeastDto dto = new UserPersonLeastDto();

        final int personId = 196;
        final int personCode = 190;

        dto.setUserPersonId(personId);
        dto.setUserPersonCode(personCode);
        dto.setUserPersonName("管理人　太郎");

        return dto;
    }
}
