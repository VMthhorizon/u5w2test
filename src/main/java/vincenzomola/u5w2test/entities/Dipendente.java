package vincenzomola.u5w2test.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Dipendenti")
public class Dipendente {

    @Id
    @GeneratedValue
    @Column(name = "id_dipendente")
    private UUID id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    @Column(name = "avatar_pic")
    private String avatar;

    protected Dipendente() {
    }

    public Dipendente(String username, String nome, String cognome, String email, String password) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.avatar = "https://ui-avatars.com/api/?name=John+Doe";
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Dipendente{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
