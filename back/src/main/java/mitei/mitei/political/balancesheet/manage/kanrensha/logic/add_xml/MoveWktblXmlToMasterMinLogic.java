package mitei.mitei.political.balancesheet.manage.kanrensha.logic.add_xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * XML登録ワークテーブルから各関連者ワークテーブルへ移管Logic
 */
@Component
public class MoveWktblXmlToMasterMinLogic {

    /** XMLから最小マスタ登録ワークテーブルRepository */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** XML追加ワークテーブルからマスタ最小個人ワークテーブル変換Logic */
    @Autowired
    private ConvertWkTblXmlToMasterPersonLogic convertWkTblXmlToMasterPersonLogic;

    /** XML追加ワークテーブルからマスタ最小企業・団体ワークテーブル変換Logic */
    @Autowired
    private ConvertWkTblXmlToMasterCorpLogic convertWkTblXmlToMasterCorpLogic;

    /** XML追加ワークテーブルからマスタ最小政治団体ワークテーブル変換Logic */
    @Autowired
    private ConvertWkTblXmlToMasterPoliOrgLogic convertWkTblXmlToMasterPoliOrgLogic;

    /** 関連者区分未定 */
    private static final int KANRENSHA_MITEI = 0;

    /**
     * 処理を行う
     *
     * @param userDto ユーザ最小限Dto
     * @return 処理完了
     */
    public boolean practce(final UserPersonLeastDto userDto) {

        for (WkTblMasterAllByXmlEntity baseEntity : wkTblMasterAllByXmlRepository
                .findByInsertUserCodeAndKanrenshaKbnNotAndIsLatest(userDto.getUserPersonCode(), KANRENSHA_MITEI,
                        SetTableDataHistoryUtil.INSERT_STATE)) {
            int newId = 0;
            switch (baseEntity.getKanrenshaKbn()) {
                case 1:
                    newId = convertWkTblXmlToMasterPersonLogic.practice(baseEntity, userDto);
                    break;
                case 2:
                    newId = convertWkTblXmlToMasterCorpLogic.practice(baseEntity, userDto);
                    break;
                case 3: // SUPPRESS CHECKSTYLE MagicNumber
                    newId = convertWkTblXmlToMasterPoliOrgLogic.practice(baseEntity, userDto);
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected value: " + baseEntity.getKanrenshaKbn());
            }
            if (0 != newId) {
                baseEntity.setIsAffected(true);
                baseEntity.setIsFinish(true);
                baseEntity.setIsAffected(true);
                baseEntity.setJudgeReason("最小マスタへ移動済;");
                setTableDataHistoryUtil.practiceDelete(userDto, baseEntity);
                wkTblMasterAllByXmlRepository.saveAndFlush(baseEntity);
            }

        }

        return true;
    }

}
