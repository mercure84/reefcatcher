package com.centropygebicolor.reefcatcher.restcontroller;

import com.centropygebicolor.reefcatcher.beans.Item;
import com.centropygebicolor.reefcatcher.beans.ScrapData;
import com.centropygebicolor.reefcatcher.beans.ScrapZone;
import com.centropygebicolor.reefcatcher.beans.WebSiteUrl;
import com.centropygebicolor.reefcatcher.beans.helpers.ScrapHelper;
import com.centropygebicolor.reefcatcher.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ItemScrappingController {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Autowired
    ScrapeZoneRepository scrapeZoneRepository;

    @Autowired
    ScrapRepository scrapRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ErrorRepository scrapErrorRepository;

    @Autowired
    WebSiteRepository webSiteRepository;

    @Autowired
    ScrapHelper scrapHelper;

    @GetMapping("/api/scrapSingleItem/{itemId}")
    public List<ScrapData> scrapSingleItem(@PathVariable int itemId) throws IOException {

        Item item = itemRepository.getItemById(itemId);
        ScrapZone scrapZone = scrapeZoneRepository.getScrapZoneByItem(item);
        List<WebSiteUrl> sitesToScrapp = scrapZone.getWebSiteUrlList();
        List<ScrapData> scrapDataList = new ArrayList<>();


        for (WebSiteUrl webSiteUrl : sitesToScrapp) {
            ScrapData scrapData = scrapHelper.getScrapData(webSiteUrl, item);
            scrapDataList.add(scrapData);
            scrapRepository.save(scrapData);
        }

        System.out.println("DATA ENREGISTREES : " + scrapDataList);
        return scrapDataList;


    }


}
