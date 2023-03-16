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
    String firstPriceClass = "";
    String secondPriceClass = "";
    String priceAttribute = "";

    public WebSite(String name, String alias, String firstPriceClass, String secondPriceClass, String priceAttribute) {
        this.name = name;
        this.alias = alias;
        this.firstPriceClass = firstPriceClass;
        this.secondPriceClass = secondPriceClass;
        this.priceAttribute = priceAttribute;
    }

    public WebSite() {

    }
}
