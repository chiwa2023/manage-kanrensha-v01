package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationAccessRepository;

/**
 * 編集用にマスタ企業団体連絡先取得Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class CallForEditMasterCorporationAccessEntityLogic {

    /** マスタ企業団体連絡先レポジトリ */
    @Autowired
    private MasterCorporationAccessRepository repository;

    /**
     * 処理を行う
     *
     * @param dto 関連者企業団体Dto
     * @return 更新が必要ない場合はnull、必要な場合はDBから取得したEntity
     */
    public MasterCorporationAccessEntity practice(final KanrenshaCorpDto dto)
            throws EmptyResultDataAccessException, ConcurrencyFailureException { // NOPMD UncheckedException

        // accessIdを使ってEntityを取得
        MasterCorporationAccessEntity entity = repository.findById(dto.getAccessId()).orElseThrow(
                () -> new EmptyResultDataAccessException("No entity found with id: " + dto.getAccessId(), 1));

        // isLatestがfalseの場合は排他エラー
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException("The record with id " + dto.getAccessId() + " is not the latest.");
        }

        // DTOとEntityの値を比較
        if (isEntityChanged(dto, entity)) {
            // 変更がある場合は、DBから取得したEntityをそのまま返す
            return entity;
        } else {
            // 変更がない場合はnullを返す
            return null;
        }
    }

    /**
     * DTOとEntityの値を比較して変更があるか確認する
     *
     * @param dto    関連者企業団体Dto
     * @param entity MasterCorporationAccessEntity
     * @return 変更があればtrue
     */
    private boolean isEntityChanged( // SUPPRESS CHECKSTYLE NPath
            final KanrenshaCorpDto dto, final MasterCorporationAccessEntity entity) {
        InputAccessDto accessDto = dto.getInputAccessDto();

        if (!Objects.equals(entity.getPartnerName(), dto.getInputOrgNameDto().getOrgName())) {
            return true;
        }
        if (!Objects.equals(entity.getPhon1(), accessDto.getPhon1())) {
            return true;
        }
        if (!Objects.equals(entity.getPhon2(), accessDto.getPhon2())) {
            return true;
        }
        if (!Objects.equals(entity.getPhon3(), accessDto.getPhon3())) {
            return true;
        }
        if (!Objects.equals(entity.getEmail(), accessDto.getEmail())) {
            return true;
        }
        if (!Objects.equals(entity.getMyPortalUrl(), accessDto.getMyPortalUrl())) {
            return true;
        }
        if (!Objects.equals(entity.getSnsServiceName(), accessDto.getSnsServiceName())) {
            return true;
        }
        if (!Objects.equals(entity.getSnsPortalUrl(), accessDto.getSnsPortalUrl())) {
            return true;
        }
        if (!Objects.equals(entity.getSnsAccount(), accessDto.getSnsAccount())) { // NOPMD
            return true;
        }

        return false;
    }

}
