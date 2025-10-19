package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.rireki;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

/**
 * 関連者企業・団体Mapper
 */
@Component
public class PartnerCorpHistoryLineMapper implements LineMapper<PartnerCorpHistoryDto> {

    /** 団体名称カラム位置 */
    private static final int POS_NAME = 0;

    /** 団体住所カラム位置 */
    private static final int POS_ADDRESS = 1;

    /** 団体代表者カラム位置 */
    private static final int POS_DELEGATE = 2;

    /** 関連者コードカラム位置 */
    private static final int POS_KANRENSHA_CODE = 3;
    
    /** 団体代表者コードカラム位置 */
    private static final int POS_DELEGATE_CODE = 4;

    /**
     * 処理を行う
     */
    @Override
    public PartnerCorpHistoryDto mapLine(final String line, final int lineNumber) throws Exception {

        PartnerCorpHistoryDto historyDto = new PartnerCorpHistoryDto();
        String[] cell = line.split(",");

        historyDto.setPartnerName(this.removeQuote(cell[POS_NAME]));
        historyDto.setAllAddress(this.removeQuote(cell[POS_ADDRESS]));
        historyDto.setCorpDelegate(this.removeQuote(cell[POS_DELEGATE]));
        historyDto.setCorpKanrenshaCode(this.removeQuote(cell[POS_KANRENSHA_CODE]));
        historyDto.setOrgDelegateCode(this.removeQuote(cell[POS_DELEGATE_CODE]));

        return historyDto;
    }

    private String removeQuote(final String data) {

        String dataNew = data;

        return dataNew.replaceAll("\"", "");
    }
}
