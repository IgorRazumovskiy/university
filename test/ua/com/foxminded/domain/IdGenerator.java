package ua.com.foxminded.domain;

public class IdGenerator {
    private Integer lastId;

    public IdGenerator() {
        this.lastId = 0;
    }

    public Integer getNextId() {
        return ++this.lastId;
    }
}
