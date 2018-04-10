package pl.ssdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.ssdemo.entity.User;
import pl.ssdemo.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.println(username);
        User user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        UserPrincipal userPrincipal = new UserPrincipal(user);
        System.out.println(userPrincipal.getId());
        System.out.println(userPrincipal.getUsername());
        System.out.println(userPrincipal.getPassword());
        System.out.println(userPrincipal.getAuthorities());
        return userPrincipal;
    }
}

//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        User activeUser = userDao.getActiveUser(username);
//        if (activeUser == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        List<GrantedAuthority> authorities = buildUserAuthority(activeUser);
//
//        return  (UserDetails)buildUserForAuthentication(activeUser, authorities);
//    }
//
//    private User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
//        return new User(user.getUsername(), user.getPassword(), true, true, true, true, authorities)
//    }
//
//    private List<GrantedAuthority> buildUserAuthority(User user) {
//        Set<GrantedAuthority> setAuths = new HashSet<>();
//        setAuths.add(new SimpleGrantedAuthority(user.getRole()));
//
//        List<GrantedAuthority> authorities = new ArrayList<>(setAuths);
//        return authorities;
//    }
//}
