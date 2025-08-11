package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_combine_org;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.KanrenshaKbnConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_combine.UpdateWkTblCombineOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCombineOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCombineOrgRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 個人企業団体紐づけワークテーブル編集Controller
 */
@Service
public class RegistCombineOrganizationService {

    /** ワークテーブル個人団体紐づけRepository */
    @Autowired
    private WkTblPartnerCombineOrgRepository wkTblPartnerCombineOrgRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集条件Dto
     * @return 新規Id
     */
    public Integer practice(final UpdateWkTblCombineOrgCapsuleDto capsuleDto) {

        WkTblPartnerCombineOrgEntity entityInput = capsuleDto.getWkTblPartnerCombineOrgEntity();

        Optional<WkTblPartnerCombineOrgEntity> optional = wkTblPartnerCombineOrgRepository
                .findById(entityInput.getWkTblPartnerCombineOrgId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return 0;
        }

        // TODO プロセッサによるチェック関連者区分でチェックが異なる
        if(KanrenshaKbnConstants.CORP.equals(entityInput.getKanrenshaKbn())) {
            // 企業団体との紐づけチェック
            
            // entityInput = partnerPoliOrgAddStdCsvProcessor.check(entityInput);
        }
        if(KanrenshaKbnConstants.POLI_ORG.equals(entityInput.getKanrenshaKbn())) {
            // 企業団体との政治団体との紐づけチェック
            
            // entityInput = partnerPoliOrgAddStdCsvProcessor.check(entityInput);
        }

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

        WkTblPartnerCombineOrgEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblPartnerCombineOrgRepository.save(entitySrc);

        entityInput.setWkTblPartnerCombineOrgId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblPartnerCombineOrgRepository.save(entityInput).getWkTblPartnerCombineOrgId();
    }

}
