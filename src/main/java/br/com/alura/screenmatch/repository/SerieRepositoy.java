package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

// vai manipular o CRUD, extendendo do jpa
public interface SerieRepositoy extends JpaRepository<Serie, Long> {
    // define a entidade que vou trabalhar e o tipo do meu ID
    // com isso já conseguimos salvar e recuperar coisas do banco de dados
}
