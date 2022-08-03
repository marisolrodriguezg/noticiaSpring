package com.egg.egg.news.entidades;

import java.util.Date;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

@Entity
@Data
public class Foto {

    @Id
    @GeneratedValue(generator = "uuid") //genera un id 
    @GenericGenerator(name = "uuid", strategy = "uuid2") //para qye no se repita el id
    private String id;

    private String nombre;
    ;
    
    private String mime;

    @Lob
    @Basic(fetch = FetchType.LAZY)//hace que los cueris sean mas livianos, 
    private byte[] contenido;
    @Temporal(TemporalType.DATE)
    private Date fCreacion;

    private boolean estado;

    public void setfCreacion(Date fCreacion) {
        this.fCreacion = fCreacion;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the mime
     */
    public String getMime() {
        return mime;
    }

    /**
     * @param mime the mime to set
     */
    public void setMime(String mime) {
        this.mime = mime;
    }

    /**
     * @return the contenido
     */
    public byte[] getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    /**
     * @return the fCreacion
     */
    public Date getfCreacion() {
        return fCreacion;
    }

    /**
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

}
