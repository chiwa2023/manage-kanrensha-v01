package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationRepository;

/**
 * 編集用に企業団体マスタを取得する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class CallForEditMasterCorporationMasterEntityLogic {

    /** 企業団体マスタリポジトリ */
    @Autowired
    private MasterCorporationRepository masterCorporationRepository;

    /**
     * 処理を行う
     *
     * @param kanrenshaCorpDto 関連者企業団体Dto
     * @return MasterCorporationEntity or null
     */
    public MasterCorporationEntity practice(final KanrenshaCorpDto kanrenshaCorpDto) {

        // IDをキーにマスタを取得
        MasterCorporationEntity entity = masterCorporationRepository.findById(kanrenshaCorpDto.getMasterId())
                .orElseThrow(() -> new EmptyResultDataAccessException("Not found master_corporation. id = " + kanrenshaCorpDto.getMasterId(), 1));

        // 最新版か確認
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException("Target data is not the latest version. id = " + kanrenshaCorpDto.getMasterId());
        }

        // DTOとEntityの値を比較
        boolean isNotChanged = Objects.equals(entity.getPartnerName(), kanrenshaCorpDto.getInputOrgNameDto().getOrgName())
                && Objects.equals(entity.getAllAddress(), kanrenshaCorpDto.getInputAddressDto().getAddressAll());

        // 変更がない場合はnullを返却
        if (isNotChanged) {
            return null;
        }

        // 変更がある場合は取得したEntityをそのまま返却
        return entity;
    }
}
