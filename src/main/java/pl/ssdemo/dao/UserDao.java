package pl.ssdemo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.ssdemo.entity.User;
import pl.ssdemo.repository.UserRepository;

@Repository
@Transactional
public class UserDao {

    @Autowired
    UserRepository userRepository;

    public User getActiveUser(String username) {
        User user = new User();
        user = userRepository.findOneByUsername(username);
        return user;
    }
}
