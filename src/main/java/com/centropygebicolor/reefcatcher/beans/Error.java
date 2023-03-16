package com.centropygebicolor.reefcatcher.beans;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Error {

    @Id
    @GeneratedValue
    int id;
    Date date = new Date();
    String message;
    String subMessage;


}
