package vincenzomola.u5w2test.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vincenzomola.u5w2test.entities.Prenotazione;
import vincenzomola.u5w2test.entities.Viaggio;
import vincenzomola.u5w2test.exceptions.ValidationException;
import vincenzomola.u5w2test.payloads.PrenotazioneRequestDTO;
import vincenzomola.u5w2test.payloads.PrenotazioneResponseDTO;
import vincenzomola.u5w2test.payloads.ViaggioRequestDTO;
import vincenzomola.u5w2test.payloads.ViaggioResponseDTO;
import vincenzomola.u5w2test.services.PrenotazioneService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;

    public PrenotazioneController(PrenotazioneService prenotazioneService) {
        this.prenotazioneService = prenotazioneService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrenotazioneResponseDTO createViaggio(@RequestBody @Validated PrenotazioneRequestDTO body,
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

        Prenotazione prenotazione = this.prenotazioneService.savePrenotazione(body, body.viaggioId(),
                body.dipendenteId());
        return new PrenotazioneResponseDTO(prenotazione.getId(), prenotazione.getViaggio()
                .getId(), prenotazione.getDipendente()
                .getId());
    }

    @GetMapping
    public Page<Prenotazione> getAll(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "1") int size) {
        return this.prenotazioneService.getAll(page, size);
    }

    @GetMapping("/{prenotazioneId}")
    public PrenotazioneResponseDTO findById(@PathVariable UUID prenotazioneId) {
        Prenotazione prenotazione = this.prenotazioneService.findById(prenotazioneId);
        return new PrenotazioneResponseDTO(prenotazione.getId(), prenotazione.getViaggio()
                .getId(), prenotazione.getDipendente()
                .getId());
    }

    @DeleteMapping("/{prenotazioneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID prenotazioneId) {
        this.prenotazioneService.delete(prenotazioneId);
    }

}
