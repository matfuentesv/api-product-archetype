package cl.company.controller;


import cl.company.exception.ApiResponse;
import cl.company.model.Product;
import cl.company.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Log
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping(value = "/findAllProduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAllProduct(){
        log.info("Se solicita la lista de todas los usuarios");
        return ResponseEntity.ok(productService.findAllProduct());
    }

    @GetMapping("/findProduct/{id}")
    public ResponseEntity<Object> findProduct(@PathVariable Long id) {

        if (StringUtils.containsWhitespace(String.valueOf(id)) || id == null) {
            log.info("El id no se ingreso");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Algunos de los parámetros no se ingresaron",false));
        }
        return ResponseEntity.ok(productService.findProduct(id));
    }

    @PostMapping("/createProduct")
    public Product createProduct(@Valid @RequestBody Product product)  {

        return productService.createProduct(product);
    }

    @PutMapping("/updateProduct")
    public Product updateProduct(@Valid @RequestBody Product product)  {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {

        if (StringUtils.containsWhitespace(String.valueOf(id))|| id == null) {
            log.info("El id no se ingreso");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Algunos de los parámetros no se ingresaron",false));
        }


        Object product = productService.findProduct(id);

        if (product != null) {
            productService.deleteProduct(id);
            return ResponseEntity.ok(new ApiResponse("Producto eliminado",true));
        } else {
            log.info("Producto no encontrado con id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Producto no encontrado",false));
        }
    }



}
