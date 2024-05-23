/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.sql.ResultSet;

public class ConectarDao {

    public Connection conectaBD() {
        Connection mycon = null;

        try {
            String url = "jdbc:mysql://localhost:3306/petlove?user=root&password=";
            mycon = DriverManager.getConnection(url);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ConectarDao: " + e.getMessage());
        }
        return mycon;
    }

    public void verificaOuCriaBD() {
        Connection mycon = null;
        Statement stmt = null;

        try {
            String url = "jdbc:mysql://localhost:3306/?user=root&password=";
            mycon = DriverManager.getConnection(url);
            stmt = mycon.createStatement();

            // Verifica se o banco de dados "petlove" existe
            ResultSet rs = stmt.executeQuery("SHOW DATABASES LIKE 'petlove'");
            if (!rs.next()) {
                // Se não existir, cria o banco de dados
                stmt.executeUpdate("CREATE DATABASE petlove");
                JOptionPane.showMessageDialog(null, "Banco de dados 'petlove' criado com sucesso!");

                // Seleciona o banco de dados recém-criado
                stmt.executeUpdate("USE petlove");

                // Executa o script SQL para configurar as tabelas e dados iniciais
                String[] script = {
                    "SET SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO'",
                    "SET time_zone = '+00:00'",
                    "CREATE TABLE IF NOT EXISTS `niveis` ("
                    + "`id_acesso` int(11) NOT NULL,"
                    + "`ds_acesso` varchar(20) NOT NULL"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci",
                    "INSERT INTO `niveis` (`id_acesso`, `ds_acesso`) VALUES (1, 'administrador'), (2, 'usuário')",
                    "CREATE TABLE IF NOT EXISTS `pets` ("
                    + "`id_pet` int(10) NOT NULL,"
                    + "`pet_nome` varchar(50) NOT NULL,"
                    + "`pet_especie` varchar(50) NOT NULL,"
                    + "`pet_raca` varchar(50) NOT NULL,"
                    + "`pet_cor` varchar(50) NOT NULL,"
                    + "`pet_detalhes` varchar(1000) NOT NULL,"
                    + "`pet_sit` int(10) NOT NULL,"
                    + "`fk_id_user` int(10) NOT NULL"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci",
                    "CREATE TABLE IF NOT EXISTS `situacao` ("
                    + "`id_situacao` int(11) NOT NULL,"
                    + "`ds_situacao` varchar(50) NOT NULL"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci",
                    "INSERT INTO `situacao` (`id_situacao`, `ds_situacao`) VALUES (1, 'Encontrado'), (2, 'Perdido')",
                    "CREATE TABLE IF NOT EXISTS `usuarios` ("
                    + "`id_user` int(10) NOT NULL,"
                    + "`user_nome` varchar(50) NOT NULL,"
                    + "`user_email` varchar(50) NOT NULL,"
                    + "`user_telefone` varchar(50) NOT NULL,"
                    + "`user_pass` varchar(50) NOT NULL,"
                    + "`cod_acesso` int(10) NOT NULL DEFAULT 2"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci",
                    "INSERT INTO `usuarios` (`id_user`, `user_nome`, `user_email`, `user_telefone`, `user_pass`, `cod_acesso`) VALUES "
                    + "(1, 'admin', 'admin', '0000', 'admin', 1)",
                    "ALTER TABLE `niveis` ADD PRIMARY KEY (`id_acesso`)",
                    "ALTER TABLE `pets` ADD PRIMARY KEY (`id_pet`), ADD KEY `fk_situacao` (`pet_sit`), ADD KEY `fk_pets_user` (`fk_id_user`)",
                    "ALTER TABLE `situacao` ADD PRIMARY KEY (`id_situacao`)",
                    "ALTER TABLE `usuarios` ADD PRIMARY KEY (`id_user`), ADD UNIQUE KEY `user_email` (`user_email`), ADD KEY `fk_niveis` (`cod_acesso`)",
                    "ALTER TABLE `niveis` MODIFY `id_acesso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4",
                    "ALTER TABLE `pets` MODIFY `id_pet` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17",
                    "ALTER TABLE `situacao` MODIFY `id_situacao` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3",
                    "ALTER TABLE `usuarios` MODIFY `id_user` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12",
                    "ALTER TABLE `pets` ADD CONSTRAINT `fk_pets_user` FOREIGN KEY (`fk_id_user`) REFERENCES `usuarios` (`id_user`), "
                    + "ADD CONSTRAINT `fk_situacao` FOREIGN KEY (`pet_sit`) REFERENCES `situacao` (`id_situacao`)",
                    "ALTER TABLE `usuarios` ADD CONSTRAINT `fk_niveis` FOREIGN KEY (`cod_acesso`) REFERENCES `niveis` (`id_acesso`)",
                    "COMMIT"
                };

                for (String command : script) {
                    stmt.executeUpdate(command);
                }
                JOptionPane.showMessageDialog(null, "Tabelas do banco de dados 'petlove' configuradas com sucesso!");
            } else {
                System.out.println("Banco de dados 'petlove' já existe!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao verificar/criar o banco de dados: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (mycon != null) {
                    mycon.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
