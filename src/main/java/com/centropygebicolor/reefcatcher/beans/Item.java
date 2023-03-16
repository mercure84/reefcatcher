package com.centropygebicolor.reefcatcher.beans;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Item {

    @Id
    @GeneratedValue
    int id;
    String label;

}
