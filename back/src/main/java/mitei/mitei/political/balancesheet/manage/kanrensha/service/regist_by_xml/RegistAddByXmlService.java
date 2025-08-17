package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_by_xml;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.add_xml.UpdateWkTblAddByXmlCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
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

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集Dto
     * @return 新たなId
     */
    public WkTblMasterAllByXmlEntity practice(final UpdateWkTblAddByXmlCapsuleDto capsuleDto) {

        WkTblMasterAllByXmlEntity entityInput = capsuleDto.getWkTblMasterAllByXmlEntity();

        Optional<WkTblMasterAllByXmlEntity> optional = wkTblMasterAllByXmlRepository
                .findById(entityInput.getWkTblMasterAllByXmlId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return new WkTblMasterAllByXmlEntity();
        }

        // TODO プロセッサによるチェック
        // entityInput = partnerPoliOrgAddStdCsvProcessor.check(entityInput);

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

        WkTblMasterAllByXmlEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblMasterAllByXmlRepository.save(entitySrc);

        entityInput.setWkTblMasterAllByXmlId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblMasterAllByXmlRepository.save(entityInput);
    }

}
