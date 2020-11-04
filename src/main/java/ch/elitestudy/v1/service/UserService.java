package ch.elitestudy.v1.service;

import ch.elitestudy.v1.model.Feed;
import org.springframework.security.core.userdetails.UserDetailsService;

import ch.elitestudy.v1.model.User;
import ch.elitestudy.v1.model.UserRegistrationDto;

import java.util.List;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);

//	List<User> loadUser(String user);

//	User findByEmail(String email);
}
