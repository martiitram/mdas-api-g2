package shared;

import java.util.ArrayList;

public interface EventPublisher {
    void publish(DomainEvent event);

    void publish(ArrayList<DomainEvent> events);
}
