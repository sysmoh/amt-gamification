package ch.heigvd.amt.gamification.Util;

public class FlashMessage {

    private String type;

    private String message;

    FlashMessage(String type, String message) {

        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
