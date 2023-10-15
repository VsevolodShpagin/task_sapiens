package guessing_game.core.database.repository;

import guessing_game.core.database.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

    @Modifying
    @Query("UPDATE GameEntity g SET g.result = :result WHERE g.id = :id")
    void updateGameResult(@Param(value = "id") Long id, @Param(value = "result") boolean result);

}
