/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author mateu
 */
public class DaoProoduto {
    private Connection connection;
    
      public void addUser(Produtos prod) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into users(nome, descricao, preco, idSerial) values (?, ?, ?, ? )");
            // Parameters start with 1
            preparedStatement.setString(1, prod.getNome());
            preparedStatement.setString(2, prod.getDescricao());
            preparedStatement.setDouble(3, prod.getPreco());
            preparedStatement.setInt(3, prod.getIdSerial());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
      
       public void updateUser(Produtos prod) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update users set nome=?, descricao=?, preco=?" +
                            "where idSerial=?");
            // Parameters start with 1
           preparedStatement.setString(1, prod.getNome());
            preparedStatement.setString(2, prod.getDescricao());
            preparedStatement.setDouble(3, prod.getPreco());
            preparedStatement.setInt(3, prod.getIdSerial());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
