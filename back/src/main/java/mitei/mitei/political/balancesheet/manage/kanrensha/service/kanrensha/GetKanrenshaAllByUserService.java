package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.KanrenshaKbnConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.constants.UserRoleConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.GetKanrenshaAllByUserResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.UserKanrenshaCombineEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.UserKanrenshaCombineRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * ユーザから紐づく関連者取得Servoce
 */
@Service
public class GetKanrenshaAllByUserService {

    /** ユーザ関連者紐づけRepository */
    @Autowired
    private UserKanrenshaCombineRepository userKanrenshaCombineRepository;

    /** 関連者個人マスタ */
    @Autowired
    private MasterPersonRepository masterPersonRepository;

    /** 関連者企業団体マスタ */
    @Autowired
    private MasterCorporationRepository masterCorporationRepository;

    /** 関連者政治団体マスタ */
    @Autowired
    private MasterPoliticalOrganizationRepository masterPoliticalOrganizationRepository;

    /**
     * 処理を行う
     *
     * @param userDto ユーザDto
     */
    public GetKanrenshaAllByUserResultDto practice(final UserPersonLeastDto userDto) {

        // ユーザに紐づく関連者をすべて取得する
        List<UserKanrenshaCombineEntity> listCombine = userKanrenshaCombineRepository
                .findByUseUserCodeAndIsLatest(userDto.getUserPersonCode(), SetTableDataHistoryUtil.INSERT_STATE);

        GetKanrenshaAllByUserResultDto resultDto = new GetKanrenshaAllByUserResultDto();

        // ユーザが個人権限を持っている場合
        if (userDto.getListRoles().contains(UserRoleConstants.ROLE_PARTNER_PERSON)) {
            MasterPersonEntity personEntity = this.getMasterPersonEntity(listCombine, userDto);

            // 1件だけの場合は結果に複写
            if (!Objects.isNull(personEntity)) {
                resultDto.setMasterPersonEntity(personEntity);
            }
        }

        // ユーザが企業団体権限を持っている場合
        if (userDto.getListRoles().contains(UserRoleConstants.ROLE_PARTNER_CORP)) {
            List<String> listKanrenshaCode = this.convertCodeList(listCombine, KanrenshaKbnConstants.CORP);

            if (!listKanrenshaCode.isEmpty()) {
                resultDto.setListCorpEntity(masterCorporationRepository
                        .findByCorpKanrenshaCodeInAndIsLatest(listKanrenshaCode, SetTableDataHistoryUtil.INSERT_STATE));
            }
        }

        // ユーザが政治団体権限を持っている場合
        if (userDto.getListRoles().contains(UserRoleConstants.ROLE_PARTNER_POLI_ORG)) {
            List<String> listKanrenshaCode = this.convertCodeList(listCombine, KanrenshaKbnConstants.POLI_ORG);

            if (!listKanrenshaCode.isEmpty()) {
                resultDto.setListPoliOrgEntity(
                        masterPoliticalOrganizationRepository.findByPoliOrgKanrenshaCodeInAndIsLatest(listKanrenshaCode,
                                SetTableDataHistoryUtil.INSERT_STATE));
            }
        }

        if (resultDto.getListCorpEntity().isEmpty() && resultDto.getListPoliOrgEntity().isEmpty()
                && 0 == resultDto.getMasterPersonEntity().getMasterPersonId()) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("1件も取得できませんでした。");
        }

        return resultDto;
    }

    private List<String> convertCodeList(final List<UserKanrenshaCombineEntity> listCombine, final Short kanrenshaKbn) {

        List<String> list = new ArrayList<>();

        // 関連者区分が一致する行の関連者コードをリストに追加する
        for (UserKanrenshaCombineEntity entity : listCombine) {
            if (kanrenshaKbn.equals(entity.getKanrenshaKbn())) {
                list.add(entity.getKanrenshaCode());
            }
        }

        return list;
    }

    private MasterPersonEntity getMasterPersonEntity(final List<UserKanrenshaCombineEntity> listCombine,
            final UserPersonLeastDto userDto) {

        List<String> listKanrenshaCode = this.convertCodeList(listCombine, KanrenshaKbnConstants.PERSON);

        int size = listKanrenshaCode.size();
        if (0 == size) {
            return null;
        }

        // 個人に紐づく関連者個人が複数いる場合はデータ障害
        final int size1 = 1;
        if (size1 < size) {
            throw new DataRetrievalFailureException("ユーザに対して複数の関連者個人が紐づいています。:" + userDto.getUserPersonCode());
        }

        String kanrenshaCode = listKanrenshaCode.get(0);
        List<MasterPersonEntity> listPerson = masterPersonRepository.findByPersonKanrenshaCodeAndIsLatest(kanrenshaCode,
                SetTableDataHistoryUtil.INSERT_STATE);
        size = listPerson.size();
        if (0 == size) {
            return null;
        }

        // 関連者コードが同一で最新が2件以上ある場合はデータ障害
        if (size1 < listPerson.size()) {
            throw new DataRetrievalFailureException("関連者コードに対し複数の最新が存在します:" + kanrenshaCode);
        }

        return listPerson.get(0);
    }

}
