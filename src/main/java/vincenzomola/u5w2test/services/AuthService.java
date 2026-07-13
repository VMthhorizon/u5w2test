package vincenzomola.u5w2test.services;

import org.springframework.stereotype.Service;
import vincenzomola.u5w2test.entities.Dipendente;
import vincenzomola.u5w2test.exceptions.UnauthorizedException;
import vincenzomola.u5w2test.payloads.LoginDTO;
import vincenzomola.u5w2test.security.JWTTools;

@Service
public class AuthService {

    private final DipendenteService dipendenteService;
    private final JWTTools jwtTools;

    public AuthService(DipendenteService dipendenteService, JWTTools jwtTools) {
        this.dipendenteService = dipendenteService;
        this.jwtTools = jwtTools;
    }

    public String checkLogin(LoginDTO body) {
        Dipendente dipendente = this.dipendenteService.findByEmail(body.email());

        if (dipendente.getPassword()
                .equals(body.password())) {
            return this.jwtTools.generateToken(dipendente);
        } else {
            throw new UnauthorizedException("Email o password non corrispondono");
        }
    }

}
