package guessing_game.core.database.repository;

import guessing_game.core.database.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

    Optional<PlayerEntity> findFirstByName(String name);

    @Query("SELECT p FROM PlayerEntity p ORDER BY ((p.totalWins * 1.0) / p.totalGames) DESC, p.totalGames")
    List<PlayerEntity> findAllOrderByWinRate();

    @Query("SELECT p FROM PlayerEntity p WHERE p.totalGames >= :count ORDER BY ((p.totalWins * 1.0) / p.totalGames) DESC, p.totalGames")
    List<PlayerEntity> findByTotalGamesOrderByWinRate(@Param(value = "count") int minGameCount);

    @Modifying
    @Query("UPDATE PlayerEntity p SET p.totalGames = p.totalGames + 1 WHERE p.id = :id")
    void increaseTotalGames(@Param(value = "id") Long id);

    @Modifying
    @Query("UPDATE PlayerEntity p SET p.totalWins = p.totalWins + 1 WHERE p.id = :id")
    void increaseTotalWins(@Param(value = "id") Long id);

}
