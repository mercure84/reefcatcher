package com.centropygebicolor.reefcatcher.repository;

import com.centropygebicolor.reefcatcher.beans.FishScrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishScrapRepository extends JpaRepository<FishScrap, Integer> {




}
