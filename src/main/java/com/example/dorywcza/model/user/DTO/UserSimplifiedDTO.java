package com.example.dorywcza.model.user.DTO;

import com.example.dorywcza.model.user.User;
import com.example.dorywcza.util.Image;
import com.example.dorywcza.util.ImageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "DTO with basic details of user plus url to avatar image")
@Data
@NoArgsConstructor
public class UserSimplifiedDTO extends UserDTO{
    @ApiModelProperty(required = true, value = "Identifier")
    private Long id;
    @ApiModelProperty(required = true, value = "Email of the user")
    private String email;
    @ApiModelProperty(required = true, value ="Rating of the user - gained when other users give 'vote-up' mark for him")
    private int overallRating;
    @ApiModelProperty(required = true, value = "Name used to identify the user")
    private String userName;
    @ApiModelProperty(value = "Container for an image used as avatar of the user")
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
