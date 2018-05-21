package com.suchaz.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.suchaz.app.domain.Vendor;
import com.suchaz.app.service.dto.VendorDTO;

/**
 * Mapper for the entity Vendor and its DTO VendorDTO.
 */
@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface VendorMapper extends EntityMapper<VendorDTO, Vendor> {

    @Mapping(source = "country.id", target = "countryId")
    @Mapping(source = "country.countryName", target = "countryCountryName")
    VendorDTO toDto(Vendor vendor);

    @Mapping(target = "items", ignore = true)
    @Mapping(target = "vendorImages", ignore = true)
    @Mapping(source = "countryId", target = "country")
    Vendor toEntity(VendorDTO vendorDTO);

    default Vendor fromId(Long id) {
        if (id == null) {
            return null;
        }
        Vendor vendor = new Vendor();
        vendor.setId(id);
        return vendor;
    }
}
