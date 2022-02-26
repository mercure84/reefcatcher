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
    String name;
    String quantityStartIndex;
    String quantityEndIndex;
    String priceStartIndex;
    String priceEndIndex;
}
