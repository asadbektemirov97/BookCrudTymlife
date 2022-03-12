package com.example.temirov_asadbek_b9v2.repository;

import com.example.temirov_asadbek_b9v2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
