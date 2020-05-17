package com.msr.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Site info
 *
 * @author Measurabl
 * @since 2019-06-06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Site {

    @Id
    private int id;

    private String name;

    private String address;

    private String city;

    private String state;

    private String zipcode;

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL)
    private List<SiteUses> siteUses = new ArrayList<>();

}

////////////////////////////////////////////////////////////
// Copyright 2018  Measurabl, Inc. All rights reserved.
////////////////////////////////////////////////////////////
    