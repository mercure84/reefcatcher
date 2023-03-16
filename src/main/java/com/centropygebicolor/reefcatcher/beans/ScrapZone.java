package com.centropygebicolor.reefcatcher.beans;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class ScrapZone {

    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    Item item ;

    @ManyToMany
    List<WebSiteUrl> webSiteUrlList;

}
