/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loja.dao;

import br.com.loja.model.Produtos;
import br.com.loja.model.Usuario;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mateu
 */
public class ProdutosDao extends DAO<Produtos, Integer>{
    
   public ProdutosDao(Connection connection){
    super(connection);
        
    }

    @Override
   public void insere(Produtos entidade) throws SQLException {
         PreparedStatement preparedStatement = getConnection()
                    .prepareStatement("insert into produtos(nome, descricao, preco,) values (?, ?, ?,)");
            // Parameters start with 1
            preparedStatement.setString(1, entidade.getNome());
            preparedStatement.setString(2, entidade.getDescricao());
            preparedStatement.setString(3, entidade.getPreco().toString());
           // preparedStatement.setInt(4,entidade.getIdSerial());
           // preparedStatement.setString(5, entidade.getImangem());

            preparedStatement.executeUpdate();
        
    }

    @Override
    void remove(Produtos entidade)throws SQLException {
        PreparedStatement preparedStatement = getConnection()
                    .prepareStatement("delete from produtos where idProdutos=?");
            // Parameters start with 1
            preparedStatement.setInt(1, entidade.getIdSerial());
            preparedStatement.executeUpdate();

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void atualiza(Produtos entidade)throws SQLException {
        PreparedStatement preparedStatement = getConnection()
                    .prepareStatement("update produtos set nome=?, descricao=?, preco=?" +
                            "where idProdutos=?");
            // Parameters start with 1
           preparedStatement.setString(1, entidade.getNome());
            preparedStatement.setString(2, entidade.getDescricao());
            preparedStatement.setString(3, entidade.getPreco().toString());
            preparedStatement.setInt(3, entidade.getIdSerial());
            preparedStatement.executeUpdate();

    }

    @Override
    Produtos procuraPorId(Integer id) throws SQLException {
        Produtos Produto = null;
        PreparedStatement preparedStatement = getConnection().prepareStatement("select * from produtos where idprodutos=?");
            
       ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Produto = new Produtos();
                Produto.setNome(rs.getString("nome"));
                Produto.setDescricao(rs.getString("descricao"));
                //Produto.setPreco(new BigDecimal (rs.getString("preco")));
                Produto.setIdSerial(rs.getInt("idprodutos"));
            }
            return Produto;
    }

    @Override
    List<Produtos> listaTodos() throws SQLException {
        List<Produtos> produtos = new ArrayList();
        PreparedStatement preparedStatement = getConnection().prepareStatement("select * from produtos where ");
            
       ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Produtos produto= new Produtos();
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
               // produto.setPreco(new BigDecimal (rs.getString("preco")));
                produto.setIdSerial(rs.getInt("idprodutos"));
               produtos.add(produto);
            }
            return produtos;
    }
}