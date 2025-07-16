package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha; // NOPMD

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPoliOrgHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory02Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory03Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory04Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory05Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory06Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory07Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory08Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory09Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory10Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory11Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory12Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory13Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory14Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory15Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory16Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory17Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory18Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory19Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory20Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory21Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory22Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory23Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory24Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory25Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory26Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory27Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory28Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory29Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory30Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory31Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory32Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory33Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory34Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory35Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory36Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory37Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory38Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory39Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory40Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory41Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory42Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory43Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory44Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory45Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory46Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory47Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory99Entity;
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
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者政治団体挿入Service
 */
@Service
public class InsertPartnerPoliOrgHistoryService { // NOPMD

    /** 住所から県 地方公共団体コード(2桁)取得Service */
    @Autowired
    private GetPrefectureLgCodeService getPrefectureLgCodeService;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者企業・団体履歴(01)Repository */
    @Autowired
    private PartnerPoliOrgHistory01Repository partnerPoliOrgHistory01Repository;
    /** 関連者企業・団体履歴(02)Repository */
    @Autowired
    private PartnerPoliOrgHistory02Repository partnerPoliOrgHistory02Repository;
    /** 関連者企業・団体履歴(03)Repository */
    @Autowired
    private PartnerPoliOrgHistory03Repository partnerPoliOrgHistory03Repository;
    /** 関連者企業・団体履歴(04)Repository */
    @Autowired
    private PartnerPoliOrgHistory04Repository partnerPoliOrgHistory04Repository;
    /** 関連者企業・団体履歴(05)Repository */
    @Autowired
    private PartnerPoliOrgHistory05Repository partnerPoliOrgHistory05Repository;
    /** 関連者企業・団体履歴(06)Repository */
    @Autowired
    private PartnerPoliOrgHistory06Repository partnerPoliOrgHistory06Repository;
    /** 関連者企業・団体履歴(07)Repository */
    @Autowired
    private PartnerPoliOrgHistory07Repository partnerPoliOrgHistory07Repository;
    /** 関連者企業・団体履歴(08)Repository */
    @Autowired
    private PartnerPoliOrgHistory08Repository partnerPoliOrgHistory08Repository;
    /** 関連者企業・団体履歴(09)Repository */
    @Autowired
    private PartnerPoliOrgHistory09Repository partnerPoliOrgHistory09Repository;
    /** 関連者企業・団体履歴(10)Repository */
    @Autowired
    private PartnerPoliOrgHistory10Repository partnerPoliOrgHistory10Repository;
    /** 関連者企業・団体履歴(11)Repository */
    @Autowired
    private PartnerPoliOrgHistory11Repository partnerPoliOrgHistory11Repository;
    /** 関連者企業・団体履歴(12)Repository */
    @Autowired
    private PartnerPoliOrgHistory12Repository partnerPoliOrgHistory12Repository;
    /** 関連者企業・団体履歴(13)Repository */
    @Autowired
    private PartnerPoliOrgHistory13Repository partnerPoliOrgHistory13Repository;
    /** 関連者企業・団体履歴(14)Repository */
    @Autowired
    private PartnerPoliOrgHistory14Repository partnerPoliOrgHistory14Repository;
    /** 関連者企業・団体履歴(15)Repository */
    @Autowired
    private PartnerPoliOrgHistory15Repository partnerPoliOrgHistory15Repository;
    /** 関連者企業・団体履歴(16)Repository */
    @Autowired
    private PartnerPoliOrgHistory16Repository partnerPoliOrgHistory16Repository;
    /** 関連者企業・団体履歴(17)Repository */
    @Autowired
    private PartnerPoliOrgHistory17Repository partnerPoliOrgHistory17Repository;
    /** 関連者企業・団体履歴(18)Repository */
    @Autowired
    private PartnerPoliOrgHistory18Repository partnerPoliOrgHistory18Repository;
    /** 関連者企業・団体履歴(19)Repository */
    @Autowired
    private PartnerPoliOrgHistory19Repository partnerPoliOrgHistory19Repository;
    /** 関連者企業・団体履歴(20)Repository */
    @Autowired
    private PartnerPoliOrgHistory20Repository partnerPoliOrgHistory20Repository;
    /** 関連者企業・団体履歴(21)Repository */
    @Autowired
    private PartnerPoliOrgHistory21Repository partnerPoliOrgHistory21Repository;
    /** 関連者企業・団体履歴(22)Repository */
    @Autowired
    private PartnerPoliOrgHistory22Repository partnerPoliOrgHistory22Repository;
    /** 関連者企業・団体履歴(23)Repository */
    @Autowired
    private PartnerPoliOrgHistory23Repository partnerPoliOrgHistory23Repository;
    /** 関連者企業・団体履歴(24)Repository */
    @Autowired
    private PartnerPoliOrgHistory24Repository partnerPoliOrgHistory24Repository;
    /** 関連者企業・団体履歴(25)Repository */
    @Autowired
    private PartnerPoliOrgHistory25Repository partnerPoliOrgHistory25Repository;
    /** 関連者企業・団体履歴(26)Repository */
    @Autowired
    private PartnerPoliOrgHistory26Repository partnerPoliOrgHistory26Repository;
    /** 関連者企業・団体履歴(27)Repository */
    @Autowired
    private PartnerPoliOrgHistory27Repository partnerPoliOrgHistory27Repository;
    /** 関連者企業・団体履歴(28)Repository */
    @Autowired
    private PartnerPoliOrgHistory28Repository partnerPoliOrgHistory28Repository;
    /** 関連者企業・団体履歴(29)Repository */
    @Autowired
    private PartnerPoliOrgHistory29Repository partnerPoliOrgHistory29Repository;
    /** 関連者企業・団体履歴(30)Repository */
    @Autowired
    private PartnerPoliOrgHistory30Repository partnerPoliOrgHistory30Repository;
    /** 関連者企業・団体履歴(31)Repository */
    @Autowired
    private PartnerPoliOrgHistory31Repository partnerPoliOrgHistory31Repository;
    /** 関連者企業・団体履歴(32)Repository */
    @Autowired
    private PartnerPoliOrgHistory32Repository partnerPoliOrgHistory32Repository;
    /** 関連者企業・団体履歴(33)Repository */
    @Autowired
    private PartnerPoliOrgHistory33Repository partnerPoliOrgHistory33Repository;
    /** 関連者企業・団体履歴(34)Repository */
    @Autowired
    private PartnerPoliOrgHistory34Repository partnerPoliOrgHistory34Repository;
    /** 関連者企業・団体履歴(35)Repository */
    @Autowired
    private PartnerPoliOrgHistory35Repository partnerPoliOrgHistory35Repository;
    /** 関連者企業・団体履歴(36)Repository */
    @Autowired
    private PartnerPoliOrgHistory36Repository partnerPoliOrgHistory36Repository;
    /** 関連者企業・団体履歴(37)Repository */
    @Autowired
    private PartnerPoliOrgHistory37Repository partnerPoliOrgHistory37Repository;
    /** 関連者企業・団体履歴(38)Repository */
    @Autowired
    private PartnerPoliOrgHistory38Repository partnerPoliOrgHistory38Repository;
    /** 関連者企業・団体履歴(39)Repository */
    @Autowired
    private PartnerPoliOrgHistory39Repository partnerPoliOrgHistory39Repository;
    /** 関連者企業・団体履歴(40)Repository */
    @Autowired
    private PartnerPoliOrgHistory40Repository partnerPoliOrgHistory40Repository;
    /** 関連者企業・団体履歴(41)Repository */
    @Autowired
    private PartnerPoliOrgHistory41Repository partnerPoliOrgHistory41Repository;
    /** 関連者企業・団体履歴(42)Repository */
    @Autowired
    private PartnerPoliOrgHistory42Repository partnerPoliOrgHistory42Repository;
    /** 関連者企業・団体履歴(43)Repository */
    @Autowired
    private PartnerPoliOrgHistory43Repository partnerPoliOrgHistory43Repository;
    /** 関連者企業・団体履歴(44)Repository */
    @Autowired
    private PartnerPoliOrgHistory44Repository partnerPoliOrgHistory44Repository;
    /** 関連者企業・団体履歴(45)Repository */
    @Autowired
    private PartnerPoliOrgHistory45Repository partnerPoliOrgHistory45Repository;
    /** 関連者企業・団体履歴(46)Repository */
    @Autowired
    private PartnerPoliOrgHistory46Repository partnerPoliOrgHistory46Repository;
    /** 関連者企業・団体履歴(47)Repository */
    @Autowired
    private PartnerPoliOrgHistory47Repository partnerPoliOrgHistory47Repository;
    /** 関連者企業・団体履歴(99)Repository */
    @Autowired
    private PartnerPoliOrgHistory99Repository partnerPoliOrgHistory99Repository;

    /**
     * 処理を行う
     *
     * @param userDto    ユーザ最低限Dto
     * @param baseEntity 関連者企業・団体BaseEntity
     */
    public void practice(final UserPersonLeastDto userDto, final PartnerPoliOrgHistoryBaseEntity baseEntity) { // NOPMD

        switch (getPrefectureLgCodeService.practice(baseEntity.getAllAddress())) {
            case GetPrefectureLgCodeService.PREF_01:
                partnerPoliOrgHistory01Repository.saveAndFlush(this.createEntity01(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_02:
                partnerPoliOrgHistory02Repository.saveAndFlush(this.createEntity02(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_03:
                partnerPoliOrgHistory03Repository.saveAndFlush(this.createEntity03(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_04:
                partnerPoliOrgHistory04Repository.saveAndFlush(this.createEntity04(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_05:
                partnerPoliOrgHistory05Repository.saveAndFlush(this.createEntity05(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_06:
                partnerPoliOrgHistory06Repository.saveAndFlush(this.createEntity06(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_07:
                partnerPoliOrgHistory07Repository.saveAndFlush(this.createEntity07(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_08:
                partnerPoliOrgHistory08Repository.saveAndFlush(this.createEntity08(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_09:
                partnerPoliOrgHistory09Repository.saveAndFlush(this.createEntity09(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_10:
                partnerPoliOrgHistory10Repository.saveAndFlush(this.createEntity10(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_11:
                partnerPoliOrgHistory11Repository.saveAndFlush(this.createEntity11(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_12:
                partnerPoliOrgHistory12Repository.saveAndFlush(this.createEntity12(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_13:
                partnerPoliOrgHistory13Repository.saveAndFlush(this.createEntity13(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_14:
                partnerPoliOrgHistory14Repository.saveAndFlush(this.createEntity14(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_15:
                partnerPoliOrgHistory15Repository.saveAndFlush(this.createEntity15(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_16:
                partnerPoliOrgHistory16Repository.saveAndFlush(this.createEntity16(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_17:
                partnerPoliOrgHistory17Repository.saveAndFlush(this.createEntity17(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_18:
                partnerPoliOrgHistory18Repository.saveAndFlush(this.createEntity18(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_19:
                partnerPoliOrgHistory19Repository.saveAndFlush(this.createEntity19(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_20:
                partnerPoliOrgHistory20Repository.saveAndFlush(this.createEntity20(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_21:
                partnerPoliOrgHistory21Repository.saveAndFlush(this.createEntity21(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_22:
                partnerPoliOrgHistory22Repository.saveAndFlush(this.createEntity22(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_23:
                partnerPoliOrgHistory23Repository.saveAndFlush(this.createEntity23(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_24:
                partnerPoliOrgHistory24Repository.saveAndFlush(this.createEntity24(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_25:
                partnerPoliOrgHistory25Repository.saveAndFlush(this.createEntity25(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_26:
                partnerPoliOrgHistory26Repository.saveAndFlush(this.createEntity26(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_27:
                partnerPoliOrgHistory27Repository.saveAndFlush(this.createEntity27(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_28:
                partnerPoliOrgHistory28Repository.saveAndFlush(this.createEntity28(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_29:
                partnerPoliOrgHistory29Repository.saveAndFlush(this.createEntity29(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_30:
                partnerPoliOrgHistory30Repository.saveAndFlush(this.createEntity30(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_31:
                partnerPoliOrgHistory31Repository.saveAndFlush(this.createEntity31(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_32:
                partnerPoliOrgHistory32Repository.saveAndFlush(this.createEntity32(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_33:
                partnerPoliOrgHistory33Repository.saveAndFlush(this.createEntity33(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_34:
                partnerPoliOrgHistory34Repository.saveAndFlush(this.createEntity34(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_35:
                partnerPoliOrgHistory35Repository.saveAndFlush(this.createEntity35(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_36:
                partnerPoliOrgHistory36Repository.saveAndFlush(this.createEntity36(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_37:
                partnerPoliOrgHistory37Repository.saveAndFlush(this.createEntity37(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_38:
                partnerPoliOrgHistory38Repository.saveAndFlush(this.createEntity38(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_39:
                partnerPoliOrgHistory39Repository.saveAndFlush(this.createEntity39(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_40:
                partnerPoliOrgHistory40Repository.saveAndFlush(this.createEntity40(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_41:
                partnerPoliOrgHistory41Repository.saveAndFlush(this.createEntity41(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_42:
                partnerPoliOrgHistory42Repository.saveAndFlush(this.createEntity42(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_43:
                partnerPoliOrgHistory43Repository.saveAndFlush(this.createEntity43(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_44:
                partnerPoliOrgHistory44Repository.saveAndFlush(this.createEntity44(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_45:
                partnerPoliOrgHistory45Repository.saveAndFlush(this.createEntity45(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_46:
                partnerPoliOrgHistory46Repository.saveAndFlush(this.createEntity46(userDto, baseEntity));
                break;
            case GetPrefectureLgCodeService.PREF_47:
                partnerPoliOrgHistory47Repository.saveAndFlush(this.createEntity47(userDto, baseEntity));
                break;
            default:
                partnerPoliOrgHistory99Repository.saveAndFlush(this.createEntity99(userDto, baseEntity));
                break;
        }
    }

    private PartnerPoliOrgHistory01Entity createEntity01(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory01Entity entity = new PartnerPoliOrgHistory01Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory02Entity createEntity02(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory02Entity entity = new PartnerPoliOrgHistory02Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory03Entity createEntity03(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory03Entity entity = new PartnerPoliOrgHistory03Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory04Entity createEntity04(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory04Entity entity = new PartnerPoliOrgHistory04Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory05Entity createEntity05(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory05Entity entity = new PartnerPoliOrgHistory05Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory06Entity createEntity06(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory06Entity entity = new PartnerPoliOrgHistory06Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory07Entity createEntity07(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory07Entity entity = new PartnerPoliOrgHistory07Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory08Entity createEntity08(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory08Entity entity = new PartnerPoliOrgHistory08Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory09Entity createEntity09(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory09Entity entity = new PartnerPoliOrgHistory09Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory10Entity createEntity10(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory10Entity entity = new PartnerPoliOrgHistory10Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory11Entity createEntity11(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory11Entity entity = new PartnerPoliOrgHistory11Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory12Entity createEntity12(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory12Entity entity = new PartnerPoliOrgHistory12Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory13Entity createEntity13(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory13Entity entity = new PartnerPoliOrgHistory13Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory14Entity createEntity14(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory14Entity entity = new PartnerPoliOrgHistory14Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory15Entity createEntity15(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory15Entity entity = new PartnerPoliOrgHistory15Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory16Entity createEntity16(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory16Entity entity = new PartnerPoliOrgHistory16Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory17Entity createEntity17(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory17Entity entity = new PartnerPoliOrgHistory17Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory18Entity createEntity18(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory18Entity entity = new PartnerPoliOrgHistory18Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory19Entity createEntity19(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory19Entity entity = new PartnerPoliOrgHistory19Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory20Entity createEntity20(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory20Entity entity = new PartnerPoliOrgHistory20Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory21Entity createEntity21(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory21Entity entity = new PartnerPoliOrgHistory21Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory22Entity createEntity22(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory22Entity entity = new PartnerPoliOrgHistory22Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory23Entity createEntity23(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory23Entity entity = new PartnerPoliOrgHistory23Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory24Entity createEntity24(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory24Entity entity = new PartnerPoliOrgHistory24Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory25Entity createEntity25(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory25Entity entity = new PartnerPoliOrgHistory25Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory26Entity createEntity26(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory26Entity entity = new PartnerPoliOrgHistory26Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory27Entity createEntity27(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory27Entity entity = new PartnerPoliOrgHistory27Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory28Entity createEntity28(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory28Entity entity = new PartnerPoliOrgHistory28Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory29Entity createEntity29(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory29Entity entity = new PartnerPoliOrgHistory29Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory30Entity createEntity30(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory30Entity entity = new PartnerPoliOrgHistory30Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory31Entity createEntity31(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory31Entity entity = new PartnerPoliOrgHistory31Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory32Entity createEntity32(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory32Entity entity = new PartnerPoliOrgHistory32Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory33Entity createEntity33(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory33Entity entity = new PartnerPoliOrgHistory33Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory34Entity createEntity34(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory34Entity entity = new PartnerPoliOrgHistory34Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory35Entity createEntity35(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory35Entity entity = new PartnerPoliOrgHistory35Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory36Entity createEntity36(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory36Entity entity = new PartnerPoliOrgHistory36Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory37Entity createEntity37(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory37Entity entity = new PartnerPoliOrgHistory37Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory38Entity createEntity38(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory38Entity entity = new PartnerPoliOrgHistory38Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory39Entity createEntity39(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory39Entity entity = new PartnerPoliOrgHistory39Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory40Entity createEntity40(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory40Entity entity = new PartnerPoliOrgHistory40Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory41Entity createEntity41(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory41Entity entity = new PartnerPoliOrgHistory41Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory42Entity createEntity42(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory42Entity entity = new PartnerPoliOrgHistory42Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory43Entity createEntity43(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory43Entity entity = new PartnerPoliOrgHistory43Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory44Entity createEntity44(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory44Entity entity = new PartnerPoliOrgHistory44Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory45Entity createEntity45(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory45Entity entity = new PartnerPoliOrgHistory45Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory46Entity createEntity46(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory46Entity entity = new PartnerPoliOrgHistory46Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory47Entity createEntity47(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory47Entity entity = new PartnerPoliOrgHistory47Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }

    private PartnerPoliOrgHistory99Entity createEntity99(final UserPersonLeastDto userDto,
            final PartnerPoliOrgHistoryBaseEntity baseEntity) {
        PartnerPoliOrgHistory99Entity entity = new PartnerPoliOrgHistory99Entity();
        BeanUtils.copyProperties(baseEntity, entity);
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示
        return entity;
    }
}
