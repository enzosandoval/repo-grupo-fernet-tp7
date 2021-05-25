/**
 * 
 */
package ar.edu.unju.fi.tp7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tp7.entity.Cliente;
import ar.edu.unju.fi.tp7.service.IClienteService;

/**
 * @author Team Fernet
 *
 */
@Controller
public class ClienteController {

	@Autowired
	@Qualifier("clienteServiceImp")
	private IClienteService serviceCliente;

	@Autowired
	private Cliente cliente;

	/**
	 * 
	 * @param model
	 * @return La página para el alta de nuevo Cliente
	 */
	@GetMapping("/cliente/nuevo")
	public String getClientePage(Model model) {
		/**
		 * El attribute "cliente" es el objeto que esta en el form mientras que cliente
		 * es el atributo de tipo Cliente de esta clase
		 */
		model.addAttribute("cliente", cliente);
		return "nuevocliente.html";
	}

	/**
	 * 
	 * @param cliente
	 * @return Un modelo y vista "clientes.html" que muestra la lista de clientes
	 */
	@PostMapping("/cliente/guardar")
	public ModelAndView getModelPageClientes(@ModelAttribute("cliente") Cliente cliente) {
		ModelAndView modelAndView = new ModelAndView("clientes");
		/**
		 * Se agrega el objeto que vino de la vista a la listaClientes de la clase que
		 * implementa IServiceCliente
		 */
		serviceCliente.guardar(cliente);
		/**
		 * Se agrega la listaClientes de la clase que implementa IServiceCliente al
		 * modelAndView para luego ser recorrida y mostrada en la vista("clientes")
		 */
		modelAndView.addObject("listado", serviceCliente.obtenerClientes());
		return modelAndView;
	}

	/**
	 * 
	 * @param model
	 * @return La página "clientes.html" con los clientes existentes
	 */
	@GetMapping("/cliente/lista")
	public String getListadoPage(Model model) {
		model.addAttribute("listado", serviceCliente.obtenerClientes());
		return "clientes";
	}

}
