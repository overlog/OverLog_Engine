package com.overlog.Service;

import com.overlog.Model.Dao.UserDao;
import com.overlog.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public long getUser(User user){
        return userDao.getUser(user);
    }
}
