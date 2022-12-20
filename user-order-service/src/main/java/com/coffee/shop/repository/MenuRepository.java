package com.coffee.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coffee.shop.domain.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

}
