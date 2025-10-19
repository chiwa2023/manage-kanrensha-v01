package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha; // NOPMD

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPoliOrgHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory01Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory02Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory03Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory04Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory05Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory06Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory07Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory08Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory09Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory10Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory11Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory12Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory13Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory14Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory15Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory16Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory17Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory18Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory19Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory20Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory21Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory22Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory23Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory24Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory25Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory26Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory27Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory28Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory29Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory30Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory31Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory32Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory33Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory34Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory35Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory36Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory37Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory38Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory39Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory40Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory41Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory42Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory43Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory44Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory45Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory46Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory47Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory99Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.util.GetPrefectureLgCodeService;

/**
 * 関連者政治団体の同属性リスト取得Service
 */
@Service
public class GetPartnerPoliOrgSameHistoryService {

    /** 住所から県 地方公共団体コード(2桁)取得Service */
    @Autowired
    private GetPrefectureLgCodeService getPrefectureLgCodeService;

    /** 関連者企業・団体履歴Repository(01) */
    @Autowired
    private PartnerPoliOrgHistory01Repository partnerPoliOrgHistory01Repository;
    /** 関連者企業・団体履歴Repository(02) */
    @Autowired
    private PartnerPoliOrgHistory02Repository partnerPoliOrgHistory02Repository;
    /** 関連者企業・団体履歴Repository(03) */
    @Autowired
    private PartnerPoliOrgHistory03Repository partnerPoliOrgHistory03Repository;
    /** 関連者企業・団体履歴Repository(04) */
    @Autowired
    private PartnerPoliOrgHistory04Repository partnerPoliOrgHistory04Repository;
    /** 関連者企業・団体履歴Repository(05) */
    @Autowired
    private PartnerPoliOrgHistory05Repository partnerPoliOrgHistory05Repository;
    /** 関連者企業・団体履歴Repository(06) */
    @Autowired
    private PartnerPoliOrgHistory06Repository partnerPoliOrgHistory06Repository;
    /** 関連者企業・団体履歴Repository(07) */
    @Autowired
    private PartnerPoliOrgHistory07Repository partnerPoliOrgHistory07Repository;
    /** 関連者企業・団体履歴Repository(08) */
    @Autowired
    private PartnerPoliOrgHistory08Repository partnerPoliOrgHistory08Repository;
    /** 関連者企業・団体履歴Repository(09) */
    @Autowired
    private PartnerPoliOrgHistory09Repository partnerPoliOrgHistory09Repository;
    /** 関連者企業・団体履歴Repository(10) */
    @Autowired
    private PartnerPoliOrgHistory10Repository partnerPoliOrgHistory10Repository;
    /** 関連者企業・団体履歴Repository(12) */
    @Autowired
    private PartnerPoliOrgHistory11Repository partnerPoliOrgHistory11Repository;
    /** 関連者企業・団体履歴Repository(13) */
    @Autowired
    private PartnerPoliOrgHistory12Repository partnerPoliOrgHistory12Repository;
    /** 関連者企業・団体履歴Repository(14) */
    @Autowired
    private PartnerPoliOrgHistory13Repository partnerPoliOrgHistory13Repository;
    /** 関連者企業・団体履歴Repository(15) */
    @Autowired
    private PartnerPoliOrgHistory14Repository partnerPoliOrgHistory14Repository;
    /** 関連者企業・団体履歴Repository(15) */
    @Autowired
    private PartnerPoliOrgHistory15Repository partnerPoliOrgHistory15Repository;
    /** 関連者企業・団体履歴Repository(16) */
    @Autowired
    private PartnerPoliOrgHistory16Repository partnerPoliOrgHistory16Repository;
    /** 関連者企業・団体履歴Repository(17) */
    @Autowired
    private PartnerPoliOrgHistory17Repository partnerPoliOrgHistory17Repository;
    /** 関連者企業・団体履歴Repository(18) */
    @Autowired
    private PartnerPoliOrgHistory18Repository partnerPoliOrgHistory18Repository;
    /** 関連者企業・団体履歴Repository(19) */
    @Autowired
    private PartnerPoliOrgHistory19Repository partnerPoliOrgHistory19Repository;
    /** 関連者企業・団体履歴Repository(20) */
    @Autowired
    private PartnerPoliOrgHistory20Repository partnerPoliOrgHistory20Repository;
    /** 関連者企業・団体履歴Repository(21) */
    @Autowired
    private PartnerPoliOrgHistory21Repository partnerPoliOrgHistory21Repository;
    /** 関連者企業・団体履歴Repository(22) */
    @Autowired
    private PartnerPoliOrgHistory22Repository partnerPoliOrgHistory22Repository;
    /** 関連者企業・団体履歴Repository(23) */
    @Autowired
    private PartnerPoliOrgHistory23Repository partnerPoliOrgHistory23Repository;
    /** 関連者企業・団体履歴Repository(24) */
    @Autowired
    private PartnerPoliOrgHistory24Repository partnerPoliOrgHistory24Repository;
    /** 関連者企業・団体履歴Repository(25) */
    @Autowired
    private PartnerPoliOrgHistory25Repository partnerPoliOrgHistory25Repository;
    /** 関連者企業・団体履歴Repository(26) */
    @Autowired
    private PartnerPoliOrgHistory26Repository partnerPoliOrgHistory26Repository;
    /** 関連者企業・団体履歴Repository(27) */
    @Autowired
    private PartnerPoliOrgHistory27Repository partnerPoliOrgHistory27Repository;
    /** 関連者企業・団体履歴Repository(28) */
    @Autowired
    private PartnerPoliOrgHistory28Repository partnerPoliOrgHistory28Repository;
    /** 関連者企業・団体履歴Repository(29) */
    @Autowired
    private PartnerPoliOrgHistory29Repository partnerPoliOrgHistory29Repository;
    /** 関連者企業・団体履歴Repository(30) */
    @Autowired
    private PartnerPoliOrgHistory30Repository partnerPoliOrgHistory30Repository;
    /** 関連者企業・団体履歴Repository(31) */
    @Autowired
    private PartnerPoliOrgHistory31Repository partnerPoliOrgHistory31Repository;
    /** 関連者企業・団体履歴Repository(32) */
    @Autowired
    private PartnerPoliOrgHistory32Repository partnerPoliOrgHistory32Repository;
    /** 関連者企業・団体履歴Repository(33) */
    @Autowired
    private PartnerPoliOrgHistory33Repository partnerPoliOrgHistory33Repository;
    /** 関連者企業・団体履歴Repository(34) */
    @Autowired
    private PartnerPoliOrgHistory34Repository partnerPoliOrgHistory34Repository;
    /** 関連者企業・団体履歴Repository(35) */
    @Autowired
    private PartnerPoliOrgHistory35Repository partnerPoliOrgHistory35Repository;
    /** 関連者企業・団体履歴Repository(36) */
    @Autowired
    private PartnerPoliOrgHistory36Repository partnerPoliOrgHistory36Repository;
    /** 関連者企業・団体履歴Repository(37) */
    @Autowired
    private PartnerPoliOrgHistory37Repository partnerPoliOrgHistory37Repository;
    /** 関連者企業・団体履歴Repository(38) */
    @Autowired
    private PartnerPoliOrgHistory38Repository partnerPoliOrgHistory38Repository;
    /** 関連者企業・団体履歴Repository(39) */
    @Autowired
    private PartnerPoliOrgHistory39Repository partnerPoliOrgHistory39Repository;
    /** 関連者企業・団体履歴Repository(40) */
    @Autowired
    private PartnerPoliOrgHistory40Repository partnerPoliOrgHistory40Repository;
    /** 関連者企業・団体履歴Repository(41) */
    @Autowired
    private PartnerPoliOrgHistory41Repository partnerPoliOrgHistory41Repository;
    /** 関連者企業・団体履歴Repository(42) */
    @Autowired
    private PartnerPoliOrgHistory42Repository partnerPoliOrgHistory42Repository;
    /** 関連者企業・団体履歴Repository(43) */
    @Autowired
    private PartnerPoliOrgHistory43Repository partnerPoliOrgHistory43Repository;
    /** 関連者企業・団体履歴Repository(44) */
    @Autowired
    private PartnerPoliOrgHistory44Repository partnerPoliOrgHistory44Repository;
    /** 関連者企業・団体履歴Repository(45) */
    @Autowired
    private PartnerPoliOrgHistory45Repository partnerPoliOrgHistory45Repository;
    /** 関連者企業・団体履歴Repository(46) */
    @Autowired
    private PartnerPoliOrgHistory46Repository partnerPoliOrgHistory46Repository;
    /** 関連者企業・団体履歴Repository(47) */
    @Autowired
    private PartnerPoliOrgHistory47Repository partnerPoliOrgHistory47Repository;
    /** 関連者企業・団体履歴Repository(99) */
    @Autowired
    private PartnerPoliOrgHistory99Repository partnerPoliOrgHistory99Repository;

    /**
     * 処理を行う
     *
     * @param name     団体名
     * @param address  住所
     * @param delegate 代表者名
     * @return 検索結果
     */
    public List<PartnerPoliOrgHistoryBaseEntity> practice( // SUPPRESS CHECKSTYLE ReturnCount NOPMD
            final String name, final String address, final String delegate) {

        switch (getPrefectureLgCodeService.practice(address)) {
            case GetPrefectureLgCodeService.PREF_01:
                return partnerPoliOrgHistory01Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_02:
                return partnerPoliOrgHistory02Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_03:
                return partnerPoliOrgHistory03Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_04:
                return partnerPoliOrgHistory04Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_05:
                return partnerPoliOrgHistory05Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_06:
                return partnerPoliOrgHistory06Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_07:
                return partnerPoliOrgHistory07Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_08:
                return partnerPoliOrgHistory08Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_09:
                return partnerPoliOrgHistory09Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_10:
                return partnerPoliOrgHistory10Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_11:
                return partnerPoliOrgHistory11Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_12:
                return partnerPoliOrgHistory12Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_13:
                return partnerPoliOrgHistory13Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_14:
                return partnerPoliOrgHistory14Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_15:
                return partnerPoliOrgHistory15Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_16:
                return partnerPoliOrgHistory16Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_17:
                return partnerPoliOrgHistory17Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_18:
                return partnerPoliOrgHistory18Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_19:
                return partnerPoliOrgHistory19Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_20:
                return partnerPoliOrgHistory20Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_21:
                return partnerPoliOrgHistory21Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_22:
                return partnerPoliOrgHistory22Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_23:
                return partnerPoliOrgHistory23Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_24:
                return partnerPoliOrgHistory24Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_25:
                return partnerPoliOrgHistory25Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_26:
                return partnerPoliOrgHistory26Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_27:
                return partnerPoliOrgHistory27Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_28:
                return partnerPoliOrgHistory28Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_29:
                return partnerPoliOrgHistory29Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_30:
                return partnerPoliOrgHistory30Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_31:
                return partnerPoliOrgHistory31Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_32:
                return partnerPoliOrgHistory32Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_33:
                return partnerPoliOrgHistory33Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_34:
                return partnerPoliOrgHistory34Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_35:
                return partnerPoliOrgHistory35Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_36:
                return partnerPoliOrgHistory36Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_37:
                return partnerPoliOrgHistory37Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_38:
                return partnerPoliOrgHistory38Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_39:
                return partnerPoliOrgHistory39Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_40:
                return partnerPoliOrgHistory40Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_41:
                return partnerPoliOrgHistory41Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_42:
                return partnerPoliOrgHistory42Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_43:
                return partnerPoliOrgHistory43Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_44:
                return partnerPoliOrgHistory44Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_45:
                return partnerPoliOrgHistory45Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_46:
                return partnerPoliOrgHistory46Repository.selectByProperty(name, address, delegate);
            case GetPrefectureLgCodeService.PREF_47:
                return partnerPoliOrgHistory47Repository.selectByProperty(name, address, delegate);
            default:
                return partnerPoliOrgHistory99Repository.selectByProperty(name, address, delegate);
        }
    }

}
