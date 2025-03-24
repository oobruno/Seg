package br.com.brunorossetti.mobile.Seg.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Usuario {
    private String usuario;
    private String senha;
    private String email;
    private String telefone;

    // Construtor que inicializa o objeto a partir de um JSONObject
    public Usuario(JSONObject jp) {
        try {
            this.setUsuario(jp.getString("usuario"));
            this.setSenha(jp.getString("senha"));
            this.setEmail(jp.getString("email"));
            this.setTelefone(jp.getString("telefone"));
            // Adicione outros campos conforme necessário
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Getter e Setter para usuario
    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) throws Exception {
        if (usuario == null || usuario.length() <= 4) {
            throw new Exception("Nome de usuário inválido! Deve ter mais de 4 caracteres.");
        }
        this.usuario = usuario;
    }

    // Getter e Setter para senha
    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) throws Exception {
        if (senha == null || senha.length() < 6) {
            throw new Exception("Senha inválida! Deve ter pelo menos 6 caracteres.");
        }
        this.senha = senha;
    }

    // Getter e Setter para email
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) throws Exception {
        if (email == null || !email.contains("@")) {
            throw new Exception("Email inválido! Deve conter um '@'.");
        }
        this.email = email;
    }

    // Getter e Setter para telefone
    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) throws Exception {
        if (telefone == null || telefone.length() < 10) {
            throw new Exception("Telefone inválido! Deve ter pelo menos 10 caracteres.");
        }
        this.telefone = telefone;
    }
}