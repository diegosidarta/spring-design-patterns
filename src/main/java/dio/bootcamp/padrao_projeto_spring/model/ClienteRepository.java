package dio.bootcamp.padrao_projeto_spring.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
