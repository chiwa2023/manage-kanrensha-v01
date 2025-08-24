package mitei.mitei.political.balancesheet.manage.kanrensha.logic.add_xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.common.constants.blancesheet_report.IncomeYoushikiKbnConstants;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070711DonateDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070812MediationDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * XMLから最小マスタ登全項目ワークテーブル挿入(07,08寄付)
 */
@Service
public class InsertWktblXmlByPublishKanrenshaDonateLogic {

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
    public boolean practice(final AllBookDto allBookDto, final UserPersonLeastDto userDto) {

        List<WkTblMasterAllByXmlEntity> listEntity = new ArrayList<>();

        // 様式7の7-1:寄付者の氏名、住所、職業
        for (Row070711DonateDto rowDto : allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070701Dto()
                .getSheet070701DonatePersonDto().getList()) {
            listEntity.add(this.createWkTblEntity(rowDto.getKifusha(), rowDto.getJusho(), rowDto.getShokugyou(),
                    IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07,
                    IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN, userDto));

        }
        this.saveEntity(listEntity);
        listEntity.clear();

        // 様式7の7-2:寄付者の団体名称、住所、代表者名
        for (Row070711DonateDto rowDto : allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070702Dto()
                .getSheet070702DonateGroupDto().getList()) {
            listEntity.add(this.createWkTblEntity(rowDto.getKifusha(), rowDto.getJusho(), rowDto.getShokugyou(),
                    IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07,
                    IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN, userDto));
        }
        this.saveEntity(listEntity);
        listEntity.clear();

        // 様式7の7-3:寄付者の団体名称、住所、代表者名
        for (Row070711DonateDto rowDto : allBookDto.getAllSheet0707DonateDto().getAllSheetKbn070703Dto()
                .getSheet070703DonatePoliticOrgDto().getList()) {
            listEntity.add(this.createWkTblEntity(rowDto.getKifusha(), rowDto.getJusho(), rowDto.getShokugyou(),
                    IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07,
                    IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI, userDto));
        }
        this.saveEntity(listEntity);
        listEntity.clear();

        // 様式7の8-1:寄付者の氏名、住所、職業
        for (Row070812MediationDto rowDto : allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070801Dto()
                .getSheet070801MediationPersonDto().getList()) {
            listEntity.add(this.createWkTblEntity(rowDto.getName(), rowDto.getJuusho(), rowDto.getShokugyou(),
                    IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08,
                    IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN, userDto));
        }
        this.saveEntity(listEntity);
        listEntity.clear();

        // 様式7の8-2:寄付者の団体名称、住所、代表者名
        for (Row070812MediationDto rowDto : allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070802Dto()
                .getSheet070802MediationGroupDto().getList()) {
            listEntity.add(this.createWkTblEntity(rowDto.getName(), rowDto.getJuusho(), rowDto.getShokugyou(),
                    IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08,
                    IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN, userDto));
        }
        this.saveEntity(listEntity);
        listEntity.clear();

        // 様式7の8-3:寄付者の団体名称、住所、代表者名
        for (Row070812MediationDto rowDto : allBookDto.getAllSheet0708MediationDto().getAllSheetKbn070803Dto()
                .getSheet070803MediationPoliticOrgDto().getList()) {
            listEntity.add(this.createWkTblEntity(rowDto.getName(), rowDto.getJuusho(), rowDto.getShokugyou(),
                    IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08,
                    IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI, userDto));
        }
        this.saveEntity(listEntity);
        listEntity.clear();

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
        wkTblMasterAllByXmlEntity.setKanrenshaKbn((short) youshikiEdaKbn);

        wkTblMasterAllByXmlEntity.setJudgeReason("別テ)");
        wkTblMasterAllByXmlEntity.setIsAffected(false);
        wkTblMasterAllByXmlEntity.setIsFinish(false);
        setTableDataHistoryUtil.practiceInsert(userDto, wkTblMasterAllByXmlEntity);

        return wkTblMasterAllByXmlEntity;
    }

    private boolean saveEntity(final List<WkTblMasterAllByXmlEntity> listEntity) {
        if (listEntity.isEmpty()) {
            return true;
        }
        
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
