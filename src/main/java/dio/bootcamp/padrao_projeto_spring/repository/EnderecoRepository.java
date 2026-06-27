package dio.bootcamp.padrao_projeto_spring.repository;

import dio.bootcamp.padrao_projeto_spring.model.Endereco;
import org.springframework.data.repository.CrudRepository;


public interface EnderecoRepository extends CrudRepository<Endereco, String> {
}
