package com.preet.store;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;
    NotificationService notificationService;

   public UserService(UserRepository userRepository, NotificationService notificationService) {
       this.userRepository = userRepository;
       this.notificationService = notificationService;
   }

   public void registerUser(User user)
   {

       if(userRepository.save(user)==1)
           notificationService.send("User is saved",user.email);
   }
}
