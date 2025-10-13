package mitei.mitei.political.balancesheet.manage.kanrensha.service.address_registory;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address_registory.InsertAddressByComponentCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressRsdtTemplateEntity;

/**
 * 住居編集コンポーネントから住所直接挿入Service
 */
@Service
public class InsertAddressByComponentService {

    /** 住所保存Sevice */
    @Autowired
    private SaveAddressRegistoryRsdtService saveAddressRegistoryRsdtService;

    /** 同一コード住所抽出Sevice */
    @Autowired
    private ChoiceAddressRegistoryByCodeService choiceAddressRegistoryByCodeService;

    /**
     * 処理を行う
     *
     * @param capsuleDto 保存住所Dto
     * @return 処理結果Dto
     */
    @Transactional
    public FrameworkMessageAndResultDto practice(final InsertAddressByComponentCapsuleDto capsuleDto) {

        AddressRsdtTemplateEntity entity = new AddressRsdtTemplateEntity();
        InputAddressDto inputAddressDto = capsuleDto.getInputAddressDto();
        BeanUtils.copyProperties(inputAddressDto, entity);
        // TODO 追加でフィールドコピーをしなくてよいようにフィールド名を調整する
        entity.setPostalCode(inputAddressDto.getPostalcode1()+inputAddressDto.getPostalcode2());
        entity.setAddressBlock(inputAddressDto.getAddressPostal() + inputAddressDto.getAddressBlock());
        entity.setParcelRsdtId(inputAddressDto.getBlkId()+inputAddressDto.getRsdtId());

        // 同一コードの住所が存在した場合は登録しない
        List<AddressRsdtTemplateEntity> list = choiceAddressRegistoryByCodeService.practice(entity);
        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        if(!list.isEmpty()) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("同一コードの住所が存在したので保存を中断しました。");
            return resultDto;
        }

        Integer updateCount = saveAddressRegistoryRsdtService.practiceByEntity(entity);

        // 保存処理結果を返す
        if (0 < updateCount) {
            resultDto.setMessage("保存できました");
        } else {
            resultDto.setIsFailure(true);
            resultDto.setMessage("保存できませんでした");
        }

        return resultDto;
    }

}
