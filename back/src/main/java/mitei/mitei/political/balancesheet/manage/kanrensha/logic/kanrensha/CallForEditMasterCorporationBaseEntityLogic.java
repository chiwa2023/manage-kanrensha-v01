package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationBaseRepository;

/**
 * 編集用に企業団体基本マスタを取得する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class CallForEditMasterCorporationBaseEntityLogic {

    /** 企業団体基本マスタリポジトリ */
    @Autowired
    private MasterCorporationBaseRepository masterCorporationBaseRepository;

    /**
     * 処理を行う
     *
     * @param kanrenshaCorpDto 関連者企業団体Dto
     * @return MasterCorporationBaseEntity or null
     */
    public MasterCorporationBaseEntity practice(final KanrenshaCorpDto kanrenshaCorpDto) {

        // IDをキーにマスタを取得 (DTOのIDはbaseIdと想定)
        MasterCorporationBaseEntity entity = masterCorporationBaseRepository.findById(kanrenshaCorpDto.getBaseId())
                .orElseThrow(() -> new EmptyResultDataAccessException(
                        "Not found master_corporation_base. id = " + kanrenshaCorpDto.getBaseId(), 1));

        // 最新版か確認
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException(
                    "Target data is not the latest version. id = " + kanrenshaCorpDto.getBaseId());
        }

        // DTOとEntityの値を比較
        final InputOrgNameDto orgNameDto = kanrenshaCorpDto.getInputOrgNameDto();

        boolean isNotChanged = Objects.equals(entity.getPartnerName(), orgNameDto.getOrgName())
                && Objects.equals(entity.getPartnerName(), orgNameDto.getOrgName())
                && Objects.equals(entity.getOrgNameKana(), orgNameDto.getOrgNameKana())
                && Objects.equals(entity.getIsShiten(), kanrenshaCorpDto.getIsShiten())
                && Objects.equals(entity.getOrgDelegateCode(), kanrenshaCorpDto.getOrgDelegateLeastDto().getPersonKanrenshaCode());

        // 変更がない場合はnullを返却
        if (isNotChanged) {
            return null;
        }

        // 変更がある場合は取得したEntityをそのまま返却
        return entity;
    }
}
