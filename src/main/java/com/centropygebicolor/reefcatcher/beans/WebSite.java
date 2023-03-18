package com.centropygebicolor.reefcatcher.beans;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class WebSite {
    @Id
    @GeneratedValue
    int id;
    String name = "New Site";
    String alias = "NS";
    String firstPriceTag = "";
    String secondPriceTag = "";
    String thirdPriceTag = "";

    String firstQuantityTag = "";
    String secondQuantityTag = "";
    String thirdQuantityTag = "";

    public WebSite(String name, String alias, String firstPriceTag, String secondPriceClass, String thirdPriceTag, String firstQuantityTag, String secondQuantityTag, String thirdQuantityTag) {
        this.name = name;
        this.alias = alias;
        this.firstPriceTag = firstPriceTag;
        this.secondPriceTag = secondPriceClass;
        this.thirdPriceTag = thirdPriceTag;
        this.firstQuantityTag = firstQuantityTag;
        this.secondQuantityTag = secondQuantityTag;
        this.thirdQuantityTag = thirdQuantityTag;
    }

    public WebSite() {

    }
}
