package com.centropygebicolor.reefcatcher.repository;

import com.centropygebicolor.reefcatcher.beans.ScrapError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapErrorRepository extends JpaRepository<ScrapError, Integer> {
}
