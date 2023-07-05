package shared;

public abstract class DomainEvent {
    public final String aggregateId;
    public final DomainEventMetadata metadata;

    protected DomainEvent(String aggregateId) {
        this.aggregateId = aggregateId;
        metadata = new DomainEventMetadata(aggregateId);
    }

    public abstract String type();
}
