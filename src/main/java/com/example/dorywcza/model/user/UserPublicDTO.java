package com.example.dorywcza.model.user;

import com.example.dorywcza.util.Image;
import com.example.dorywcza.util.ImageBox;
import com.example.dorywcza.util.ImageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserPublicDTO extends UserGeneralDTO{

    private String experienceDescription;
    private List<ImageDTO> pictures;
    private ImageDTO avatar;



    public UserPublicDTO(User user){
        super(user);
        if (user.hasProfile()){
            loadUserProfile(user.getUserProfile());
        }
    }

    private void loadUserProfile(UserProfile userProfile) {
        loadExperience(userProfile);
        loadAvatar(userProfile);
    }

    private void loadExperience(UserProfile userProfile) {
        if (userProfile.hasExperience()) {
            Experience experience = userProfile.getExperience();
            this.experienceDescription = experience.getDescription();
            loadImageBox(experience);
        }
    }

    private void loadImageBox(Experience experience) {
        if (experience.hasImageBox()){
            loadImages(experience.getImageBox());
        }
    }

    private void loadAvatar(UserProfile userProfile) {
        if (userProfile.hasAvatar()){
            Image avatar = userProfile.getAvatar();
            if (avatar.hasFile()){
                this.avatar = new ImageDTO(avatar);
            }
        }
    }

    private void loadImages(ImageBox imageBox) {
        if (imageBox.hasPicturesInImages() && imageBox.isNotEmpty()){
            this.pictures = imageBox.getImages()
                    .stream()
                    .filter(Image::hasFile)
                    .map(image -> new ImageDTO(image))
                    .collect(Collectors.toList());
        }
    }

    public UserPublicDTO(String email) {
        super.setEmail(email);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserPublicDTO that = (UserPublicDTO) o;
        return Objects.equals(experienceDescription, that.experienceDescription) && Objects.equals(pictures, that.pictures) && Objects.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experienceDescription, pictures, avatar);
    }
}
