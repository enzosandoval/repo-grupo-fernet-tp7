/**
 * 
 */
package ar.edu.unju.fi.tp7.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unju.fi.tp7.entity.Producto;
import ar.edu.unju.fi.tp7.service.IProductoService;

/**
 * 
 * @author Team Fernet
 *
 */
@Controller
public class ProductoController {

	@Autowired
	private Producto producto;

	@Autowired
	private IProductoService productoService;

	/**
	 * 
	 * @return La página del formulario para el alta de nuevo Producto
	 */
	@GetMapping("/producto")
	public String getPage(Model model) {
		model.addAttribute(producto);
		return "nuevo";
	}

	/**
	 * 
	 * @return La página resultado
	 */
	@PostMapping(value = "/producto/guardar", consumes = "multipart/form-data")
	public String getResultado(@RequestParam("file") MultipartFile file, @ModelAttribute("producto") Producto producto,
			Model model) throws IOException {
		byte[] content = file.getBytes();
		String base64 = Base64.getEncoder().encodeToString(content);
		producto.setImagen(base64);
		productoService.guardar(producto);
		model.addAttribute("productos", productoService.obtenerProductos());
		return "resultado";
	}

	/**
	 * 
	 * @return La página que muestra el último producto agregado
	 * @throws Exception
	 */
	@GetMapping("/producto/ultimo")
	public String getUltimoProducto(Model map) throws Exception {
			map.addAttribute("producto", productoService.obtenerUltimo());
		return "ultimoproducto";
	}

	/**
	 * 
	 * @return La página de productos
	 */
	@GetMapping("/productos")
	public String getProductos(Model map) {
		map.addAttribute("productos", productoService.obtenerProductos());
		return "compraproductos";
	}

}
