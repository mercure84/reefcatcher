package com.centropygebicolor.reefcatcher.repository;

import com.centropygebicolor.reefcatcher.beans.ScrapData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapRepository extends JpaRepository<ScrapData, Integer> {

}
