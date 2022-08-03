package com.egg.egg.news.controladores;

import com.egg.egg.news.entidades.Foto;
import com.egg.egg.news.entidades.Noticia;
import com.egg.egg.news.exceptiones.MiException;
import com.egg.egg.news.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.egg.news.exceptiones.MiException;
import com.egg.egg.news.servicios.FotoServicio;
import java.util.List;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/foto")
public class FotoControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;
    @Autowired
    private FotoServicio fotoServicio;

        @GetMapping("/noticia")
    public ResponseEntity<byte[]> fotoNoticia(@RequestParam String numNoticia) throws Exception{
        Noticia noticia = noticiaServicio.buscarNoticiaPorId(numNoticia);
        try{
            if(noticia.getFoto()==null){
                throw new Exception ();
            }
            byte[] foto = noticia.getFoto().getContenido();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(foto, headers, HttpStatus.OK);
        }catch(Exception ex){
            System.out.println("La noticia no tiene foto");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }  
    }
    
    @GetMapping("/listar")
    public String listarFotos (ModelMap modelo){
        List <Foto> mostrarFotos=fotoServicio.listarFotos();
        modelo.addAttribute("fotoLista", mostrarFotos);
        return "mostrar_foto.html";
    }
}
