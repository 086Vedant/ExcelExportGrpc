package org.acme;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

//Service class for managing users. Handling business logic related to user management.
@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepo;
 
    public List<User> getAllUsers() {
        return userRepo.listAll();
    }    
}
