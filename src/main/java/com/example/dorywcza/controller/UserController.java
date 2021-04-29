package com.example.dorywcza.controller;

import com.example.dorywcza.exceptions.ErrorDTO;
import com.example.dorywcza.exceptions.ParameterNotValid;
import com.example.dorywcza.model.user.DTO.*;
import com.example.dorywcza.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("Gets list of DTOs ('public' type) for all users.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Gets list of users' DTOs", response = UserPublicDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorDTO.class),
    @ApiResponse(code = 404, message = "The desired resource was not found", response = ErrorDTO.class)})
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserPublicDTO> getUsers(){
        return userService.findAll();
    }


    @Validated
    @ApiOperation("Gets a specified type of user DTO accordingly to type of request parameter. Accepted parameters: [public , update , simplified]")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Gets userDTO of a given type", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Not valid parameter: accepted params: update, public, simplified", response = ErrorDTO.class)})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User's id", example = "1", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "type", value = "DTO's type",example = "update", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<? extends UserDTO> getUser(@PathVariable Long id, @RequestParam String type) {
        List<String> params = new ArrayList<>(List.of("public", "update", "simplified"));
        if (!params.contains(type)){
            throw new ParameterNotValid(HttpStatus.BAD_REQUEST, "Not valid parameter: accepted params: update, public, simplified");
        }
        switch (type){
            case "public":
                return  userService.findPublicDTOById(id);
            case "update":
                return userService.findUpdateDTOById(id);
            default:
                //when "simplified":
                return userService.findSimplifiedDTOById(id);
        }
    }

    @ApiOperation("Creates new user in database")
    @ApiResponse(code = 200, message = "User has been created", response = UserUpdateDTO.class)
    @PostMapping("/users")
    public UserUpdateDTO addUser(@RequestBody UserUpdateDTO userDTO){
        return userService.addUser(userDTO);
    }

    @ApiOperation("Update user and details in user's profile")
    @ApiResponse(code = 200, message = "User details have been updated", response = UserUpdateDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User's id",example = "1", required = true, dataType = "long", paramType = "path"),
    })
    @PutMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserUpdateDTO updateUser(@RequestBody UserUpdateDTO userDTO, @PathVariable Long id){
        return userService.updateUser(userDTO, id);
    }

    @ApiOperation("Set 'deleted' status of user with value true")
    @ApiResponse(code = 200, message = "User has been marked as deleted", response = UserUpdateDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User's id",example = "1", required = true, dataType = "long", paramType = "path"),
    })
    @DeleteMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserUpdateDTO deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

}
