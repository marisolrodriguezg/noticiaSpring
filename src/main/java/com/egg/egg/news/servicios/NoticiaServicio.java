package com.egg.egg.news.servicios;

import com.egg.egg.news.entidades.Noticia;
import com.egg.egg.news.exceptiones.MiException;
import com.egg.egg.news.repositorio.NoticiaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticiaServicio {

    @Autowired //inyeccion de dependencia hace q se inicialise el Repositorio
    private NoticiaRepositorio noticiaRepositorio; //lo llamamos para persistir la noticia crada

    @Transactional //se pone en los que modifican la base de dato por si larga una exeption para que vuelva a iniciar
    public void crearNoticia( String titulo, String cuerpo, String foto) throws MiException {

        validar( titulo, cuerpo, foto);//llamamos al metodo de validacion de excepciones

        Noticia noticia = new Noticia();

        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticia.setFoto(foto);
        noticia.setDarAlta(true);

        noticia.setAlta(new Date()); //se auto crea el dia que se ingrese 

        noticiaRepositorio.save(noticia);//save la guarda en la base de datos

    }

//    public List<Noticia> ListarNoticias() {
//
//        List<Noticia> noticias = new ArrayList();
//        noticias = noticiaRepositorio.findAll(); //metodo que trae repositori trae todas las noticias que esta  guardados en la tabla noticias
//
//        return noticias;
//    }

   @Transactional// (readOnly = true)
    public List<Noticia> listarActivos() throws MiException {
        System.out.println("imprimir servicio");
        List<Noticia> lista = noticiaRepositorio.buscarActivos();
        for (Noticia noticia : lista) {
            System.out.println(""+ noticia);
       }
        return lista;
    }
    
    public void modificarNoticia(String numNoticia, String titulo, String cuerpo, String foto) throws MiException {
         validar(titulo, cuerpo, foto);//llamamos al metodo de validacion de excepciones 
        
        Optional<Noticia> respuesta = noticiaRepositorio.findById(numNoticia);//optional nos dice si esta  el numNoticia si es true continua

        if (respuesta.isPresent()) {//si la respuesta esta presente modificamos 

            Noticia noticia = respuesta.get();
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);//NO SE SI TIENE QUE IR
            noticia.setFoto(foto);//NO SE SI TIENE QUE IR

            noticiaRepositorio.save(noticia);//para guardar nuevamente en la bae de datos

        }
    }
    
    //metodo que buscA LA NOTICIA PARA MODIFICARLA
    public Noticia getOne(String numNoticia){
        return noticiaRepositorio.getOne(numNoticia);
    }

    //este metodo maneja los exception
    private void validar(String titulo, String cuerpo, String foto) throws MiException {
        //los if son para ver que no vengan vacio ni null
     
        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("el titulo no puede ser nulo");
        }
        if (cuerpo.isEmpty() || cuerpo == null) {
            throw new MiException("el cuerpo no puede ser nulo");
        }
        if (foto.isEmpty() || foto == null) {
            throw new MiException("el foto no puede ser nulo");
        }

    }
    
//    @Transactional
//    public Noticias baja(Integer id) {
//
//        Noticias entidad = noticiaRepositorio.getOne(Integer.SIZE);
//
//        entidad.setActivo(false);
//        return noticiaRepositorio.save(entidad);
//    }
    
    
    
    //VIDEO BENJA SUBIR IMAGENES //   https://www.youtube.com/watch?v=LLNGhNBayTA


}
