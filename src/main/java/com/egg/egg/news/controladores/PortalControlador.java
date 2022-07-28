
package com.egg.egg.news.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @GetMapping("/")
    public String index (){  //localhost:8080/
        
        return "index.html"; //localhost:8080/                   
    }
      @GetMapping("/noticia_form") //localhost:8080/noticia_form
    public String noticia_form (){
        
        return "noticia_form.html"; 
    }
    
      @GetMapping("/admin")  ////localhost:8080/admin
    public String admin() {

        return "admin.html";
    }
    
      

}


