package com.msr.dto;

import com.msr.entity.Site;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 * Data object to represent the Site Use database table.
 *
 * @author Measurabl
 * @since 2019-05-23
 */
@Getter
@Setter
@Builder
public class SiteUseDto {

    private int id;

    private Site site;

    private String description;

    private long sizeSqft;

    private UseTypeDto useTypes;

}
