package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha; // NOPMD

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerCorpHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory02Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory03Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory04Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory05Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory06Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory07Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory08Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory09Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory10Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory11Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory12Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory13Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory14Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory15Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory16Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory17Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory18Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory19Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory20Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory21Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory22Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory23Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory24Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory25Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory26Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory27Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory28Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory29Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory30Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory31Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory32Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory33Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory34Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory35Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory36Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory37Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory38Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory39Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory40Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory41Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory42Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory43Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory44Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory45Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory46Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory47Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory99Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory01Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory02Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory03Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory04Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory05Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory06Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory07Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory08Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory09Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory10Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory11Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory12Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory13Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory14Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory15Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory16Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory17Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory18Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory19Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory20Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory21Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory22Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory23Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory24Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory25Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory26Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory27Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory28Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory29Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory30Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory31Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory32Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory33Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory34Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory35Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory36Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory37Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory38Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory39Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory40Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory41Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory42Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory43Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory44Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory45Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory46Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory47Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory99Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.util.GetPrefectureLgCodeService;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業・団体履歴を挿入する
 */
@Service
public class InsertPartnerCorpHistoryService { // NOPMD

    /** 住所から県 地方公共団体コード(2桁)取得Service */
    @Autowired
    private GetPrefectureLgCodeService getPrefectureLgCodeService;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者企業・団体履歴(01)Repository */
    @Autowired
    private PartnerCorpHistory01Repository partnerCorpHistory01Repository;
    /** 関連者企業・団体履歴(02)Repository */
    @Autowired
    private PartnerCorpHistory02Repository partnerCorpHistory02Repository;
    /** 関連者企業・団体履歴(03)Repository */
    @Autowired
    private PartnerCorpHistory03Repository partnerCorpHistory03Repository;
    /** 関連者企業・団体履歴(04)Repository */
    @Autowired
    private PartnerCorpHistory04Repository partnerCorpHistory04Repository;
    /** 関連者企業・団体履歴(05)Repository */
    @Autowired
    private PartnerCorpHistory05Repository partnerCorpHistory05Repository;
    /** 関連者企業・団体履歴(06)Repository */
    @Autowired
    private PartnerCorpHistory06Repository partnerCorpHistory06Repository;
    /** 関連者企業・団体履歴(07)Repository */
    @Autowired
    private PartnerCorpHistory07Repository partnerCorpHistory07Repository;
    /** 関連者企業・団体履歴(08)Repository */
    @Autowired
    private PartnerCorpHistory08Repository partnerCorpHistory08Repository;
    /** 関連者企業・団体履歴(09)Repository */
    @Autowired
    private PartnerCorpHistory09Repository partnerCorpHistory09Repository;
    /** 関連者企業・団体履歴(10)Repository */
    @Autowired
    private PartnerCorpHistory10Repository partnerCorpHistory10Repository;
    /** 関連者企業・団体履歴(11)Repository */
    @Autowired
    private PartnerCorpHistory11Repository partnerCorpHistory11Repository;
    /** 関連者企業・団体履歴(12)Repository */
    @Autowired
    private PartnerCorpHistory12Repository partnerCorpHistory12Repository;
    /** 関連者企業・団体履歴(13)Repository */
    @Autowired
    private PartnerCorpHistory13Repository partnerCorpHistory13Repository;
    /** 関連者企業・団体履歴(14)Repository */
    @Autowired
    private PartnerCorpHistory14Repository partnerCorpHistory14Repository;
    /** 関連者企業・団体履歴(15)Repository */
    @Autowired
    private PartnerCorpHistory15Repository partnerCorpHistory15Repository;
    /** 関連者企業・団体履歴(16)Repository */
    @Autowired
    private PartnerCorpHistory16Repository partnerCorpHistory16Repository;
    /** 関連者企業・団体履歴(17)Repository */
    @Autowired
    private PartnerCorpHistory17Repository partnerCorpHistory17Repository;
    /** 関連者企業・団体履歴(18)Repository */
    @Autowired
    private PartnerCorpHistory18Repository partnerCorpHistory18Repository;
    /** 関連者企業・団体履歴(19)Repository */
    @Autowired
    private PartnerCorpHistory19Repository partnerCorpHistory19Repository;
    /** 関連者企業・団体履歴(20)Repository */
    @Autowired
    private PartnerCorpHistory20Repository partnerCorpHistory20Repository;
    /** 関連者企業・団体履歴(21)Repository */
    @Autowired
    private PartnerCorpHistory21Repository partnerCorpHistory21Repository;
    /** 関連者企業・団体履歴(22)Repository */
    @Autowired
    private PartnerCorpHistory22Repository partnerCorpHistory22Repository;
    /** 関連者企業・団体履歴(23)Repository */
    @Autowired
    private PartnerCorpHistory23Repository partnerCorpHistory23Repository;
    /** 関連者企業・団体履歴(24)Repository */
    @Autowired
    private PartnerCorpHistory24Repository partnerCorpHistory24Repository;
    /** 関連者企業・団体履歴(25)Repository */
    @Autowired
    private PartnerCorpHistory25Repository partnerCorpHistory25Repository;
    /** 関連者企業・団体履歴(26)Repository */
    @Autowired
    private PartnerCorpHistory26Repository partnerCorpHistory26Repository;
    /** 関連者企業・団体履歴(27)Repository */
    @Autowired
    private PartnerCorpHistory27Repository partnerCorpHistory27Repository;
    /** 関連者企業・団体履歴(28)Repository */
    @Autowired
    private PartnerCorpHistory28Repository partnerCorpHistory28Repository;
    /** 関連者企業・団体履歴(29)Repository */
    @Autowired
    private PartnerCorpHistory29Repository partnerCorpHistory29Repository;
    /** 関連者企業・団体履歴(30)Repository */
    @Autowired
    private PartnerCorpHistory30Repository partnerCorpHistory30Repository;
    /** 関連者企業・団体履歴(31)Repository */
    @Autowired
    private PartnerCorpHistory31Repository partnerCorpHistory31Repository;
    /** 関連者企業・団体履歴(32)Repository */
    @Autowired
    private PartnerCorpHistory32Repository partnerCorpHistory32Repository;
    /** 関連者企業・団体履歴(33)Repository */
    @Autowired
    private PartnerCorpHistory33Repository partnerCorpHistory33Repository;
    /** 関連者企業・団体履歴(34)Repository */
    @Autowired
    private PartnerCorpHistory34Repository partnerCorpHistory34Repository;
    /** 関連者企業・団体履歴(35)Repository */
    @Autowired
    private PartnerCorpHistory35Repository partnerCorpHistory35Repository;
    /** 関連者企業・団体履歴(36)Repository */
    @Autowired
    private PartnerCorpHistory36Repository partnerCorpHistory36Repository;
    /** 関連者企業・団体履歴(37)Repository */
    @Autowired
    private PartnerCorpHistory37Repository partnerCorpHistory37Repository;
    /** 関連者企業・団体履歴(38)Repository */
    @Autowired
    private PartnerCorpHistory38Repository partnerCorpHistory38Repository;
    /** 関連者企業・団体履歴(39)Repository */
    @Autowired
    private PartnerCorpHistory39Repository partnerCorpHistory39Repository;
    /** 関連者企業・団体履歴(40)Repository */
    @Autowired
    private PartnerCorpHistory40Repository partnerCorpHistory40Repository;
    /** 関連者企業・団体履歴(41)Repository */
    @Autowired
    private PartnerCorpHistory41Repository partnerCorpHistory41Repository;
    /** 関連者企業・団体履歴(42)Repository */
    @Autowired
    private PartnerCorpHistory42Repository partnerCorpHistory42Repository;
    /** 関連者企業・団体履歴(43)Repository */
    @Autowired
    private PartnerCorpHistory43Repository partnerCorpHistory43Repository;
    /** 関連者企業・団体履歴(44)Repository */
    @Autowired
    private PartnerCorpHistory44Repository partnerCorpHistory44Repository;
    /** 関連者企業・団体履歴(45)Repository */
    @Autowired
    private PartnerCorpHistory45Repository partnerCorpHistory45Repository;
    /** 関連者企業・団体履歴(46)Repository */
    @Autowired
    private PartnerCorpHistory46Repository partnerCorpHistory46Repository;
    /** 関連者企業・団体履歴(47)Repository */
    @Autowired
    private PartnerCorpHistory47Repository partnerCorpHistory47Repository;
    /** 関連者企業・団体履歴(99)Repository */
    @Autowired
    private PartnerCorpHistory99Repository partnerCorpHistory99Repository;

    /**
     * 処理を行う
     *
     * @param userDto    ユーザ最低限Dto
     * @param baseEntity 関連者企業・団体BaseEntity
     */
    public void practice(final UserPersonLeastDto userDto, final PartnerCorpHistoryBaseEntity baseEntity) { // NOPMD

        switch (getPrefectureLgCodeService.practice(baseEntity.getAllAddress())) {
            case GetPrefectureLgCodeService.PREF_01:
                partnerCorpHistory01Repository.saveAndFlush(this.createEntity01(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_02:
                partnerCorpHistory02Repository.saveAndFlush(this.createEntity02(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_03:
                partnerCorpHistory03Repository.saveAndFlush(this.createEntity03(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_04:
                partnerCorpHistory04Repository.saveAndFlush(this.createEntity04(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_05:
                partnerCorpHistory05Repository.saveAndFlush(this.createEntity05(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_06:
                partnerCorpHistory06Repository.saveAndFlush(this.createEntity06(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_07:
                partnerCorpHistory07Repository.saveAndFlush(this.createEntity07(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_08:
                partnerCorpHistory08Repository.saveAndFlush(this.createEntity08(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_09:
                partnerCorpHistory09Repository.saveAndFlush(this.createEntity09(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_10:
                partnerCorpHistory10Repository.saveAndFlush(this.createEntity10(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_11:
                partnerCorpHistory11Repository.saveAndFlush(this.createEntity11(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_12:
                partnerCorpHistory12Repository.saveAndFlush(this.createEntity12(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_13:
                partnerCorpHistory13Repository.saveAndFlush(this.createEntity13(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_14:
                partnerCorpHistory14Repository.saveAndFlush(this.createEntity14(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_15:
                partnerCorpHistory15Repository.saveAndFlush(this.createEntity15(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_16:
                partnerCorpHistory16Repository.saveAndFlush(this.createEntity16(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_17:
                partnerCorpHistory17Repository.saveAndFlush(this.createEntity17(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_18:
                partnerCorpHistory18Repository.saveAndFlush(this.createEntity18(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_19:
                partnerCorpHistory19Repository.saveAndFlush(this.createEntity19(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_20:
                partnerCorpHistory20Repository.saveAndFlush(this.createEntity20(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_21:
                partnerCorpHistory21Repository.saveAndFlush(this.createEntity21(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_22:
                partnerCorpHistory22Repository.saveAndFlush(this.createEntity22(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_23:
                partnerCorpHistory23Repository.saveAndFlush(this.createEntity23(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_24:
                partnerCorpHistory24Repository.saveAndFlush(this.createEntity24(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_25:
                partnerCorpHistory25Repository.saveAndFlush(this.createEntity25(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_26:
                partnerCorpHistory26Repository.saveAndFlush(this.createEntity26(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_27:
                partnerCorpHistory27Repository.saveAndFlush(this.createEntity27(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_28:
                partnerCorpHistory28Repository.saveAndFlush(this.createEntity28(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_29:
                partnerCorpHistory29Repository.saveAndFlush(this.createEntity29(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_30:
                partnerCorpHistory30Repository.saveAndFlush(this.createEntity30(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_31:
                partnerCorpHistory31Repository.saveAndFlush(this.createEntity31(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_32:
                partnerCorpHistory32Repository.saveAndFlush(this.createEntity32(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_33:
                partnerCorpHistory33Repository.saveAndFlush(this.createEntity33(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_34:
                partnerCorpHistory34Repository.saveAndFlush(this.createEntity34(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_35:
                partnerCorpHistory35Repository.saveAndFlush(this.createEntity35(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_36:
                partnerCorpHistory36Repository.saveAndFlush(this.createEntity36(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_37:
                partnerCorpHistory37Repository.saveAndFlush(this.createEntity37(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_38:
                partnerCorpHistory38Repository.saveAndFlush(this.createEntity38(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_39:
                partnerCorpHistory39Repository.saveAndFlush(this.createEntity39(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_40:
                partnerCorpHistory40Repository.saveAndFlush(this.createEntity40(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_41:
                partnerCorpHistory41Repository.saveAndFlush(this.createEntity41(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_42:
                partnerCorpHistory42Repository.saveAndFlush(this.createEntity42(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_43:
                partnerCorpHistory43Repository.saveAndFlush(this.createEntity43(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_44:
                partnerCorpHistory44Repository.saveAndFlush(this.createEntity44(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_45:
                partnerCorpHistory45Repository.saveAndFlush(this.createEntity45(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_46:
                partnerCorpHistory46Repository.saveAndFlush(this.createEntity46(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_47:
                partnerCorpHistory47Repository.saveAndFlush(this.createEntity47(userDto, baseEntity));
                break;
            default:
                partnerCorpHistory99Repository.saveAndFlush(this.createEntity99(userDto, baseEntity));
                break;
        }
    }

    private PartnerCorpHistory01Entity createEntity01(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory01Entity entity = new PartnerCorpHistory01Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory02Entity createEntity02(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory02Entity entity = new PartnerCorpHistory02Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory03Entity createEntity03(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory03Entity entity = new PartnerCorpHistory03Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory04Entity createEntity04(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory04Entity entity = new PartnerCorpHistory04Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory05Entity createEntity05(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory05Entity entity = new PartnerCorpHistory05Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory06Entity createEntity06(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory06Entity entity = new PartnerCorpHistory06Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory07Entity createEntity07(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory07Entity entity = new PartnerCorpHistory07Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory08Entity createEntity08(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory08Entity entity = new PartnerCorpHistory08Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory09Entity createEntity09(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory09Entity entity = new PartnerCorpHistory09Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory10Entity createEntity10(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory10Entity entity = new PartnerCorpHistory10Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory11Entity createEntity11(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory11Entity entity = new PartnerCorpHistory11Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory12Entity createEntity12(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory12Entity entity = new PartnerCorpHistory12Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory13Entity createEntity13(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory13Entity entity = new PartnerCorpHistory13Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory14Entity createEntity14(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory14Entity entity = new PartnerCorpHistory14Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory15Entity createEntity15(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory15Entity entity = new PartnerCorpHistory15Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory16Entity createEntity16(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory16Entity entity = new PartnerCorpHistory16Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory17Entity createEntity17(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory17Entity entity = new PartnerCorpHistory17Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory18Entity createEntity18(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory18Entity entity = new PartnerCorpHistory18Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory19Entity createEntity19(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory19Entity entity = new PartnerCorpHistory19Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory20Entity createEntity20(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory20Entity entity = new PartnerCorpHistory20Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory21Entity createEntity21(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory21Entity entity = new PartnerCorpHistory21Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory22Entity createEntity22(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory22Entity entity = new PartnerCorpHistory22Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory23Entity createEntity23(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory23Entity entity = new PartnerCorpHistory23Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory24Entity createEntity24(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory24Entity entity = new PartnerCorpHistory24Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory25Entity createEntity25(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory25Entity entity = new PartnerCorpHistory25Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory26Entity createEntity26(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory26Entity entity = new PartnerCorpHistory26Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory27Entity createEntity27(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory27Entity entity = new PartnerCorpHistory27Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory28Entity createEntity28(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory28Entity entity = new PartnerCorpHistory28Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory29Entity createEntity29(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory29Entity entity = new PartnerCorpHistory29Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory30Entity createEntity30(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory30Entity entity = new PartnerCorpHistory30Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory31Entity createEntity31(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory31Entity entity = new PartnerCorpHistory31Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory32Entity createEntity32(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory32Entity entity = new PartnerCorpHistory32Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory33Entity createEntity33(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory33Entity entity = new PartnerCorpHistory33Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory34Entity createEntity34(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory34Entity entity = new PartnerCorpHistory34Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory35Entity createEntity35(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory35Entity entity = new PartnerCorpHistory35Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory36Entity createEntity36(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory36Entity entity = new PartnerCorpHistory36Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory37Entity createEntity37(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory37Entity entity = new PartnerCorpHistory37Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory38Entity createEntity38(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory38Entity entity = new PartnerCorpHistory38Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory39Entity createEntity39(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory39Entity entity = new PartnerCorpHistory39Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory40Entity createEntity40(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory40Entity entity = new PartnerCorpHistory40Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory41Entity createEntity41(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory41Entity entity = new PartnerCorpHistory41Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory42Entity createEntity42(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory42Entity entity = new PartnerCorpHistory42Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory43Entity createEntity43(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory43Entity entity = new PartnerCorpHistory43Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory44Entity createEntity44(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory44Entity entity = new PartnerCorpHistory44Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory45Entity createEntity45(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory45Entity entity = new PartnerCorpHistory45Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory46Entity createEntity46(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory46Entity entity = new PartnerCorpHistory46Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory47Entity createEntity47(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory47Entity entity = new PartnerCorpHistory47Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerCorpHistory99Entity createEntity99(final UserPersonLeastDto userDto,
            final PartnerCorpHistoryBaseEntity baseEntity) {
        PartnerCorpHistory99Entity entity = new PartnerCorpHistory99Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示
        return entity;
    }

}
