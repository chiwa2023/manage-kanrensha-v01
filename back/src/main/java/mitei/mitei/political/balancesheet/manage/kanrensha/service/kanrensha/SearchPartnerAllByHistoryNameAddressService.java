package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha; // NOPMD

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.partner.PartnerCommonInfoDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory01Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory02Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory03Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory04Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory05Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory06Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory07Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory08Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory09Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory10Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory11Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory12Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory13Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory14Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory15Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory16Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory17Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory18Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory19Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory20Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory21Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory22Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory23Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory24Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory25Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory26Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory27Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory28Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory29Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory30Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory31Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory32Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory33Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory34Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory35Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory36Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory37Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory38Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory39Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory40Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory41Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory42Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory43Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory44Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory45Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory46Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory47Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory99Repository;
//import mitei.mitei.political.balancesheet.manage.kanrensha.service.util.GetPrefectureLgCodeService;

/**
 * 関連者を個人と企業・団体と政治団体を対象に一括検索Service
 */
@Service
public class SearchPartnerAllByHistoryNameAddressService {

//    /** 住所から県 地方公共団体コード(2桁)取得Service */
//    @Autowired
//    private GetPrefectureLgCodeService getPrefectureLgCodeService;

    /** 関連者企業・団体履歴Repository(01) */
    @Autowired
    private PartnerCorpHistory01Repository partnerCorpHistory01Repository;
//    /** 関連者企業・団体履歴Repository(02) */
//    @Autowired
//    private PartnerCorpHistory02Repository partnerCorpHistory02Repository;
//    /** 関連者企業・団体履歴Repository(03) */
//    @Autowired
//    private PartnerCorpHistory03Repository partnerCorpHistory03Repository;
//    /** 関連者企業・団体履歴Repository(04) */
//    @Autowired
//    private PartnerCorpHistory04Repository partnerCorpHistory04Repository;
//    /** 関連者企業・団体履歴Repository(05) */
//    @Autowired
//    private PartnerCorpHistory05Repository partnerCorpHistory05Repository;
//    /** 関連者企業・団体履歴Repository(06) */
//    @Autowired
//    private PartnerCorpHistory06Repository partnerCorpHistory06Repository;
//    /** 関連者企業・団体履歴Repository(07) */
//    @Autowired
//    private PartnerCorpHistory07Repository partnerCorpHistory07Repository;
//    /** 関連者企業・団体履歴Repository(08) */
//    @Autowired
//    private PartnerCorpHistory08Repository partnerCorpHistory08Repository;
//    /** 関連者企業・団体履歴Repository(09) */
//    @Autowired
//    private PartnerCorpHistory09Repository partnerCorpHistory09Repository;
//    /** 関連者企業・団体履歴Repository(10) */
//    @Autowired
//    private PartnerCorpHistory10Repository partnerCorpHistory10Repository;
//    /** 関連者企業・団体履歴Repository(12) */
//    @Autowired
//    private PartnerCorpHistory11Repository partnerCorpHistory11Repository;
//    /** 関連者企業・団体履歴Repository(13) */
//    @Autowired
//    private PartnerCorpHistory12Repository partnerCorpHistory12Repository;
//    /** 関連者企業・団体履歴Repository(14) */
//    @Autowired
//    private PartnerCorpHistory13Repository partnerCorpHistory13Repository;
//    /** 関連者企業・団体履歴Repository(15) */
//    @Autowired
//    private PartnerCorpHistory14Repository partnerCorpHistory14Repository;
//    /** 関連者企業・団体履歴Repository(15) */
//    @Autowired
//    private PartnerCorpHistory15Repository partnerCorpHistory15Repository;
//    /** 関連者企業・団体履歴Repository(16) */
//    @Autowired
//    private PartnerCorpHistory16Repository partnerCorpHistory16Repository;
//    /** 関連者企業・団体履歴Repository(17) */
//    @Autowired
//    private PartnerCorpHistory17Repository partnerCorpHistory17Repository;
//    /** 関連者企業・団体履歴Repository(18) */
//    @Autowired
//    private PartnerCorpHistory18Repository partnerCorpHistory18Repository;
//    /** 関連者企業・団体履歴Repository(19) */
//    @Autowired
//    private PartnerCorpHistory19Repository partnerCorpHistory19Repository;
//    /** 関連者企業・団体履歴Repository(20) */
//    @Autowired
//    private PartnerCorpHistory20Repository partnerCorpHistory20Repository;
//    /** 関連者企業・団体履歴Repository(21) */
//    @Autowired
//    private PartnerCorpHistory21Repository partnerCorpHistory21Repository;
//    /** 関連者企業・団体履歴Repository(22) */
//    @Autowired
//    private PartnerCorpHistory22Repository partnerCorpHistory22Repository;
//    /** 関連者企業・団体履歴Repository(23) */
//    @Autowired
//    private PartnerCorpHistory23Repository partnerCorpHistory23Repository;
//    /** 関連者企業・団体履歴Repository(24) */
//    @Autowired
//    private PartnerCorpHistory24Repository partnerCorpHistory24Repository;
//    /** 関連者企業・団体履歴Repository(25) */
//    @Autowired
//    private PartnerCorpHistory25Repository partnerCorpHistory25Repository;
//    /** 関連者企業・団体履歴Repository(26) */
//    @Autowired
//    private PartnerCorpHistory26Repository partnerCorpHistory26Repository;
//    /** 関連者企業・団体履歴Repository(27) */
//    @Autowired
//    private PartnerCorpHistory27Repository partnerCorpHistory27Repository;
//    /** 関連者企業・団体履歴Repository(28) */
//    @Autowired
//    private PartnerCorpHistory28Repository partnerCorpHistory28Repository;
//    /** 関連者企業・団体履歴Repository(29) */
//    @Autowired
//    private PartnerCorpHistory29Repository partnerCorpHistory29Repository;
//    /** 関連者企業・団体履歴Repository(30) */
//    @Autowired
//    private PartnerCorpHistory30Repository partnerCorpHistory30Repository;
//    /** 関連者企業・団体履歴Repository(31) */
//    @Autowired
//    private PartnerCorpHistory31Repository partnerCorpHistory31Repository;
//    /** 関連者企業・団体履歴Repository(32) */
//    @Autowired
//    private PartnerCorpHistory32Repository partnerCorpHistory32Repository;
//    /** 関連者企業・団体履歴Repository(33) */
//    @Autowired
//    private PartnerCorpHistory33Repository partnerCorpHistory33Repository;
//    /** 関連者企業・団体履歴Repository(34) */
//    @Autowired
//    private PartnerCorpHistory34Repository partnerCorpHistory34Repository;
//    /** 関連者企業・団体履歴Repository(35) */
//    @Autowired
//    private PartnerCorpHistory35Repository partnerCorpHistory35Repository;
//    /** 関連者企業・団体履歴Repository(36) */
//    @Autowired
//    private PartnerCorpHistory36Repository partnerCorpHistory36Repository;
//    /** 関連者企業・団体履歴Repository(37) */
//    @Autowired
//    private PartnerCorpHistory37Repository partnerCorpHistory37Repository;
//    /** 関連者企業・団体履歴Repository(38) */
//    @Autowired
//    private PartnerCorpHistory38Repository partnerCorpHistory38Repository;
//    /** 関連者企業・団体履歴Repository(39) */
//    @Autowired
//    private PartnerCorpHistory39Repository partnerCorpHistory39Repository;
//    /** 関連者企業・団体履歴Repository(40) */
//    @Autowired
//    private PartnerCorpHistory40Repository partnerCorpHistory40Repository;
//    /** 関連者企業・団体履歴Repository(41) */
//    @Autowired
//    private PartnerCorpHistory41Repository partnerCorpHistory41Repository;
//    /** 関連者企業・団体履歴Repository(42) */
//    @Autowired
//    private PartnerCorpHistory42Repository partnerCorpHistory42Repository;
//    /** 関連者企業・団体履歴Repository(43) */
//    @Autowired
//    private PartnerCorpHistory43Repository partnerCorpHistory43Repository;
//    /** 関連者企業・団体履歴Repository(44) */
//    @Autowired
//    private PartnerCorpHistory44Repository partnerCorpHistory44Repository;
//    /** 関連者企業・団体履歴Repository(45) */
//    @Autowired
//    private PartnerCorpHistory45Repository partnerCorpHistory45Repository;
//    /** 関連者企業・団体履歴Repository(46) */
//    @Autowired
//    private PartnerCorpHistory46Repository partnerCorpHistory46Repository;
//    /** 関連者企業・団体履歴Repository(47) */
//    @Autowired
//    private PartnerCorpHistory47Repository partnerCorpHistory47Repository;
//    /** 関連者企業・団体履歴Repository(99) */
//    @Autowired
//    private PartnerCorpHistory99Repository partnerCorpHistory99Repository;

    /**
     * 処理を行う
     *
     * @param name          名称
     * @param address       住所
     * @return 検索結果
     */
    public List<PartnerCommonInfoDto> practice( // SUPPRESS CHECKSTYLE ReturnCount NOPMD
            final String name, final String address) {
        
        return partnerCorpHistory01Repository.findKanrenshaByNameAddress(name, address);
        
//        switch (getPrefectureLgCodeService.practice(address)) {
//            case GetPrefectureLgCodeService.PREF_01:
//                return partnerCorpHistory01Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_02:
//                return partnerCorpHistory02Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_03:
//                return partnerCorpHistory03Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_04:
//                return partnerCorpHistory04Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_05:
//                return partnerCorpHistory05Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_06:
//                return partnerCorpHistory06Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_07:
//                return partnerCorpHistory07Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_08:
//                return partnerCorpHistory08Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_09:
//                return partnerCorpHistory09Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_10:
//                return partnerCorpHistory10Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_11:
//                return partnerCorpHistory11Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_12:
//                return partnerCorpHistory12Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_13:
//                return partnerCorpHistory13Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_14:
//                return partnerCorpHistory14Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_15:
//                return partnerCorpHistory15Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_16:
//                return partnerCorpHistory16Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_17:
//                return partnerCorpHistory17Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_18:
//                return partnerCorpHistory18Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_19:
//                return partnerCorpHistory19Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_20:
//                return partnerCorpHistory20Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_21:
//                return partnerCorpHistory21Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_22:
//                return partnerCorpHistory22Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_23:
//                return partnerCorpHistory23Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_24:
//                return partnerCorpHistory24Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_25:
//                return partnerCorpHistory25Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_26:
//                return partnerCorpHistory26Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_27:
//                return partnerCorpHistory27Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_28:
//                return partnerCorpHistory28Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_29:
//                return partnerCorpHistory29Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_30:
//                return partnerCorpHistory30Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_31:
//                return partnerCorpHistory31Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_32:
//                return partnerCorpHistory32Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_33:
//                return partnerCorpHistory33Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_34:
//                return partnerCorpHistory34Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_35:
//                return partnerCorpHistory35Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_36:
//                return partnerCorpHistory36Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_37:
//                return partnerCorpHistory37Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_38:
//                return partnerCorpHistory38Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_39:
//                return partnerCorpHistory39Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_40:
//                return partnerCorpHistory40Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_41:
//                return partnerCorpHistory41Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_42:
//                return partnerCorpHistory42Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_43:
//                return partnerCorpHistory43Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_44:
//                return partnerCorpHistory44Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_45:
//                return partnerCorpHistory45Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_46:
//                return partnerCorpHistory46Repository.findKanrenshaByNameAddress(name, address);
//            case GetPrefectureLgCodeService.PREF_47:
//                return partnerCorpHistory47Repository.findKanrenshaByNameAddress(name, address);
//            default:
//                return partnerCorpHistory99Repository.findKanrenshaByNameAddress(name, address);
//        }
    }

}
