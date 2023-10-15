package guessing_game.core.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guess")
public class GuessEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game", nullable = false)
    private GameEntity game;
    @Column(name = "guess", nullable = false)
    private String guess;
    @Column(name = "match_count", nullable = false)
    private Integer matchCount;
    @Column(name = "place_match_count", nullable = false)
    private Integer placeMatchCount;
    @Column(name = "creation_time")
    private LocalDateTime creationTime;

}
