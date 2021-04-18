package com.example.dorywcza.service;

import com.example.dorywcza.exceptions.RecordNotFound;
import com.example.dorywcza.model.user.DTO.UserPublicDTO;
import com.example.dorywcza.model.user.DTO.UserSimplifiedDTO;
import com.example.dorywcza.model.user.DTO.UserUpdateDTO;
import com.example.dorywcza.model.user.Experience;
import com.example.dorywcza.model.user.User;
import com.example.dorywcza.model.user.UserProfile;
import com.example.dorywcza.repository.UserRepository;
import com.example.dorywcza.service.ImageService.ImageService;
import com.example.dorywcza.util.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@PersistenceContext(type = PersistenceContextType.EXTENDED)
public class UserService {

    private final UserRepository userRepository;
    private ImageService imageService;

    @Autowired
    public UserService(UserRepository userRepository, ImageService imageService) {
        this.userRepository = userRepository;
        this.imageService = imageService;
    }

    public List<UserPublicDTO> findAll() {
        return userRepository.findAll().stream().map(this::getUserPublicDTOFrom).collect(Collectors.toList());
    }

    public User findUserById(Long id) {
        return findOrThrowException(id).get();
    }

    private Optional<User> findOrThrowException(Long id) {
        if (!userRepository.existsById(id)){throw new RecordNotFound(HttpStatus.BAD_REQUEST, "user with id " + id );}
        return userRepository.findById(id);
    }

    public Optional<UserSimplifiedDTO> findSimplifiedDTOById(Long id){
        return findOrThrowException(id).map(this::getSimplifiedDTOById);
    }

    public Optional<UserPublicDTO> findPublicDTOById(Long id) {
        return findOrThrowException(id).map(this::getUserPublicDTOFrom);
    }

    public Optional<UserUpdateDTO> findUpdateDTOById(Long id) {
        return findOrThrowException(id).map(this::getUserUpdateDTOFrom);
    }


    /** function that marks user as deleted **/
    public UserUpdateDTO deleteUser(Long id) {
        User userToDelete = findOrThrowException(id).get();
        userToDelete.setDeleted(true);
        User markedAsDeleted = userRepository.save(userToDelete);
        return getUserUpdateDTOFrom(markedAsDeleted);
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

    /** function that update user existing in DB **/
    public void updateUser(User user){
        userRepository.save(user);
    }

    /** function that update user with userProfile (without images) **/
    public UserUpdateDTO updateUser(UserUpdateDTO userUpdateDTO, Long id) {
        User userFromDb = findOrThrowException(id).get();

        User userToUpdate = updateUserFromDb(userFromDb, userUpdateDTO);
        User updatedUser = userRepository.save(userToUpdate);

        return getUserUpdateDTOFrom(updatedUser);
    }

    /** function that updates user from DB with data from UserDTO (without images) **/
    private User updateUserFromDb(User userFromDb, UserUpdateDTO userUpdateDTO){
        userFromDb.setPhoneNumber(userUpdateDTO.getPhoneNumber());
        if (!userFromDb.hasProfile()){
            userFromDb.setUserProfile(new UserProfile(userFromDb));
        }
        UserProfile profile = userFromDb.getUserProfile();
        updateProfileData(userUpdateDTO, profile);

        return userFromDb;
    }

    private void updateProfileData(UserUpdateDTO userUpdateDTO, UserProfile profile) {
        profile.setFirstName(userUpdateDTO.getFirstName());
        profile.setLastName(userUpdateDTO.getLastName());
        profile.setUserName(userUpdateDTO.getUserName());
        Address address = profile.getAddress();
        address.setStreet(userUpdateDTO.getStreet());
        Experience experience = profile.getExperience();
        experience.setDescription(userUpdateDTO.getExperienceDescription());
    }

    public User getUserFromUpdateDTO(UserUpdateDTO userUpdateDTO, Long id){
        User userFromDb = findOrThrowException(id).get();
        return updateUserFromDb(userFromDb,userUpdateDTO);
    }

    private UserPublicDTO getUserPublicDTOFrom(User user){
        return new UserPublicDTO(user);
    }

    private UserUpdateDTO getUserUpdateDTOFrom(User user){
        return new UserUpdateDTO(user);
    }

    public UserSimplifiedDTO getSimplifiedDTOById(User user) {
        return new UserSimplifiedDTO(user);
    }
}
