package com.example.dorywcza.service;

import com.example.dorywcza.model.user.User;
import com.example.dorywcza.model.user.UserDTO;
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
        User addedUser = userRepository.save(convert(userDTO));
        return convert(addedUser);

    }

    public User updateUser(User user, Long id) {
        if (!userRepository.existsById(id)) throw new RuntimeException("User Not Found");
        user.setId(id);
        return userRepository.save(user);
    }

    private User convert(UserDTO userDTO){
        return new User(userDTO);

    }

    private UserDTO convert(User user){

        UserDTO userDTO = new UserDTO(user) ;                ;

        return userDTO;

    }

//    private UserDTO convert(User user){
//        List<File> experienceImages =
//                user.getUserProfile().getExperience().getImageBox().
//                getImages().stream().map(Image::getImage)
//                .collect(Collectors.toList());
//
//        UserDTO userDTO = new UserDTO(
//                user.getId(),user.getEmail(), user.getPhone_number(),
//                user.getOverallRating(), user.getUserProfile().getFirst_name(),
//                user.getUserProfile().getLast_name(),
//                user.getUserProfile().getUser_name(),
//                user.getUserProfile().getAddress().getStreet(),
//                user.getUserProfile().getDescription(),
//                user.getUserProfile().getExperience().getDescription(),
//                user.getUserProfile().getAvatar().getImage(),
//                experienceImages);                ;
//
//        return userDTO;
//
//    }
}
