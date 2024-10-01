package com.dushyant.learn_spring_boot.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dushyant.learn_spring_boot.courses.bean.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

}
