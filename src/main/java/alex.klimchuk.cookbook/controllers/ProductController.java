package alex.klimchuk.cookbook.controllers;

import alex.klimchuk.cookbook.dto.ProductDto;
import alex.klimchuk.cookbook.converters.ProductToProductDto;
import alex.klimchuk.cookbook.domain.Product;
import alex.klimchuk.cookbook.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Copyright Alex Klimchuk (c) 31.10.2021.
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private ProductToProductDto productToProductDto;

    public ProductController(ProductService productService, ProductToProductDto productToProductDto) {
        this.productService = productService;
        this.productToProductDto = productToProductDto;
    }

    @GetMapping("/")
    public String redirectToList() {
        return "redirect:/list";
    }

    @GetMapping({"", "/list"})
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product/list";
    }

    @GetMapping("/show/{id}")
    public String getProduct(@PathVariable /*String -- type for NoSqlDB / UUID -- type for CassandraDB*/  Long id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "product/show";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable /*String -- type for NoSqlDB / UUID -- type for CassandraDB*/  Long id, Model model) {
        Product product = productService.getById(id);
        ProductDto ProductDto = productToProductDto.convert(product);

        model.addAttribute("ProductDto", ProductDto);
        return "product/productForm";
    }

    @PostMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("ProductDto", new ProductDto());
        return "product/productForm";
    }

    @PostMapping
    public String saveOrUpdateProduct(@Valid ProductDto ProductDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "product/productForm";
        }

        Product savedProduct = productService.saveOrUpdateProductDto(ProductDto);

        return "redirect:/show/" + savedProduct.getId();
    }

    @DeleteMapping("/deleteById/{id}")
    public String deleteById(@PathVariable /*String -- type for NoSqlDB / UUID -- type for CassandraDB*/  Long id) {
        productService.deleteById(id);
        return "redirect:/list";
    }

}
