package com.example.crm.dataAccess.abstracts;

import com.example.crm.entities.concretes.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
