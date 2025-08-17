package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_master_std;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_std.PartnerPersonAddStdCsvProcessor;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_std.UpdateWkTblStdPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * ワークテーブルマスタ企業／団体標準編集Service
 */
@Service
public class RegistBulkMasterStdPersonService {

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblMasterPersonRepository wkTblMasterPersonRepository;

    /** テーブル履歴セットRepository */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 編集内容確認Peocssor */
    @Autowired
    private PartnerPersonAddStdCsvProcessor partnerPersonAddStdCsvProcessor;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 追加されたId
     */
    @Transactional
    public WkTblMasterPersonEntity practice(final UpdateWkTblStdPersonCapsuleDto capsuleDto) {

        WkTblMasterPersonEntity entityInput = capsuleDto.getWkTblMasterPersonEntity();

        Optional<WkTblMasterPersonEntity> optional = wkTblMasterPersonRepository
                .findById(entityInput.getWkTblMasterPersonId());

        // 万が一元データが探せない場合は処理中断
        if (optional.isEmpty()) {
            return new WkTblMasterPersonEntity();
        }

        entityInput = partnerPersonAddStdCsvProcessor.check(entityInput);

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

        WkTblMasterPersonEntity entitySrc = optional.get();
        setTableDataHistoryUtil.practiceDelete(userDto, entitySrc);
        wkTblMasterPersonRepository.save(entitySrc);

        entityInput.setWkTblMasterPersonId(0); // 履歴を積むのでauto_increment
        setTableDataHistoryUtil.practiceInsert(userDto, entityInput);

        return wkTblMasterPersonRepository.save(entityInput);
    }

}
