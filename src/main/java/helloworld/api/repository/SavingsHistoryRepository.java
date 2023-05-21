package helloworld.api.repository;

import helloworld.api.domain.SavingsHistory;
import helloworld.api.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavingsHistoryRepository extends JpaRepository<SavingsHistory, Long> {
    Optional<SavingsHistory> findByUserId(Long userId);
}
