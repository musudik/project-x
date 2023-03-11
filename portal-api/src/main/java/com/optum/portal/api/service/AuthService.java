package com.optum.portal.api.service;

import com.optum.portal.api.model.User;
import com.optum.portal.api.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private IUserRepository userRepository;

    /**
     * save user
     * @param user
     * @return
     */
    public User save(User user) {
        if(user.getId() != null) {
            user.setUpdatedDate(LocalDate.now());
            user.setUpdatedBy("System");
        } else {
            user.setCreatedDate(LocalDate.now());
            user.setCreatedBy("System");
        }
        return userRepository.save(user);
    }

    /**
     * login user with credentials
     * @param user
     * @return
     * @throws UsernameNotFoundException
     */
    public User login(User user) throws UsernameNotFoundException {
        final User loggedUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if(loggedUser == null) {
            throw new UsernameNotFoundException("User with the username:"+user.getUsername()+" is not found");
        }
        return loggedUser;
    }

    /**
     * get user by id.
     * @param id
     * @return
     * @throws UsernameNotFoundException
     */
    public User getUserById(Long id) throws UsernameNotFoundException {
        final Optional<User> loggedUser = userRepository.findById(id);
        if(loggedUser == null) {
            throw new UsernameNotFoundException("User with the id:"+id+" is not found");
        }
        return loggedUser.get();
    }

    /**
     * get all users irrespective of role.
     * @return
     * @throws UsernameNotFoundException
     */
    public List<User> getAllUsers() throws UsernameNotFoundException {
        final List<User> users = userRepository.findAll();
        if(users == null) {
            throw new UsernameNotFoundException("No Users are found");
        }
        return users;
    }

    /**
     * get users by role 'user'.
     * @return
     * @throws UsernameNotFoundException
     */
    public List<User> getUsers() throws UsernameNotFoundException {
        final List<User> users = userRepository.findUsers("user");
        if(users == null) {
            throw new UsernameNotFoundException("No Users are found");
        }
        return users;
    }

    /**
     * get users by role 'doctor'.
     * @return
     * @throws UsernameNotFoundException
     */
    public List<User> getDoctors() throws UsernameNotFoundException {
        final List<User> users = userRepository.findUsers("doctor");
        if(users == null) {
            throw new UsernameNotFoundException("No Doctors are found");
        }
        return users;
    }


}
