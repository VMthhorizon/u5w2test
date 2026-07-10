package vincenzomola.u5w2test.payloads;

import vincenzomola.u5w2test.enums.StatoViaggio;

import java.time.LocalDate;
import java.util.UUID;

public record ViaggioResponseDTO(UUID id, LocalDate data, StatoViaggio statoViaggio) {
}
