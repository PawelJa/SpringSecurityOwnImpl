package pl.ssdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ssdemo.entity.UserRole;

public interface UserRoleRepository extends JpaRepository <UserRole, Long>{

//    public UserRole findOneByUsername(String username);
}
