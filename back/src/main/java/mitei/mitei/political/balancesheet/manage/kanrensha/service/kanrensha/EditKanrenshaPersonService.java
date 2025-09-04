package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonPropertyRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人を編集する
 */
@Service
public class EditKanrenshaPersonService {

    /** 企業団体マスタRepository */
    @Autowired
    private MasterPersonRepository masterPersonRepository;

    /** 企業団体マスタ連絡先Repository */
    @Autowired
    private MasterPersonAccessRepository masterPersonAccessRepository;

    /** 企業団体マスタ連絡先Repository */
    @Autowired
    private MasterPersonAddressRepository masterPersonAddressRepository;

    /** 企業団体マスタ連絡先Repository */
    @Autowired
    private MasterPersonBaseRepository masterPersonBaseRepository;

    /** 企業団体マスタ属性Repository */
    @Autowired
    private MasterPersonPropertyRepository masterPersonPropertyRepository;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;


    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件
     * @return 処理結果
     */
    public FrameworkMessageAndResultDto practice(final SaveKanrenshaPersonCapsuleDto capsuleDto) {

        

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

        MasterPersonEntity corporationEntity = new MasterPersonEntity();
        // TODO 情報設定
        setTableDataHistoryUtil.practiceInsert(userDto, corporationEntity);
        String newCode = masterPersonRepository.save(corporationEntity).getPersonKanrenshaCode();

        MasterPersonAccessEntity corporationAccessEntity = new MasterPersonAccessEntity();
        // TODO 情報設定
        corporationAccessEntity.setPersonKanrenshaCode(newCode);
        setTableDataHistoryUtil.practiceInsert(userDto, corporationAccessEntity);
        masterPersonAccessRepository.save(corporationAccessEntity);

        MasterPersonAddressEntity corporationAddressEntity = new MasterPersonAddressEntity();
        // TODO 情報設定
        corporationAddressEntity.setPersonKanrenshaCode(newCode);
        setTableDataHistoryUtil.practiceInsert(userDto, corporationAddressEntity);
        masterPersonAddressRepository.save(corporationAddressEntity);

        MasterPersonBaseEntity corporationBaseEntity = new MasterPersonBaseEntity();
        // TODO 情報設定
        corporationBaseEntity.setPersonKanrenshaCode(newCode);
        setTableDataHistoryUtil.practiceInsert(userDto, corporationBaseEntity);
        masterPersonBaseRepository.save(corporationBaseEntity);

        MasterPersonPropertyEntity corporationPropertyEntity = new MasterPersonPropertyEntity();
        // TODO 情報設定
        corporationPropertyEntity.setPersonKanrenshaCode(newCode);
        setTableDataHistoryUtil.practiceInsert(userDto, corporationPropertyEntity);
        masterPersonPropertyRepository.save(corporationPropertyEntity);
        
        
        // 更新処理に対して処理結果を返す
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        resultDto.setMessage("個人仮設定");

        
        return resultDto;
    }

}
