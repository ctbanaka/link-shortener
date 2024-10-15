package in.rushlnk.linkshortner.service;

import in.rushlnk.linkshortner.dto.UserDto;
import in.rushlnk.linkshortner.models.User;

public interface UserService {

    public String registerUser(UserDto userDto);
}
