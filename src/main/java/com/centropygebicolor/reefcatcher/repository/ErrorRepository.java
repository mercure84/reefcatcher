package com.centropygebicolor.reefcatcher.repository;

import com.centropygebicolor.reefcatcher.beans.Error;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorRepository extends JpaRepository<Error, Integer> {





}
