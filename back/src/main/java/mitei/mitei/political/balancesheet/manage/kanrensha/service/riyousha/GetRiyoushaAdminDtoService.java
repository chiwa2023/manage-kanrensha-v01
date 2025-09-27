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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.RiyoushaAdminDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaAdminAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaAdminAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaAdminEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaAdminNameEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaAdminAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaAdminAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaAdminNameRepository;

/**
 * 管理ユーザDtoを取得する
 */
@Service
public class GetRiyoushaAdminDtoService {

    /** 管理ユーザ連作先Repository */
    @Autowired
    private RiyoushaAdminAccessRepository riyoushaAdminAccessRepository;

    /** 管理ユーザ住所Repository */
    @Autowired
    private RiyoushaAdminAddressRepository riyoushaAdminAddressRepository;

    /** 管理ユーザ名称Repository */
    @Autowired
    private RiyoushaAdminNameRepository riyoushaAdminNameRepository;

    /** 全角スペース */
    private static final String WIDE_SPACE = "　";

    /** 全角スペース */
    private static final Integer SIZE_ONE = 1;

    /**
     * EntityからDTOを生成する
     *
     * @param entity RiyoushaAdminEntity
     * @return RiyoushaAdminDto
     */
    public RiyoushaAdminDto practice(final RiyoushaAdminEntity entity) {
        RiyoushaAdminDto dto = new RiyoushaAdminDto();

        // 1. RiyoushaAdminEntity -> RiyoushaAdminDto
        BeanUtils.copyProperties(entity, dto);
        dto.setIsNotOrg(entity.getIsNotOrg());

        // 2. RiyoushaAdminAccessEntity -> InputAccessDto
        RiyoushaAdminAccessEntity accessEntity = this.getLatestAccessEntity(entity.getRiyoushaAdminId());
        dto.setInputAccessDto(createInputAccessDto(accessEntity));
        dto.setAccessId(accessEntity.getRiyoushaAdminAccessId());

        // 3. RiyoushaAdminAddressEntity -> InputAddressDto
        RiyoushaAdminAddressEntity addressEntity = this.getLatestAddressEntity(entity.getRiyoushaAdminId());
        dto.setInputAddressDto(createInputAddressDto(addressEntity));
        dto.setAddressId(addressEntity.getRiyoushaAdminAddressId());

        // 4. RiyoushaAdminNameEntity -> InputPersonNameDto or InputOrgNameDto
        RiyoushaAdminNameEntity nameEntity = getLatestNameEntity(entity.getRiyoushaAdminId());
        if (entity.getIsNotOrg()) {
            dto.setInputPersonNameDto(createInputPersonNameDto(nameEntity));
        } else {
            dto.setInputOrgNameDto(createInputOrgNameDto(nameEntity));
        }
        dto.setNameId(nameEntity.getRiyoushaAdminNameId());

        return dto;
    }

    private RiyoushaAdminAccessEntity getLatestAccessEntity(final Integer riyoushaAdminId) {

        List<RiyoushaAdminAccessEntity> accessList = riyoushaAdminAccessRepository
                .findByRiyoushaAdminIdAndIsLatest(riyoushaAdminId, true);

        if (accessList.isEmpty()) {
            throw new EmptyResultDataAccessException("RiyoushaAdminAccessEntity not found.", 1);
        } else if (accessList.size() > SIZE_ONE) {
            throw new ConcurrencyFailureException("Multiple RiyoushaAdminAccessEntity found.");
        }
        return accessList.get(0);
    }

    private InputAccessDto createInputAccessDto(final RiyoushaAdminAccessEntity accessEntity) {
        InputAccessDto accessDto = new InputAccessDto();
        BeanUtils.copyProperties(accessEntity, accessDto);

        return accessDto;
    }

    private RiyoushaAdminAddressEntity getLatestAddressEntity(final Integer riyoushaAdminId) {

        List<RiyoushaAdminAddressEntity> addressList = riyoushaAdminAddressRepository
                .findByRiyoushaAdminIdAndIsLatest(riyoushaAdminId, true);

        if (addressList.isEmpty()) {
            throw new EmptyResultDataAccessException("RiyoushaAdminAddressEntity not found.", 1);
        } else if (addressList.size() > SIZE_ONE) {
            throw new ConcurrencyFailureException("Multiple RiyoushaAdminAddressEntity found.");
        }

        return addressList.get(0);
    }

    private InputAddressDto createInputAddressDto(final RiyoushaAdminAddressEntity addressEntity) {
        InputAddressDto addressDto = new InputAddressDto();
        BeanUtils.copyProperties(addressEntity, addressDto);
        addressDto.setPostalcode1(addressEntity.getPostal1());
        addressDto.setPostalcode2(addressEntity.getPostal2());

        addressDto.setAddressAll(addressEntity.getAddressPostal() + addressEntity.getAddressBlock() + WIDE_SPACE
                + addressEntity.getAddressBuilding());
        addressDto.setOrginAddressAll(addressDto.getAddressAll());

        return addressDto;
    }

    private RiyoushaAdminNameEntity getLatestNameEntity(final Integer riyoushaAdminId) {
        List<RiyoushaAdminNameEntity> nameList = riyoushaAdminNameRepository
                .findByRiyoushaAdminIdAndIsLatest(riyoushaAdminId, true);

        if (nameList.isEmpty()) {
            throw new EmptyResultDataAccessException("RiyoushaAdminNameEntity not found.", 1);
        } else if (nameList.size() > SIZE_ONE) {
            throw new ConcurrencyFailureException("Multiple RiyoushaAdminNameEntity found.");
        }
        return nameList.get(0);
    }

    private InputPersonNameDto createInputPersonNameDto(final RiyoushaAdminNameEntity nameEntity) {
        InputPersonNameDto personDto = new InputPersonNameDto();
        BeanUtils.copyProperties(nameEntity, personDto);

        personDto.setAllName(
                nameEntity.getLastName() + WIDE_SPACE + nameEntity.getMiddleName() + nameEntity.getFirstName());
        personDto.setAllNameKana(nameEntity.getLastNameKana() + WIDE_SPACE + nameEntity.getMiddleNameKana()
                + nameEntity.getFirstNameKana());

        return personDto;
    }

    private InputOrgNameDto createInputOrgNameDto(final RiyoushaAdminNameEntity nameEntity) {
        InputOrgNameDto orgDto = new InputOrgNameDto();
        BeanUtils.copyProperties(nameEntity, orgDto);

        orgDto.setOrgName(nameEntity.getOrgName());
        orgDto.setOrgNameKana(nameEntity.getOrgNameKana());

        return orgDto;
    }

}
