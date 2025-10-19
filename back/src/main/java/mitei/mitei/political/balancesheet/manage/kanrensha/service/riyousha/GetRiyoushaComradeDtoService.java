package mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.RiyoushaComradeDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaComradeAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaComradeAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaComradeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaComradeNameEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaComradeAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaComradeAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaComradeNameRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaComradeRepository;

/**
 * APIユーザユーザDtoを取得する
 */
@Service
public class GetRiyoushaComradeDtoService {

    /** APIユーザユーザ連作先Repository */
    @Autowired
    private RiyoushaComradeAccessRepository riyoushaComradeAccessRepository;

    /** APIユーザユーザ住所Repository */
    @Autowired
    private RiyoushaComradeAddressRepository riyoushaComradeAddressRepository;

    /** APIユーザユーザ名称Repository */
    @Autowired
    private RiyoushaComradeNameRepository riyoushaComradeNameRepository;

    /** APIユーザユーザ名称Repository */
    @Autowired
    private RiyoushaComradeRepository riyoushaComradeRepository;

    /** 全角スペース */
    private static final String WIDE_SPACE = "　";

    /** 全角スペース */
    private static final Integer SIZE_ONE = 1;

    /**
     * EntityからDTOを生成する
     *
     * @param entity RiyoushaComradeEntity
     * @return RiyoushaComradeDto
     */
    public RiyoushaComradeDto practice(final RiyoushaComradeEntity entity) {
        RiyoushaComradeDto dto = new RiyoushaComradeDto();

        // 1. RiyoushaComradeEntity -> RiyoushaComradeDto
        BeanUtils.copyProperties(entity, dto);
        dto.setIsNotOrg(entity.getIsNotOrg());

        // 2. RiyoushaComradeAccessEntity -> InputAccessDto
        RiyoushaComradeAccessEntity accessEntity = this.getLatestAccessEntity(entity.getRiyoushaComradeId());
        dto.setInputAccessDto(createInputAccessDto(accessEntity));
        dto.setAccessId(accessEntity.getRiyoushaComradeAccessId());

        // 3. RiyoushaComradeAddressEntity -> InputAddressDto
        RiyoushaComradeAddressEntity addressEntity = this.getLatestAddressEntity(entity.getRiyoushaComradeId());
        dto.setInputAddressDto(createInputAddressDto(addressEntity));
        dto.setAddressId(addressEntity.getRiyoushaComradeAddressId());

        // 4. RiyoushaComradeNameEntity -> InputPersonNameDto or InputOrgNameDto
        RiyoushaComradeNameEntity nameEntity = getLatestNameEntity(entity.getRiyoushaComradeId());
        if (entity.getIsNotOrg()) {
            dto.setInputPersonNameDto(createInputPersonNameDto(nameEntity));
        } else {
            dto.setInputOrgNameDto(createInputOrgNameDto(nameEntity));
        }
        dto.setNameId(nameEntity.getRiyoushaComradeNameId());

        // 組織の場合は紐づくユーザをリストアップ
        if(!dto.getIsNotOrg()) {
            dto.setListPerson(riyoushaComradeRepository.findCombinePerson(entity.getRiyoushaComradeCode()));
        }
        
        return dto;
    }

    private RiyoushaComradeAccessEntity getLatestAccessEntity(final Integer riyoushaComradeId) {

        List<RiyoushaComradeAccessEntity> accessList = riyoushaComradeAccessRepository
                .findByRiyoushaComradeIdAndIsLatest(riyoushaComradeId, true);

        if (accessList.isEmpty()) {
            throw new EmptyResultDataAccessException("RiyoushaComradeAccessEntity not found.", 1);
        } else if (accessList.size() > SIZE_ONE) {
            throw new ConcurrencyFailureException("Multiple RiyoushaComradeAccessEntity found.");
        }
        return accessList.get(0);
    }

    private InputAccessDto createInputAccessDto(final RiyoushaComradeAccessEntity accessEntity) {
        InputAccessDto accessDto = new InputAccessDto();
        BeanUtils.copyProperties(accessEntity, accessDto);

        return accessDto;
    }

    private RiyoushaComradeAddressEntity getLatestAddressEntity(final Integer riyoushaComradeId) {

        List<RiyoushaComradeAddressEntity> addressList = riyoushaComradeAddressRepository
                .findByRiyoushaComradeIdAndIsLatest(riyoushaComradeId, true);

        if (addressList.isEmpty()) {
            throw new EmptyResultDataAccessException("RiyoushaComradeAddressEntity not found.", 1);
        } else if (addressList.size() > SIZE_ONE) {
            throw new ConcurrencyFailureException("Multiple RiyoushaComradeAddressEntity found.");
        }

        return addressList.get(0);
    }

    private InputAddressDto createInputAddressDto(final RiyoushaComradeAddressEntity addressEntity) {
        InputAddressDto addressDto = new InputAddressDto();
        BeanUtils.copyProperties(addressEntity, addressDto);
        addressDto.setPostalcode1(addressEntity.getPostal1());
        addressDto.setPostalcode2(addressEntity.getPostal2());

        addressDto.setAddressAll(addressEntity.getAddressPostal() + addressEntity.getAddressBlock() + WIDE_SPACE
                + addressEntity.getAddressBuilding());
        addressDto.setOrginAddressAll(addressDto.getAddressAll());

        return addressDto;
    }

    private RiyoushaComradeNameEntity getLatestNameEntity(final Integer riyoushaComradeId) {
        List<RiyoushaComradeNameEntity> nameList = riyoushaComradeNameRepository
                .findByRiyoushaComradeIdAndIsLatest(riyoushaComradeId, true);

        if (nameList.isEmpty()) {
            throw new EmptyResultDataAccessException("RiyoushaComradeNameEntity not found.", 1);
        } else if (nameList.size() > SIZE_ONE) {
            throw new ConcurrencyFailureException("Multiple RiyoushaComradeNameEntity found.");
        }
        return nameList.get(0);
    }

    private InputPersonNameDto createInputPersonNameDto(final RiyoushaComradeNameEntity nameEntity) {
        InputPersonNameDto personDto = new InputPersonNameDto();
        BeanUtils.copyProperties(nameEntity, personDto);

        personDto.setAllName(
                nameEntity.getLastName() + WIDE_SPACE + nameEntity.getMiddleName() + nameEntity.getFirstName());
        personDto.setAllNameKana(nameEntity.getLastNameKana() + WIDE_SPACE + nameEntity.getMiddleNameKana()
                + nameEntity.getFirstNameKana());

        return personDto;
    }

    private InputOrgNameDto createInputOrgNameDto(final RiyoushaComradeNameEntity nameEntity) {
        InputOrgNameDto orgDto = new InputOrgNameDto();
        BeanUtils.copyProperties(nameEntity, orgDto);

        orgDto.setOrgName(nameEntity.getOrgName());
        orgDto.setOrgNameKana(nameEntity.getOrgNameKana());

        return orgDto;
    }

}
