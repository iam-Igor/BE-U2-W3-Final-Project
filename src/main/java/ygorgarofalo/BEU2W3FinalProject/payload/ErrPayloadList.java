package ygorgarofalo.BEU2W3FinalProject.payload;

import java.util.List;

public record ErrPayloadList(
        String message,
        List<String> errorsList) {


}
