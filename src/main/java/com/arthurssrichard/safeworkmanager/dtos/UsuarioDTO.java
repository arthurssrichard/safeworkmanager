package com.arthurssrichard.safeworkmanager.dtos;

import com.arthurssrichard.safeworkmanager.models.NivelAcesso;
import com.arthurssrichard.safeworkmanager.models.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioDTO {
    @NotBlank
    private String nome;

    @NotBlank
    private String senha;

    @NotNull // Use @NotNull para tipos enum
    private NivelAcesso nivelAcesso;

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public NivelAcesso getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(NivelAcesso nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public Usuario toUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNome(this.nome);
        usuario.setNivelAcesso(this.nivelAcesso);
        return usuario;
    }

    public Usuario toUsuario(Usuario usuario) {
        usuario.setNome(this.nome);
        usuario.setNivelAcesso(this.nivelAcesso);
        return usuario;
    }


    public boolean fromUsuario(Usuario usuario){
        if(usuario.getNivelAcesso() != null && usuario.getNome() != null){
            this.nome = usuario.getNome();
            this.nivelAcesso = usuario.getNivelAcesso();

            return true;
        }
        return false;
    }
}
