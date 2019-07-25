package com.sandy.user.dao;

import com.sandy.user.model.Users;

public interface UsersMapper {

    Users selectByEmail(String email);

    int insert(Users users);
}
