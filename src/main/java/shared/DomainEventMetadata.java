package shared;

import java.time.ZonedDateTime;

public class DomainEventMetadata {
    private String aggregateId;
    private ZonedDateTime occurredOn = ZonedDateTime.now();
}
