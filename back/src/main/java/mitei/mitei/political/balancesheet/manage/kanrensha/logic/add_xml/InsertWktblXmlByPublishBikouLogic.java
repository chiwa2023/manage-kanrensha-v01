package mitei.mitei.political.balancesheet.manage.kanrensha.logic.add_xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.common.constants.blancesheet_report.IncomeYoushikiKbnConstants;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070300JournalAndOtherDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070400BorrowedMoneyDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row070600OtherIncomeDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * XMLから最小マスタ登録1項目ワークテーブル挿入
 */
@Component
public class InsertWktblXmlByPublishBikouLogic {

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

        // 様式7の3:備考欄に取引相手の記載がある可能性がある
        boolean isGoNext3;
        List<Row070300JournalAndOtherDto> listRow0703 = allBookDto.getAllSheet0703JournalAndOtherDto()
                .getSheet070300JournalAndOtherDto().getList();
        if (listRow0703.isEmpty()) {
            isGoNext3 = true;
        } else {
            List<WkTblMasterAllByXmlEntity> listEntity = new ArrayList<>();
            for (Row070300JournalAndOtherDto row : listRow0703) {
                listEntity.add(
                        this.createWkTblEntity(row.getBikou(), IncomeYoushikiKbnConstants.YOUSHIKI_KBN_03, userDto));
            }
            isGoNext3 = this.saveEntity(listEntity);
        }

        // 様式7の4:わかるのは借入先(備考に住所の記載があればラッキー)
        boolean isGoNext4;
        List<Row070400BorrowedMoneyDto> listRow0704 = allBookDto.getAllSheet0704BorrowedMoneyDto()
                .getSheet070400BorrowedMoneyDto().getList();
        if (listRow0704.isEmpty() && isGoNext3) {
            isGoNext4 = true;
        } else {
            List<WkTblMasterAllByXmlEntity> listEntity = new ArrayList<>();
            for (Row070400BorrowedMoneyDto row : listRow0704) {
                listEntity.add(
                        this.createWkTblEntity(row.getBikou(), IncomeYoushikiKbnConstants.YOUSHIKI_KBN_04, userDto));
            }
            isGoNext4 = this.saveEntity(listEntity);
        }

        // 様式7の6:備考欄に取引相手の記載がある可能性がある
        boolean isGoNext6;
        List<Row070600OtherIncomeDto> listRow0706 = allBookDto.getAllSheet0706OtherIncomeDto()
                .getSheet070600OtherIncomeDto().getList();
        if (listRow0706.isEmpty() && isGoNext4) {
            isGoNext6 = true;
        } else {
            List<WkTblMasterAllByXmlEntity> listEntity = new ArrayList<>();
            for (Row070600OtherIncomeDto row : listRow0706) {
                listEntity.add(
                        this.createWkTblEntity(row.getBikou(), IncomeYoushikiKbnConstants.YOUSHIKI_KBN_06, userDto));
            }
            isGoNext6 = this.saveEntity(listEntity);
        }

        return isGoNext6;
    }

    private WkTblMasterAllByXmlEntity createWkTblEntity(final String bikou, final int youshikiKbn,
            final UserPersonLeastDto userDto) {

        WkTblMasterAllByXmlEntity wkTblMasterAllByXmlEntity = new WkTblMasterAllByXmlEntity();
        wkTblMasterAllByXmlEntity.setBikou(bikou);
        wkTblMasterAllByXmlEntity.setYoushikiKbn((short) youshikiKbn);
        wkTblMasterAllByXmlEntity.setJudgeReason("関連者区分が未決定です;");
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
