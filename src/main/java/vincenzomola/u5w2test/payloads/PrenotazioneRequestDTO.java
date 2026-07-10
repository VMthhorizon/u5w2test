package vincenzomola.u5w2test.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record PrenotazioneRequestDTO(

        @NotBlank(message = "Il campo non può essere vuoto")
        @Size(min = 2, max = 100, message = "La nota deve avere tra 2 e 100 caratteri")
        String note,
        @NotNull(message = "Lo UUID non può essere vuoto")
        UUID viaggioId,
        @NotNull(message = "Lo UUID non può essere vuoto")
        UUID dipendeteId
) {
}
