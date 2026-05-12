package com.goonamvina.goonamvina.Controller;

import com.goonamvina.goonamvina.model.News;
import com.goonamvina.goonamvina.model.Product;
import com.goonamvina.goonamvina.model.ProductCategory;
import com.goonamvina.goonamvina.model.ProductSubCategory;
import com.goonamvina.goonamvina.repository.PCRepository;
import com.goonamvina.goonamvina.service.MediaStorageService;
import com.goonamvina.goonamvina.service.PCService;
import com.goonamvina.goonamvina.service.PSCService;
import com.goonamvina.goonamvina.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductAdminController {
    @Autowired
    private PCService pcService;

    @Autowired
    private PSCService pscService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MediaStorageService mediaStorageService;
    @GetMapping("/product-category")
    public String viewProductCategory(Model model){
        model.addAttribute("pc", new ProductCategory());
        model.addAttribute("pcList",pcService.getAllPC());
        return "admin/product/products-category";
    }

    @PostMapping("product-category")
    public String savePC(@ModelAttribute("pc") ProductCategory productCategory,
                         @RequestParam("pc-image") MultipartFile file,
                         Model model) {
        if (!file.isEmpty()) {
            // Sử dụng MediaStorageService để upload file và lưu tên file
            String fileName = mediaStorageService.uploadFile(file);
            productCategory.setImagePC(fileName); // Lưu tên file vào cơ sở dữ liệu
        }

        pcService.savePC(productCategory);
        return "redirect:/admin/product-category";
    }
    @GetMapping("/edit-pc/{id}")
    public String editPCForm(@PathVariable("id") Long id, Model model) {
        ProductCategory existingPC = pcService.getPCById(id);
        if (existingPC != null) {
            model.addAttribute("pc", existingPC);
            return "admin/product/products-category"; // Trang hiển thị form chỉnh sửa
        } else {
            return "redirect:/admin/product-category"; // Điều hướng nếu bài viết không tồn tại
        }
    }
    @PostMapping("/update-pc/{id}")
    public String updatePC(@PathVariable("id") Long id,
                           @ModelAttribute("pc") ProductCategory updatedPC,
                           @RequestParam("pc-image") MultipartFile file) {

        ProductCategory existingPC = pcService.getPCById(id);
        if (existingPC != null) {
            existingPC.setNameVi(updatedPC.getNameVi());
            existingPC.setNameEn(updatedPC.getNameEn());
            existingPC.setSubProductCategories(existingPC.getSubProductCategories());

            if (!file.isEmpty()) {
                // Sử dụng MediaStorageService để upload file và lưu tên file
                String fileName = mediaStorageService.uploadFile(file);
                if (fileName != null) {
                    existingPC.setImagePC(fileName); // Cập nhật tên file mới vào cơ sở dữ liệu
                }
            }

            // Lưu thông tin bài viết đã cập nhật vào cơ sở dữ liệu
            pcService.savePC(existingPC);
        }

        return "redirect:/admin/product-category";
    }
    @GetMapping("/delete-pc/{id}")
    public String deletePC(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            // Xóa sản phẩm từ bảng products category
            pcService.deletePC(id);
            redirectAttributes.addFlashAttribute("message", "Product Category deleted successfully.");
        } catch (DataIntegrityViolationException e) {
            // Thông báo lỗi khi không thể xóa do có liên kết với sản phẩm con
            redirectAttributes.addFlashAttribute("error", "Cannot delete this category. Please delete its sub-products first.");
        }
        return "redirect:/admin/product-category";
    }

    //Sub Product Categoryyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy

    @GetMapping("/product-subcategory")
    public String viewPSC(Model model){
        List<ProductCategory> productCategories = pcService.getAllPC();
        model.addAttribute("psc",new ProductSubCategory());
        model.addAttribute("pscList",pscService.getAllPSC());
        model.addAttribute("pc",productCategories);
        return "admin/product/product-subcategory";
    }
    @PostMapping("product-subcategory")
    public String savePSC(@ModelAttribute("psc") ProductSubCategory productSubCategory,
                          @RequestParam("psc-image") MultipartFile file,
                          Model model) {
        if (!file.isEmpty()) {
            // Sử dụng MediaStorageService để upload file và lưu tên file
            String fileName = mediaStorageService.uploadFile(file);
            if (fileName != null) {
                productSubCategory.setImagePSC(fileName); // Lưu tên file vào cơ sở dữ liệu
            }
        }

        pscService.savePSC(productSubCategory);
        return "redirect:/admin/product-subcategory";
    }
    @GetMapping("/edit-psc/{id}")
    public String editPSCForm(@PathVariable("id") Long id, Model model) {
        ProductSubCategory existingPSC = pscService.getPSCById(id);
        List<ProductCategory> productCategories = pcService.getAllPC();

        if (existingPSC != null) {
            model.addAttribute("psc", existingPSC);
            model.addAttribute("pc",productCategories);
            return "admin/product/product-subcategory"; // Trang hiển thị form chỉnh sửa
        } else {
            return "redirect:/admin/product-subcategory"; // Điều hướng nếu bài viết không tồn tại
        }
    }
    @PostMapping("/update-psc/{id}")
    public String updatePSC(@PathVariable("id") Long id,
                            @ModelAttribute("psc") ProductSubCategory updatedPSC,
                            BindingResult result,
                            @RequestParam("psc-image") MultipartFile file) {
        ProductSubCategory existingPSC = pscService.getPSCById(id);
        if ("Cửa thép/Cửa thép chống cháy".equals(existingPSC.getNameVi()) || "Steel Door/Fireproof Steel Door".equals(existingPSC.getNameEn())) {
            result.rejectValue("name", "error.productSubCategory", "Tên này không thể thay đổi.");
        }
        if (existingPSC != null) {
            existingPSC.setNameVi(updatedPSC.getNameVi());
            existingPSC.setNameEn(updatedPSC.getNameEn());
            existingPSC.setProductCategory(existingPSC.getProductCategory());

            if (!file.isEmpty()) {
                // Sử dụng MediaStorageService để upload file và lưu tên file
                String fileName = mediaStorageService.uploadFile(file);
                if (fileName != null) {
                    existingPSC.setImagePSC(fileName); // Cập nhật tên file mới vào cơ sở dữ liệu
                }
            }

            // Lưu thông tin đã cập nhật vào cơ sở dữ liệu
            pscService.savePSC(existingPSC);
        }

        return "redirect:/admin/product-subcategory";
    }
    @GetMapping("/delete-psc/{id}")
    public String deletePSC(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            // Xóa sản phẩm từ bảng products category
            pscService.deletePSC(id);
            redirectAttributes.addFlashAttribute("message", "Product Category deleted successfully.");
        } catch (DataIntegrityViolationException e) {
            // Thông báo lỗi khi không thể xóa do có liên kết với sản phẩm con
            redirectAttributes.addFlashAttribute("error", "Cannot delete this category. Please delete its products first.");
        }
        return "redirect:/admin/product-subcategory";
    }


    //Producttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt


    @GetMapping("/add-product")
    public String viewAddProduct(Model model) {
        List<ProductSubCategory> productSubCategories = pscService.getAllPSC();


        model.addAttribute("product", new Product());
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("psc", productSubCategories);


        return "admin/product/add-products";
    }
    @PostMapping("add-product")
    public String saveProduct(@ModelAttribute("product") Product product,
                              @RequestParam("product-image") MultipartFile file,
                              BindingResult result,
                              Model model) {
        // Lấy danh mục con của sản phẩm để kiểm tra tên
        String subCategoryNameVi = product.getProductSubCategory().getNameVi();
        String subCategoryNameEn = product.getProductSubCategory().getNameEn();
        // Kiểm tra nếu danh mục là "Cửa thép/Cửa thép chống cháy" thì `Finish Type` bắt buộc phải có
        if ("Cửa thép/Cửa thép chống cháy".equals(subCategoryNameVi) || "Steel Door/Fireproof Steel Door".equals(subCategoryNameEn)) {
            if ((product.getFinishTypeVi() == null || product.getFinishTypeVi().isEmpty())
                    && (product.getFinishTypeEn() == null || product.getFinishTypeEn().isEmpty())) {
                result.rejectValue("finishTypeVi", "error.product", "Finish Type is required for Cửa thép/Cửa thép chống cháy.");
                result.rejectValue("finishTypeEn", "error.product", "Finish Type is required for Steel Door/Fireproof Steel Door.");
            }
        }

        if (product.getDescriptionVi().length() > 1000 ) {
            result.rejectValue("descriptionVi", "error.product", "Description (Vi) is too long. Maximum 1000 characters allowed.");
        }
        if (product.getDescriptionEn().length() > 1000) {
            result.rejectValue("descriptionEn", "error.product", "Description (En) is too long. Maximum 1000 characters allowed.");
        }

        if (result.hasErrors()) {
            List<ProductSubCategory> productSubCategories = pscService.getAllPSC();
            model.addAttribute("psc", productSubCategories);
            return "admin/product/add-products";
        }

        // Sử dụng MediaStorageService để upload file
        if (!file.isEmpty()) {
            String fileName = mediaStorageService.uploadFile(file);
            if (fileName != null) {
                product.setImageP(fileName); // Lưu tên file vào cơ sở dữ liệu
            }
        }

        productService.saveProduct(product);
        return "redirect:/admin/add-product";
    }
    @GetMapping("/edit-product/{id}")
    public String editProductForm(@PathVariable("id") Long id, Model model) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct == null) {
            return "redirect:/admin/add-product"; // Điều hướng nếu không tìm thấy sản phẩm
        }
        List<ProductSubCategory> productSubCategories = pscService.getAllPSC();
        List<Product> products = productService.getAllProduct(); // Lấy danh sách tất cả sản phẩm

        model.addAttribute("product", existingProduct);
        model.addAttribute("psc", productSubCategories);
        model.addAttribute("products", products); // Truyền danh sách sản phẩm vào model

        return "admin/product/add-products";
    }
    @PostMapping("/update-product/{id}")
    public String updateProduct(@PathVariable("id") Long id,
                                @ModelAttribute("product") Product updatedProduct,
                                @RequestParam("product-image") MultipartFile file,
                                BindingResult result,
                                Model model) {
        Product existingProduct = productService.getProductById(id);

        if (existingProduct != null) {
            // Lấy tên danh mục con hiện tại của sản phẩm
            String subCategoryNameVi = updatedProduct.getProductSubCategory().getNameVi();
            String subCategoryNameEn = updatedProduct.getProductSubCategory().getNameEn();
            // Kiểm tra nếu danh mục là "Cửa thép/Cửa thép chống cháy" thì `Finish Type` bắt buộc phải có
            if ("Cửa thép/Cửa thép chống cháy".equals(subCategoryNameVi) || "Steel Door/Fireproof Steel Door".equals(subCategoryNameEn)) {
                if ((updatedProduct.getFinishTypeVi() == null || updatedProduct.getFinishTypeVi().isEmpty())
                        && (updatedProduct.getFinishTypeEn() == null || updatedProduct.getFinishTypeEn().isEmpty())) {
                    result.rejectValue("finishTypeVi", "error.product", "Finish Type is required for Cửa thép/Cửa thép chống cháy.");
                    result.rejectValue("finishTypeEn", "error.product", "Finish Type is required for Steel Door/Fireproof Steel Door.");
                }
            }

            if (updatedProduct.getDescriptionVi().length() > 1000  ) {
                result.rejectValue("descriptionVi", "error.product", "Description (Vi) is too long. Maximum 1000 characters allowed.");
            }
            if (updatedProduct.getDescriptionEn().length() > 1000) {
                result.rejectValue("descriptionEn", "error.product", "Description (En) is too long. Maximum 1000 characters allowed.");
            }

            if (result.hasErrors()) {
                List<ProductSubCategory> productSubCategories = pscService.getAllPSC();
                model.addAttribute("psc", productSubCategories);
                model.addAttribute("product", updatedProduct);
                return "admin/product/add-products"; // Trả về form chỉnh sửa với thông báo lỗi
            }

            // Cập nhật các trường thông tin của sản phẩm
            existingProduct.setNameVi(updatedProduct.getNameVi());
            existingProduct.setNameEn(updatedProduct.getNameEn());
            existingProduct.setDescriptionVi(updatedProduct.getDescriptionVi());
            existingProduct.setDescriptionEn(updatedProduct.getDescriptionEn());
            existingProduct.setProductSubCategory(updatedProduct.getProductSubCategory());
            existingProduct.setFinishTypeVi(updatedProduct.getFinishTypeVi());
            existingProduct.setFinishTypeEn(updatedProduct.getFinishTypeEn());

            // Sử dụng MediaStorageService để upload file nếu có file mới
            if (!file.isEmpty()) {
                String fileName = mediaStorageService.uploadFile(file);
                if (fileName != null) {
                    existingProduct.setImageP(fileName); // Cập nhật tên file mới vào cơ sở dữ liệu
                }
            }

            // Lưu thông tin sản phẩm đã cập nhật vào cơ sở dữ liệu
            productService.saveProduct(existingProduct);
        }

        return "redirect:/admin/add-product";
    }
    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        // Xóa sản phẩm từ bảng products category
        productService.deleteProduct(id);
        return "redirect:/admin/add-product";
    }
    @GetMapping("/search-products")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productService.searchProducts(keyword);
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        return "admin/product/add-products";
    }
}
