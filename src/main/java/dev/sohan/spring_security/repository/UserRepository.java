package dev.sohan.spring_security.repository;


import dev.sohan.spring_security.entities.Role;
import dev.sohan.spring_security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   public Optional<User> findByEmail(String email);

    public User findByRole(Role role);
}
