package vincenzomola.u5w2test.entities;

import jakarta.persistence.*;
import vincenzomola.u5w2test.enums.StatoViaggio;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Viaggi")
public class Viaggio {

    @Id
    @GeneratedValue
    @Column(name = "id_viaggio")
    private UUID id;
    private String destinazione;
    private LocalDate data;
    @Column(name = "stato_viaggio")
    @Enumerated(EnumType.STRING)
    private StatoViaggio stato;

    protected Viaggio() {
    }

    public Viaggio(String destinazione, LocalDate data) {
        this.destinazione = destinazione;
        this.data = data;
        this.stato = StatoViaggio.IN_PROGRAMMA;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public StatoViaggio getStato() {
        return stato;
    }

    public void setStato(StatoViaggio stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "Viaggio{" +
                "id=" + id +
                ", destinazione='" + destinazione + '\'' +
                ", data=" + data +
                ", stato=" + stato +
                '}';
    }
}
