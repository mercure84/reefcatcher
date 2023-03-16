package com.centropygebicolor.reefcatcher.repository;

import com.centropygebicolor.reefcatcher.beans.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    Item getItemById(int id);

}
