package mitei.mitei.political.balancesheet.manage.kanrensha.service.postal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.SavePostalCodeCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalRepository;

/**
 * 郵便番号挿入保存Service
 */
@Service
public class SavePostalCodeService {

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 処理結果Dto
     */
    public FrameworkMessageAndResultDto practice(final SavePostalCodeCapsuleDto capsuleDto) {
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();

        // TODO 処理者履歴を残していないので全面的にやり直し

        List<AddressPostalEntity> listSave = new ArrayList<>();
        listSave.add(capsuleDto.getAddressPostalEntity());

        // メッセージ処理
        if (listSave.size() == addressPostalRepository.saveAll(listSave).size()) {
            resultDto.setMessage("保存しました");

        } else {
            resultDto.setIsFailure(true);
            resultDto.setMessage("正常に保存できませんでした");
        }

        return resultDto;
    }
}
