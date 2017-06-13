package com.jsonexercise.services.user;

import com.jsonexercise.domain.dto.SellerDto;
import com.jsonexercise.domain.dto.UserDto;
import com.jsonexercise.domain.entities.User;
import com.jsonexercise.parser.ModelParser;
import com.jsonexercise.repositories.UserRepository;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final ModelParser modelParser;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, ModelParser modelParser) {
		this.modelParser = modelParser;
		this.userRepository=userRepository;
	}

	@Override
	public void create(UserDto userDto) {
		User user = this.modelParser.convert(userDto, User.class);
		this.userRepository.save(user);
	}

	@Override
	public List<SellerDto> findWithMoreThanOneBuyer() {
		List<User> users = this.userRepository.findWithMoreThanOneBuyer();
        List<SellerDto> sellerDtos = this.modelParser.convert(users, SellerDto.class);
		return sellerDtos;
	}
}