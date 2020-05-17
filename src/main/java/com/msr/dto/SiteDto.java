package com.msr.dto;

import com.msr.entity.Site;
import lombok.Getter;
import lombok.Setter;

/**
 * Data object to represent the Site database table.
 *
 * @author Measurabl
 * @since 2019-05-23
 */
@Getter
@Setter
public class SiteDto {
    private int id;

    private String name;

    private String address;

    private String city;

    private String state;

    private String zipcode;

    public SiteDto(Site site) {
        id = site.getId();
        name = site.getName();
        address = site.getAddress();
        city = site.getCity();
        state = site.getState();
        zipcode = site.getZipcode();
    }

}
