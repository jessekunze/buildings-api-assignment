package com.msr.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class SiteControllerTest {

    private final String findId1JsonStringBySize = "[\n" +
            "  {\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"Measurabl HQ\",\n" +
            "    \"address\": \"707 Broadway Suite 1000\",\n" +
            "    \"city\": \"San Diego\",\n" +
            "    \"state\": \"CA\",\n" +
            "    \"zipcode\": \"92101\",\n" +
            "    \"totalSize\": 13000,\n" +
            "    \"primaryType\": {\n" +
            "      \"id\": 54,\n" +
            "      \"name\": \"Office\"\n" +
            "    }\n" +
            "  }\n" +
            "]";

    private final String findId1JsonString = "{\n" +
            "  \"id\": 1,\n" +
            "  \"name\": \"Measurabl HQ\",\n" +
            "  \"address\": \"707 Broadway Suite 1000\",\n" +
            "  \"city\": \"San Diego\",\n" +
            "  \"state\": \"CA\",\n" +
            "  \"zipcode\": \"92101\",\n" +
            "  \"totalSize\": 13000,\n" +
            "  \"primaryType\": {\n" +
            "    \"id\": 54,\n" +
            "    \"name\": \"Office\"\n" +
            "  }\n" +
            "}";

    private final String findAllJsonString = "[\n" +
            "  {\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"Measurabl HQ\",\n" +
            "    \"address\": \"707 Broadway Suite 1000\",\n" +
            "    \"city\": \"San Diego\",\n" +
            "    \"state\": \"CA\",\n" +
            "    \"zipcode\": \"92101\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 2,\n" +
            "    \"name\": \"Arclight\",\n" +
            "    \"address\": \"4425 La Jolla Village Dr\",\n" +
            "    \"city\": \"San Diego\",\n" +
            "    \"state\": \"CA\",\n" +
            "    \"zipcode\": \"92122\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 3,\n" +
            "    \"name\": \"TGI Fridays\",\n" +
            "    \"address\": \"743 Fifth Ave\",\n" +
            "    \"city\": \"San Diego\",\n" +
            "    \"state\": \"CA\",\n" +
            "    \"zipcode\": \"92101\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 4,\n" +
            "    \"name\": \"Tavern+Bowl East Village\",\n" +
            "    \"address\": \"930 Market St\",\n" +
            "    \"city\": \"San Diego\",\n" +
            "    \"state\": \"CA\",\n" +
            "    \"zipcode\": \"92101\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 5,\n" +
            "    \"name\": \"Bellagio\",\n" +
            "    \"address\": \"3600 S Las Vegas Blvd\",\n" +
            "    \"city\": \"Las Vegas\",\n" +
            "    \"state\": \"NV\",\n" +
            "    \"zipcode\": \"89109\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 6,\n" +
            "    \"name\": \"Petco Park\",\n" +
            "    \"address\": \"100 Park Blvd\",\n" +
            "    \"city\": \"San Diego\",\n" +
            "    \"state\": \"CA\",\n" +
            "    \"zipcode\": \"92101\"\n" +
            "  }\n" +
            "]";

    @Autowired
    private SiteController siteController;

    /**
     * Test getting 100% of sites
     */
    @Test
    public void testGetAllSites() {
        ResponseEntity<String> sites = siteController.findAllSites();
        ResponseEntity<String> expected = ResponseEntity.ok(findAllJsonString);
        assertEquals(expected, sites);
    }

    /**
     * Test a succesful case of finding a site by ID.
     */
    @Test
    public void testGetSiteByIdSuccess() {
        ResponseEntity<String> site = siteController.getSiteDetailsById(1);
        ResponseEntity<String> expected = ResponseEntity.ok(findId1JsonString);
        assertEquals(expected, site);
    }

    /**
     * Test the controller's get site by ID functionality - we expect a 404 if there's no valid site matching the ID.
     */
    @Test
    public void testGetSiteById404() {
        ResponseEntity<String> site = siteController.getSiteDetailsById(12345);
        ResponseEntity<String> expected = ResponseEntity.notFound().build();
        assertEquals(expected, site);
    }

    /**
     * Test the controller's get site by site size functionality -
     */
    @Test
    public void testGetSiteBySize() {
        ResponseEntity<String> site = siteController.findBySize(10000L, 13000L);
        ResponseEntity<String> expected = ResponseEntity.ok(findId1JsonStringBySize);
        assertEquals(expected, site);
    }
}