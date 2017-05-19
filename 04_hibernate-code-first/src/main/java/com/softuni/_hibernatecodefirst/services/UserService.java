package com.softuni._hibernatecodefirst.services;


import com.softuni._hibernatecodefirst.entities.User;

import java.util.List;

public interface UserService {
    void createUser(User user);

    //find users by passing the email
    List<User> findUsersByEmail(String email);

    //find users that have the same email provider
    List<User> findUserByEmailProvider(String emailProvider);

    //return number of pictures with size of picture greater than given byte[]
    int countByProfilePictureGreaterThan(byte[] size);

}
