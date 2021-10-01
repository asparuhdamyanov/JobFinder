package tu.project.jobfinder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tu.project.jobfinder.entities.Ad;

import java.util.Optional;

public interface AdRepository extends JpaRepository<Ad, Long> {

    Optional<Ad> findAdByTitle(String title);
}
