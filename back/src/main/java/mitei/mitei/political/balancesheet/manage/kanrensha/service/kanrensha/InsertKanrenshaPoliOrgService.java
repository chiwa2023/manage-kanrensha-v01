package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationPropertyRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者政治団体を編集する
 */
@Service
public class InsertKanrenshaPoliOrgService {


    /** 政治団体マスタRepository */
    @Autowired
    private MasterPoliticalOrganizationRepository masterPoliticalOrganizationRepository;

    /** 政治団体マスタ連絡先Repository */
    @Autowired
    private MasterPoliticalOrganizationAccessRepository masterPoliticalOrganizationAccessRepository;

    /** 政治団体マスタ連絡先Repository */
    @Autowired
    private MasterPoliticalOrganizationAddressRepository masterPoliticalOrganizationAddressRepository;

    /** 政治団体マスタ連絡先Repository */
    @Autowired
    private MasterPoliticalOrganizationBaseRepository masterPoliticalOrganizationBaseRepository;

    /** 政治団体マスタ属性Repository */
    @Autowired
    private MasterPoliticalOrganizationPropertyRepository masterPoliticalOrganizationPropertyRepository;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件
     * @return 処理結果
     */
    public FrameworkMessageAndResultDto practice(final SaveKanrenshaPoliOrgDto capsuleDto) {

        
        
        

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

        MasterPoliticalOrganizationEntity poliOrgEntity = new MasterPoliticalOrganizationEntity();
        // TODO 情報設定
        setTableDataHistoryUtil.practiceInsert(userDto, poliOrgEntity);
        String newCode = masterPoliticalOrganizationRepository.save(poliOrgEntity).getPoliOrgKanrenshaCode();

        MasterPoliticalOrganizationAccessEntity poliOrgAccessEntity = new MasterPoliticalOrganizationAccessEntity();
        // TODO 情報設定
        poliOrgAccessEntity.setPoliOrgKanrenshaCode(newCode);
        setTableDataHistoryUtil.practiceInsert(userDto, poliOrgAccessEntity);
        masterPoliticalOrganizationAccessRepository.save(poliOrgAccessEntity);

        MasterPoliticalOrganizationAddressEntity poliOrgAddressEntity = new MasterPoliticalOrganizationAddressEntity();
        // TODO 情報設定
        poliOrgAddressEntity.setPoliOrgKanrenshaCode(newCode);
        setTableDataHistoryUtil.practiceInsert(userDto, poliOrgAddressEntity);
        masterPoliticalOrganizationAddressRepository.save(poliOrgAddressEntity);

        MasterPoliticalOrganizationBaseEntity poliOrgBaseEntity = new MasterPoliticalOrganizationBaseEntity();
        // TODO 情報設定
        poliOrgBaseEntity.setPoliOrgKanrenshaCode(newCode);
        setTableDataHistoryUtil.practiceInsert(userDto, poliOrgBaseEntity);
        masterPoliticalOrganizationBaseRepository.save(poliOrgBaseEntity);

        MasterPoliticalOrganizationPropertyEntity poliOrgPropertyEntity = new MasterPoliticalOrganizationPropertyEntity();
        // TODO 情報設定
        poliOrgPropertyEntity.setPoliOrgKanrenshaCode(newCode);
        setTableDataHistoryUtil.practiceInsert(userDto, poliOrgPropertyEntity);
        masterPoliticalOrganizationPropertyRepository.save(poliOrgPropertyEntity);

        // 更新処理に対して処理結果を返す
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        resultDto.setMessage("政治団体仮設定");

        return resultDto;
    }

}
