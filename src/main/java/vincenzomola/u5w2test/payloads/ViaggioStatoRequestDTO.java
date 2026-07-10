package vincenzomola.u5w2test.payloads;

import jakarta.validation.constraints.NotBlank;

public record ViaggioStatoRequestDTO(
        @NotBlank(message = "Questo campo non può essere null")
        String statoViaggio
) {
}
