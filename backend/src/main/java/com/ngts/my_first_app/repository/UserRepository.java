package com.ngts.my_first_app.repository;

import com.ngts.my_first_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// ! JpaRepository<EntityType, PrimaryKeyType>
// By extending JpaRepository, we get these methods*:
//        save()
//        findById()
//        findAll()
//        deleteById()
//        count()
//        * And many more...

public interface UserRepository extends JpaRepository<User, Integer> {
}
