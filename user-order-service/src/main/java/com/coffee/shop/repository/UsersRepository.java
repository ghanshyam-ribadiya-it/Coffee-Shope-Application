package com.coffee.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coffee.shop.domain.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

}
