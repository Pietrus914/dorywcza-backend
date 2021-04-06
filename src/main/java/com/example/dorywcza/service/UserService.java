package com.example.dorywcza.service;

import com.example.dorywcza.model.user.User;
import com.example.dorywcza.model.user.UserDTO;
import com.example.dorywcza.model.user.UserProfile;
import com.example.dorywcza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@PersistenceContext(type = PersistenceContextType.EXTENDED)
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {

        return userRepository.findAll().stream().map(user -> convert(user)).collect(Collectors.toList());
    }

    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id).map(e -> convert(e));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO addUser(UserDTO userDTO) {
        userDTO.setId(null);
        User toAddUser = convert(userDTO);
        toAddUser.setDeleted(false);
        User addedUser = userRepository.save(toAddUser);

        return convert(addedUser);
    }

    public UserDTO updateUser(UserDTO userDTO, Long id) {
        if (!userRepository.existsById(id)) throw new RuntimeException("User Not Found");
        User userToUpdate = convert(userDTO);
        userToUpdate.setId(id);
        userToUpdate.getUserProfile().setId(id);
        User updatedUser = userRepository.save(userToUpdate);

        return convert(updatedUser);
    }

    private User convert(UserDTO userDTO){
        User fromDTO = new User(userDTO);
        fromDTO.setUserProfile(new UserProfile(fromDTO, userDTO.getFirst_name(),userDTO.getLast_name(),
                userDTO.getUser_name(), userDTO.getDescription(), userDTO.getStreet(), userDTO.getExperienceDescription(),
                userDTO.getPictures(), userDTO.getAvatar()));
        return fromDTO;
    }

    private UserDTO convert(User user){

        UserDTO userDTO = new UserDTO(user) ;                ;

        return userDTO;

    }
}
