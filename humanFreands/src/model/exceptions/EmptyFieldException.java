package model.exceptions;

public class EmptyFieldException extends RuntimeException {

    private final int count;

    public EmptyFieldException(String m, int count) {
        super(m);
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
