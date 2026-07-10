package vincenzomola.u5w2test.payloads;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public record ErrorsDTO(String message, LocalDateTime timestamp, List<String> errorsList) {

    public ErrorsDTO(String message, LocalDateTime timestamp) {
        this(message, timestamp, Collections.emptyList());
    }
}
