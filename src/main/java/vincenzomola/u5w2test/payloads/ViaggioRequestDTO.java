package vincenzomola.u5w2test.payloads;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;


public record ViaggioRequestDTO(
        @NotBlank(message = "Il campo non può essere vuoto")
        @Size(min = 2, max = 30, message = "La destinazione deve avere tra 2 e 30 caratteri")
        @Pattern(regexp = "^[a-zA-Z\\sàèìòùòóÁÉÍÓÚçÇñÑ'-]+$", message = "La destinazione non può contenere numeri o " +
                "caratteri speciali")
        String destinazione,
        @NotNull(message = "Il campo non può essere vuoto")
        @FutureOrPresent(message = "La data non potrà essere passata ma solo presente o futura")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate data) {
}
