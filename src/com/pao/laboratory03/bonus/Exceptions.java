package com.pao.laboratory03.bonus;

class DuplicateTaskException extends RuntimeException {
    public DuplicateTaskException(String message) {
        super(message);
    }
}

class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}

class InvalidTransitionException extends RuntimeException {
    private final Status fromStatus;
    private final Status toStatus;

    public InvalidTransitionException(Status fromStatus, Status toStatus) {
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
    }

    @Override
    public String getMessage() {
        return "Nu se poate trece din " + fromStatus + " in " + toStatus;
    }
}