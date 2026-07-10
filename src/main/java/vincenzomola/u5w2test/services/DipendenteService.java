package vincenzomola.u5w2test.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vincenzomola.u5w2test.entities.Dipendente;
import vincenzomola.u5w2test.entities.Viaggio;
import vincenzomola.u5w2test.exceptions.NotFoundException;
import vincenzomola.u5w2test.payloads.DipendenteRequestDTO;
import vincenzomola.u5w2test.repositories.DipendenteRepository;

import java.util.UUID;

@Service
public class DipendenteService {

    private final DipendenteRepository dipendenteRepository;

    public DipendenteService(DipendenteRepository dipendenteRepository) {
        this.dipendenteRepository = dipendenteRepository;
    }

    public Dipendente saveDipendente(DipendenteRequestDTO payload) {
        Dipendente dipendente = new Dipendente(payload.username(), payload.nome(), payload.cognome(), payload.email());
        return this.dipendenteRepository.save(dipendente);
    }

    public Page<Dipendente> getAll(int page, int size) {
        if (page < 0) page = 0;
        if (size > 15) size = 15;
        if (size < 0) size = 1;
        Pageable pageable = PageRequest.of(page, size);
        return this.dipendenteRepository.findAll(pageable);
    }

    public Dipendente findById(UUID id) {
        return this.dipendenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public void deleteById(UUID id) {
        Dipendente dipendente = this.findById(id);
        this.dipendenteRepository.deleteById(id);
    }
}
