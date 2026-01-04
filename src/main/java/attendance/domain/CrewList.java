package attendance.domain;

import java.util.HashSet;
import java.util.Set;

public class CrewList {
    private final HashSet<Crew> crews;

    public CrewList(Set<Crew> crews) {
        this.crews = new HashSet<>(crews);
    }

    public boolean isExist(Crew crew) {
        return crews.contains(crew);
    }
}
