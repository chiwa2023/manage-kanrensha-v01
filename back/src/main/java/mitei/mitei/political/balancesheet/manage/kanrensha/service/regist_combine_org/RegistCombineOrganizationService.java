package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_combine_org;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.combine_org.CombineOrgCsvProcessor;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_combine.UpdateWkTblCombineOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCombineOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.year.GetCombineYearListLogic;
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

    /** 個人団体紐づけ入力内容Processor */
    @Autowired
    private CombineOrgCsvProcessor combineOrgCsvProcessor;

    /** 個人団体紐づけ登録可能年リスト */
    @Autowired
    private GetCombineYearListLogic getCombineYearListLogic;

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

        // 登録作業年を取得してprocessorによるチェックにセット
        List<Short> listYear = getCombineYearListLogic.practice();
        entityInput = combineOrgCsvProcessor.check(entityInput, listYear.getFirst(), listYear.getLast());

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

        WkTblPartnerCombineOrgEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblPartnerCombineOrgRepository.save(entitySrc);

        entityInput.setWkTblPartnerCombineOrgId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblPartnerCombineOrgRepository.save(entityInput).getWkTblPartnerCombineOrgId();
    }

}
