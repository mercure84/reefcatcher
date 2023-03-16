package com.centropygebicolor.reefcatcher;

import com.centropygebicolor.reefcatcher.beans.WebSite;
import lombok.Data;

@Data
public class MockWebSite extends WebSite {

    String mockedUrl;

    public MockWebSite(String name, String alias, String firstPriceClass, String secondPriceClass, String priceAttribute, String mockedUrl) {
        super(name, alias, firstPriceClass, secondPriceClass, priceAttribute);
        this.mockedUrl = mockedUrl;
    }

}
