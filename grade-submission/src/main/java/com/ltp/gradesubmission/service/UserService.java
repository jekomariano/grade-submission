package com.ltp.gradesubmission.service;

import com.ltp.gradesubmission.entity.User;

public interface UserService {
    User getUser(long id);

    User getUser(String username);
    User saveUser(User user);

}
