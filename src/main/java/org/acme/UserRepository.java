package org.acme;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

//repository interface to interact with the User entity
@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<User> {

}
