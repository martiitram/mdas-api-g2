package shared;

import java.time.ZonedDateTime;

public class DomainEventMetadata {
    public final String aggregateId;
    public final ZonedDateTime occurredOn;

    public DomainEventMetadata(String aggregateId) {
        this.aggregateId = aggregateId;
        occurredOn = ZonedDateTime.now();
    }
}
