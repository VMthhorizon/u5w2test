package vincenzomola.u5w2test.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vincenzomola.u5w2test.entities.Viaggio;
import vincenzomola.u5w2test.enums.StatoViaggio;
import vincenzomola.u5w2test.exceptions.FormatoStatoViaggioException;
import vincenzomola.u5w2test.exceptions.NotFoundException;
import vincenzomola.u5w2test.payloads.ViaggioRequestDTO;
import vincenzomola.u5w2test.repositories.ViaggioRepository;

import java.util.UUID;

@Service
public class ViaggioService {

    private final ViaggioRepository viaggioRepository;

    public ViaggioService(ViaggioRepository viaggioRepository) {
        this.viaggioRepository = viaggioRepository;
    }

    public Viaggio saveViaggio(ViaggioRequestDTO payload) {
        Viaggio viaggio = new Viaggio(payload.destinazione(), payload.data());
        return this.viaggioRepository.save(viaggio);
    }

    public Page<Viaggio> getAll(int page, int size) {
        if (page < 0) page = 0;
        if (size > 15) size = 15;
        if (size < 0) size = 1;
        Pageable pageable = PageRequest.of(page, size);
        return this.viaggioRepository.findAll(pageable);
    }

    public Viaggio findById(UUID id) {
        return this.viaggioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Viaggio findAndUpdateStatoById(UUID id, String stato) {
        Viaggio found = findById(id);

        String nuovoStato = stato.trim()
                .toUpperCase()
                .replace(" ", "_");

        try {
            found.setStato(StatoViaggio.valueOf(nuovoStato));
        } catch (IllegalArgumentException ex) {
            throw new FormatoStatoViaggioException("Formato dello stato del viaggio non corretto!");
        }
        return this.viaggioRepository.save(found);

    }

    public void deleteById(UUID id) {
        Viaggio ById = this.findById(id);
        this.viaggioRepository.delete(ById);
    }
}
