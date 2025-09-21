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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.RiyoushaManagerDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerNameEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaManagerAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaManagerAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaManagerNameRepository;

/**
 * 運営者ユーザDtoを取得する
 */
@Service
public class GetRiyoushaManagerDtoService {

    /** 運営者ユーザ連作先Repository */
    @Autowired
    private RiyoushaManagerAccessRepository riyoushaManagerAccessRepository;

    /** 運営者ユーザ住所Repository */
    @Autowired
    private RiyoushaManagerAddressRepository riyoushaManagerAddressRepository;

    /** 運営者ユーザ名称Repository */
    @Autowired
    private RiyoushaManagerNameRepository riyoushaManagerNameRepository;

    /** 全角スペース */
    private static final String WIDE_SPACE = "　";

    /** 全角スペース */
    private static final Integer SIZE_ONE = 1;

    /**
     * EntityからDTOを生成する
     *
     * @param entity RiyoushaManagerEntity
     * @return RiyoushaManagerDto
     */
    public RiyoushaManagerDto practice(final RiyoushaManagerEntity entity) {
        RiyoushaManagerDto dto = new RiyoushaManagerDto();

        // 1. RiyoushaManagerEntity -> RiyoushaManagerDto
        BeanUtils.copyProperties(entity, dto);
        dto.setIsNotOrg(entity.getIsNotOrg());

        // 2. RiyoushaManagerAccessEntity -> InputAccessDto
        RiyoushaManagerAccessEntity accessEntity = this.getLatestAccessEntity(entity.getRiyoushaManagerId());
        dto.setInputAccessDto(createInputAccessDto(accessEntity));
        dto.setAccessId(accessEntity.getRiyoushaManagerAccessId());

        // 3. RiyoushaManagerAddressEntity -> InputAddressDto
        RiyoushaManagerAddressEntity addressEntity = this.getLatestAddressEntity(entity.getRiyoushaManagerId());
        dto.setInputAddressDto(createInputAddressDto(addressEntity));
        dto.setAddressId(addressEntity.getRiyoushaManagerAddressId());

        // 4. RiyoushaManagerNameEntity -> InputPersonNameDto or InputOrgNameDto
        RiyoushaManagerNameEntity nameEntity = getLatestNameEntity(entity.getRiyoushaManagerId());
        if (entity.getIsNotOrg()) {
            dto.setInputPersonNameDto(createInputPersonNameDto(nameEntity));
        } else {
            dto.setInputOrgNameDto(createInputOrgNameDto(nameEntity));
        }
        dto.setNameId(nameEntity.getRiyoushaManagerNameId());

        return dto;
    }

    private RiyoushaManagerAccessEntity getLatestAccessEntity(final Integer riyoushaManagerId) {

        List<RiyoushaManagerAccessEntity> accessList = riyoushaManagerAccessRepository
                .findByRiyoushaManagerIdAndIsLatest(riyoushaManagerId, true);

        if (accessList.isEmpty()) {
            throw new EmptyResultDataAccessException("RiyoushaManagerAccessEntity not found.", 1);
        } else if (accessList.size() > SIZE_ONE) {
            throw new ConcurrencyFailureException("Multiple RiyoushaManagerAccessEntity found.");
        }
        return accessList.get(0);
    }

    private InputAccessDto createInputAccessDto(final RiyoushaManagerAccessEntity accessEntity) {
        InputAccessDto accessDto = new InputAccessDto();
        BeanUtils.copyProperties(accessEntity, accessDto);

        return accessDto;
    }

    private RiyoushaManagerAddressEntity getLatestAddressEntity(final Integer riyoushaManagerId) {

        List<RiyoushaManagerAddressEntity> addressList = riyoushaManagerAddressRepository
                .findByRiyoushaManagerIdAndIsLatest(riyoushaManagerId, true);

        if (addressList.isEmpty()) {
            throw new EmptyResultDataAccessException("RiyoushaManagerAddressEntity not found.", 1);
        } else if (addressList.size() > SIZE_ONE) {
            throw new ConcurrencyFailureException("Multiple RiyoushaManagerAddressEntity found.");
        }

        return addressList.get(0);
    }

    private InputAddressDto createInputAddressDto(final RiyoushaManagerAddressEntity addressEntity) {
        InputAddressDto addressDto = new InputAddressDto();
        BeanUtils.copyProperties(addressEntity, addressDto);
        addressDto.setPostalcode1(addressEntity.getPostal1());
        addressDto.setPostalcode2(addressEntity.getPostal2());

        addressDto.setAddressAll(addressEntity.getAddressPostal() + addressEntity.getAddressBlock() + WIDE_SPACE
                + addressEntity.getAddressBuilding());
        addressDto.setOrginAddressAll(addressDto.getAddressAll());

        return addressDto;
    }

    private RiyoushaManagerNameEntity getLatestNameEntity(final Integer riyoushaManagerId) {
        List<RiyoushaManagerNameEntity> nameList = riyoushaManagerNameRepository
                .findByRiyoushaManagerIdAndIsLatest(riyoushaManagerId, true);

        if (nameList.isEmpty()) {
            throw new EmptyResultDataAccessException("RiyoushaManagerNameEntity not found.", 1);
        } else if (nameList.size() > SIZE_ONE) {
            throw new ConcurrencyFailureException("Multiple RiyoushaManagerNameEntity found.");
        }
        return nameList.get(0);
    }

    private InputPersonNameDto createInputPersonNameDto(final RiyoushaManagerNameEntity nameEntity) {
        InputPersonNameDto personDto = new InputPersonNameDto();
        BeanUtils.copyProperties(nameEntity, personDto);

        personDto.setAllName(
                nameEntity.getLastName() + WIDE_SPACE + nameEntity.getMiddleName() + nameEntity.getFirstName());
        personDto.setAllNameKana(nameEntity.getLastNameKana() + WIDE_SPACE + nameEntity.getMiddleNameKana()
                + nameEntity.getFirstNameKana());

        return personDto;
    }

    private InputOrgNameDto createInputOrgNameDto(final RiyoushaManagerNameEntity nameEntity) {
        InputOrgNameDto orgDto = new InputOrgNameDto();
        BeanUtils.copyProperties(nameEntity, orgDto);

        orgDto.setOrgName(nameEntity.getOrgName());
        orgDto.setOrgNameKana(nameEntity.getOrgNameKana());

        return orgDto;
    }

}
