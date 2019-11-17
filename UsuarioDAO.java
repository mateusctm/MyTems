package br.com.loja.dao;

import br.com.loja.exception.NegocioException;
import br.com.loja.model.TipoUsuario;
import br.com.loja.model.Usuario;
import br.com.loja.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDAO extends DAO<Usuario, String> {

    public UsuarioDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void insere(Usuario entidade) throws SQLException {
        DBUtil.criaPreparedStatement(getConnection(),
                "INSERT INTO usuario (cpf, nome, sobrenome, email, senha, telefone, data_nascimento, tipo, endereco) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                entidade.getCpf(), entidade.getNome(), entidade.getSobrenome(), entidade.getEmail(),
                entidade.getSenha(), entidade.getTelefone(), entidade.getDataNascimento(), entidade.getTipo().getValor(), entidade.getEndereco().getId()).execute();

    }

    @Override
    public void remove(Usuario entidade) throws SQLException {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement("delete from usuario where cpf=?");
        // Parameters start with 1
        preparedStatement.setString(1, entidade.getCpf());
        preparedStatement.executeUpdate();

    }

    @Override
    public void atualiza(Usuario entidade) throws SQLException {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement("update usuario set nome=?, sobrenome=?, email=?, senha=?, telefone=?, data_nascimento=?, tipo=?, endereco=?"
                        + "where cpf=?");
        // Parameters start with 1
        preparedStatement.setString(1, entidade.getNome());
        preparedStatement.setString(2, entidade.getSobrenome());
        preparedStatement.setString(3, entidade.getEmail());
         preparedStatement.setString(4, entidade.getSenha());
        preparedStatement.setString(5, entidade.getTelefone());
        preparedStatement.setObject(6, entidade.getDataNascimento());
         preparedStatement.setObject(7, entidade.getTipo());
          preparedStatement.setObject(8, entidade.getEndereco());
        preparedStatement.executeUpdate();

    }

    @Override
    public Usuario procuraPorId(String s) {
        return null;
    }

    @Override
    public List<Usuario> listaTodos() {
        return null;
    }

    public Usuario procuraPorEmailSenha(String email, String senha) throws SQLException, NegocioException {
        PreparedStatement preparedStatement = DBUtil.criaPreparedStatement(getConnection(), "SELECT * FROM usuario WHERE email = ? AND senha = ?", email, senha);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return criaUsuario(resultSet);
        }
        return null;
    }

    private Usuario criaUsuario(ResultSet resultSet) throws NegocioException, SQLException {
        Usuario usuario = new Usuario();
        usuario.setCpf(resultSet.getString("cpf"));
        usuario.setNome(resultSet.getString("nome"));
        usuario.setSobrenome(resultSet.getString("sobrenome"));
        usuario.setEmail(resultSet.getString("email"));
        usuario.setTelefone(resultSet.getString("telefone"));
        usuario.setTipo(TipoUsuario.get(resultSet.getInt("tipo")));
        return usuario;
    }
}
