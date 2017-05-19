package com.softuni._hibernatecodefirst.dao;


import com.softuni._hibernatecodefirst.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserDao extends JpaRepository<User, Long> {

    //find users by passing the email
    List<User> findUsersByEmail(String email);

    //find users that have the same email provider
    List<User> findByEmailEndingWith(String emailProvider);

    //return number of pictures with size of picture greater than given byte[]
    int countByProfilePictureGreaterThan(byte[] size);
}
