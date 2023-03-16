package com.centropygebicolor.reefcatcher.restcontroller;

import com.centropygebicolor.reefcatcher.beans.WebSite;
import com.centropygebicolor.reefcatcher.repository.WebSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSiteController {

    @Autowired
    WebSiteRepository webSiteRepository;


    @PostMapping(value = "/api/addNewWebSite")
    public WebSite addNewWebSite(@RequestBody WebSite webSite) {

        WebSite newWebSite = new WebSite();
        newWebSite.setName(webSite.getName());
        newWebSite.setAlias(webSite.getAlias());


        webSiteRepository.save(newWebSite);
        System.out.println("A new WEBSITE has ben registered " + webSite.getName());
        return newWebSite ;
    }



}
