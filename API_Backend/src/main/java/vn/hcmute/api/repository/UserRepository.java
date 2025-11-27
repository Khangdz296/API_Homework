package vn.hcmute.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hcmute.api.entity.AppUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
