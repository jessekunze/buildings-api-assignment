package com.msr.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Site uses POJO
 *
 * @author Measurabl
 * @since 2019-06-11
 */
@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SiteUses {

    @Id
    private int id;

    @ManyToOne
    private Site site;

    private String description;

    private long sizeSqft;

    @ManyToOne
    private UseTypes useTypes;
}

////////////////////////////////////////////////////////////
// Copyright 2018  Measurabl, Inc. All rights reserved.
////////////////////////////////////////////////////////////
    