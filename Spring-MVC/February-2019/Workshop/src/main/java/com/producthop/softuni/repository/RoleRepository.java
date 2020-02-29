package com.producthop.softuni.repository;

import com.producthop.softuni.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    public Role findByAuthority(String authority);
}
