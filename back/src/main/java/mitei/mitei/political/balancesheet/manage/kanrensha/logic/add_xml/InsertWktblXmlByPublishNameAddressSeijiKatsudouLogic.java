package mitei.mitei.political.balancesheet.manage.kanrensha.logic.add_xml; // NOPMD

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.constants.blancesheet_report.OutcomeYoushikiKbnConstants;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row071415OrdinaryExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071501OrganizationalActivityExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071502ElectionRelatedExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071503MagazinePublicationExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071504AdvertisingExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071505PartyHostingFeeDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071506OtherBusinessExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071507ResearchExpensesDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071508DonationsGrantsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071509OtherExpensesDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * XMLから最小マスタ登録名称住所ワークテーブル挿入(15政治活動費)
 */
@Component
public class InsertWktblXmlByPublishNameAddressSeijiKatsudouLogic {

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
    public boolean practice(final AllBookDto allBookDto, // SUPPRESS CHECKSTYLE NPath NOPMD
            final UserPersonLeastDto userDto) {

        // 様式7の15(1-13):氏名と事務所の所在地
        for (Sheet071501OrganizationalActivityExpensesDto dto : allBookDto.getAllSheet0715ExpenseDto()
                .getAllSheetKbn071501Dto().getList()) {
            this.loopRow(dto.getList(), OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15,
                    OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SOSHIKIKATSUDOUHI, userDto);
        }

        // 枝区分項目2
        for (Sheet071502ElectionRelatedExpensesDto dto : allBookDto.getAllSheet0715ExpenseDto()
                .getAllSheetKbn071502Dto().getList()) {
            this.loopRow(dto.getList(), OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15,
                    OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SENKYOKATSUDOUHI, userDto);
        }

        // 枝区分項目3
        for (Sheet071503MagazinePublicationExpensesDto dto : allBookDto.getAllSheet0715ExpenseDto()
                .getAllSheetKbn071503Dto().getList()) {
            this.loopRow(dto.getList(), OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15,
                    OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_KIKANSHIHAKKOUHI, userDto);
        }

        // 枝区分項目4
        for (Sheet071504AdvertisingExpensesDto dto : allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071504Dto()
                .getList()) {
            this.loopRow(dto.getList(), OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15,
                    OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SENDENKOUKOKUHI, userDto);
        }

        // 枝区分項目5
        for (Sheet071505PartyHostingFeeDto dto : allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071505Dto()
                .getList()) {

            this.loopRow(dto.getList(), OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15,
                    OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_PARTYKAISAIHI, userDto);
        }

        // 枝区分項目6
        for (Sheet071506OtherBusinessExpensesDto dto : allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071506Dto()
                .getList()) {
            this.loopRow(dto.getList(), OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15,
                    OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SONOTAJIGYOU, userDto);
        }

        // 枝区分項目7
        for (Sheet071507ResearchExpensesDto dto : allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071507Dto()
                .getList()) {
            this.loopRow(dto.getList(), OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15,
                    OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_CHOUSAKENKYUHI, userDto);
        }

        // 枝区分項目8
        for (Sheet071508DonationsGrantsDto dto : allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071508Dto()
                .getList()) {
            this.loopRow(dto.getList(), OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15,
                    OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_KIFUKOUFUKIN, userDto);
        }

        // 枝区分項目9
        for (Sheet071509OtherExpensesDto dto : allBookDto.getAllSheet0715ExpenseDto().getAllSheetKbn071509Dto()
                .getList()) {
            this.loopRow(dto.getList(), OutcomeYoushikiKbnConstants.YOUSHIKI_KBN_15,
                    OutcomeYoushikiKbnConstants.YOUSHIKI_EDA15_KBN_SONOTAKEIHI, userDto);
        }

        return true;
    }

    private boolean loopRow(final List<Row071415OrdinaryExpensesDto> listRow07145, final int youshikiKbn,
            final int youshikiEdaKn, final UserPersonLeastDto userDto) {

        if (listRow07145.isEmpty()) {
            return true;
        }

        List<WkTblMasterAllByXmlEntity> listEntity = new ArrayList<>();
        for (Row071415OrdinaryExpensesDto row : listRow07145) {
            listEntity.add(this.createWkTblEntity(row.getName(), row.getJusho(), youshikiKbn, youshikiEdaKn, userDto));
        }
        return this.saveEntity(listEntity);
    }

    private WkTblMasterAllByXmlEntity createWkTblEntity(final String name, final String allAddress,
            final int youshikiKbn, final int youshikiEdaKbn, final UserPersonLeastDto userDto) {

        WkTblMasterAllByXmlEntity wkTblMasterAllByXmlEntity = new WkTblMasterAllByXmlEntity();

        wkTblMasterAllByXmlEntity.setInputSrcName(name);
        wkTblMasterAllByXmlEntity.setPartnerName(name);
        wkTblMasterAllByXmlEntity.setInputSrcAddress(allAddress);
        wkTblMasterAllByXmlEntity.setAllAddress(allAddress);
        wkTblMasterAllByXmlEntity.setYoushikiKbn((short) youshikiKbn);
        wkTblMasterAllByXmlEntity.setYoushikiEdaKbn((short) youshikiEdaKbn);

        wkTblMasterAllByXmlEntity.setJudgeReason("関連者区分が未決定です;");
        wkTblMasterAllByXmlEntity.setIsAffected(true);
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
