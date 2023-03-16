package com.centropygebicolor.reefcatcher.repository;

import com.centropygebicolor.reefcatcher.beans.Item;
import com.centropygebicolor.reefcatcher.beans.ScrapZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapeZoneRepository extends JpaRepository<ScrapZone, Integer> {

    ScrapZone getScrapZoneByItem(Item item);

}
