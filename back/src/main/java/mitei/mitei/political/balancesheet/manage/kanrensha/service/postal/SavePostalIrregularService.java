package mitei.mitei.political.balancesheet.manage.kanrensha.service.postal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.SavePostalIrregularCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalIrregularEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalIrregularRepository;

/**
 * 郵便番号不規則更新Service
 */
@Service
public class SavePostalIrregularService {

    /** 郵便番号不規則データRepository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 編集内容Dto
     * @return 処理結果Dto
     */
    public FrameworkMessageAndResultDto practice(final SavePostalIrregularCapsuleDto capsuleDto) {

        // TODO 処理者履歴を残していないので全面的にやり直し

        List<AddressPostalIrregularEntity> listSave = new ArrayList<>();
        listSave.add(capsuleDto.getAddressPostalIrregularEntity());

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        // メッセージ処理
        if (listSave.size() == addressPostalIrregularRepository.saveAll(listSave).size()) {
            resultDto.setMessage("保存しました");

        } else {
            resultDto.setIsFailure(true);
            resultDto.setMessage("正常に保存できませんでした");
        }

        return resultDto;
    }

}
