package com.gpt5.gpt5.Repository;



import com.gpt5.gpt5.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
