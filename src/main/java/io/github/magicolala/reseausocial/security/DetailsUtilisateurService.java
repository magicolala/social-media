package io.github.magicolala.reseausocial.security;

import io.github.magicolala.reseausocial.entity.User;
import io.github.magicolala.reseausocial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetailsUtilisateurService implements UserDetailsService {

    private final UserRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User utilisateur = utilisateurRepository.findByEmail(username);

        if (utilisateur != null) {
            return new DetailsUtilisateur(utilisateur);
        }

        throw new UsernameNotFoundException(username + " pas dans la BD");
    }

}
