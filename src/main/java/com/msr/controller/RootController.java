package com.msr.controller;

import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Respond to root requests
 *
 * @author Measurabl
 * @since 2019-05-23
 */

@Log4j2
@RestController
@RequestMapping("/")
public class RootController {

    private BuildProperties buildProperties;

    @Autowired
    public RootController(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    /**
     * Used to return basic information on the API to lead people to the docs.
     *
     * @return Map of information for the user.
     */
    @ApiOperation("Returns basic information on the API so the user can be led to more informative data.")
    @GetMapping("/")
    public Map<String, Object> getRoot() {
        return ImmutableMap.of(
                "status", "up",
                "version", buildProperties.getVersion(),
                "name", buildProperties.getName(),
                "time", buildProperties.getTime(),
                "API information (human readable)", "http://localhost:8080/swagger-ui.html#/"
        );
    }
}

////////////////////////////////////////////////////////////
// Copyright 2018  Measurabl, Inc. All rights reserved.
////////////////////////////////////////////////////////////
    