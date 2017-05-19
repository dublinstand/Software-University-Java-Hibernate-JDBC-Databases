package com.softuni._hibernatecodefirst.services;


import com.softuni._hibernatecodefirst.dao.UserDao;
import com.softuni._hibernatecodefirst.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createUser(User user) {
        userDao.saveAndFlush(user);
    }

    //find users by passing the email
    @Override
    public List<User> findUsersByEmail(String email) {
        return userDao.findUsersByEmail(email);
    }

    //find users that have the same email provider
    @Override
    public List<User> findUserByEmailProvider(String emailProvider) {
        return userDao.findByEmailEndingWith(emailProvider);
    }

    //return number of pictures with size of picture greater than given byte[]
    @Override
    public int countByProfilePictureGreaterThan(byte[] size) {
        return userDao.countByProfilePictureGreaterThan(size);
    }
}
