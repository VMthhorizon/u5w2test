package vincenzomola.u5w2test.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vincenzomola.u5w2test.entities.Dipendente;
import vincenzomola.u5w2test.exceptions.ValidationException;
import vincenzomola.u5w2test.payloads.DipendenteRequestDTO;
import vincenzomola.u5w2test.payloads.DipendenteResponseDTO;
import vincenzomola.u5w2test.payloads.LoginDTO;
import vincenzomola.u5w2test.payloads.LogingResponseDTO;
import vincenzomola.u5w2test.services.AuthService;
import vincenzomola.u5w2test.services.DipendenteService;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final DipendenteService dipendenteService;

    public AuthController(AuthService authService, DipendenteService dipendenteService) {
        this.authService = authService;
        this.dipendenteService = dipendenteService;
    }


    @PostMapping("/login")
    public LogingResponseDTO login(@RequestBody LoginDTO body) {
        return new LogingResponseDTO(this.authService.checkLogin(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public DipendenteResponseDTO createDipendente(@RequestBody @Validated DipendenteRequestDTO body,
                                                  BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            validationResult.getFieldErrors()
                    .forEach(fieldError -> System.out.println(fieldError.getDefaultMessage()));

            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValidationException(errorsList);
        }

        Dipendente dipendente = this.dipendenteService.saveDipendente(body);
        return new DipendenteResponseDTO(dipendente.getId(), dipendente.getUsername(), dipendente.getPassword());
    }
}
