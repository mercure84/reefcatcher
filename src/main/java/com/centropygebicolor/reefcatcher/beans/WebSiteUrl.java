package com.centropygebicolor.reefcatcher.beans;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class WebSiteUrl {

    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    WebSite website;
    String url = "";

}
