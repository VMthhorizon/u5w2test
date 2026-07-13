package vincenzomola.u5w2test.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vincenzomola.u5w2test.entities.Dipendente;
import vincenzomola.u5w2test.entities.Viaggio;
import vincenzomola.u5w2test.exceptions.ValidationException;
import vincenzomola.u5w2test.payloads.*;
import vincenzomola.u5w2test.services.DipendenteService;
import vincenzomola.u5w2test.services.ViaggioService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    private final DipendenteService dipendenteService;

    public DipendenteController(DipendenteService dipendenteService) {
        this.dipendenteService = dipendenteService;
    }

    @GetMapping
    public Page<Dipendente> getAll(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "100") int size) {
        return this.dipendenteService.getAll(page, size);
    }

    @GetMapping("/{dipendenteId}")
    public DipendenteResponseDTO findById(@PathVariable UUID dipendenteId) {
        Dipendente dipendente = this.dipendenteService.findById(dipendenteId);
        return new DipendenteResponseDTO(dipendente.getId(), dipendente.getUsername(), dipendente.getPassword());
    }

    @PatchMapping("/{dipendenteId}/avatar")
    public void updateAvatar(@PathVariable UUID dipendenteId, @RequestParam("avatar_pic") MultipartFile file) {
        this.dipendenteService.updateAvatar(dipendenteId, file);
    }

    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID dipendenteId) {
        this.dipendenteService.deleteById(dipendenteId);
    }

}