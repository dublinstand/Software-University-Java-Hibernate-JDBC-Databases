package app.service;

import app.domain.dto.UserDto;
import app.domain.model.User;

import java.util.List;

public interface UserService {

    void create(UserDto userDto);

    List<User> findAll();

    List<UserDto> findUserWithSoldProductsChangeToDto();
}
