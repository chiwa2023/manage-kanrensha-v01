package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha; // NOPMD

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPersonHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory01Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory02Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory03Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory04Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory05Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory06Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory07Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory08Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory09Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory10Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory11Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory12Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory13Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory14Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory15Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory16Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory17Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory18Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory19Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory20Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory21Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory22Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory23Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory24Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory25Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory26Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory27Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory28Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory29Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory30Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory31Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory32Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory33Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory34Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory35Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory36Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory37Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory38Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory39Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory40Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory41Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory42Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory43Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory44Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory45Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory46Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory47Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory99Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.util.GetPrefectureLgCodeService;

/**
 * 関連者個人の同属性リスト取得Service
 */
@Service
public class GetPartnerPersonSameHistoryService {

    /** 住所から県 地方公共団体コード(2桁)取得Service */
    @Autowired
    private GetPrefectureLgCodeService getPrefectureLgCodeService;

    /** 関連者企業・団体履歴Repository(01) */
    @Autowired
    private PartnerPersonHistory01Repository partnerPersonHistory01Repository;
    /** 関連者企業・団体履歴Repository(02) */
    @Autowired
    private PartnerPersonHistory02Repository partnerPersonHistory02Repository;
    /** 関連者企業・団体履歴Repository(03) */
    @Autowired
    private PartnerPersonHistory03Repository partnerPersonHistory03Repository;
    /** 関連者企業・団体履歴Repository(04) */
    @Autowired
    private PartnerPersonHistory04Repository partnerPersonHistory04Repository;
    /** 関連者企業・団体履歴Repository(05) */
    @Autowired
    private PartnerPersonHistory05Repository partnerPersonHistory05Repository;
    /** 関連者企業・団体履歴Repository(06) */
    @Autowired
    private PartnerPersonHistory06Repository partnerPersonHistory06Repository;
    /** 関連者企業・団体履歴Repository(07) */
    @Autowired
    private PartnerPersonHistory07Repository partnerPersonHistory07Repository;
    /** 関連者企業・団体履歴Repository(08) */
    @Autowired
    private PartnerPersonHistory08Repository partnerPersonHistory08Repository;
    /** 関連者企業・団体履歴Repository(09) */
    @Autowired
    private PartnerPersonHistory09Repository partnerPersonHistory09Repository;
    /** 関連者企業・団体履歴Repository(10) */
    @Autowired
    private PartnerPersonHistory10Repository partnerPersonHistory10Repository;
    /** 関連者企業・団体履歴Repository(12) */
    @Autowired
    private PartnerPersonHistory11Repository partnerPersonHistory11Repository;
    /** 関連者企業・団体履歴Repository(13) */
    @Autowired
    private PartnerPersonHistory12Repository partnerPersonHistory12Repository;
    /** 関連者企業・団体履歴Repository(14) */
    @Autowired
    private PartnerPersonHistory13Repository partnerPersonHistory13Repository;
    /** 関連者企業・団体履歴Repository(15) */
    @Autowired
    private PartnerPersonHistory14Repository partnerPersonHistory14Repository;
    /** 関連者企業・団体履歴Repository(15) */
    @Autowired
    private PartnerPersonHistory15Repository partnerPersonHistory15Repository;
    /** 関連者企業・団体履歴Repository(16) */
    @Autowired
    private PartnerPersonHistory16Repository partnerPersonHistory16Repository;
    /** 関連者企業・団体履歴Repository(17) */
    @Autowired
    private PartnerPersonHistory17Repository partnerPersonHistory17Repository;
    /** 関連者企業・団体履歴Repository(18) */
    @Autowired
    private PartnerPersonHistory18Repository partnerPersonHistory18Repository;
    /** 関連者企業・団体履歴Repository(19) */
    @Autowired
    private PartnerPersonHistory19Repository partnerPersonHistory19Repository;
    /** 関連者企業・団体履歴Repository(20) */
    @Autowired
    private PartnerPersonHistory20Repository partnerPersonHistory20Repository;
    /** 関連者企業・団体履歴Repository(21) */
    @Autowired
    private PartnerPersonHistory21Repository partnerPersonHistory21Repository;
    /** 関連者企業・団体履歴Repository(22) */
    @Autowired
    private PartnerPersonHistory22Repository partnerPersonHistory22Repository;
    /** 関連者企業・団体履歴Repository(23) */
    @Autowired
    private PartnerPersonHistory23Repository partnerPersonHistory23Repository;
    /** 関連者企業・団体履歴Repository(24) */
    @Autowired
    private PartnerPersonHistory24Repository partnerPersonHistory24Repository;
    /** 関連者企業・団体履歴Repository(25) */
    @Autowired
    private PartnerPersonHistory25Repository partnerPersonHistory25Repository;
    /** 関連者企業・団体履歴Repository(26) */
    @Autowired
    private PartnerPersonHistory26Repository partnerPersonHistory26Repository;
    /** 関連者企業・団体履歴Repository(27) */
    @Autowired
    private PartnerPersonHistory27Repository partnerPersonHistory27Repository;
    /** 関連者企業・団体履歴Repository(28) */
    @Autowired
    private PartnerPersonHistory28Repository partnerPersonHistory28Repository;
    /** 関連者企業・団体履歴Repository(29) */
    @Autowired
    private PartnerPersonHistory29Repository partnerPersonHistory29Repository;
    /** 関連者企業・団体履歴Repository(30) */
    @Autowired
    private PartnerPersonHistory30Repository partnerPersonHistory30Repository;
    /** 関連者企業・団体履歴Repository(31) */
    @Autowired
    private PartnerPersonHistory31Repository partnerPersonHistory31Repository;
    /** 関連者企業・団体履歴Repository(32) */
    @Autowired
    private PartnerPersonHistory32Repository partnerPersonHistory32Repository;
    /** 関連者企業・団体履歴Repository(33) */
    @Autowired
    private PartnerPersonHistory33Repository partnerPersonHistory33Repository;
    /** 関連者企業・団体履歴Repository(34) */
    @Autowired
    private PartnerPersonHistory34Repository partnerPersonHistory34Repository;
    /** 関連者企業・団体履歴Repository(35) */
    @Autowired
    private PartnerPersonHistory35Repository partnerPersonHistory35Repository;
    /** 関連者企業・団体履歴Repository(36) */
    @Autowired
    private PartnerPersonHistory36Repository partnerPersonHistory36Repository;
    /** 関連者企業・団体履歴Repository(37) */
    @Autowired
    private PartnerPersonHistory37Repository partnerPersonHistory37Repository;
    /** 関連者企業・団体履歴Repository(38) */
    @Autowired
    private PartnerPersonHistory38Repository partnerPersonHistory38Repository;
    /** 関連者企業・団体履歴Repository(39) */
    @Autowired
    private PartnerPersonHistory39Repository partnerPersonHistory39Repository;
    /** 関連者企業・団体履歴Repository(40) */
    @Autowired
    private PartnerPersonHistory40Repository partnerPersonHistory40Repository;
    /** 関連者企業・団体履歴Repository(41) */
    @Autowired
    private PartnerPersonHistory41Repository partnerPersonHistory41Repository;
    /** 関連者企業・団体履歴Repository(42) */
    @Autowired
    private PartnerPersonHistory42Repository partnerPersonHistory42Repository;
    /** 関連者企業・団体履歴Repository(43) */
    @Autowired
    private PartnerPersonHistory43Repository partnerPersonHistory43Repository;
    /** 関連者企業・団体履歴Repository(44) */
    @Autowired
    private PartnerPersonHistory44Repository partnerPersonHistory44Repository;
    /** 関連者企業・団体履歴Repository(45) */
    @Autowired
    private PartnerPersonHistory45Repository partnerPersonHistory45Repository;
    /** 関連者企業・団体履歴Repository(46) */
    @Autowired
    private PartnerPersonHistory46Repository partnerPersonHistory46Repository;
    /** 関連者企業・団体履歴Repository(47) */
    @Autowired
    private PartnerPersonHistory47Repository partnerPersonHistory47Repository;
    /** 関連者企業・団体履歴Repository(99) */
    @Autowired
    private PartnerPersonHistory99Repository partnerPersonHistory99Repository;

    /**
     * 処理を行う
     *
     * @param name      個人名
     * @param address   住所
     * @param shokugyou 個人職業
     * @return 検索結果
     */
    public List<PartnerPersonHistoryBaseEntity> practice( // SUPPRESS CHECKSTYLE ReturnCount NOPMD
            final String name, final String address, final String shokugyou) {

        switch (getPrefectureLgCodeService.practice(address)) {
            case GetPrefectureLgCodeService.PREF_01:
                return partnerPersonHistory01Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_02:
                return partnerPersonHistory02Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_03:
                return partnerPersonHistory03Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_04:
                return partnerPersonHistory04Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_05:
                return partnerPersonHistory05Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_06:
                return partnerPersonHistory06Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_07:
                return partnerPersonHistory07Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_08:
                return partnerPersonHistory08Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_09:
                return partnerPersonHistory09Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_10:
                return partnerPersonHistory10Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_11:
                return partnerPersonHistory11Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_12:
                return partnerPersonHistory12Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_13:
                return partnerPersonHistory13Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_14:
                return partnerPersonHistory14Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_15:
                return partnerPersonHistory15Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_16:
                return partnerPersonHistory16Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_17:
                return partnerPersonHistory17Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_18:
                return partnerPersonHistory18Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_19:
                return partnerPersonHistory19Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_20:
                return partnerPersonHistory20Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_21:
                return partnerPersonHistory21Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_22:
                return partnerPersonHistory22Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_23:
                return partnerPersonHistory23Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_24:
                return partnerPersonHistory24Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_25:
                return partnerPersonHistory25Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_26:
                return partnerPersonHistory26Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_27:
                return partnerPersonHistory27Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_28:
                return partnerPersonHistory28Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_29:
                return partnerPersonHistory29Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_30:
                return partnerPersonHistory30Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_31:
                return partnerPersonHistory31Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_32:
                return partnerPersonHistory32Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_33:
                return partnerPersonHistory33Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_34:
                return partnerPersonHistory34Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_35:
                return partnerPersonHistory35Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_36:
                return partnerPersonHistory36Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_37:
                return partnerPersonHistory37Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_38:
                return partnerPersonHistory38Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_39:
                return partnerPersonHistory39Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_40:
                return partnerPersonHistory40Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_41:
                return partnerPersonHistory41Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_42:
                return partnerPersonHistory42Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_43:
                return partnerPersonHistory43Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_44:
                return partnerPersonHistory44Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_45:
                return partnerPersonHistory45Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_46:
                return partnerPersonHistory46Repository.selectByProperty(name, address, shokugyou);
            case GetPrefectureLgCodeService.PREF_47:
                return partnerPersonHistory47Repository.selectByProperty(name, address, shokugyou);
            default:
                return partnerPersonHistory99Repository.selectByProperty(name, address, shokugyou);
        }
    }

}
