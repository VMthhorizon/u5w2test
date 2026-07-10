package vincenzomola.u5w2test.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vincenzomola.u5w2test.entities.Dipendente;
import vincenzomola.u5w2test.exceptions.BadRequestException;
import vincenzomola.u5w2test.exceptions.NotFoundException;
import vincenzomola.u5w2test.payloads.DipendenteRequestDTO;
import vincenzomola.u5w2test.repositories.DipendenteRepository;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class DipendenteService {

    private final DipendenteRepository dipendenteRepository;
    private final Cloudinary fileUploader;


    public DipendenteService(DipendenteRepository dipendenteRepository, Cloudinary fileUploader) {
        this.dipendenteRepository = dipendenteRepository;
        this.fileUploader = fileUploader;
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

    public void updateAvatar(UUID dipendenteId, MultipartFile file) {
        if (file.getSize() >= 10485760) throw new BadRequestException("File size can't be more than 10MB");
        if (!(Objects.equals(file.getContentType(), "image/jpeg") || Objects.equals(file.getContentType(),
                "image/gif") || Objects.equals(file.getContentType(), "image/png") || Objects.equals(
                file.getContentType(), "image/webp")))
            throw new BadRequestException("File must be an img");

        Dipendente dipendenteFromDb = findById(dipendenteId);

        try {
            Map result = fileUploader.uploader()
                    .upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("secure_url");
            dipendenteFromDb.setAvatar(url);
            this.dipendenteRepository.save(dipendenteFromDb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteById(UUID id) {
        Dipendente dipendente = this.findById(id);
        this.dipendenteRepository.deleteById(id);
    }
}
