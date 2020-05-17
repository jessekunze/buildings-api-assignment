package com.msr.util;

import com.msr.dto.UseTypeDto;
import com.msr.entity.Site;
import com.msr.entity.SiteUses;
import com.msr.entity.UseTypes;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class PrimaryTypeUtilTest {

    /**
     * A test to determine the correct UseType is selected for an example site.
     */
    @Test
    public void getPrimaryType() {
        List<SiteUses> inputArrayList = generateList();
        UseTypeDto primaryType = PrimaryTypeUtil.getPrimaryType(inputArrayList);
        assertEquals(2, primaryType.getId());
        assertEquals("Grocery", primaryType.getName());
    }

    private List<SiteUses> generateList() {
        UseTypes restaurantUseType = new UseTypes(1, "Restaurant");
        UseTypes groceryUseType = new UseTypes(2, "Grocery");

        Site site = new Site(1, "Winter Park Village", "440 Orlando Ave", "Winter Park", "FL", "32789", new ArrayList<SiteUses>());

        SiteUses publixSiteUse = new SiteUses(1, site, "Publix Supermarket", 1000L, groceryUseType);
        SiteUses cheesecakeSiteUse = new SiteUses(2, site, "Cheescake Factory", 500L, restaurantUseType);

        site.getSiteUses().add(publixSiteUse);
        site.getSiteUses().add(cheesecakeSiteUse);

        return site.getSiteUses();

    }
}