package com.egg.egg.news.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Noticia {

    @Id
    @GeneratedValue(generator = "uuid") //genera un id 
    @GenericGenerator(name = "uuid", strategy = "uuid2") //para qye no se repita el id
    private String numNoticia;

    private String titulo;
    private String cuerpo;
    private String fo;

    private boolean darAlta;
    @Temporal(TemporalType.DATE)
    private Date alta;

   @OneToOne 
   private Foto foto;
    
   
     
   
    
    public boolean isDarAlta() {
        return darAlta;
    }

    public void setDarAlta(boolean darAlta) {
        this.darAlta = darAlta;
    }

    public Noticia() {
    }

    public String getNumNoticia() {
        return numNoticia;
    }

    public void setNumNoticia(String numNoticia) {
        this.numNoticia = numNoticia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getFo() {
        return fo;
    }

    public void setFo(String fo) {
        this.fo = fo;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    /**
     * @return the foto
     */
    public Foto getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(Foto foto) {
        this.foto = foto;
    }

}
