
package com.egg.egg.news.repositorio;

import com.egg.egg.news.entidades.Noticia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, String> {
    
    
    @Query("SELECT n FROM Noticia n WHERE n.titulo = :titulo")
    public Noticia BuscarPorTitulo(@Param("titulo") String titulo);
    
  
    
    @Query("SELECT a from Noticia a WHERE a.darAlta = true ")
	public List<Noticia> buscarActivos();
}