package service;

import model.User;
import repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(){
        userRepository = new UserRepository();
    }

    public void createUser(User user){
        userRepository.insertUser(user);
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
