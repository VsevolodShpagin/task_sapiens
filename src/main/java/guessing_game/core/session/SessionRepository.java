package guessing_game.core.session;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class SessionRepository {

    private Map<String, Session> sessions = new HashMap<>();

    public Session getSession(String id) {
        return sessions.get(id);
    }

    public void createSession(String id) {
        sessions.put(id, new Session());
    }

}
