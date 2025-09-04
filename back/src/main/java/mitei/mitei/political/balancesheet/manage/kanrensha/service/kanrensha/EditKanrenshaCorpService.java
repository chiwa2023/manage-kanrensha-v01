package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaCorpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationPropertyRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業・団体を編集する
 */
@Service
public class EditKanrenshaCorpService {

    /** 企業団体マスタRepository */
    @Autowired
    private MasterCorporationRepository masterCorporationRepository;

    /** 企業団体マスタ連絡先Repository */
    @Autowired
    private MasterCorporationAccessRepository masterCorporationAccessRepository;

    /** 企業団体マスタ連絡先Repository */
    @Autowired
    private MasterCorporationAddressRepository masterCorporationAddressRepository;

    /** 企業団体マスタ連絡先Repository */
    @Autowired
    private MasterCorporationBaseRepository masterCorporationBaseRepository;

    /** 企業団体マスタ属性Repository */
    @Autowired
    private MasterCorporationPropertyRepository masterCorporationPropertyRepository;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件
     * @return 処理結果
     */
    public FrameworkMessageAndResultDto practice(final SaveKanrenshaCorpCapsuleDto capsuleDto) {

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

        MasterCorporationEntity corporationEntity = new MasterCorporationEntity();
        // TODO 情報設定
        setTableDataHistoryUtil.practiceInsert(userDto, corporationEntity);
        String newCode = masterCorporationRepository.save(corporationEntity).getCorpKanrenshaCode();

        MasterCorporationAccessEntity corporationAccessEntity = new MasterCorporationAccessEntity();
        // TODO 情報設定
        corporationAccessEntity.setCorpKanrenshaCode(newCode);
        setTableDataHistoryUtil.practiceInsert(userDto, corporationAccessEntity);
        masterCorporationAccessRepository.save(corporationAccessEntity);

        MasterCorporationAddressEntity corporationAddressEntity = new MasterCorporationAddressEntity();
        // TODO 情報設定
        corporationAddressEntity.setCorpKanrenshaCode(newCode);
        setTableDataHistoryUtil.practiceInsert(userDto, corporationAddressEntity);
        masterCorporationAddressRepository.save(corporationAddressEntity);

        MasterCorporationBaseEntity corporationBaseEntity = new MasterCorporationBaseEntity();
        // TODO 情報設定
        corporationBaseEntity.setCorpKanrenshaCode(newCode);
        setTableDataHistoryUtil.practiceInsert(userDto, corporationBaseEntity);
        masterCorporationBaseRepository.save(corporationBaseEntity);

        MasterCorporationPropertyEntity corporationPropertyEntity = new MasterCorporationPropertyEntity();
        // TODO 情報設定
        corporationPropertyEntity.setCorpKanrenshaCode(newCode);
        setTableDataHistoryUtil.practiceInsert(userDto, corporationPropertyEntity);
        masterCorporationPropertyRepository.save(corporationPropertyEntity);

        // 更新処理に対して処理結果を返す
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        resultDto.setMessage("企業団体仮設定");

        return resultDto;
    }

}
