package model;

public class Animal {

    private int pet_id;
    private String pet_nome;
    private String pet_raca;
    private String pet_detalhes;
    private String pet_especie;
    private String pet_cor;
    private int pet_sit;
    private int fk_id_user;

    public int getFk_id_user() {
        return fk_id_user;
    }

    public void setFk_id_user(int fk_id_user) {
        this.fk_id_user = fk_id_user;
    }

    
    public int getPet_id() {
        return pet_id;
    }

    public void setPet_id(int pet_id) {
        this.pet_id = pet_id;
    }

    public String getPet_nome() {
        return pet_nome;
    }

    public void setPet_nome(String pet_nome) {
        this.pet_nome = pet_nome;
    }

    public String getPet_raca() {
        return pet_raca;
    }

    public void setPet_raca(String pet_raca) {
        this.pet_raca = pet_raca;
    }

    public String getPet_detalhes() {
        return pet_detalhes;
    }

    public void setPet_detalhes(String pet_detalhes) {
        this.pet_detalhes = pet_detalhes;
    }

    public String getPet_especie() {
        return pet_especie;
    }

    public void setPet_especie(String pet_especie) {
        this.pet_especie = pet_especie;
    }

    public String getPet_cor() {
        return pet_cor;
    }

    public void setPet_cor(String pet_cor) {
        this.pet_cor = pet_cor;
    }

    public int getPet_sit() {
        return pet_sit;
    }

    public void setPet_sit(int pet_sit) {
        this.pet_sit = pet_sit;
    }
    
    

}
