package com.centropygebicolor.reefcatcher.beans;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class ScrapData {

    @Id
    @GeneratedValue
    int id;
    Date date = new Date();
    float price;
    int quantity;
    boolean isAvailable;

    @ManyToOne
    Item item;

    @ManyToOne
    WebSite webSite;

}
