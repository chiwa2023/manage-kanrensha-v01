package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha; // NOPMD

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPersonHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory02Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory03Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory04Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory05Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory06Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory07Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory08Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory09Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory10Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory11Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory12Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory13Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory14Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory15Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory16Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory17Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory18Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory19Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory20Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory21Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory22Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory23Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory24Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory25Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory26Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory27Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory28Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory29Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory30Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory31Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory32Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory33Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory34Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory35Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory36Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory37Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory38Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory39Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory40Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory41Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory42Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory43Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory44Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory45Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory46Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory47Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory99Entity;
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
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人履歴挿入Service
 */
@Service
public class InsertPartnerPersonHistoryService { // NOPMD

    /** 住所から県 地方公共団体コード(2桁)取得Service */
    @Autowired
    private GetPrefectureLgCodeService getPrefectureLgCodeService;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者企業・団体履歴(01)Repository */
    @Autowired
    private PartnerPersonHistory01Repository partnerPersonHistory01Repository;
    /** 関連者企業・団体履歴(02)Repository */
    @Autowired
    private PartnerPersonHistory02Repository partnerPersonHistory02Repository;
    /** 関連者企業・団体履歴(03)Repository */
    @Autowired
    private PartnerPersonHistory03Repository partnerPersonHistory03Repository;
    /** 関連者企業・団体履歴(04)Repository */
    @Autowired
    private PartnerPersonHistory04Repository partnerPersonHistory04Repository;
    /** 関連者企業・団体履歴(05)Repository */
    @Autowired
    private PartnerPersonHistory05Repository partnerPersonHistory05Repository;
    /** 関連者企業・団体履歴(06)Repository */
    @Autowired
    private PartnerPersonHistory06Repository partnerPersonHistory06Repository;
    /** 関連者企業・団体履歴(07)Repository */
    @Autowired
    private PartnerPersonHistory07Repository partnerPersonHistory07Repository;
    /** 関連者企業・団体履歴(08)Repository */
    @Autowired
    private PartnerPersonHistory08Repository partnerPersonHistory08Repository;
    /** 関連者企業・団体履歴(09)Repository */
    @Autowired
    private PartnerPersonHistory09Repository partnerPersonHistory09Repository;
    /** 関連者企業・団体履歴(10)Repository */
    @Autowired
    private PartnerPersonHistory10Repository partnerPersonHistory10Repository;
    /** 関連者企業・団体履歴(11)Repository */
    @Autowired
    private PartnerPersonHistory11Repository partnerPersonHistory11Repository;
    /** 関連者企業・団体履歴(12)Repository */
    @Autowired
    private PartnerPersonHistory12Repository partnerPersonHistory12Repository;
    /** 関連者企業・団体履歴(13)Repository */
    @Autowired
    private PartnerPersonHistory13Repository partnerPersonHistory13Repository;
    /** 関連者企業・団体履歴(14)Repository */
    @Autowired
    private PartnerPersonHistory14Repository partnerPersonHistory14Repository;
    /** 関連者企業・団体履歴(15)Repository */
    @Autowired
    private PartnerPersonHistory15Repository partnerPersonHistory15Repository;
    /** 関連者企業・団体履歴(16)Repository */
    @Autowired
    private PartnerPersonHistory16Repository partnerPersonHistory16Repository;
    /** 関連者企業・団体履歴(17)Repository */
    @Autowired
    private PartnerPersonHistory17Repository partnerPersonHistory17Repository;
    /** 関連者企業・団体履歴(18)Repository */
    @Autowired
    private PartnerPersonHistory18Repository partnerPersonHistory18Repository;
    /** 関連者企業・団体履歴(19)Repository */
    @Autowired
    private PartnerPersonHistory19Repository partnerPersonHistory19Repository;
    /** 関連者企業・団体履歴(20)Repository */
    @Autowired
    private PartnerPersonHistory20Repository partnerPersonHistory20Repository;
    /** 関連者企業・団体履歴(21)Repository */
    @Autowired
    private PartnerPersonHistory21Repository partnerPersonHistory21Repository;
    /** 関連者企業・団体履歴(22)Repository */
    @Autowired
    private PartnerPersonHistory22Repository partnerPersonHistory22Repository;
    /** 関連者企業・団体履歴(23)Repository */
    @Autowired
    private PartnerPersonHistory23Repository partnerPersonHistory23Repository;
    /** 関連者企業・団体履歴(24)Repository */
    @Autowired
    private PartnerPersonHistory24Repository partnerPersonHistory24Repository;
    /** 関連者企業・団体履歴(25)Repository */
    @Autowired
    private PartnerPersonHistory25Repository partnerPersonHistory25Repository;
    /** 関連者企業・団体履歴(26)Repository */
    @Autowired
    private PartnerPersonHistory26Repository partnerPersonHistory26Repository;
    /** 関連者企業・団体履歴(27)Repository */
    @Autowired
    private PartnerPersonHistory27Repository partnerPersonHistory27Repository;
    /** 関連者企業・団体履歴(28)Repository */
    @Autowired
    private PartnerPersonHistory28Repository partnerPersonHistory28Repository;
    /** 関連者企業・団体履歴(29)Repository */
    @Autowired
    private PartnerPersonHistory29Repository partnerPersonHistory29Repository;
    /** 関連者企業・団体履歴(30)Repository */
    @Autowired
    private PartnerPersonHistory30Repository partnerPersonHistory30Repository;
    /** 関連者企業・団体履歴(31)Repository */
    @Autowired
    private PartnerPersonHistory31Repository partnerPersonHistory31Repository;
    /** 関連者企業・団体履歴(32)Repository */
    @Autowired
    private PartnerPersonHistory32Repository partnerPersonHistory32Repository;
    /** 関連者企業・団体履歴(33)Repository */
    @Autowired
    private PartnerPersonHistory33Repository partnerPersonHistory33Repository;
    /** 関連者企業・団体履歴(34)Repository */
    @Autowired
    private PartnerPersonHistory34Repository partnerPersonHistory34Repository;
    /** 関連者企業・団体履歴(35)Repository */
    @Autowired
    private PartnerPersonHistory35Repository partnerPersonHistory35Repository;
    /** 関連者企業・団体履歴(36)Repository */
    @Autowired
    private PartnerPersonHistory36Repository partnerPersonHistory36Repository;
    /** 関連者企業・団体履歴(37)Repository */
    @Autowired
    private PartnerPersonHistory37Repository partnerPersonHistory37Repository;
    /** 関連者企業・団体履歴(38)Repository */
    @Autowired
    private PartnerPersonHistory38Repository partnerPersonHistory38Repository;
    /** 関連者企業・団体履歴(39)Repository */
    @Autowired
    private PartnerPersonHistory39Repository partnerPersonHistory39Repository;
    /** 関連者企業・団体履歴(40)Repository */
    @Autowired
    private PartnerPersonHistory40Repository partnerPersonHistory40Repository;
    /** 関連者企業・団体履歴(41)Repository */
    @Autowired
    private PartnerPersonHistory41Repository partnerPersonHistory41Repository;
    /** 関連者企業・団体履歴(42)Repository */
    @Autowired
    private PartnerPersonHistory42Repository partnerPersonHistory42Repository;
    /** 関連者企業・団体履歴(43)Repository */
    @Autowired
    private PartnerPersonHistory43Repository partnerPersonHistory43Repository;
    /** 関連者企業・団体履歴(44)Repository */
    @Autowired
    private PartnerPersonHistory44Repository partnerPersonHistory44Repository;
    /** 関連者企業・団体履歴(45)Repository */
    @Autowired
    private PartnerPersonHistory45Repository partnerPersonHistory45Repository;
    /** 関連者企業・団体履歴(46)Repository */
    @Autowired
    private PartnerPersonHistory46Repository partnerPersonHistory46Repository;
    /** 関連者企業・団体履歴(47)Repository */
    @Autowired
    private PartnerPersonHistory47Repository partnerPersonHistory47Repository;
    /** 関連者企業・団体履歴(99)Repository */
    @Autowired
    private PartnerPersonHistory99Repository partnerPersonHistory99Repository;

    /**
     * 処理を行う
     *
     * @param userDto    ユーザ最低限Dto
     * @param baseEntity 関連者企業・団体BaseEntity
     */
    public void practice(final UserPersonLeastDto userDto, final PartnerPersonHistoryBaseEntity baseEntity) { // NOPMD

        switch (getPrefectureLgCodeService.practice(baseEntity.getAllAddress())) {
            case GetPrefectureLgCodeService.PREF_01:
                partnerPersonHistory01Repository.saveAndFlush(this.createEntity01(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_02:
                partnerPersonHistory02Repository.saveAndFlush(this.createEntity02(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_03:
                partnerPersonHistory03Repository.saveAndFlush(this.createEntity03(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_04:
                partnerPersonHistory04Repository.saveAndFlush(this.createEntity04(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_05:
                partnerPersonHistory05Repository.saveAndFlush(this.createEntity05(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_06:
                partnerPersonHistory06Repository.saveAndFlush(this.createEntity06(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_07:
                partnerPersonHistory07Repository.saveAndFlush(this.createEntity07(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_08:
                partnerPersonHistory08Repository.saveAndFlush(this.createEntity08(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_09:
                partnerPersonHistory09Repository.saveAndFlush(this.createEntity09(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_10:
                partnerPersonHistory10Repository.saveAndFlush(this.createEntity10(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_11:
                partnerPersonHistory11Repository.saveAndFlush(this.createEntity11(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_12:
                partnerPersonHistory12Repository.saveAndFlush(this.createEntity12(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_13:
                partnerPersonHistory13Repository.saveAndFlush(this.createEntity13(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_14:
                partnerPersonHistory14Repository.saveAndFlush(this.createEntity14(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_15:
                partnerPersonHistory15Repository.saveAndFlush(this.createEntity15(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_16:
                partnerPersonHistory16Repository.saveAndFlush(this.createEntity16(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_17:
                partnerPersonHistory17Repository.saveAndFlush(this.createEntity17(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_18:
                partnerPersonHistory18Repository.saveAndFlush(this.createEntity18(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_19:
                partnerPersonHistory19Repository.saveAndFlush(this.createEntity19(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_20:
                partnerPersonHistory20Repository.saveAndFlush(this.createEntity20(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_21:
                partnerPersonHistory21Repository.saveAndFlush(this.createEntity21(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_22:
                partnerPersonHistory22Repository.saveAndFlush(this.createEntity22(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_23:
                partnerPersonHistory23Repository.saveAndFlush(this.createEntity23(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_24:
                partnerPersonHistory24Repository.saveAndFlush(this.createEntity24(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_25:
                partnerPersonHistory25Repository.saveAndFlush(this.createEntity25(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_26:
                partnerPersonHistory26Repository.saveAndFlush(this.createEntity26(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_27:
                partnerPersonHistory27Repository.saveAndFlush(this.createEntity27(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_28:
                partnerPersonHistory28Repository.saveAndFlush(this.createEntity28(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_29:
                partnerPersonHistory29Repository.saveAndFlush(this.createEntity29(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_30:
                partnerPersonHistory30Repository.saveAndFlush(this.createEntity30(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_31:
                partnerPersonHistory31Repository.saveAndFlush(this.createEntity31(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_32:
                partnerPersonHistory32Repository.saveAndFlush(this.createEntity32(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_33:
                partnerPersonHistory33Repository.saveAndFlush(this.createEntity33(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_34:
                partnerPersonHistory34Repository.saveAndFlush(this.createEntity34(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_35:
                partnerPersonHistory35Repository.saveAndFlush(this.createEntity35(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_36:
                partnerPersonHistory36Repository.saveAndFlush(this.createEntity36(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_37:
                partnerPersonHistory37Repository.saveAndFlush(this.createEntity37(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_38:
                partnerPersonHistory38Repository.saveAndFlush(this.createEntity38(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_39:
                partnerPersonHistory39Repository.saveAndFlush(this.createEntity39(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_40:
                partnerPersonHistory40Repository.saveAndFlush(this.createEntity40(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_41:
                partnerPersonHistory41Repository.saveAndFlush(this.createEntity41(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_42:
                partnerPersonHistory42Repository.saveAndFlush(this.createEntity42(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_43:
                partnerPersonHistory43Repository.saveAndFlush(this.createEntity43(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_44:
                partnerPersonHistory44Repository.saveAndFlush(this.createEntity44(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_45:
                partnerPersonHistory45Repository.saveAndFlush(this.createEntity45(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_46:
                partnerPersonHistory46Repository.saveAndFlush(this.createEntity46(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_47:
                partnerPersonHistory47Repository.saveAndFlush(this.createEntity47(userDto, baseEntity));
                break;
            default:
                partnerPersonHistory99Repository.saveAndFlush(this.createEntity99(userDto, baseEntity));
                break;
        }
    }

    private PartnerPersonHistory01Entity createEntity01(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory01Entity entity = new PartnerPersonHistory01Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory02Entity createEntity02(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory02Entity entity = new PartnerPersonHistory02Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory03Entity createEntity03(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory03Entity entity = new PartnerPersonHistory03Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory04Entity createEntity04(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory04Entity entity = new PartnerPersonHistory04Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory05Entity createEntity05(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory05Entity entity = new PartnerPersonHistory05Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory06Entity createEntity06(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory06Entity entity = new PartnerPersonHistory06Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory07Entity createEntity07(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory07Entity entity = new PartnerPersonHistory07Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory08Entity createEntity08(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory08Entity entity = new PartnerPersonHistory08Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory09Entity createEntity09(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory09Entity entity = new PartnerPersonHistory09Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory10Entity createEntity10(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory10Entity entity = new PartnerPersonHistory10Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory11Entity createEntity11(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory11Entity entity = new PartnerPersonHistory11Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory12Entity createEntity12(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory12Entity entity = new PartnerPersonHistory12Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory13Entity createEntity13(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory13Entity entity = new PartnerPersonHistory13Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory14Entity createEntity14(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory14Entity entity = new PartnerPersonHistory14Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory15Entity createEntity15(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory15Entity entity = new PartnerPersonHistory15Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory16Entity createEntity16(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory16Entity entity = new PartnerPersonHistory16Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory17Entity createEntity17(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory17Entity entity = new PartnerPersonHistory17Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory18Entity createEntity18(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory18Entity entity = new PartnerPersonHistory18Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory19Entity createEntity19(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory19Entity entity = new PartnerPersonHistory19Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory20Entity createEntity20(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory20Entity entity = new PartnerPersonHistory20Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory21Entity createEntity21(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory21Entity entity = new PartnerPersonHistory21Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory22Entity createEntity22(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory22Entity entity = new PartnerPersonHistory22Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory23Entity createEntity23(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory23Entity entity = new PartnerPersonHistory23Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory24Entity createEntity24(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory24Entity entity = new PartnerPersonHistory24Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory25Entity createEntity25(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory25Entity entity = new PartnerPersonHistory25Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory26Entity createEntity26(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory26Entity entity = new PartnerPersonHistory26Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory27Entity createEntity27(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory27Entity entity = new PartnerPersonHistory27Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory28Entity createEntity28(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory28Entity entity = new PartnerPersonHistory28Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory29Entity createEntity29(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory29Entity entity = new PartnerPersonHistory29Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory30Entity createEntity30(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory30Entity entity = new PartnerPersonHistory30Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory31Entity createEntity31(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory31Entity entity = new PartnerPersonHistory31Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory32Entity createEntity32(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory32Entity entity = new PartnerPersonHistory32Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory33Entity createEntity33(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory33Entity entity = new PartnerPersonHistory33Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory34Entity createEntity34(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory34Entity entity = new PartnerPersonHistory34Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory35Entity createEntity35(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory35Entity entity = new PartnerPersonHistory35Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory36Entity createEntity36(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory36Entity entity = new PartnerPersonHistory36Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory37Entity createEntity37(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory37Entity entity = new PartnerPersonHistory37Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory38Entity createEntity38(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory38Entity entity = new PartnerPersonHistory38Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory39Entity createEntity39(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory39Entity entity = new PartnerPersonHistory39Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory40Entity createEntity40(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory40Entity entity = new PartnerPersonHistory40Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory41Entity createEntity41(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory41Entity entity = new PartnerPersonHistory41Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory42Entity createEntity42(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory42Entity entity = new PartnerPersonHistory42Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory43Entity createEntity43(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory43Entity entity = new PartnerPersonHistory43Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory44Entity createEntity44(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory44Entity entity = new PartnerPersonHistory44Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory45Entity createEntity45(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory45Entity entity = new PartnerPersonHistory45Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory46Entity createEntity46(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory46Entity entity = new PartnerPersonHistory46Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory47Entity createEntity47(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory47Entity entity = new PartnerPersonHistory47Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPersonHistory99Entity createEntity99(final UserPersonLeastDto userDto,
            final PartnerPersonHistoryBaseEntity baseEntity) {
        PartnerPersonHistory99Entity entity = new PartnerPersonHistory99Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示
        return entity;
    }
}
