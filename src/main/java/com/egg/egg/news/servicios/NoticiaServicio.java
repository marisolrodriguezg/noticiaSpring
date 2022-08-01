package com.egg.egg.news.servicios;

import com.egg.egg.news.entidades.Foto;
import com.egg.egg.news.entidades.Noticia;
import com.egg.egg.news.exceptiones.MiException;
import com.egg.egg.news.repositorio.FotoRepositorio;
import com.egg.egg.news.repositorio.NoticiaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

@Service
public class NoticiaServicio {
    
    @Autowired //inyeccion de dependencia hace q se inicialise el Repositorio
    private NoticiaRepositorio noticiaRepositorio; //lo llamamos para persistir la noticia crada
    
    @Autowired    
    private FotoServicio fotoServicio;    
    
    @Transactional //se pone en los que modifican la base de dato por si larga una exeption para que vuelva a iniciar
    public void crearNoticia(MultipartFile archivo, String titulo, String cuerpo) throws MiException {
        
        validar(titulo, cuerpo);//llamamos al metodo de validacion de excepciones

        Noticia noticia = new Noticia();
        
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        
        noticia.setDarAlta(true);
        
        noticia.setAlta(new Date()); //se auto crea el dia que se ingrese 
        Foto foto = fotoServicio.guardar(archivo);
        noticia.setFoto(foto);
        noticiaRepositorio.save(noticia);//save la guarda en la base de datos

    }
    
    @Transactional(readOnly = true)
    public List<Noticia> listarActivos() throws MiException {
        System.out.println("imprimir servicio");
        List<Noticia> lista = noticiaRepositorio.buscarActivos();
        for (Noticia noticia : lista) {
            System.out.println("" + noticia);
        }
        return lista;
    }
    
    @Transactional
    public void modificarNoticia(MultipartFile archivo,String numNoticia, String titulo, String cuerpo) throws MiException {
        validar(titulo, cuerpo);//llamamos al metodo de validacion de excepciones 

        Optional<Noticia> respuesta = noticiaRepositorio.findById(numNoticia);//optional nos dice si esta  el numNoticia si es true continua

        if (respuesta.isPresent()) {//si la respuesta esta presente modificamos 

            Noticia noticia = respuesta.get();
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            
           String idFoto=null;
           if(noticia.getFoto()!= null){
               idFoto =noticia.getFoto().getId();
           }
            
           Foto foto =fotoServicio.actualizar(idFoto, archivo);
           noticia.setFoto(foto);

            noticiaRepositorio.save(noticia);//para guardar nuevamente en la bae de datos

        }
    }
       public Noticia buscarNoticiaPorId(String numNoticia){
        Noticia noticia = new Noticia();
        noticia = null;
        Optional<Noticia> respuestaNoticia = noticiaRepositorio.findById(numNoticia);
        if (respuestaNoticia.isPresent()){
            noticia = respuestaNoticia.get();   
        }          
        return noticia;
    }
    
    @Transactional
    public void ocultarNoticia(String numNoticia) {
        
        Optional<Noticia> respuesta = noticiaRepositorio.findById(numNoticia);//optional nos dice si esta  el numNoticia si es true continua

        if (respuesta.isPresent()) {//si la respuesta esta presente modificamos 

            Noticia noticia = respuesta.get();
            noticia.setDarAlta(false);
            
            noticiaRepositorio.save(noticia);//para guardar nuevamente en la bae de datos

        }
    }

    //metodo que buscA LA NOTICIA PARA MODIFICARLA
    public Noticia getOne(String numNoticia) {
        return noticiaRepositorio.getOne(numNoticia);
    }

    //este metodo maneja los exception
    private void validar(String titulo, String cuerpo) throws MiException {
        //los if son para ver que no vengan vacio ni null

        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("el titulo no puede ser nulo");
        }
        if (cuerpo.isEmpty() || cuerpo == null) {
            throw new MiException("el cuerpo no puede ser nulo");
        }
      
    }
    
}
