package com.centropygebicolor.reefcatcher.restcontroller;

import com.centropygebicolor.reefcatcher.beans.*;
import com.centropygebicolor.reefcatcher.repository.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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

    @GetMapping("/api/scrapSingleItem/{itemId}")
    public List<ScrapData> scrapSingleItem(@PathVariable int itemId) throws IOException {

        Item item = itemRepository.getItemById(itemId);
        ScrapZone scrapZone = scrapeZoneRepository.getScrapZoneByItem(item);
        List<WebSiteUrl> sitesToScrapp = scrapZone.getWebSiteUrlList();
        List<ScrapData> scrapDataList = new ArrayList<>();


        for (WebSiteUrl webSiteUrl : sitesToScrapp) {
            Document fullpage = Jsoup.connect(webSiteUrl.getUrl()).get();
            WebSite website = webSiteUrl.getWebsite();
            String firstPriceClass = website.getFirstPriceClass();
            String secondPriceClass = website.getSecondPriceClass();
            String priceAttribute = website.getPriceAttribute();
            Element priceElement = fullpage.getElementsByClass(firstPriceClass).get(0);
            if (!Objects.equals(secondPriceClass, null)) {
                priceElement = priceElement.getElementsByClass(secondPriceClass).get(0);
            } else {
                priceElement = priceElement.getElementsByAttribute(priceAttribute).get(0);
            }
            String price = priceElement.attr(priceAttribute);
            System.out.println("SCrapped Price==> " + price);
            ScrapData scrapData = new ScrapData();
            scrapData.setItem(item);
            scrapData.setPrice(Float.parseFloat(price));
            scrapData.setWebSite(website);
            scrapRepository.save(scrapData);
            scrapDataList.add(scrapData);

        }

        System.out.println("DATA ENREGISTREES : " + scrapDataList);
        return scrapDataList;


    }


}
