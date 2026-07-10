package vincenzomola.u5w2test.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vincenzomola.u5w2test.entities.Viaggio;
import vincenzomola.u5w2test.exceptions.ValidationException;
import vincenzomola.u5w2test.payloads.ViaggioRequestDTO;
import vincenzomola.u5w2test.payloads.ViaggioResponseDTO;
import vincenzomola.u5w2test.payloads.ViaggioStatoRequestDTO;
import vincenzomola.u5w2test.payloads.ViaggioStatoResponseDTO;
import vincenzomola.u5w2test.services.ViaggioService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    private final ViaggioService viaggioService;

    public ViaggioController(ViaggioService viaggioService) {
        this.viaggioService = viaggioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ViaggioResponseDTO createViaggio(@RequestBody @Validated ViaggioRequestDTO body,
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

        Viaggio viaggio = this.viaggioService.saveViaggio(body);
        return new ViaggioResponseDTO(viaggio.getId(), viaggio.getData(), viaggio.getStato());
    }

    @GetMapping
    public Page<Viaggio> getAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size) {
        return this.viaggioService.getAll(page, size);
    }

    @GetMapping("/{viaggioId}")
    public ViaggioResponseDTO findById(@PathVariable UUID viaggioId) {
        Viaggio viaggio = this.viaggioService.findById(viaggioId);
        return new ViaggioResponseDTO(viaggio.getId(), viaggio.getData(), viaggio.getStato());
    }

    @PatchMapping("/{viaggioId}/stato")
    public ViaggioStatoResponseDTO findAndUpdateStatoById(@PathVariable UUID viaggioId,
                                                          @RequestBody @Validated ViaggioStatoRequestDTO body,
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
        Viaggio viaggio = this.viaggioService.findAndUpdateStatoById(viaggioId, body.statoViaggio());
        return new ViaggioStatoResponseDTO(viaggio.getId(), viaggio.getStato());
    }

    @DeleteMapping("/{viaggioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID viaggioId) {
        this.viaggioService.deleteById(viaggioId);
    }

}
