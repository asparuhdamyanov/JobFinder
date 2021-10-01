package tu.project.jobfinder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tu.project.jobfinder.entities.Users;
import tu.project.jobfinder.repositories.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/id")
    public Users getUserById(@RequestParam Long id) {
        Optional<Users> result = userRepository.findById(id);
        return result.isPresent() ? result.get() : null;
    }

    @PostMapping("/save/user")
    public ResponseEntity<?> saveUser(@RequestBody Users form) {
        boolean isNew = form.getId() == null;

        Map<String, Object> response = new HashMap<>();
        response.put("user", userRepository.save(form));
        if(isNew) {
            response.put("message", "User saved!");
            response.put("message", "User edited!");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveOrUpdate(@RequestParam(required = false) Long id,
                                      @RequestParam(required = false) String username,
                                      @RequestParam(required = false) String password,
                                      @RequestParam(required = false) String firstName,
                                      @RequestParam(required = false) String lastName,
                                      @RequestParam(required = false) String email) {

        boolean isNew = id == null;

        Users user = new Users(id, username, password, firstName, lastName, email);
        user = userRepository.save(user);

        Map<String, Object> response = new HashMap<>();
        response.put("generatedId", user.getId());
        response.put("generatedUsername", user.getUsername());
        response.put("generatedPassword", user.getPassword());
        response.put("generatedFirstName", user.getFirstName());
        response.put("generatedLastName", user.getLastName());
        response.put("generatedEmail", user.getEmail());
        if (isNew) {
            response.put("message", "Successfully added!");
        } else {
            response.put("message", "Successfully edited!");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {

        if (!userRepository.existsById(id)) {
            return ResponseEntity.ok("No such User!");
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully!");
    }
}
