package dio.bootcamp.padrao_projeto_spring.repository;

import dio.bootcamp.padrao_projeto_spring.model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
