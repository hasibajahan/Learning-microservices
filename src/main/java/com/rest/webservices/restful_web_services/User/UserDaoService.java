package com.rest.webservices.restful_web_services.User;

import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users=new ArrayList<>();

    //to assign the ids of the users dynamically
    private static int userCount=0;

    //initialize it with some users
    static{
        users.add(new User(++userCount, "Rani", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount, "Jahan", LocalDate.now().minusYears(20)));
        users.add(new User(++userCount, "Hasiba", LocalDate.now().minusYears(10)));
    }

    // Get all users

    public List<User> findAll(){
        return users;
    }

    public User findUserById(int id) {
//        for(int i=0;i<users.size();i++){
//            if(users.get(i).getId()==id){
//                return users.get(i);
//            }
//        }
//        return null;

        //using functional programming
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Create a new user
    public User saveUser(User user){
        user.setId(++userCount);
        users.add(user);
        return user;
    }

    //Get a specific user
}
