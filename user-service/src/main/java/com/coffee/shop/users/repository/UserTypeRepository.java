package com.coffee.shop.users.repository;

import com.coffee.shop.users.constants.RequestType;
import com.coffee.shop.users.domain.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {
    UserType findByName(String type);
}
