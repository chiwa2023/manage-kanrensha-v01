package mitei.mitei.political.balancesheet.manage.kanrensha.logic.add_xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.common.constants.blancesheet_report.IncomeYoushikiKbnConstants;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070711DonateDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070812MediationDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071101ConsiderationPartyPerspnalDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071102ConsiderationPartyGroupDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071103ConsiderationPartyPoliticOrgDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071201ConsiderationMediationPartyPersonalDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071202ConsiderationMediationPartyGroupDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071203ConsiderationMediationPartyPoliticOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * XMLから最小マスタ登全項目ワークテーブル挿入(11,12パーティ)
 */
@Service
public class InsertWktblXmlByPublishKanrenshaPoliPartyLogic {

    /** XMLから最小マスタ登録Repositry */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param allBookDto 政治資金収支報告書XMlDto
     * @param userDto    ユーザ最小限Dto
     * @return true(悲観ロックをしているので処理が直列でなければならない対策)
     */
    @Transactional
    public boolean practice(final AllBookDto allBookDto, final UserPersonLeastDto userDto) {

        // 様式7-11-1
        for (Sheet071101ConsiderationPartyPerspnalDto sheetDto : allBookDto.getAllSheet0711ConsiderationPartyDto()
                .getAllSheetKbn071101Dto().getList()) {
            this.loopRow11(sheetDto.getList(), IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN, userDto);
        }

        // 様式7-11-2
        for (Sheet071102ConsiderationPartyGroupDto sheetDto : allBookDto.getAllSheet0711ConsiderationPartyDto()
                .getAllSheetKbn071102Dto().getList()) {
            this.loopRow11(sheetDto.getList(), IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN, userDto);
        }

        // 様式7-11-3
        for (Sheet071103ConsiderationPartyPoliticOrgDto sheetDto : allBookDto.getAllSheet0711ConsiderationPartyDto()
                .getAllSheetKbn071103Dto().getList()) {
            this.loopRow11(sheetDto.getList(), IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI, userDto);
        }

        // 様式7-12-1
        for (Sheet071201ConsiderationMediationPartyPersonalDto sheetDto : allBookDto.getAllSheet0712PartyMediationDto()
                .getAllSheetKbn071201Dto().getList()) {
            this.loopRow12(sheetDto.getList(), IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN, userDto);
        }

        // 様式7-12-2
        for (Sheet071202ConsiderationMediationPartyGroupDto sheetDto : allBookDto.getAllSheet0712PartyMediationDto()
                .getAllSheetKbn071202Dto().getList()) {
            this.loopRow12(sheetDto.getList(), IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN, userDto);
        }

        // 様式7-12-3
        for (Sheet071203ConsiderationMediationPartyPoliticOrgDto sheetDto : allBookDto
                .getAllSheet0712PartyMediationDto().getAllSheetKbn071203Dto().getList()) {
            this.loopRow12(sheetDto.getList(), IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI, userDto);
        }

        return true;
    }

    private boolean loopRow12(final List<Row070812MediationDto> listRow0812, final int youshikiEdaKn,
            final UserPersonLeastDto userDto) {
        List<WkTblMasterAllByXmlEntity> listEntity = new ArrayList<>();
        for (Row070812MediationDto row : listRow0812) {
            listEntity.add(this.createWkTblEntity(row.getName(), row.getJuusho(), row.getShokugyou(),
                    IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12, youshikiEdaKn, userDto));
        }
        this.saveEntity(listEntity);
        return true;
    }

    private boolean loopRow11(final List<Row070711DonateDto> listRow0711, final int youshikiEdaKn,
            final UserPersonLeastDto userDto) {
        List<WkTblMasterAllByXmlEntity> listEntity = new ArrayList<>();
        for (Row070711DonateDto row : listRow0711) {
            listEntity.add(this.createWkTblEntity(row.getKifusha(), row.getJusho(), row.getShokugyou(),
                    IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11, youshikiEdaKn, userDto));
        }
        this.saveEntity(listEntity);
        return true;
    }

    private WkTblMasterAllByXmlEntity createWkTblEntity(final String name, final String allAddress, final String key,
            final int youshikiKbn, final int youshikiEdaKbn, final UserPersonLeastDto userDto) {

        WkTblMasterAllByXmlEntity wkTblMasterAllByXmlEntity = new WkTblMasterAllByXmlEntity();

        wkTblMasterAllByXmlEntity.setInputSrcName(name);
        wkTblMasterAllByXmlEntity.setPartnerName(name);
        wkTblMasterAllByXmlEntity.setInputSrcAddress(allAddress);
        wkTblMasterAllByXmlEntity.setAllAddress(allAddress);
        wkTblMasterAllByXmlEntity.setInputSrcKey(key);
        wkTblMasterAllByXmlEntity.setOrgDelegate(key);
        wkTblMasterAllByXmlEntity.setPersonShokugyou(key);

        wkTblMasterAllByXmlEntity.setYoushikiKbn((short) youshikiKbn);
        wkTblMasterAllByXmlEntity.setYoushikiEdaKbn((short) youshikiEdaKbn);
        wkTblMasterAllByXmlEntity.setKanrenshaKbn((short)youshikiEdaKbn);

        wkTblMasterAllByXmlEntity.setJudgeReason("別テ)");
        wkTblMasterAllByXmlEntity.setIsAffected(false);
        wkTblMasterAllByXmlEntity.setIsFinish(false);
        setTableDataHistoryUtil.practiceInsert(userDto, wkTblMasterAllByXmlEntity);

        return wkTblMasterAllByXmlEntity;
    }

    private boolean saveEntity(final List<WkTblMasterAllByXmlEntity> listEntity) {
        Integer code = 1;

        Optional<WkTblMasterAllByXmlEntity> optional = wkTblMasterAllByXmlRepository
                .findFirstByOrderByWkTblMasterAllByXmlCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getWkTblMasterAllByXmlCode();
        }

        for (WkTblMasterAllByXmlEntity entity : listEntity) {
            entity.setWkTblMasterAllByXmlCode(code);
            code++;
        }

        wkTblMasterAllByXmlRepository.saveAllAndFlush(listEntity);
        return true;
    }

}
