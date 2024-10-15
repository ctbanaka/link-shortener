package in.rushlnk.linkshortner.service;

import in.rushlnk.linkshortner.dto.UserDto;
import in.rushlnk.linkshortner.exceptions.InvalidInputException;
import in.rushlnk.linkshortner.exceptions.UsernameAlreadyExistsException;
import in.rushlnk.linkshortner.models.User;
import in.rushlnk.linkshortner.repositories.UserRepository;
import in.rushlnk.linkshortner.utils.InputValidatorUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public String registerUser(UserDto userDto) {
        validateUserInput(userDto);

        if (userRepository.findByUsername(userDto.getUserName()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists.");
        }

        if (!userRepository.findByEmail(userDto.getEmail()).isEmpty()) {
            throw new UsernameAlreadyExistsException("Email already registered.");
        }

        User user = new User();
        user.setUsername(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return "registered successfully";
    }

    private void validateUserInput(UserDto userDto) {
        if (!InputValidatorUtil.isValidUsername(userDto.getUserName())) {
            throw new InvalidInputException("Invalid username. It should be 3-20 characters long and contain only letters, numbers, and underscores.");
        }
        if (!InputValidatorUtil.isValidEmail(userDto.getEmail())) {
            throw new InvalidInputException("Invalid email address.");
        }
        if (!InputValidatorUtil.isValidPassword(userDto.getPassword())) {
            throw new InvalidInputException("Invalid password. It should be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one number, and one special character.");
        }
    }
}