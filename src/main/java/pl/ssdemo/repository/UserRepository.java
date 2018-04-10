package pl.ssdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ssdemo.entity.User;

public interface UserRepository extends JpaRepository <User, Long>{

    public User findOneByUsername(String username);
}
