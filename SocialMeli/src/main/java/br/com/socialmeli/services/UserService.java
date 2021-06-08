package br.com.socialmeli.services;

import br.com.socialmeli.dtos.UserDTO;
import br.com.socialmeli.exceptions.UserNotFound;
import br.com.socialmeli.models.User;
import br.com.socialmeli.resposistories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public ResponseEntity insertUser(UserDTO userDTO){
        User user = new User();
        user.setUserName(userDTO.getUserName());
        System.out.println(userDTO.getUserName());
        user = userRepository.save(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    public ResponseEntity<?> listUser() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = users
                .stream()
                .map(user -> new UserDTO(user.getUserId(), user.getUserName()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteUser(Integer userId){
        Optional<User> optionalUser = userRepository.findById((long) userId);
        if (!optionalUser.isPresent()){
            throw new UserNotFound("User not found for ID: " + userId);
        }
        User user = optionalUser.get();
        userRepository.delete(user);
        return new ResponseEntity<>("Removed success", HttpStatus.OK);

    }
}
