package com.egg.egg.news.controladores;

import com.egg.egg.news.entidades.Noticia;
import com.egg.egg.news.exceptiones.MiException;
import com.egg.egg.news.servicios.NoticiaServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("noticia")//localhost:8080/noticia

public class NoticiaControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;//para poder ingresar a sus metdos

    
    //PARA GARGAR UN LIBRO
    @GetMapping("/registrar")//localhost:8080/noticia/registrar
    public String registrar() {

        return "noticia_form.html";
    }

    @PostMapping("/registro")//ingresa una vez que presionamos el "subir", vijan os parametros hasta aca 
    public String registro(@RequestParam String titulo, @RequestParam(required = false) String cuerpo, @RequestParam(required = false) String foto, ModelMap modelo) {
        //se pone el request false para que entre en a exeption y pueda manejarlo con el servicio
        //model maps: modelo se usa para mostrar por pantalla del lado del usuario
        try {
            noticiaServicio.crearNoticia(titulo, cuerpo, foto);

            modelo.put("exito", "la noticia fue cargada correctamente");
            // videoo 11 , falta que me muestre la etiqueta de exito en el index
        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());

            Logger.getLogger(NoticiaControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "noticia_form.html";

        }

        return "index.html";

    }

  
//METODO DE INICIO MUESTRA LAS TARJETAS
    @GetMapping("/listar") //localhost:8080/noticia/listar
    public String listar(ModelMap modelo) {
        System.out.println("estamos imprimiendo");
        try {
            List<Noticia> listaNoticias = noticiaServicio.listarActivos();

            modelo.addAttribute("listaNoticias", listaNoticias);
            modelo.put("exito", "la noticia fue cargada correctamente");

        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "index.html";
        }
        return "admin.html";
    }
//MUESTRA LA TABLA PARA ELIMINAR Y MODIFICAR
    @GetMapping("/listar_tabla")//localhost:8080/noticia/listar_tabla
    public String listarTabla(ModelMap modelo) {
        System.out.println("estamos imprimiendo");
        try {
            List<Noticia> listaNoticias = noticiaServicio.listarActivos();

            modelo.addAttribute("listaNoticias", listaNoticias);
            modelo.put("exito", "la noticia fue cargada correctamente");

        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "index.html";
        }
        return "vista_tabla.html";
    }


    
    //metodo que carga el formulario para modificar
@GetMapping("/modificar/{numNoticia}") //localhost:8080/noticia/modificar
public String modificar (@PathVariable String numNoticia, ModelMap modelo){
    modelo.put("noticia", noticiaServicio.getOne(numNoticia));
    return "noticia_modificar.html";
}

//continua del de arriba envia los datos ya modificados
@PostMapping("/modificar/{numNoticia}")
public String modificar(@RequestParam (required=false)  String numNoticia, @RequestParam (required=false) String nombre, @RequestParam (required=false) String titulo,@RequestParam (required=false) String cuerpo,@RequestParam (required=false) String foto, ModelMap modelo){
        try {
            noticiaServicio.modificarNoticia(numNoticia, titulo, cuerpo, foto);
            return "redirect:/noticia/listar";//te redirecciona la la lista con la noticia modificada
        } catch (MiException ex) {
           modelo.put("error", ex.getMessage());
           return "noticia_modificar.html";
        }
}
}


//@GetMapping("/editar/{id}")
//    public String editarNoticia (@PathVariable String id, ModelMap modeloNoticia, ModelMap modeloAutor){
//        modeloNoticia.put("noticia",noticiaServicio.getOne(id));
//        List<Autor> autores = autorServicio.listarAutores();
//        modeloAutor.addAttribute("autores", autores);
//        return "noticia_edicion.html";
//    }
//    
//    @PostMapping("/editar/{id}")
//    public String editarNoticia (@RequestParam  (required=false) MultipartFile archivo, @RequestParam  (required=false)String id, @RequestParam  (required=false) String titulo, @RequestParam (required=false) String contenido, @RequestParam (required=false) String idAutor)throws Exception{
//        
//        try {
//            noticiaServicio.editarNoticia(archivo, id, titulo, contenido, idAutor);
//            return "redirect:/noticia/listar";
//        } catch (Exception e) {
//            return "redirect:/noticia/editar";
//        }   
//    }
