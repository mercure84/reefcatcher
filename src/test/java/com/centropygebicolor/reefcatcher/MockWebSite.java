package com.centropygebicolor.reefcatcher;

import com.centropygebicolor.reefcatcher.beans.WebSite;
import lombok.Data;

@Data
public class MockWebSite extends WebSite {

    String mockedUrl;

    public MockWebSite(String name, String alias, String firstPriceTag, String secondPrinceTag, String thirdPriceTag, String firstQuantityTag, String secondQuantityTag, String thirdQuantityTag, String mockedUrl) {
        super(name, alias, firstPriceTag, secondPrinceTag, thirdPriceTag, firstQuantityTag, secondQuantityTag, thirdQuantityTag);
        this.mockedUrl = mockedUrl;
    }
}
