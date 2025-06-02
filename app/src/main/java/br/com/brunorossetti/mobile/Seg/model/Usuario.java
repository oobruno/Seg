package br.com.brunorossetti.mobile.Seg.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Usuario {
    private String idUsuario;
    private String idSenha;
    private String idEmail;
    private String idNumero;
    private int id;
    private String nomeUsuario;

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
        } catch (Exception e) {
            // Trate a exceção lançada pelos setters
            e.printStackTrace();
        }
    }

    // Construtor padrão
    public Usuario() throws Exception {
        this.setId(0);
        this.setNome("");
        this.setSenha("");
        this.setEmail("");
        this.setTelefone("");
    }

    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("id", this.id);
            json.put("nome", this.idUsuario);
            json.put("senha", this.idSenha);
            json.put("email", this.idEmail);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }


    // Getters e Setters para usuario
    public String getUsuario() {
        return this.idUsuario;
    }

    public void setUsuario(String usuario) throws Exception {
        if (usuario == null || usuario.length() <= 4) {
            throw new Exception("Nome de usuário inválido! Deve ter mais de 4 caracteres.");
        }
        this.idUsuario = usuario;
    }

    // Getters e Setters para senha
    public String getSenha() {
        return this.idSenha;
    }

    public void setSenha(String senha) throws Exception {
        if (senha == null || senha.length() < 6) {
            throw new Exception("Senha inválida! Deve ter pelo menos 6 caracteres.");
        }
        this.idSenha = senha;
    }

    // Getters e Setters para email
    public String getEmail() {
        return this.idEmail;
    }

    public void setEmail(String email) throws Exception {
        if (email == null || !email.contains("@")) {
            throw new Exception("Email inválido! Deve conter um '@'.");
        }
        this.idEmail = email;
    }

    // Getters e Setters para telefone
    public String getTelefone() {
        return this.idNumero;
    }

    public void setTelefone(String telefone) throws Exception {
        if (telefone == null || telefone.length() < 10) {
            throw new Exception("Telefone inválido! Deve ter pelo menos 10 caracteres.");
        }
        this.idNumero = telefone;
    }

    // Getters e Setters para id
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters e Setters para nome
    public String getNome() {
        return this.idUsuario;
    }

    public void setNome(String nome) {
        this.idUsuario = nome;
    }
}