package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_by_xml;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.add_xml.ConvertWkTblXmlToMasterCorpLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.add_xml.ConvertWkTblXmlToMasterPersonLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.add_xml.ConvertWkTblXmlToMasterPoliOrgLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * XMLからマスタ登録ワークテーブル編集Service
 */
@Service
public class RegistAddByXmlService {

    /** ワークテーブルXMLマスタ登録Repository */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** XMLワークテーブル個人最小変換Logic */
    @Autowired
    private ConvertWkTblXmlToMasterPersonLogic convertWkTblXmlToMasterPersonLogic;

    /** XMLワークテーブル企業最小変換Logic */
    @Autowired
    private ConvertWkTblXmlToMasterCorpLogic convertWkTblXmlToMasterCorpLogic;

    /** XMLワークテーブル政治団体最小変換Logic */
    @Autowired
    private ConvertWkTblXmlToMasterPoliOrgLogic convertWkTblXmlToMasterPoliOrgLogic;

    /**
     * 処理を行う
     *
     * @param entityInput 編集対象Entity
     * @param userDto     ユーザ最小限Dto
     * @return 新たなId
     */
    @Transactional
    public WkTblMasterAllByXmlEntity practice(final WkTblMasterAllByXmlEntity entityInput,
            final UserPersonLeastDto userDto) {

        Optional<WkTblMasterAllByXmlEntity> optional = wkTblMasterAllByXmlRepository
                .findById(entityInput.getWkTblMasterAllByXmlId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return new WkTblMasterAllByXmlEntity();
        }

        // 関連者区分を決めたら個人・企業・政治団体各最小マスタワークテーブルに転換
        Integer newId = 0;
        switch (entityInput.getKanrenshaKbn()) {
            case 1: // 個人
                newId = convertWkTblXmlToMasterPersonLogic.practice(entityInput, userDto);
                // 影響発生させて項目として終了
                entityInput.setIsFinish(true);
                entityInput.setIsAffected(true);
                break;
            case 2: // 企業団体
                newId = convertWkTblXmlToMasterCorpLogic.practice(entityInput, userDto);
                // 影響発生させて項目として終了
                entityInput.setIsFinish(true);
                entityInput.setIsAffected(true);
                break;
            case 3: // 政治団体 SUPPRESS CHECKSTYLE MagicNumber
                newId = convertWkTblXmlToMasterPoliOrgLogic.practice(entityInput, userDto);
                // 影響発生させて項目として終了
                entityInput.setIsFinish(true);
                entityInput.setIsAffected(true);
                break;

            default:
                // 外部で更新した体を取って削除フラグを立てる
                newId = -1;
                break;
        }

        // 更新できたら必ず旧データを削除
        Integer wkTblId = 0;
        if (newId != 0) {
            WkTblMasterAllByXmlEntity entitySrc = optional.get();
            setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
            wkTblId = wkTblMasterAllByXmlRepository.save(entitySrc).getWkTblMasterAllByXmlId();
        }

        if (0 != wkTblId) {
            entityInput.setWkTblMasterAllByXmlId(0); // 履歴を積むのでauto_increment
            final Short zero = Short.valueOf("0");
            if (zero.equals(entityInput.getKanrenshaKbn())) {
                // 団体区分が0の時は引き続きXMLワークテーブルにい続ける
                setTableDataHistoryUtil.practiceInsert(userDto, entityInput);
            } else {
                entityInput.setIsAffected(true);
                entityInput.setIsFinish(true);
                entityInput.setIsDisabled(true);
                entityInput.setJudgeReason("最小マスタへ移動済;");
                setTableDataHistoryUtil.practiceDelete(userDto, entityInput);
            }
            return wkTblMasterAllByXmlRepository.save(entityInput);
        }

        return new WkTblMasterAllByXmlEntity();
    }
}
