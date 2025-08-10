package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_master_min;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_min.PartnerPersonAddMiniCsvProcessor;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_min.UpdateWkTblMinPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPersonAddMinRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * ワークテーブルマスタ企業／団体最小編集Service
 */
@Service
public class RegistBulkMasterMinPersonService {

    /** ワークテーブルマスタ企業／団体最小Repository */
    @Autowired
    private WkTblPartnerPersonAddMinRepository wkTblPartnerPersonAddMinRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 編集内容確認Peocssor */
    @Autowired
    private PartnerPersonAddMiniCsvProcessor partnerPersonAddMiniCsvProcessor;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @Transactional
    public Integer practice(final UpdateWkTblMinPersonCapsuleDto capsuleDto) {

        WkTblPartnerPersonAddMinEntity entityInput = capsuleDto.getWkTblPartnerPersonAddMinEntity();

        Optional<WkTblPartnerPersonAddMinEntity> optional = wkTblPartnerPersonAddMinRepository
                .findById(entityInput.getWkTblPartnerPersonAddMinId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return 0;
        }

        entityInput = partnerPersonAddMiniCsvProcessor.check(entityInput);

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

        WkTblPartnerPersonAddMinEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblPartnerPersonAddMinRepository.save(entitySrc);

        entityInput.setWkTblPartnerPersonAddMinId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblPartnerPersonAddMinRepository.save(entityInput).getWkTblPartnerPersonAddMinId();
    }

}
