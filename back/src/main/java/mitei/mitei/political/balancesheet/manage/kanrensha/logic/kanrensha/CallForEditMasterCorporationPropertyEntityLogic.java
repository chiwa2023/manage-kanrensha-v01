package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.HoujinShubetsuConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationPropertyRepository;

/**
 * 編集用に企業団体属性マスタを取得する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class CallForEditMasterCorporationPropertyEntityLogic {

    /** 企業団体属性マスタリポジトリ */
    @Autowired
    private MasterCorporationPropertyRepository masterCorporationPropertyRepository;

    /**
     * 処理を行う
     *
     * @param kanrenshaCorpDto 関連者企業団体Dto
     * @return MasterCorporationPropertyEntity or null
     */
    public MasterCorporationPropertyEntity practice(final KanrenshaCorpDto kanrenshaCorpDto) {

        // IDをキーにマスタを取得
        MasterCorporationPropertyEntity entity = masterCorporationPropertyRepository.findById(kanrenshaCorpDto.getPropertyId())
                .orElseThrow(() -> new EmptyResultDataAccessException("Not found master_corporation_property. id = " + kanrenshaCorpDto.getPropertyId(), 1));

        // 最新版か確認
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException("Target data is not the latest version. id = " + kanrenshaCorpDto.getPropertyId());
        }

        // DTOとEntityの値を比較
        boolean isNotChanged = Objects.equals(entity.getPartnerName(), kanrenshaCorpDto.getInputOrgNameDto().getOrgName())
                && Objects.equals(entity.getHoujinSbts(), kanrenshaCorpDto.getHoujinSbts())
                && Objects.equals(entity.getIsForeign(), HoujinShubetsuConstants.GAIKOKU_KAISHA.equals(kanrenshaCorpDto.getHoujinSbts()));

        // 変更がない場合はnullを返却
        if (isNotChanged) {
            return null;
        }

        // 変更がある場合は取得したEntityをそのまま返却
        return entity;
    }
}
