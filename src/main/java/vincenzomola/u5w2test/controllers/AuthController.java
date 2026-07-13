package vincenzomola.u5w2test.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vincenzomola.u5w2test.payloads.LoginDTO;
import vincenzomola.u5w2test.payloads.LogingResponseDTO;
import vincenzomola.u5w2test.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public LogingResponseDTO login(@RequestBody LoginDTO body) {
        return new LogingResponseDTO(this.authService.checkLogin(body));
    }
}
