package vincenzomola.u5w2test.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DipendenteRequestDTO(
        @NotBlank(message = "Il campo non può essere vuoto")
        @Size(min = 2, max = 30, message = "Lo username deve avere tra 2 e 30 caratteri")
        String username,
        @NotBlank(message = "Il campo non può essere vuoto")
        @Size(min = 2, max = 30, message = "Il nome deve avere tra 2 e 30 caratteri")
        @Pattern(regexp = "^[a-zA-Z\\sàèìòùòóÁÉÍÓÚçÇñÑ'-]+$", message = "Il nome non può contenere numeri o " +
                "caratteri speciali")
        String nome,
        @NotBlank(message = "Il campo non può essere vuoto")
        @Size(min = 2, max = 30, message = "Il cognome deve avere tra 2 e 30 caratteri")
        @Pattern(regexp = "^[a-zA-Z\\sàèìòùòóÁÉÍÓÚçÇñÑ'-]+$", message = "Il cognome non può contenere numeri o " +
                "caratteri speciali")
        String cognome,
        @NotBlank(message = "Il campo non può essere vuoto")
        @Size(min = 2, max = 50, message = "La mail deve avere tra 2 e 50 caratteri")
        @Email(message = "Formato per la mail non valido")
        String email,
        String password) {
}
