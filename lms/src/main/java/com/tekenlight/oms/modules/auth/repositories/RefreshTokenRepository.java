package com.tekenlight.oms.modules.auth.repositories;

import com.tekenlight.oms.modules.auth.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByUserId(String userId);

    void deleteByToken(String token);

    boolean existsByTokenAndIsRevokedFalse(String token);
}