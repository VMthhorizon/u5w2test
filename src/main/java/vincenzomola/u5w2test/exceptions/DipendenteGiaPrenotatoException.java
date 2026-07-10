package vincenzomola.u5w2test.exceptions;

import java.time.LocalDate;
import java.util.UUID;

public class DipendenteGiaPrenotatoException extends RuntimeException {
    public DipendenteGiaPrenotatoException(UUID id, LocalDate data) {
        super("Il dipendente: " + id + " ha gia una prenotazione per il: " + data);
    }
}
