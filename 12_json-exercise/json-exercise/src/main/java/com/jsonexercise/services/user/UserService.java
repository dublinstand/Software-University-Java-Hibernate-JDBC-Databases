package com.jsonexercise.services.user;

import com.jsonexercise.domain.dto.SellerDto;
import com.jsonexercise.domain.dto.UserDto;
import com.jsonexercise.domain.entities.User;

import java.util.List;

public interface UserService {

    void create(UserDto userDto);

    List<SellerDto> findWithMoreThanOneBuyer();
}