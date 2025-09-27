package mitei.mitei.political.balancesheet.manage.kanrensha.logic.add_xml;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.constants.blancesheet_report.IncomeYoushikiKbnConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.xml.XmlKanrenshaUniquekeyDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 名前、住所、職業(団体代表者)3項目の様式の重複データを処理対象外と中断させる
 */
@Component
public class SuspendDuplicateWkTblXmlDecideKanrenshaLogic {

    /** XMLから最小マスタ登録ワークテーブルRepository */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 抽出様式区分リスト */
    private final List<Integer> listYoushikiKbn = new ArrayList<>();

    /**
     * コンストラクタ
     */
    public SuspendDuplicateWkTblXmlDecideKanrenshaLogic() {
        listYoushikiKbn.add(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_07);
        listYoushikiKbn.add(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_08);
        listYoushikiKbn.add(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11);
        listYoushikiKbn.add(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_12);
    }

    /**
     * 処理を行う
     *
     * @param userDto ユーザ最小限Dto
     */
    public void practice(final UserPersonLeastDto userDto) {

        Integer userCode = userDto.getUserPersonCode();

        List<XmlKanrenshaUniquekeyDto> listKeyGroup = wkTblMasterAllByXmlRepository
                .findDuplicateUniqueKeyDecideKanrensha(userCode);

        for (XmlKanrenshaUniquekeyDto uniqueDto : listKeyGroup) {
            List<WkTblMasterAllByXmlEntity> list = wkTblMasterAllByXmlRepository
                    .findByInputSrcNameAndInputSrcAddressAndInputSrcKeyAndYoushikiKbnInAndInsertUserCodeOrderByWkTblMasterAllByXmlIdAsc(
                            uniqueDto.getInputSrcName(), uniqueDto.getInputSrcAddress(), uniqueDto.getInputSrcKey(),
                            listYoushikiKbn, userCode);
            list.remove(0); // 1行だけは処理実行行として残す
            for (WkTblMasterAllByXmlEntity entity : list) {
                setTableDataHistoryUtil.practiceDelete(userDto, entity); // 削除
                entity.setIsFinish(true);
                entity.setIsDisabled(true);
                entity.setJudgeReason("アップロードファイル内で重複しているデータです");
            }
            wkTblMasterAllByXmlRepository.saveAllAndFlush(list);
        }

    }

}
