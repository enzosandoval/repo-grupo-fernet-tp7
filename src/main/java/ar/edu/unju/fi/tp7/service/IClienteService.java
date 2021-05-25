/**
 * 
 */
package ar.edu.unju.fi.tp7.service;

import java.util.List;

import ar.edu.unju.fi.tp7.entity.Cliente;

/**
 * @author Team Fernet
 *
 */
public interface IClienteService {

	public void guardar(Cliente cliente);

	public Cliente obtenerCliente(int dni);
	
	public List<Cliente> obtenerClientes();
	
}
