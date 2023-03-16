package com.centropygebicolor.reefcatcher.restcontroller;

import com.centropygebicolor.reefcatcher.beans.Item;
import com.centropygebicolor.reefcatcher.beans.ScrapZone;
import com.centropygebicolor.reefcatcher.beans.WebSite;
import com.centropygebicolor.reefcatcher.beans.WebSiteUrl;
import com.centropygebicolor.reefcatcher.beans.helpers.ScrapZoneForm;
import com.centropygebicolor.reefcatcher.beans.helpers.WebSiteUrlForm;
import com.centropygebicolor.reefcatcher.repository.ItemRepository;
import com.centropygebicolor.reefcatcher.repository.ScrapeZoneRepository;
import com.centropygebicolor.reefcatcher.repository.WebSiteRepository;
import com.centropygebicolor.reefcatcher.repository.WebSiteUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ScrapeZoneRepository scrapeZoneRepository;

    @Autowired
    WebSiteRepository webSiteRepository;

    @Autowired
    WebSiteUrlRepository webSiteUrlRepository;

    @PostMapping(value = "/api/addNewItem")
    public Item addNewItem(@RequestBody Item item) {
        Item newItem = new Item();
        newItem.setLabel(item.getLabel());
        itemRepository.save(newItem);
        System.out.println("A new WEBSITE has ben registered " + newItem.getLabel());
        return newItem;
    }


    @PostMapping(value = "/api/updateScrapZone")
    public ScrapZone updateScrapZone(@RequestBody ScrapZoneForm scrapZoneForm) {
        Item item = itemRepository.getItemById(scrapZoneForm.getItemId());
        List<WebSiteUrlForm> webSiteUrls = scrapZoneForm.getWebSiteUrlForms();
        System.out.println("Item found for updating its scrapzone .... " + item.getLabel());
        ScrapZone scrapZone = new ScrapZone();
        ScrapZone existingScrapZone = scrapeZoneRepository.getScrapZoneByItem(item);

        if(existingScrapZone != null){
            scrapZone = existingScrapZone;
        } else {
            scrapZone.setItem(item);
        }
        Iterator<WebSiteUrlForm> it = webSiteUrls.iterator();
        List<WebSiteUrl> webSiteUrlList = new ArrayList<>();
        while (it.hasNext()) {
            WebSiteUrlForm webSiteUrlForm = it.next();
            WebSite webSite = webSiteRepository.getWebSiteByAlias(webSiteUrlForm.getAlias());
            String url = webSiteUrlForm.getUrl();
            WebSiteUrl webSiteUrl = new WebSiteUrl();
            webSiteUrl.setUrl(url);
            webSiteUrl.setWebsite(webSite);
            webSiteUrlRepository.save(webSiteUrl);
            webSiteUrlList.add(webSiteUrl);
        }
        scrapZone.setWebSiteUrlList(webSiteUrlList);
        scrapeZoneRepository.save(scrapZone);
        System.out.println("Scrapzone for item " + item.getLabel() + " has been save !");
        return scrapZone;

    }


}
