package com.centropygebicolor.reefcatcher.beans;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class SoldFish {

    @Id
    @GeneratedValue
    int id;
    String name;
    String website;
    String url;

}
