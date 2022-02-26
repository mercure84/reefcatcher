package com.centropygebicolor.reefcatcher.repository;

import com.centropygebicolor.reefcatcher.beans.SoldFish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoldFishRepository extends JpaRepository<SoldFish, Integer> {

    List<SoldFish> getAllByWebsite(String website);

}
