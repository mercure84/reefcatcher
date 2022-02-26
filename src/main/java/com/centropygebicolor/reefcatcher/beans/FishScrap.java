package com.centropygebicolor.reefcatcher.beans;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class FishScrap {

    @Id
    @GeneratedValue
    int id;
    Date date = new Date();
    float price;
    int quantity;

    @ManyToOne
    SoldFish soldFish ;


}
