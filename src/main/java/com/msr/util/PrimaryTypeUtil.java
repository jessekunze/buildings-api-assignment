package com.msr.util;

import com.msr.dto.UseTypeDto;
import com.msr.entity.SiteUses;
import com.msr.entity.UseTypes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A utility class to determine which UseType is primary at a site.
 *
 * @author Measurabl
 * @since 2019-05-23
 */
public class PrimaryTypeUtil {

    /**
     * This method determines which UseType is primary based on which consumes the most space. In the case of a tie,
     * it picks the initial happening of the largest size.
     * @param siteUses A list of Site Uses
     * @return The primary UseType given a list of SiteUses
     */
    public static UseTypeDto getPrimaryType(List<SiteUses> siteUses) {
        HashMap<UseTypes, Long> siteUseMap = new HashMap<>();


        for (SiteUses siteUse : siteUses) {
            UseTypes useType = siteUse.getUseTypes();
            if (siteUseMap.containsKey(useType)) {
                Long currentSize = siteUseMap.get(useType);
                Long newSize = Long.sum(currentSize, siteUse.getSizeSqft());
                siteUseMap.put(useType, newSize);
            } else {
                siteUseMap.put(useType, siteUse.getSizeSqft());
            }
        }

        UseTypes largestUseType = null;
        Long largestUseTypeSize = 0L;
        for (Map.Entry<UseTypes, Long> entry : siteUseMap.entrySet()) {
            if (largestUseType == null || entry.getValue().compareTo(largestUseTypeSize) > 0) {
                largestUseType = entry.getKey();
                largestUseTypeSize = entry.getValue();
            }
        }
        return new UseTypeDto(largestUseType);
    }

}
