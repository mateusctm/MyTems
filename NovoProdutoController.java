/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loja.controller.produto;

import br.com.loja.model.Produtos;
import br.com.loja.controller.base.Contexto;
import br.com.loja.controller.base.HttpServletBase;
import br.com.loja.dao.ProdutosDao;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mateu
 */
@WebServlet("/novo-produto")
public class NovoProdutoController extends HttpServletBase {

    NovoProdutoController() {
        super("ADM.jsp");
    }

    @Override
    public void processaGet(Contexto contexto) throws ServletException, IOException {

        
    }

    @Override
    public void processaPost(Contexto contexto)throws ServletException, IOException, SQLException {

        HttpServletRequest request = contexto.getRequest();

        Produtos produto = new Produtos();

        produto.setNome(request.getParameter("nome"));
        produto.setDescricao(request.getParameter("descricao"));
       // produto.setIdSerial(Integer.parseInt(request.getParameter("idserial")));
       // produto.setImagem(request.getParameter("imagem"));
        produto.setPreco(new Double(request.getParameter("preco")));

        new ProdutosDao(contexto.getConnection()).insere(produto);

    }
}
