package io.github.magicolala.reseausocial.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DBInit implements CommandLineRunner {

    private final PasswordEncoder       passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
//        utilisateurRepository.save(new Utilisateur("admin", passwordEncoder.encode("123"), RoleUtilisateur.ADMIN));
//        utilisateurRepository.save(new Utilisateur("user", passwordEncoder.encode("123"), RoleUtilisateur.USER));
    }

}
