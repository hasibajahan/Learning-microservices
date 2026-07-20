package com.rest.webservices.restful_web_services.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    //Autowire the UserDaoService(it is managed by spring, so we can do it.)
    private UserDaoService service;

    //create a constructor (needed for constructor injection)
    public UserResource(UserDaoService service) {
        this.service = service;
    }

    //Retrieve all users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    //Retrieve a specific user
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        return service.findUserById(id);
    }

//    @PostMapping("/users")
//    public void createUser(@RequestBody User user){
//        service.saveUser(user);
//    }

    //return proper HTTP status code and location
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser=service.saveUser(user);
//      return a URI - /users/{id}, replace id with user.getId().
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
