package com.centropygebicolor.reefcatcher;

import com.centropygebicolor.reefcatcher.beans.Item;
import com.centropygebicolor.reefcatcher.beans.ScrapData;
import com.centropygebicolor.reefcatcher.beans.WebSiteUrl;
import com.centropygebicolor.reefcatcher.beans.helpers.ScrapHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ReefcatcherApplicationTests {


	@Autowired
	ScrapHelper scrapHelper;


	@Test
	void testPricesScrapData() throws IOException {

		MockWebSite zoanthus = new MockWebSite("Zoanthus", "ZOA", "current-price", "span", "content",
				"ql_ctn_availability_message", "span", "",
				"https://www.zoanthus.fr/appareils-de-mesure/6412-hanna-instruments-reactifs-en-poudre-pour-hi782-25-tests.html");

		MockWebSite recifart = new MockWebSite("Recifart", "RCA", "current-price", "price", "content",
				"tv-items", "", "",
				"https://www.recifart.com/fr/filtration/7382-rouleau-pour-reefmat-1200-filtration-red-sea-7290116399454.html");

		MockWebSite RAH = new MockWebSite("RecifAtHome", "RAH", "our_price_display ", "price", "content",
				"warning_inline", "", "",
				"https://www.recifathome.com/filtre-a-rouleaux-redsea-clarisea-d-d-reefmat/27238-rouleau-redsea-pour-reef-mat-1200-9000l-h-max-reefmat.html");

		MockWebSite europrix = new MockWebSite("Europrix", "EUP", "our_price_display", "span", "content",
				"", "", "",
				"https://www.achat-aquarium.fr/filtre-a-rouleau/11032-rouleau-reemat-1200.html");

		MockWebSite lmdlm = new MockWebSite("Le Monde de la mer", "LMDLM", "our_price_display", "price", "content",
				"quantityAvailable", "", "",
				"https://www.shop.lemondedelamer.com/fr/sels/8928-sel-pro-reef-30-kg-tropic-marin.html");

		MockWebSite poissondor = new MockWebSite("Poisson d'Or", "PO", "our_price_display", "price", "content",
				"quantityAvailable", "", "",
				"https://www.poisson-or.com/poissons-anges-pomacanthides/14380-centropyge-loriculus-5-7-cm.html");

		MockWebSite aquaplante = new MockWebSite("Aquaplante", "AP", "our_price_display", "price", "content",
				"", "", "",
				"https://www.aquaplante.fr/masses-de-filtration/red-sea/79743-red-sea-rouleau-pour-filtre-a-papier-reefmat-1200.html");

		MockWebSite aquastore = new MockWebSite("Aqua Store", "AQS", "our_price_display", "price", "content",
				"availability_value", "", "",
				"https://www.aqua-store.fr/recharges-tests-d-eau-d-aquarium-marin/67061-hanna-instruments-reactifs-pour-traces-de-phosphates-aquariums-marins.html");


		MockWebSite masterfisch = new MockWebSite("Master Fisch", "MF", "our_price_display", "price", "content",
				"", "", "",
				"https://www.masterfisch.fr/poissons-marins-anges/54043-paracentropyge-multifasciatus-elevage.html");


		List<MockWebSite> sitesToTest = new ArrayList();
		sitesToTest.add(recifart);

		sitesToTest.add(zoanthus);
		sitesToTest.add(RAH);
		sitesToTest.add(europrix);
		sitesToTest.add(lmdlm);
		sitesToTest.add(poissondor);
		sitesToTest.add(aquaplante);
		sitesToTest.add(aquastore);
		sitesToTest.add(masterfisch);


		for (MockWebSite currentWebSite : sitesToTest) {
			Item item = new Item();
			item.setLabel("MOCCKED ITEM for" + currentWebSite.getAlias());
			WebSiteUrl webSiteUrl =  new WebSiteUrl();
			webSiteUrl.setWebsite(currentWebSite);
			webSiteUrl.setUrl(currentWebSite.getMockedUrl());

			ScrapData scrapData = scrapHelper.getScrapData(webSiteUrl, item);
			System.out.println("MY SCRAP DATA ? " + scrapData);

		}
	}


}
