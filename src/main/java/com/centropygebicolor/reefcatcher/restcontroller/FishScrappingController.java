package com.centropygebicolor.reefcatcher.restcontroller;

import com.centropygebicolor.reefcatcher.beans.FishScrap;
import com.centropygebicolor.reefcatcher.beans.ScrapError;
import com.centropygebicolor.reefcatcher.beans.SoldFish;
import com.centropygebicolor.reefcatcher.beans.WebSite;
import com.centropygebicolor.reefcatcher.repository.FishScrapRepository;
import com.centropygebicolor.reefcatcher.repository.ScrapErrorRepository;
import com.centropygebicolor.reefcatcher.repository.SoldFishRepository;
import com.centropygebicolor.reefcatcher.repository.WebSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;


@RestController
public class FishScrappingController {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Autowired
    FishScrapRepository fishScrapRepository;

    @Autowired
    SoldFishRepository soldFishRepository;

    @Autowired
    ScrapErrorRepository scrapErrorRepository;

    @Autowired
    WebSiteRepository webSiteRepository;

    @GetMapping("/getRAH")
    public void getBispinosus() {
        String myUrl = "https://www.recifathome.com/poissons-clowns/25815-amphiprion-darwini-elevage-france-acdp.html";
        String response = restTemplateBuilder.build().getForObject(myUrl, String.class);
        String quantity = response.substring(response.indexOf("quantityAvailable=") + 18, response.indexOf(";var quickView"));
        System.out.println("***FISH FROM RAH***");
        System.out.println("Available Quantity= " + quantity);
        String price = response.substring(response.indexOf("productPrice=") + 13, response.indexOf(";var productPriceTaxExcluded="));
        System.out.println("Price = " + price);

    }

    @GetMapping("/getFlavescensFromPO")
    public void getFlavescens() {
        WebSite po = webSiteRepository.getWebSiteByName("PO");
        SoldFish myFish = soldFishRepository.getById(16);
        String scrappedWebsite = restTemplateBuilder.build().getForObject(myFish.getUrl(), String.class);
        String quantity ="";
        String price = "";
            int poQuantityIndex = scrappedWebsite.indexOf(po.getQuantityStartIndex()) + po.getQuantityStartIndex().length();
            int poPriceIndex = scrappedWebsite.indexOf(po.getPriceStartIndex()) + po.getPriceStartIndex().length();
            String rawQuantity = scrappedWebsite.substring(poQuantityIndex , poQuantityIndex + 7);
            quantity = rawQuantity.substring(0, rawQuantity.indexOf(po.getQuantityEndIndex()));
            String rawPrice = scrappedWebsite.substring(poPriceIndex , poPriceIndex + 7);
            price = rawPrice.substring(0, rawPrice.indexOf(po.getPriceEndIndex()));

        FishScrap fishScrap = new FishScrap();
        fishScrap.setSoldFish(myFish);
        fishScrap.setPrice(Float.parseFloat(price));
        fishScrap.setQuantity(Integer.parseInt(quantity));
        System.out.println(fishScrap + " has been saved !");


    }


    @GetMapping("/getAllFishScrap")
    public void getAllFromRAH() {

        List<SoldFish> fishes = soldFishRepository.findAll();
        WebSite rah = webSiteRepository.getWebSiteByName("RAH");
        WebSite po = webSiteRepository.getWebSiteByName("PO");


        Iterator<SoldFish> it = fishes.iterator();
        while (it.hasNext()) {
            SoldFish currentFish = it.next();
            try {
                String scrappedWebsite = restTemplateBuilder.build().getForObject(currentFish.getUrl(), String.class);
                String quantity ="";
                String price = "";
                if(currentFish.getWebsite().equals("RAH")){
                    quantity = scrappedWebsite.substring(scrappedWebsite.indexOf(rah.getQuantityStartIndex()) + rah.getQuantityStartIndex().length() , scrappedWebsite.indexOf(rah.getQuantityEndIndex()));
                    price = scrappedWebsite.substring(scrappedWebsite.indexOf(rah.getPriceStartIndex()) + rah.getPriceStartIndex().length(), scrappedWebsite.indexOf(rah.getPriceEndIndex()));
                }

                if (currentFish.getWebsite().equals("PO")){
                    int poQuantityIndex = scrappedWebsite.indexOf(po.getQuantityStartIndex()) + po.getQuantityStartIndex().length();
                    int poPriceIndex = scrappedWebsite.indexOf(po.getPriceStartIndex()) + po.getPriceStartIndex().length();
                    String rawQuantity = scrappedWebsite.substring(poQuantityIndex , poQuantityIndex + 7);
                    quantity = rawQuantity.substring(0, rawQuantity.indexOf(po.getQuantityEndIndex()));
                    String rawPrice = scrappedWebsite.substring(poPriceIndex , poPriceIndex + 7);
                    price = rawPrice.substring(0, rawPrice.indexOf(po.getPriceEndIndex()));
                }
                FishScrap fishScrap = new FishScrap();
                fishScrap.setSoldFish(currentFish);
                fishScrap.setPrice(Float.parseFloat(price));
                fishScrap.setQuantity(Integer.parseInt(quantity));
                fishScrapRepository.save(fishScrap);
                System.out.println(fishScrap + " has been saved !");

            } catch (Exception e) {
                System.out.println("Error in iteration : " + e);
                ScrapError error = new ScrapError();
                error.setMessage("Error");
                error.setSubMessage("Error due to : " + currentFish.getUrl());
                scrapErrorRepository.save(error);
            }

        }


    }


}
