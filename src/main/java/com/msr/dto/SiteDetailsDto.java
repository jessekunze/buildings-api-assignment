package com.msr.dto;

import com.msr.entity.Site;
import com.msr.util.PrimaryTypeUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * Data object to represent a Site with it's Details, notably it's total size and primary type.
 *
 * @author Measurabl
 * @since 2019-05-23
 */
@Getter
@Setter
public class SiteDetailsDto {

    private int id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private Long totalSize;
    private UseTypeDto primaryType;

    public SiteDetailsDto(Site site) {
        id = site.getId();
        name = site.getName();
        address = site.getAddress();
        city = site.getCity();
        state = site.getState();
        zipcode = site.getZipcode();
        totalSize = site.getSiteUses().stream().mapToLong(siteUse -> siteUse.getSizeSqft()).sum();
        primaryType = PrimaryTypeUtil.getPrimaryType(site.getSiteUses());
    }

}
