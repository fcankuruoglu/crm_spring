package com.example.crm.business.concretes;

import com.example.crm.business.abstracts.UserService;
import com.example.crm.dataAccess.abstracts.RoleDao;
import com.example.crm.dataAccess.abstracts.UserDao;
import com.example.crm.dataTransferObjects.UserDto;
import com.example.crm.entities.concretes.Role;
import com.example.crm.entities.concretes.User;
import com.example.crm.exception.custom.AlreadyExistsException;
import com.example.crm.exception.custom.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user  = this.userDao.findByUsername(username);
        if(user == null) {throw new UsernameNotFoundException("User not found in the database");}
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new  org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
    @Override
    public void save(User user) {
//        Integer userId = user.getId();
//        if (this.userDao.findById(userId).isPresent()) {throw new AlreadyExistsException(userId);}
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        this.userDao.save(user);
        log.info("Saved new user to the database");
    }

    @Override
    public void delete(Integer id) {
        if (this.userDao.findById(id).isEmpty()) {throw new UserNotFoundException(id);}
        this.userDao.delete(id);
    }

    @Override
    public void activate(Integer id) {
        if (this.userDao.findById(id).isEmpty()) {throw new UserNotFoundException(id);}
        this.userDao.activate(id);
    }

    @Override
    public void disable(Integer id) {
        if (this.userDao.findById(id).isEmpty()) {throw new UserNotFoundException(id);}
        this.userDao.disable(id);
    }

    @Override
    public void saveRole(String roleName) {
        this.roleDao.save(new Role(null, roleName));
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = this.userDao.findByUsername(username);
        Role role = this.roleDao.findByName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public User getUserWithPassword(String username) {
        return this.userDao.findByUsername(username);
    }

    @Override
    public List<User> getUsersWithPassword() {
        return this.userDao.findAll();
    }

    @Override
    public UserDto getUser(String username) {
        User user = this.userDao.findByUsername(username);
        return mapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getUsers() {
        return this.userDao.findAll().stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

}
