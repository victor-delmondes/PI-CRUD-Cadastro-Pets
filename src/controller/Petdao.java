/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author PISCA
 */

import java.sql.Connection;
import model.Animal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;



public class Petdao extends ConectarDao {
    
    Connection mycon;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<Animal> Lista = new ArrayList<>();
    
    
    public void cadastrarPet (Animal objpet) {
        String sql = "INSERT INTO PETS (pet_nome, pet_especie, pet_raca, pet_cor, pet_detalhes, pet_sit, fk_id_user) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        mycon = new ConectarDao().conectaBD();
        
        try {
            
            pstm = mycon.prepareStatement(sql);
            pstm.setString(1, objpet.getPet_nome());
            pstm.setString(2, objpet.getPet_especie());
            pstm.setString(3, objpet.getPet_raca());
            pstm.setString(4, objpet.getPet_cor());
            pstm.setString(5, objpet.getPet_detalhes());
            pstm.setInt(6, objpet.getPet_sit());
            pstm.setInt(7, objpet.getFk_id_user());
            
            pstm.execute();
            pstm.close();
            
            
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "PetDao - CadastrarPet: " + erro);
        }
        
    }
    
    public ArrayList<Animal> PesquisarPet () {
        String sql = "SELECT * FROM PETS";
        
        mycon = new ConectarDao().conectaBD();
        
        try {
            pstm = mycon.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()) {
                Animal objpets = new Animal();
                objpets.setPet_id(rs.getInt("id_pet"));
                objpets.setPet_nome(rs.getString("pet_nome"));
                objpets.setPet_especie(rs.getString("pet_especie"));
                objpets.setPet_raca(rs.getString("pet_raca"));
                objpets.setPet_cor(rs.getString("pet_cor"));
                objpets.setPet_detalhes(rs.getString("pet_detalhes"));
                objpets.setPet_sit(rs.getInt("pet_sit"));
                objpets.setFk_id_user(rs.getInt("fk_id_user"));
                
                Lista.add(objpets);
            }
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "PetDao - PesquisarPet: " + erro);
        }
        return Lista;
    }
    
        public ArrayList<Animal> PesquisarPetPerdido () {
        String sql = "SELECT * FROM PETS WHERE pet_sit = 2";
        
        mycon = new ConectarDao().conectaBD();
        
        try {
            pstm = mycon.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()) {
                Animal objpets = new Animal();
                objpets.setPet_id(rs.getInt("id_pet"));
                objpets.setPet_nome(rs.getString("pet_nome"));
                objpets.setPet_especie(rs.getString("pet_especie"));
                objpets.setPet_raca(rs.getString("pet_raca"));
                objpets.setPet_cor(rs.getString("pet_cor"));
                objpets.setPet_detalhes(rs.getString("pet_detalhes"));
                objpets.setPet_sit(rs.getInt("pet_sit"));
                
                Lista.add(objpets);
            }
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "PetDao - PesquisarPet: " + erro);
        }
        return Lista;
    }
        
            public ArrayList<Animal> PesquisarPetEncontrado () {
        String sql = "SELECT * FROM PETS WHERE pet_sit = 1";
        
        mycon = new ConectarDao().conectaBD();
        
        try {
            pstm = mycon.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()) {
                Animal objpets = new Animal();
                objpets.setPet_id(rs.getInt("id_pet"));
                objpets.setPet_nome(rs.getString("pet_nome"));
                objpets.setPet_especie(rs.getString("pet_especie"));
                objpets.setPet_raca(rs.getString("pet_raca"));
                objpets.setPet_cor(rs.getString("pet_cor"));
                objpets.setPet_detalhes(rs.getString("pet_detalhes"));
                objpets.setPet_sit(rs.getInt("pet_sit"));
                
                Lista.add(objpets);
            }
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "PetDao - PesquisarPet: " + erro);
        }
        return Lista;
    }
    
    public void alterarPet(Animal objpet) {
        String sql = "UPDATE PETS SET pet_nome = ?, pet_especie = ?, pet_raca = ?, pet_cor = ?, pet_detalhes = ?, pet_sit = ? where id_pet = ?";
        
        mycon = new ConectarDao().conectaBD();
        
        try {
            
            pstm = mycon.prepareStatement(sql);
            pstm.setString(1, objpet.getPet_nome());
            pstm.setString(2, objpet.getPet_especie());
            pstm.setString(3, objpet.getPet_raca());
            pstm.setString(4, objpet.getPet_cor());
            pstm.setString(5, objpet.getPet_detalhes());
            pstm.setInt(6, objpet.getPet_sit());
            pstm.setInt(7, objpet.getPet_id());
            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "PetDao - Alterar: " + erro);
        }
    }
    
    public ResultSet listarSituacao(){
        mycon = new ConectarDao().conectaBD();
        String sql = "SELECT * FROM SITUACAO ORDER BY ds_situacao;";
        
        try {
            pstm = mycon.prepareStatement(sql);
            return pstm.executeQuery();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "PetDao - ListarSituacao: " + erro);
            return null;
        }
    }
    
    public void excluirPet(Animal objpet){
        String sql = "DELETE FROM PETS WHERE id_pet = ?";
        
        mycon = new ConectarDao().conectaBD();
        
        try {
            
            pstm = mycon.prepareStatement(sql);
            
            pstm.setInt(7, objpet.getPet_id());
            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "PetDao - Excluir: " + erro);
        }
    }
    
     public ResultSet pesquisarId(Animal objpet) {
        mycon = new ConectarDao().conectaBD();
        
        try {

            String sql =  "SELECT * FROM PETS WHERE id_pet = ?"; 

            pstm = mycon.prepareStatement(sql);
            pstm.setInt(1, objpet.getPet_id());
                        
            ResultSet rs = pstm.executeQuery();
            return rs;
            
        } catch (SQLException e) {
            return null;
        }
        
    }
    
    
}
