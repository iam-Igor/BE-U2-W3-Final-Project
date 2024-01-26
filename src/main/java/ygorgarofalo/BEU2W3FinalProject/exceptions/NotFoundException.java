package ygorgarofalo.BEU2W3FinalProject.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(long id) {
        super("Item with id: " + id + " not found");
    }
}
