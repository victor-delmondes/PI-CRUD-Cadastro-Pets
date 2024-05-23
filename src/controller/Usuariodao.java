/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.mysql.cj.jdbc.result.ResultSetFactory;
import model.User;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author PISCA
 */
public class Usuariodao extends ConectarDao {
    
    Connection mycon;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<User> Lista = new ArrayList<>();
    
    public ResultSet autenticar(User objuser) {
        mycon = new ConectarDao().conectaBD();
        
        try {

            String sql =  "SELECT * FROM USUARIOS WHERE user_email = ? and user_pass = ?"; 
            
            pstm = mycon.prepareStatement(sql);
            pstm.setString(1, objuser.getEmail());
            pstm.setString(2, objuser.getPass());
            
            
            
            ResultSet rs = pstm.executeQuery();
            return rs;
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "UsuarioDao: " + e);
            return null;
        }
        
    }
    
    public ResultSet pesquisarId(User objuser) {
        mycon = new ConectarDao().conectaBD();
        
        try {

            String sql =  "SELECT * FROM USUARIOS WHERE id_user = ?"; 

            pstm = mycon.prepareStatement(sql);
            pstm.setInt(1, objuser.getId());
                        
            ResultSet rs = pstm.executeQuery();
            return rs;
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "UsuarioDao: " + e);
            return null;
        }
        
    }
    
    public void cadastrarUsuario (User objuser) {
        String sql = "INSERT INTO USUARIOS (user_nome, user_email, user_telefone, user_pass) VALUES (?, ?, ?, ?)";
        
        mycon = new ConectarDao().conectaBD();
        
        try {
            
            pstm = mycon.prepareStatement(sql);
            pstm.setString(1, objuser.getNome());
            pstm.setString(2, objuser.getEmail());
            pstm.setString(3, objuser.getTelefone());
            pstm.setString(4, objuser.getPass());
            
            pstm.execute();
            pstm.close();
            
            
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDao" + erro);
        }
        
    }
    
    public ArrayList<User> PesquisarUsuario () {
        String sql = "SELECT * FROM USUARIOS";
        
        mycon = new ConectarDao().conectaBD();
        
        try {
            pstm = mycon.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()) {
                User objUser = new User();
                objUser.setId(rs.getInt("id_user"));
                objUser.setNome(rs.getString("user_nome"));
                objUser.setEmail(rs.getString("user_email"));
                objUser.setTelefone(rs.getString("user_telefone"));
                objUser.setCod_acesso(rs.getInt("cod_acesso"));
                
                
                Lista.add(objUser);
            }
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDao - Pesquisar: " + erro);
        }
        return Lista;
    }
    
    public void alterarUsuario(User objuser) {
        String sql = "UPDATE USUARIOS SET user_nome = ?, user_email = ?, user_telefone = ?, cod_acesso = ? where id_user = ?";
        
        mycon = new ConectarDao().conectaBD();
        
        try {
            
            pstm = mycon.prepareStatement(sql);
            pstm.setString(1, objuser.getNome());
            pstm.setString(2, objuser.getEmail());
            pstm.setString(3, objuser.getTelefone());
            pstm.setInt(4, objuser.getCod_acesso());
            pstm.setInt(5, objuser.getId());
            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDao - Alterar: " + erro);
        }
    }
    
     public void alterarUsuario2(User objuser) {
        String sql = "UPDATE USUARIOS SET user_nome = ?, user_email = ?, user_telefone = ?, cod_acesso = ?, user_pass = ? where id_user = ?";
        
        mycon = new ConectarDao().conectaBD();
        
        try {
            
            pstm = mycon.prepareStatement(sql);
            pstm.setString(1, objuser.getNome());
            pstm.setString(2, objuser.getEmail());
            pstm.setString(3, objuser.getTelefone());
            pstm.setInt(4, objuser.getCod_acesso());
            pstm.setString(5, objuser.getPass());
            pstm.setInt(6, objuser.getId());
            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDao - Alterar: " + erro);
        }
    }
    
    public ResultSet listarAcesso(){
        mycon = new ConectarDao().conectaBD();
        String sql = "SELECT * FROM NIVEIS ORDER BY ds_acesso;";
        
        try {
            pstm = mycon.prepareStatement(sql);
            return pstm.executeQuery();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDao - ListarCargo: " + erro);
            return null;
        }
    }
    
    public void excluirUsuario(User objuser){
        String sql = "DELETE FROM USUARIOS WHERE id_user = ?";
        
        mycon = new ConectarDao().conectaBD();
        
        try {
            
            pstm = mycon.prepareStatement(sql);
           
            pstm.setInt(1, objuser.getId());
            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDao - Excluir: " + erro);
        }
    }
    
    
    
    

    public Usuariodao() {
        super(); // Executa construtor da classe mãe
    }

    /*public void alterar(User obj) {
        sql = "UPDATE USUARIOS SET nome= ?, email= ?, telefone= ?, senha =  ?" ;

        try {
            ps = mycon.prepareStatement(sql);
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getEmail());
            ps.setString(3, obj.getTelefone());
            ps.setString(4, obj.getPass());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Registro Alterado com Sucesso!");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar usuário!" + err.getMessage());
        }
    }*/

    
    /*
    
    */
    //public void incluir(User obj) {

        /*Cria o comando SQL com 5 parâmetros ?, ?, ?, ?, ? */
        //sql = "INSERT INTO USUARIOS VALUES (?, ?, ?, ?)";

        /*try {

            // Envia o SQL para dentro da conexão
            ps = mycon.prepareStatement(sql);

            // Configura a posição de cada parâmetro começando pelo primeiro
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getEmail());
            ps.setString(3, obj.getTelefone());
            ps.setString(4, obj.getPass());
            ps.execute(); // Finalmente executa o comando sql dentro de ps
            ps.close();// finaliza o comando de execução do sql

            JOptionPane.showMessageDialog(null, "Registro Incluído com Sucesso!");

        }*/ /*catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir usuário!" + err.getMessage());
        }*/
    //}

// Resultset armazena um array de registros   
    /*public ResultSet validarLogin(String login, String senha) {
        // cria o comando sql para executar dentro do MySQL
        sql = "FROM USUARIOS WHERE ucase(email) = ucase('" + login + "') "
               + "and senha = ucase('" + senha + "')";

        try {   // prepara a execução do comando sql dentro da conexão mycon
            ps = mycon.prepareStatement(sql);
            return ps.executeQuery(); // retorna a execução do comando sql 

            // toda consulta em banco de dados deve ter um tratamento de erros
            // A classe SQLException faz o tratamento de erros
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar usuário!" + err.getMessage());
            return null;
            // se haver erro armazena null na ResultSet
        }
    }*/
    


    /*public ResultSet buscartodos() {
        // o comando select traz um conjunto de registros
        // e armazena dentro de um ResultSet
        sql = "SELECT * FROM USUARIOS ORDER BY nome ";

        try {   // armazena o comando sql dentro da conexão mycon
            ps = mycon.prepareStatement(sql);

            // retorna um ResultSet com os registros selecionados
            return ps.executeQuery();

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar usuário!" + err.getMessage());
            // Se haver erros exibe a mensagem e returno nulo dentro 
            // do ResultSet
            return null;
        }
    }*/

    /*public ResultSet buscar(User obj) {
        // para buscar um registro especifico cria-se um sql com um parãmetro chave
        // no caso a busca está sendo feita pelo cpf do usuario
        sql = "SELECT * FROM USUARIOS WHERE Nome = ?";

        try {   // liga o sql com a conexão atraveś do objeto ps
            ps = mycon.prepareStatement(sql);

            // configura o único parametro existente
            ps.setString(1, obj.getNome());

            // retorna o registro selecionado
            return ps.executeQuery();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar usuário!" + err.getMessage());
            return null;
        }
    }*/

    /*public void excluir(String nome) {

        // configura o comando sql de exclusão delete por ID
        sql = "DELETE FROM USUARIOS WHERE ID = '" + nome + "'";

        try { // envia o comando sql para dentro da conexão através de ps
            ps = mycon.prepareStatement(sql);
            // executa o comando delete dentro do mysql
            ps.execute();

            ps.close(); // fecha o objeto usado para executar o comando sql

            JOptionPane.showMessageDialog(null, "Registro Excluido com Sucesso!");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir usuário!" + err.getMessage());
        }
    }*/

}