package com.centropygebicolor.reefcatcher;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class ReefcatcherApplicationTests {

	@Test
	void testPricesScrapData() throws IOException {

		MockWebSite zoanthus = new MockWebSite("Zoanthus", "ZOA", "current-price", "", "content",
				"https://www.zoanthus.fr/filtre-a-rouleau/6708-red-sea-fleece-roll-35-m-rouleau-pour-filtre-reefmat-1200-7290116399454.html");

		MockWebSite recifart = new MockWebSite("Recifart", "RCA", "current-price", "price", "content",
				"https://www.recifart.com/fr/filtration/7382-rouleau-pour-reefmat-1200-filtration-red-sea-7290116399454.html");

		MockWebSite RAH = new MockWebSite("RecifAtHome", "RAH", "our_price_display ", "price", "content",
				"https://www.recifathome.com/filtre-a-rouleaux-redsea-clarisea-d-d-reefmat/27238-rouleau-redsea-pour-reef-mat-1200-9000l-h-max-reefmat.html");

		MockWebSite europrix = new MockWebSite("Europrix", "EUP", "our_price_display", "", "content",
				"https://www.achat-aquarium.fr/filtre-a-rouleau/11032-rouleau-reemat-1200.html");

		MockWebSite lmdlm = new MockWebSite("Le Monde de la mer", "LMDLM", "our_price_display", "price", "content",
				"https://www.shop.lemondedelamer.com/fr/sels/8928-sel-pro-reef-30-kg-tropic-marin.html");

		MockWebSite poissondor = new MockWebSite("Poisson d'Or", "PO", "our_price_display", "price", "content",
				"https://www.poisson-or.com/red-sea-filtre-%C3%A0-papier-fleece-filter/134246-red-sea-filtre-a-papier-rouleau-pour-rm1200-2095-eur.html");

		MockWebSite aquaplante = new MockWebSite("Aquaplante", "AP", "our_price_display", "price", "content",
				"https://www.aquaplante.fr/masses-de-filtration/red-sea/79743-red-sea-rouleau-pour-filtre-a-papier-reefmat-1200.html");

		MockWebSite aquastore = new MockWebSite("Aqua Store", "AQS", "our_price_display", "price", "content",
				"https://www.aqua-store.fr/recharges-tests-d-eau-d-aquarium-marin/67061-hanna-instruments-reactifs-pour-traces-de-phosphates-aquariums-marins.html");


		MockWebSite masterfisch = new MockWebSite("Master Fisch", "MF", "our_price_display", "price", "content",
				"https://www.masterfisch.fr/poissons-marins-anges/54043-paracentropyge-multifasciatus-elevage.html");


		List<MockWebSite> sitesToTest = new ArrayList();
		sitesToTest.add(zoanthus);
		sitesToTest.add(recifart);
		sitesToTest.add(RAH);
		sitesToTest.add(europrix);
		sitesToTest.add(lmdlm);
		sitesToTest.add(poissondor);
		sitesToTest.add(aquaplante);
		sitesToTest.add(aquastore);
		sitesToTest.add(masterfisch);


		for (MockWebSite currentWebSite : sitesToTest) {
			Document fullpage = Jsoup.connect(currentWebSite.getMockedUrl()).get();
			String firstPriceClass = currentWebSite.getFirstPriceClass();
			String secondPriceClass = currentWebSite.getSecondPriceClass();
			String priceAttribute = currentWebSite.getPriceAttribute();
			Element priceElement = fullpage.getElementsByClass(firstPriceClass).get(0);
			if (!Objects.equals(secondPriceClass, "")) {
				priceElement = priceElement.getElementsByClass(secondPriceClass).get(0);
			} else {
				priceElement = priceElement.getElementsByAttribute(priceAttribute).get(0);
			}
			String myPrice = priceElement.attr(priceAttribute);
			System.out.println("MY PRICE ==> " + myPrice);

		}


	}

}
