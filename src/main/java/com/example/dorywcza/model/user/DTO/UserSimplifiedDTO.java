package com.example.dorywcza.model.user.DTO;

import com.example.dorywcza.model.user.User;
import com.example.dorywcza.util.Image;
import com.example.dorywcza.util.ImageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserSimplifiedDTO extends UserDTO{
    private Long id;
    private String email;
    private int overallRating;
    private String userName;
    private ImageDTO avatar;


    public UserSimplifiedDTO(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.overallRating = user.getOverallRating();
        if (user.hasProfile()){
            loadAvatar(user);
        }
    }

    private void loadAvatar(User user) {
        if(user.getUserProfile().hasAvatar()){
            Image avatar = user.getUserProfile().getAvatar();
            if (avatar.hasFile()){
                this.avatar = new ImageDTO(avatar);
            }
        }
    }
}
