package com.msr.data;

import com.msr.dto.SiteDetailsDto;
import com.msr.dto.SiteDto;
import com.msr.entity.Site;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Global DAO functionality not domain driven
 *
 * @author Measurabl
 * @since 2019-06-06
 */
@Service
@Slf4j
public class SiteDao {

    @Autowired
    SiteRepository siteRepository;

    public List<SiteDto> getAllSites() {
        Iterable<Site> siteIterator = siteRepository.findAll();
        List<SiteDto> siteDtoList = new ArrayList<>();
        for (Site site : siteIterator) {
            siteDtoList.add(new SiteDto(site));
        }
        return siteDtoList;
    }

    public List<SiteDetailsDto> getAllSiteDetails() {
        Iterable<Site> siteIterator = siteRepository.findAll();
        List<SiteDetailsDto> siteDtoList = new ArrayList<>();
        for (Site site : siteIterator) {
            SiteDetailsDto siteDetailsDto = new SiteDetailsDto(site);
            siteDtoList.add(new SiteDetailsDto(site));
        }
        return siteDtoList;
    }

    public SiteDetailsDto getSiteDetailsById(int id) {
        Optional<Site> site = siteRepository.findById(id);
        if (site.isPresent()) {
            return new SiteDetailsDto(site.get());
        } else {
            return null;
        }

    }

}


////////////////////////////////////////////////////////////
// Copyright 2018  Measurabl, Inc. All rights reserved.
////////////////////////////////////////////////////////////
    