package com.egg.egg.news.servicios;

import com.egg.egg.news.entidades.Foto;
import com.egg.egg.news.exceptiones.MiException;
import com.egg.egg.news.repositorio.FotoRepositorio;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoServicio {

    @Autowired
    private FotoRepositorio fotoRepositorio;

    @Transactional
    public Foto guardar(MultipartFile archivo) throws MiException {
        if (archivo != null) {
            try {
                Foto foto = new Foto();
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());
                foto.setfCreacion(new Date());
                foto.setEstado(true);
                
                return fotoRepositorio.save(foto);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
        return null;

    }
    
    public Foto actualizar (String idFoto, MultipartFile archivo) throws MiException {
     if (archivo != null) {
            try {
                Foto foto = new Foto();
                if(idFoto != null){
                    Optional<Foto> respuesta=fotoRepositorio.findById(idFoto);
                    if(respuesta.isPresent()){
                        foto=respuesta.get();
                    }
                }
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());

                return fotoRepositorio.save(foto);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
        return null;

    }
        public Foto buscarFotoPorId(String idFoto){
        Foto autor = new Foto();
        autor = null;
        Optional<Foto> respuestaFoto = fotoRepositorio.findById(idFoto);
        if (respuestaFoto.isPresent()){
            autor = respuestaFoto.get();   
        }          
        return autor;
    }
    
    @Transactional
    public void softDeleteFoto(String idFoto){
        Foto foto = buscarFotoPorId(idFoto);
        foto.setEstado(false);
    }
}
