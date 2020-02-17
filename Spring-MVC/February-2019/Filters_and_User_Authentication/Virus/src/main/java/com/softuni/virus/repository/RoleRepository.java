package com.softuni.virus.repository;

import com.softuni.virus.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    public Role findByAuthority(String authority);
}
