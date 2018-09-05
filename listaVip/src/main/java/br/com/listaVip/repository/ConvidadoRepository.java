package br.com.listaVip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.listaVip.model.Convidado;

@Repository
@Transactional
public interface ConvidadoRepository extends CrudRepository<Convidado, Integer> {

}
