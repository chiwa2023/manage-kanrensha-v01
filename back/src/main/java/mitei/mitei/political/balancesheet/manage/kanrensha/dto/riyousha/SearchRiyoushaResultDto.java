package mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaAdminEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaComradeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerEntity;

/**
 * 利用者検索結果Dto
 */
public class SearchRiyoushaResultDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** APIユーザリスト */
    private List<RiyoushaComradeEntity> listComrade = new ArrayList<>();

    /** 運営者リスト */
    private List<RiyoushaManagerEntity> listManager = new ArrayList<>();

    /** 管理者リスト */
    private List<RiyoushaAdminEntity> listAdmin = new ArrayList<>();

    /**
     * APIユーザリストを取得する
     *
     * @return APIユーザリスト
     */
    public List<RiyoushaComradeEntity> getListComrade() {
        return listComrade;
    }

    /**
     * APIユーザリストを設定する
     *
     * @param listComrade APIユーザリスト
     */
    public void setListComrade(final List<RiyoushaComradeEntity> listComrade) {
        this.listComrade = listComrade;
    }

    /**
     * 運営者リストを取得する
     *
     * @return 運営者リスト
     */
    public List<RiyoushaManagerEntity> getListManager() {
        return listManager;
    }

    /**
     * 運営者リストを設定する
     *
     * @param listManager 運営者リスト
     */
    public void setListManager(final List<RiyoushaManagerEntity> listManager) {
        this.listManager = listManager;
    }

    /**
     * 管理者リストを取得する
     *
     * @return 管理者リスト
     */
    public List<RiyoushaAdminEntity> getListAdmin() {
        return listAdmin;
    }

    /**
     * 管理者リストを設定する
     *
     * @param listAdmin 管理者リスト
     */
    public void setListAdmin(final List<RiyoushaAdminEntity> listAdmin) {
        this.listAdmin = listAdmin;
    }

}
