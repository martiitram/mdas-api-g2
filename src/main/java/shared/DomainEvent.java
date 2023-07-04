package shared;

public abstract class DomainEvent {
    private String aggregateId;
    private final DomainEventMetadata metadata = new DomainEventMetadata();

    public abstract String type();
}
