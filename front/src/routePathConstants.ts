export default class RoutePathConstants {

    /** ログインページ */
    static readonly PAGE_LOGIN: string = "/";

    /** 開発台紙 */
    static readonly PAGE_COMPONENT: string = "/component";

    /** SE権限メニュー */
    static readonly PAGE_MENU_ADMIN: string = "/menu-admin";

    /** 管理者メニュー */
    static readonly PAGE_MENU_MANAGER: string = "/menu-manager";

    /** APIユーザメニュー */
    static readonly PAGE_MENU_COMRADE: string = "/menu-comrade";

    /** 関連者メニュー */
    static readonly PAGE_MENU_PARTNER: string = "/menu-partner";

    /** 全ユーザ共通メニュー */
    static readonly PAGE_MENU_ALL_USER: string = "/menu-user-all";

    /** 郵便番号建物入力 */
    static readonly PAGE_POSTAL_BUILDING: string = "/postal-code-building";

    /** 郵便番号編集 */
    static readonly PAGE_POSTAL_EDIT: string = "/postal-code-edit";

    /** 郵便番号差分 */
    static readonly PAGE_POSTAL_SABUN: string = "/postal-code-sabun";

    /** アドレス・ベース・レジストリ編集 */
    static readonly PAGE_ADDRESS_REGI_EDIT: string = "/address-registry=edit";

    /** アドレス・ベース・レジストリ差分 */
    static readonly PAGE_ADDRESS_REGI_SABUN: string = "/address=registry-sabun";

    /** 関連者企業・団体履歴一括登録 */
    static readonly PAGE_REGI_BULK_HISTORY_CORP: string = "/bulk-history-corp";
    /** 関連者個人履歴一括登録 */
    static readonly PAGE_REGI_BULK_HISTORY_PERSON: string = "/bulk-history-person";
    /** 関連者政治団体履歴一括登録 */
    static readonly PAGE_REGI_BULK_HISTORY_POLI_ORG: string = "/bulk-history-poli-org";

    /** 関連者企業・団体マスタ一括登録 */
    static readonly PAGE_REGI_BULK_MASTER_CORP: string = "/bulk-master-corp";
    /** 関連者個人マスタ一括登録 */
    static readonly PAGE_REGI_BULK_MASTER_PERSON: string = "/bulk-master-person";
    /** 関連者政治団体マスタ一括登録 */
    static readonly PAGE_REGI_BULK_MASTER_POLI_ORG: string = "/bulk-master-poli-org";

    /** 関連者マスタ一ダンプ(指定期間まで) */
    static readonly PAGE_DUMP_MASTER: string = "/dump-master";
    /** 関連者履歴一ダンプ(指定期間まで) */
    static readonly PAGE_DUMP_HISTORY: string = "/dump-history";
    /** 関連者マスタ一標準ダンプ(指定期間まで) */
    static readonly PAGE_DUMP_MASTER_STD: string = "/dump-master-std";

    /** 関連者マスタ一差分ダンプ */
    static readonly PAGE_DUMP_SABUN_MASTER: string = "/dump-sabun-master";
    /** 関連者履歴一差分ダンプ */
    static readonly PAGE_DUMP_SABUN_HISTORY: string = "/dump-sabun-history";
    /** 関連者マスタ一(標準)差分ダンプ */
    static readonly PAGE_DUMP_SABUN_MASTER_STD: string = "/dump-sabun-master-std";

    /** 関連者XML編集登録 */
    static readonly PAGE_ADD_XML: string = "/add-by-xml";

    /** 関連者マスタ標準データダウンロード */
    static readonly PAGE_DOWNLOAD_MASTER_STD: string = "/download-master-std";

    /** 関連者最小標準データダウンロード */
    static readonly PAGE_DOWNLOAD_MASTER_MIN: string = "/download-master-min";

    /** 関連者履歴データダウンロード */
    static readonly PAGE_DOWNLOAD_HISTORY: string = "/download-history";

    /** 関連者マスタ標準データ差分ダウンロード */
    static readonly PAGE_DOWNLOAD_SABUN_MASTER_STD: string = "/sabun-master-std";

    /** 関連者最小標準データ差分ダウンロード */
    static readonly PAGE_DOWNLOAD_SABUN_MASTER_MIN: string = "/sabun-master-min";

    /** 関連者履歴データ差分ダウンロード */
    static readonly PAGE_DOWNLOAD_SABUN_HISTORY: string = "/sabun-history";

    /** 関連者個人－企業／団体紐づけ登録 */
    static readonly PAGE_REGI_COMBINE_CORP: string = "/combine-corp";

    /** 関連者個人－企業／団体紐づけ登録 */
    static readonly PAGE_REGI_COMBINE_POLI_ORG: string = "/combine-poli-org";

    /** 管理者による関連者管理 */
    static readonly PAGE_REGI_PARTNER_MANAGE: string = "/partner-manage";

    /** 本人による関連者編集 */
    static readonly PAGE_REGI_PARTNER_EDIT: string = "/partner-edit";

    /** 作業内容承認 */
    static readonly PAGE_WORKS_APPROVAL: string = "/works-approval";

    /** 作業内容承認 */
    static readonly PAGE_SEARCH_RIYOUSHA: string = "/search-riyousha";


}
