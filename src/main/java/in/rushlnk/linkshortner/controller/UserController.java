package in.rushlnk.linkshortner.controller;

import in.rushlnk.linkshortner.dto.UserDto;
import in.rushlnk.linkshortner.exceptions.InvalidInputException;
import in.rushlnk.linkshortner.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "https://rushlnk.in")
@Tag(name = "users",description = "User related user Api's")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
//       try {
           String result = userService.registerUser(userDto);
           return ResponseEntity.ok(result);
//        } catch (InvalidInputException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (UserAlreadyExistsException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request.");
//        }
    }
}