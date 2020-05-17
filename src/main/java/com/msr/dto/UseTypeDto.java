package com.msr.dto;

import com.msr.entity.UseTypes;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Data object to represent the Use Type table.
 *
 * @author Measurabl
 * @since 2019-05-23
 */
@Getter
@Setter
public class UseTypeDto {

    int id;
    String name;

    public UseTypeDto(UseTypes useTypes) {
        this.id = useTypes.getId();
        this.name = useTypes.getName();
    }

}
