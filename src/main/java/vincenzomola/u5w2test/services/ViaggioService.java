package vincenzomola.u5w2test.services;

import org.springframework.stereotype.Service;
import vincenzomola.u5w2test.entities.Viaggio;
import vincenzomola.u5w2test.payloads.ViaggioRequestDTO;
import vincenzomola.u5w2test.repositories.ViaggioRepository;

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
}
