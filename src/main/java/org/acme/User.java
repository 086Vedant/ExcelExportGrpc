package org.acme;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

//represent the User entity In MongoDB
@MongoEntity(collection = "datas")
public class User extends PanacheMongoEntity {
    
    public String name;
    public String phone;
    public String city;
    
    public User () {}
    public User(String name , String phone , String city )
    {
        this.name = name;
        this.phone = phone;
        this.city = city;
    }   
}
