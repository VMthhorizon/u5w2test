package vincenzomola.u5w2test.entities;

import com.fasterxml.jackson.annotation.JacksonInject;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "data_richiest")
    private LocalDate dataRichiesta;
    private String note;
    @ManyToOne
    @JoinColumn(name = "id_viaggio")
    private Viaggio viaggio;
    @ManyToOne
    @JoinColumn(name = "id_dipendente")
    private Dipendente dipendente;

    protected Prenotazione() {
    }

    public Prenotazione(LocalDate dataRichiesta, String note, Viaggio viaggio, Dipendente dipendente) {
        this.dataRichiesta = dataRichiesta;
        this.note = note;
        this.viaggio = viaggio;
        this.dipendente = dipendente;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", dataRichiesta=" + dataRichiesta +
                ", note='" + note + '\'' +
                ", viaggio=" + viaggio +
                ", dipendente=" + dipendente +
                '}';
    }
}
