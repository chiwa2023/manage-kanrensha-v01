package mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.UserRoleConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.SelectOptionIntegerDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.PickupOrgSelectOptionResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.UserRiyoushaCombineEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaComradeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.UserRiyoushaCombineRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * APIユーザ組織選択肢を抽出する
 */
@Service
public class PickupRiyoushaOrgOptionComradeService {

    /** APIユーザ紐づきRepository */
    @Autowired
    private UserRiyoushaCombineRepository userRiyoushaCombineRepository;

    /** APIユーザマスタRepository */
    @Autowired
    private RiyoushaComradeRepository riyoushaComradeRepository;

    /**
     * 処理を行う
     *
     * @param userDto ユーザDto
     * @return 組織選択肢
     */
    public PickupOrgSelectOptionResultDto practice(final UserPersonLeastDto userDto) {

        List<UserRiyoushaCombineEntity> listUserCombine = userRiyoushaCombineRepository
                .findByUseUserCodeAndRoleAndIsLatest(userDto.getUserPersonCode(), UserRoleConstants.ROLE_COMRADE,
                        SetTableDataHistoryUtil.INSERT_STATE);

        if (listUserCombine.isEmpty()) {
            throw new EmptyResultDataAccessException("ユーザからAPIユーザが取得できない", 1);
        }

        // TODO 複数は取れない予定だが、未決定
        Integer comradeCode = listUserCombine.get(0).getRiyoushaCode();
        
        
        // 組織一覧をselectボックス変換する
        List<SelectOptionIntegerDto> listComradeOrg = riyoushaComradeRepository.findByUserCodeOrgOptions(comradeCode);
        // 取得できない場合は新規追加
        if (listComradeOrg.isEmpty()) {
            SelectOptionIntegerDto dto = new SelectOptionIntegerDto(0, "新規追加");
            listComradeOrg.add(dto);
        }

        PickupOrgSelectOptionResultDto resultDto = new PickupOrgSelectOptionResultDto();
        resultDto.setListOrgOptions(listComradeOrg);
        resultDto.setRiyoushaComradeEntity(riyoushaComradeRepository
                .findByRiyoushaComradeCodeAndIsLatest(comradeCode, SetTableDataHistoryUtil.INSERT_STATE).get(0));

        return resultDto;
    }
}
