package tu.project.jobfinder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tu.project.jobfinder.entities.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);
}
