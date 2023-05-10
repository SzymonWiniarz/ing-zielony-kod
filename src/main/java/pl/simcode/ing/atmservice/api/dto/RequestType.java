package pl.simcode.ing.atmservice.api.dto;

public enum RequestType {
    FAILURE_RESTART(1), PRIORITY(2), SIGNAL_LOW(3), STANDARD(4);

    private final int priority;

    RequestType(int priority) {
        this.priority = priority;
    }

    public int priority() {
        return priority;
    }

}
