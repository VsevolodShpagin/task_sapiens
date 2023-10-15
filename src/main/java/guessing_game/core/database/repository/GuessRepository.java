package guessing_game.core.database.repository;

import guessing_game.core.database.entity.GameEntity;
import guessing_game.core.database.entity.GuessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuessRepository extends JpaRepository<GuessEntity, Long> {

    @Query("SELECT g FROM GuessEntity g WHERE g.game = :game ORDER BY g.creationTime DESC")
    List<GuessEntity> findByGame(@Param(value = "game") GameEntity game);

}
