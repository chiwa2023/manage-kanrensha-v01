import type JwtTokenInterface from "./jwtTokenDto";
import JwtTokenDto from "./jwtTokenDto";

export default async function getAuthorizedPromiseArea(): Promise<string | undefined> {
    const KEY_JWT:string = "jwtToken";

    // 保存していたアクセストークンと有効期限を取得
    const sessionStorage = window["sessionStorage"];

    const jwtText: string | null = sessionStorage.getItem(KEY_JWT);
    let jwtToken: JwtTokenInterface = new JwtTokenDto();
    if (jwtText !== null) {
        jwtToken = JSON.parse(jwtText);
    }

    // 有効期限の××分(秒?)前ならアクセス可能としてアクセストークンを使用する
    const dateExpires: Date = new Date(jwtToken.expiresAt);
    dateExpires.setMinutes(dateExpires.getMinutes() - 1);
    if (new Date() < dateExpires) {
        return jwtToken.accessToken;
    }

    // アクセストークンが期限切れ(直前)で保存出来ていない場合は
    // リフレッシュトークンを使ってアクセストークンを再取得
    const url = "http://localhost:6080/reflesh-token";
    const method = "POST";
    const body = JSON.stringify(jwtToken);
    const headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
    };
    const response = await fetch(url, { method, headers, body });
    if (response.status === 200) {
        const jwt: JwtTokenDto = await response.json();
        sessionStorage.setItem(KEY_JWT, JSON.stringify(jwt));
        return jwt.accessToken;
    }
    // とにかくステータス200でなければアクセスさせない
    // 今入っているユーザ情報、トークンもクリア
    sessionStorage.clear();
    return ""; // ログインページに飛ばす
}



