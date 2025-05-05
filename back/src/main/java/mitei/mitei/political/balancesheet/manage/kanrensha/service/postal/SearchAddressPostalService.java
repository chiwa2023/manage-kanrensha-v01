package mitei.mitei.political.balancesheet.manage.kanrensha.service.postal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.SelectOptionNumberDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.PostalCodePostalResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalIrregularRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalRepository;

/**
 * 住所郵便番号まで取得Service
 */
@Service
public class SearchAddressPostalService {

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    /**
     * 処理を行う
     *
     * @param postalCode 郵便番号
     * @return 検索結果
     */
    public PostalCodePostalResultDto practice(final String postalCode) {

        // 正規郵便番号リスト件数を郵便番号、自治体住居を検索するで取得する
        List<SelectOptionNumberDto> list = addressPostalRepository.findByPostalCodeAndSearchGyoseiku(postalCode);

        if (list.isEmpty()) {
            // 空の場合は不規則を郵便番号で取得する
            PostalCodePostalResultDto resultDto = new PostalCodePostalResultDto();
            resultDto.setIsGyouseikuData(false);
            resultDto.setListOptions(addressPostalIrregularRepository.findByPostalCode(postalCode));

            return resultDto;
        } else {
            // 空でない場合はそのまま送付
            PostalCodePostalResultDto resultDto = new PostalCodePostalResultDto();
            resultDto.setIsGyouseikuData(true);
            resultDto.setListOptions(list);

            return resultDto;
        }
    }

}
