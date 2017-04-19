package u26c4.brain.exception;

import lombok.Getter;

public class BrainException extends RuntimeException {

    @Getter
    private String html;

    public BrainException(String message, String html) {
        super(message);
        this.html = html;
    }
}
