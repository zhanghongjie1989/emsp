package com.volvo.emsp.reposity;



import com.volvo.emsp.domain.Account;
import com.volvo.emsp.reposity.entity.AccountEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByEmail(String email);

    List<AccountEntity> findByUpdatedAt(LocalDateTime lastUpdated, Pageable pageable);
}