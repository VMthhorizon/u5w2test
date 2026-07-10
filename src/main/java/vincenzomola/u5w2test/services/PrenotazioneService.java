package vincenzomola.u5w2test.services;

import org.springframework.stereotype.Service;
import vincenzomola.u5w2test.entities.Dipendente;
import vincenzomola.u5w2test.entities.Prenotazione;
import vincenzomola.u5w2test.entities.Viaggio;
import vincenzomola.u5w2test.exceptions.DipendenteGiaPrenotatoException;
import vincenzomola.u5w2test.exceptions.NotFoundException;
import vincenzomola.u5w2test.payloads.PrenotazioneRequestDTO;
import vincenzomola.u5w2test.payloads.ViaggioRequestDTO;
import vincenzomola.u5w2test.repositories.DipendenteRepository;
import vincenzomola.u5w2test.repositories.PrenotazioneRepository;
import vincenzomola.u5w2test.repositories.ViaggioRepository;

import java.util.UUID;

@Service
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final ViaggioRepository viaggioRepository;
    private final DipendenteRepository dipendenteRepository;

    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository, ViaggioRepository viaggioRepository,
                               DipendenteRepository dipendenteRepository) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.viaggioRepository = viaggioRepository;
        this.dipendenteRepository = dipendenteRepository;
    }


    public Prenotazione savePrenotazione(PrenotazioneRequestDTO payload, UUID viaggioId, UUID dipendenteId) {
        Viaggio viaggio = this.viaggioRepository.findById(viaggioId)
                .orElseThrow(() -> new NotFoundException(viaggioId));
        Dipendente dipendente = this.dipendenteRepository.findById(dipendenteId)
                .orElseThrow(() -> new NotFoundException(dipendenteId));

        boolean giaPrenotato = this.prenotazioneRepository
                .existsByDipendenteIdAndViaggioData(dipendente.getId(), viaggio.getData());
        if (giaPrenotato) throw new DipendenteGiaPrenotatoException(dipendenteId, viaggio.getData());

        Prenotazione prenotazione = new Prenotazione(payload.note(), viaggio, dipendente);
        return this.prenotazioneRepository.save(prenotazione);
    }
}
