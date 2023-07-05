package shared;

import java.time.ZonedDateTime;

public abstract class DomainEvent {
    public final String aggregateId;
    public final String occurredOn;
    protected DomainEvent(String aggregateId) {
        this.aggregateId = aggregateId;
        occurredOn = ZonedDateTime.now().toString();
    }
    protected DomainEvent(String aggregateId, String occurredOn) {
        this.aggregateId = aggregateId;
        this.occurredOn = occurredOn;
    }

    public abstract String type();
}
