package com.example.Security_project.repositories;

import com.example.Security_project.models.Role;
import com.example.Security_project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
