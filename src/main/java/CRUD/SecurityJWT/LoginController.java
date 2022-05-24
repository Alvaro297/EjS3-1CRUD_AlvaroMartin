package CRUD.SecurityJWT;

import CRUD.Controller.dto.PersonaOutputDTO;
import CRUD.Controller.servicios.PersonaI;
import CRUD.SecurityJWT.Errores.NotFoundException;
import CRUD.SecurityJWT.Errores.UnprocesableException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LoginController {

    @Autowired
    PersonaI personaI;

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String pwd)
            throws NotFoundException, UnprocesableException
    {
        List<PersonaOutputDTO> lista = personaI.findByName(username);
        if(lista.size() == 0) throw new NotFoundException("Usuario " + username + "no obtenido");
        if (lista.size() > 1) throw new UnprocesableException("Usuario ya existente");
        PersonaOutputDTO personaOutputDTO = lista.get(0);
        if(!pwd.equals(personaOutputDTO.getPassword())) throw new UnprocesableException("Password erroneo");
        String rol =personaOutputDTO.getAdmin();
        return new ResponseEntity<>(getJWTToken(username,rol), HttpStatus.OK);

    }
    private String getJWTToken(String username, String rol) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(rol);

        String token = Jwts
                .builder()
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 300000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;

    }
}