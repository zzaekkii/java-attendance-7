package attendance.domain;

import attendance.exception.ErrorMessage;
import java.util.List;

public class Crews {
    private final List<Crew> crews;

    public Crews(List<Crew> crews) {
        this.crews = crews;
    }

    public Crew getCrewByName(String name) {
        for (Crew crew : crews) {
            if (crew.getName().equals(name)) {
                return crew;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.NICKNAME_NOT_FOUND.getMessage());
    }

    public List<Crew> getCrews() {
        return crews;
    }
}
