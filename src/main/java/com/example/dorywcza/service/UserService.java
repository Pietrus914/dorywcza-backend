package com.example.dorywcza.service;

import com.example.dorywcza.model.user.User;
import com.example.dorywcza.model.user.UserGeneralDTO;
import com.example.dorywcza.model.user.UserPublicDTO;
import com.example.dorywcza.model.user.UserUpdateDTO;
import com.example.dorywcza.repository.UserRepository;
import com.example.dorywcza.service.ImageService.ImageService;
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
    private final UserRepository userRepository;
    @Autowired
    private ImageService imageService;


    public UserService(UserRepository userRepository, ImageService imageService) {
        this.userRepository = userRepository;
        this.imageService = imageService;
    }

    public List<UserPublicDTO> findAll() {
        return userRepository.findAll().stream().map(this::getUserPublicDTOFrom).collect(Collectors.toList());
    }

    public Optional<UserPublicDTO> findById(Long id) {
        return userRepository.findById(id).map(this::getUserPublicDTOFrom);
    }

    /** function that marks user as deleted **/
    public void deleteUser(Long id) {
        User userToDelete = userRepository.findById(id).orElseThrow();
        userToDelete.setDeleted(true);
        userRepository.save(userToDelete);
    }

    /** function that saves user without userProfile **/
    public UserUpdateDTO addUser(UserGeneralDTO userGeneralDTO) {
        userGeneralDTO.setId(null);
        User toAddUser = createFrom(userGeneralDTO);
        User addedUser = userRepository.save(toAddUser);

        return getUserUpdateDTOFrom(addedUser);
    }

    /** function that update user with userProfile **/
    public UserUpdateDTO updateUser(UserUpdateDTO userUpdateDTO, Long id) {
        User userFromDb = userRepository.findById(id).orElseThrow();

        User userToUpdate = updateUserFromDb(userFromDb, userUpdateDTO);
        User updatedUser = userRepository.save(userToUpdate);

        return getUserUpdateDTOFrom(updatedUser);
    }

    /** function that creates user without userProfile **/
    public User createFrom(UserGeneralDTO userGeneralDTO){
        User fromDTO = new User(userGeneralDTO);
        return fromDTO;
    }

    /** function that updates user from DB with data from UserDTO (without images) **/
    private User updateUserFromDb(User userFromDb, UserUpdateDTO userUpdateDTO){

        return null;
    }

    public User convertFrom(UserUpdateDTO userDTO){
        User fromDTO = new User(userDTO);
//        List<Image> pictures = null;
//        Image avatar = null;
//        if (userDTO.getAvatar() != null){
//            avatar = imageService.findRealImage(userDTO.getAvatar().getImageIdFromUrl());
//        }
//        if(userDTO.getPictures() != null){
//            List<Long> imageIds = userDTO.getPictures().stream().map(ImageDTO::getImageIdFromUrl)
//                    .collect(Collectors.toList());
//            pictures = imageService.findRealImagesByIds(imageIds);
//        }
//
//        fromDTO.setUserProfile(new UserProfile(fromDTO, userDTO.getFirst_name(),userDTO.getLast_name(),
//                userDTO.getUser_name(), userDTO.getDescription(), userDTO.getStreet(), userDTO.getExperienceDescription(),
//                pictures, avatar));
        return fromDTO;
    }

    public User convertFrom(UserPublicDTO userPublicDTO){
        return new User(userPublicDTO);
    }

    private UserGeneralDTO getUserGeneralDTOFrom(User user){
        return new UserGeneralDTO(user);
    }

    private UserPublicDTO getUserPublicDTOFrom(User user){
        return new UserPublicDTO(user);
    }

    private UserUpdateDTO getUserUpdateDTOFrom(User user){
        return new UserUpdateDTO(user);
    }
}
