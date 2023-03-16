package com.centropygebicolor.reefcatcher.repository;

import com.centropygebicolor.reefcatcher.beans.WebSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebSiteRepository extends JpaRepository<WebSite, Integer> {
    WebSite getWebSiteByName(String name);
    WebSite getWebSiteByAlias(String alias);

}
