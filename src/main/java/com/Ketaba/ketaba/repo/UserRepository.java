package com.Ketaba.ketaba.repo;



import com.Ketaba.ketaba.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
