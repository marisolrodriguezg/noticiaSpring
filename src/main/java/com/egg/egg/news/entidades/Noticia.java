
package com.egg.egg.news.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Noticia {
    @Id
    @GeneratedValue(generator="uuid") //genera un id 
    @GenericGenerator(name="uuid", strategy = "uuid2") //para qye no se repita el id
    private String numNoticia;
    
    private String titulo;
    private String cuerpo;
    private String foto;
    
    private boolean darAlta;

    public boolean isDarAlta() {
        return darAlta;
    }

    public void setDarAlta(boolean darAlta) {
        this.darAlta = darAlta;
    }
    
    @Temporal(TemporalType.DATE)
    private Date alta;
    
    //    @Lob
//    @Basic(fetch = FetchType.LAZY)
//    private byte[] foto;


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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    
    
    
}
