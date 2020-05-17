package com.msr.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.msr.data.SiteDao;
import com.msr.dto.SiteDetailsDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for site-level REST requests.
 *
 * @author Measurabl
 * @since 2019-06-06
 */
@RestController
@RequestMapping("/sites")
public class SiteController {

    @Autowired
    SiteDao siteDao;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Used to return every Site.
     *
     * @return All of the sites. Does not include total size/primary UseType data.
     */
    @ApiOperation("Returns all sites in the database. Does not include total size/primary UseType data.")
    @GetMapping("")
    public ResponseEntity<String> findAllSites() {
        return ResponseEntity.ok(gson.toJson(siteDao.getAllSites()));
    }

    /**
     * Used to return every Site between two sizes in Square feet
     *
     * @return All of the sites between or equal to a given size. Includes total Size/Primary UseType data.
     */
    @ApiOperation("Returns all sites in the database within a given size range. Includes total Size/Primary UseType data.")
    @GetMapping("/detail/")
    public ResponseEntity<String> findBySize(@ApiParam("Minimum size in square feet to search. This value is inclusive.")
                                             @RequestParam(required = true) Long minSize,
                                             @ApiParam("Maximum size in square feet to search. This value is inclusive.")
                                             @RequestParam(required = true) Long maxSize) {
        List<SiteDetailsDto> siteDtoList = siteDao.getAllSiteDetails();

        List<SiteDetailsDto> outputList = siteDtoList.stream()
                .filter(siteDetailsDto -> siteDetailsDto.getTotalSize() >= minSize && siteDetailsDto.getTotalSize() <= maxSize)
                .collect(Collectors.toList());
        return ResponseEntity.ok(gson.toJson(outputList));
    }

    /**
     * This returns the data about the site, plus it's total site size and primary UseTypes type.
     *
     * @return Site Data, plus total site size and primary UseTypes type.
     */
    @ApiOperation("Returns the site and it's primary use case and total size.")
    @GetMapping("/detail/{id}")
    public ResponseEntity<String> getSiteDetailsById(@ApiParam("The primary ID of the site.")
                                                     @PathVariable(required = true) final int id) {
        SiteDetailsDto siteDetailsDto = siteDao.getSiteDetailsById(id);
        if (siteDetailsDto != null) {
            return ResponseEntity.ok(gson.toJson(siteDetailsDto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
