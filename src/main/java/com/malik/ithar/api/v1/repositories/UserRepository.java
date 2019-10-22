package com.malik.ithar.api.v1.repositories;

import com.malik.ithar.api.v1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
}
