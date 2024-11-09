package com.arthurssrichard.safeworkmanager.config;

import com.arthurssrichard.safeworkmanager.models.Usuario;
import com.arthurssrichard.safeworkmanager.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNome(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return User.builder()
                .username(usuario.getNome())
                .password(usuario.getSenha()) // a senha já está codificada com bcrypt
                .roles(usuario.getNivelAcesso().toString()) // pode ser ADMINISTRADOR, por exemplo
                .build();
    }
}

