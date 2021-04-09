package com.example.dorywcza.service;

import com.example.dorywcza.model.user.*;
import com.example.dorywcza.model.user.DTO.UserPublicDTO;
import com.example.dorywcza.model.user.DTO.UserUpdateDTO;
import com.example.dorywcza.repository.UserRepository;
import com.example.dorywcza.service.ImageService.ImageService;
import com.example.dorywcza.util.Address;
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

    public User findUserById(Long id) {
        if (!userRepository.existsById(id)){throw new RuntimeException();}
        return userRepository.findById(id).get();
    }

    public Optional<UserPublicDTO> findPublicDTOById(Long id) {
        if (!userRepository.existsById(id)){throw new RuntimeException();}
        return userRepository.findById(id).map(this::getUserPublicDTOFrom);
    }

    public Optional<UserUpdateDTO> findUpdateDTOById(Long id) {
        if (!userRepository.existsById(id)){throw new RuntimeException();}
        return userRepository.findById(id).map(this::getUserUpdateDTOFrom);
    }


    /** function that marks user as deleted **/
    public void deleteUser(Long id) {
        User userToDelete = userRepository.findById(id).orElseThrow();
        userToDelete.setDeleted(true);
        userRepository.save(userToDelete);
    }

    /** function that saves user without userProfile **/
    public UserUpdateDTO addUser(UserUpdateDTO userUpdateDTO) {
        User toAddUser = createFrom(userUpdateDTO);
        userUpdateDTO.setId(null);
        User addedUser = userRepository.save(toAddUser);

        return getUserUpdateDTOFrom(addedUser);
    }

    /** function that creates user without userProfile **/
    public User createFrom(UserUpdateDTO userUpdateDTO){
        User fromDTO = new User(userUpdateDTO);
        return fromDTO;
    }

    /** function that update user with userProfile (without images) **/
    public UserUpdateDTO updateUser(UserUpdateDTO userUpdateDTO, Long id) {
        User userFromDb = userRepository.findById(id).orElseThrow();

        User userToUpdate = updateUserFromDb(userFromDb, userUpdateDTO);
        User updatedUser = userRepository.save(userToUpdate);

        return getUserUpdateDTOFrom(updatedUser);
    }

    /** function that updates user from DB with data from UserDTO (without images) **/
    private User updateUserFromDb(User userFromDb, UserUpdateDTO userUpdateDTO){
        userFromDb.setPhone_number(userUpdateDTO.getPhone_number());
        if (!userFromDb.hasProfile()){
            userFromDb.setUserProfile(new UserProfile(userFromDb));
        }
        UserProfile profile = userFromDb.getUserProfile();
        updateProfileData(userUpdateDTO, profile);

        return userFromDb;
    }

    private void updateProfileData(UserUpdateDTO userUpdateDTO, UserProfile profile) {
        profile.setFirst_name(userUpdateDTO.getFirst_name());
        profile.setLast_name(userUpdateDTO.getLast_name());
        profile.setUser_name(userUpdateDTO.getUser_name());
        Address address = profile.getAddress();
        address.setStreet(userUpdateDTO.getStreet());
        Experience experience = profile.getExperience();
        experience.setDescription(userUpdateDTO.getExperienceDescription());
    }

    public User getUserFromUpdateDTO(UserUpdateDTO userUpdateDTO, Long id){
        User userFromDb = userRepository.findById(id).orElseThrow();
        return updateUserFromDb(userFromDb,userUpdateDTO);
    }

    private UserPublicDTO getUserPublicDTOFrom(User user){
        return new UserPublicDTO(user);
    }

    private UserUpdateDTO getUserUpdateDTOFrom(User user){
        return new UserUpdateDTO(user);
    }
}
