package com.ltp.gradesubmission.service;

import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.exception.EntityNotFoundException;
import com.ltp.gradesubmission.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private CommonService commonService;
    @Override
    public User getUser(long id) {
        Optional<User> user = userRepository.findById(id);
        return (User) commonService.unwrapObject(user, id, User.class);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(username, User.class));
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // COMMON SERVICE WILL HANDLE THIS
//    static User unwrapObject(Optional<User> entity, Long id) {
//        if (entity.isPresent()) return entity.get();
//        else throw new EntityNotFoundException(id, User.class);
//    }
}
