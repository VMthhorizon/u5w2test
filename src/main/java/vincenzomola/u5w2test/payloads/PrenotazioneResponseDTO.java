package vincenzomola.u5w2test.payloads;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PrenotazioneResponseDTO(
        @NotNull(message = "Lo UUID non può essere vuoto")
        UUID prenotazioneId,
        @NotNull(message = "Lo UUID non può essere vuoto")
        UUID viaggioId,
        @NotNull(message = "Lo UUID non può essere vuoto")
        UUID dipendeteId) {
}
