/**
 * 
 */
package ar.edu.unju.fi.tp7.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.tp7.entity.Cliente;

/**
 * @author Team Fernet
 * @param Cliente
 *
 */
@Repository
public interface ClienteRepository extends CrudRepository <Cliente , Long> {
	
	public Cliente findByNroDocumento(int dni);
	
}
