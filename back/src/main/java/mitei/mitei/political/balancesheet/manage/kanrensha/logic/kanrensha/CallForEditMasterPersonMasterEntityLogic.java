package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonRepository;

/**
 * 編集用に個人マスタを取得する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class CallForEditMasterPersonMasterEntityLogic {

    /** 個人マスタリポジトリ */
    @Autowired
    private MasterPersonRepository masterPersonRepository;

    /**
     * 処理を行う
     *
     * @param kanrenshaPersonDto 関連者個人Dto
     * @return MasterPersonEntity or null
     */
    public MasterPersonEntity practice(final KanrenshaPersonDto kanrenshaPersonDto) {

        // IDをキーにマスタを取得
        MasterPersonEntity entity = masterPersonRepository.findById(kanrenshaPersonDto.getMasterId())
                .orElseThrow(() -> new EmptyResultDataAccessException("Not found master_person. id = " + kanrenshaPersonDto.getMasterId(), 1));

        // 最新版か確認
        if (!entity.getIsLatest()) {
            throw new ConcurrencyFailureException("Target data is not the latest version. id = " + kanrenshaPersonDto.getMasterId());
        }

        // DTOとEntityの値を比較
        boolean isNotChanged = Objects.equals(entity.getPartnerName(), kanrenshaPersonDto.getInputPersonNameDto().getAllName())
                && Objects.equals(entity.getAllAddress(), kanrenshaPersonDto.getInputAddressDto().getAddressAll())
                && Objects.equals(entity.getPersonShokugyou(), kanrenshaPersonDto.getInputShokugyouDto().getAllShokugyou());

        // 変更がない場合はnullを返却
        if (isNotChanged) {
            return null;
        }

        // 変更がある場合は取得したEntityをそのまま返却
        return entity;
    }
}
