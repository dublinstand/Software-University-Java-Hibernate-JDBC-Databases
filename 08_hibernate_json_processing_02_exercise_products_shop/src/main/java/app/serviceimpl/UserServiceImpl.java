package app.serviceimpl;

import app.domain.dto.UserDto;
import app.domain.model.User;
import app.repository.UserRepository;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void create(UserDto userDto) {
        User user = convertToEntity(userDto);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public List<UserDto> findUserWithSoldProducts() {
        List<UserDto> userDtos = new ArrayList<>();

        List<User> users = this.userRepository.findUserWithSoldProducts();

        for (User user : users) {
            UserDto userDto = convertToDto(user);
            userDtos.add(userDto);
        }

        return userDtos;
    }

    private User convertToEntity(UserDto userDto){
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());

        return user;
    }

    private UserDto convertToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setAge(user.getAge());
        
        return userDto;
    }
}
