package bek.uz.securityjwt.repositories;

import bek.uz.securityjwt.entity.Role;
import bek.uz.securityjwt.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
