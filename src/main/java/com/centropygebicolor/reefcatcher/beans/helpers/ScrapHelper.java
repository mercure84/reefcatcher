package com.centropygebicolor.reefcatcher.beans.helpers;

import com.centropygebicolor.reefcatcher.beans.Item;
import com.centropygebicolor.reefcatcher.beans.ScrapData;
import com.centropygebicolor.reefcatcher.beans.WebSiteUrl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class ScrapHelper {


    public ScrapData getScrapData(WebSiteUrl webSiteUrl, Item item) throws IOException {
        System.out.println("Trying to scrap data in : " + webSiteUrl.getWebsite().getName() + " ...");
        Document fullpage = Jsoup.connect(webSiteUrl.getUrl()).get();
        String firstPriceClass = webSiteUrl.getWebsite().getFirstPriceTag();
        String secondPriceClass = webSiteUrl.getWebsite().getSecondPriceTag();
        String thirdPriceTag = webSiteUrl.getWebsite().getThirdPriceTag();
        String price = "0";
        String quantity = "0";
        boolean isAvailable = true;
        if (!Objects.equals(firstPriceClass, null)) {
            Element priceElement = null;
            Element priceElement2 = null;
            try {
                priceElement = fullpage.getElementsByClass(firstPriceClass).get(0);
            } catch (Exception p1) {
                try {
                    priceElement = fullpage.getElementById(firstPriceClass);
                } catch (Exception p2) {
                    try {
                        priceElement = fullpage.getElementsByTag(firstPriceClass).get(0);
                    } catch (Exception p3) {
                        System.out.println("NO DATA FOR PRICE");
                    }
                }
            }

            if (!Objects.equals(secondPriceClass, null)) {

                try {
                    assert priceElement != null;
                    priceElement2 = priceElement.getElementsByClass(secondPriceClass).get(0);
                } catch (Exception q1) {
                    try {
                        priceElement2 = priceElement.getElementsByTag(secondPriceClass).get(0);
                    } catch (Exception q2) {
                        try {
                            priceElement2 = priceElement.getElementById(secondPriceClass);
                        } catch (Exception q3) {
                            System.out.println("NO DATA FOR PRICE");
                        }
                    }
                }
            }

            if (!Objects.equals(thirdPriceTag, null)) {
                if (priceElement != null) {
                    try {
                        price = priceElement.attr(thirdPriceTag);
                    } catch (Exception a) {
                        System.out.println("Erreur 1");
                    }
                }
                if (priceElement2 != null) {
                    try {
                        price = priceElement2.attr(thirdPriceTag);
                    } catch (Exception b) {
                        System.out.println("Erreur 2");
                    }

                }

                System.out.println("MY PRICE ==> " + price);
            }
        }


        String firstQuantityTag = webSiteUrl.getWebsite().getFirstQuantityTag();
        String secondQuantityTag = webSiteUrl.getWebsite().getSecondQuantityTag();


        Element quantityElement = null;
        Element quantityElement2 = null;

        try {
            quantityElement = fullpage.getElementsByClass(firstQuantityTag).get(0);
            if (quantityElement == null) {
                throw new Exception();
            }
        } catch (Exception e1) {
            try {
                quantityElement = fullpage.getElementById(firstQuantityTag);
                if (quantityElement == null) {
                    throw new Exception();
                }
            } catch (Exception e2) {
                try {
                    quantityElement = fullpage.getElementsByTag(firstQuantityTag).get(0);
                    if (quantityElement == null) {
                        throw new Exception();
                    }
                } catch (Exception e3) {
                    System.out.println("NO DATA FOR QUANTITY");
                }
            }
        }
        if (quantityElement != null) {
            String elementString = String.valueOf(quantityElement);
            quantity = elementString.replaceAll("[^0-9]", "");
        }
        if (!Objects.equals(secondQuantityTag, null) && quantityElement != null ) {
            try {
                quantityElement2 = quantityElement.getElementsByClass(secondQuantityTag).get(0);
                if (quantityElement2 == null) {
                    throw new Exception();
                }
            } catch (Exception f1) {
                try {
                    quantityElement2 = quantityElement.getElementById(secondQuantityTag);
                    if (quantityElement2 == null) {
                        throw new Exception();
                    }
                } catch (Exception f2) {
                    try {
                        quantityElement2 = quantityElement.getElementsByTag(secondQuantityTag).get(0);
                        if (quantityElement2 == null) {
                            throw new Exception();
                        }
                    } catch (Exception f3) {
                        System.out.println("NO SECOND LEVEL FOR QUANTITY");

                    }
                }
            }
            if (quantityElement2 != null) {
                String elementString = String.valueOf(quantityElement2);
                quantity = elementString.replaceAll("[^0-9]", "");
            }
        }

        System.out.println("MY QUANTITY ==> " + quantity);

        ScrapData scrapData = new ScrapData();
        scrapData.setWebSite(webSiteUrl.getWebsite());
        scrapData.setItem(item);
        scrapData.setPrice(Float.parseFloat(price));
        scrapData.setQuantity(Integer.parseInt(quantity));
        scrapData.setAvailable(isAvailable);

        return scrapData;
    }


}
