package com.centropygebicolor.reefcatcher.repository;

import com.centropygebicolor.reefcatcher.beans.WebSite;
import com.centropygebicolor.reefcatcher.beans.WebSiteUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WebSiteUrlRepository extends JpaRepository<WebSiteUrl, Integer> {



}
