package vincenzomola.u5w2test.payloads;

import vincenzomola.u5w2test.enums.StatoViaggio;

import java.util.UUID;

public record ViaggioStatoResponseDTO(UUID id, StatoViaggio statoViaggio) {
}
