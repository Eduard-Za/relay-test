package constants;

import lombok.Getter;

public enum Type {


    AUDIENCES("audience"),
    ENGAGEMENTS("engagement");

    @Getter
    private final String tab;

    Type(String tab) {
        this.tab = tab;
    }
}
