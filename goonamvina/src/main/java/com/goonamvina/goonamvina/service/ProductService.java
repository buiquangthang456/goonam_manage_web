package com.goonamvina.goonamvina.service;

import com.goonamvina.goonamvina.model.Product;
import com.goonamvina.goonamvina.model.ProductCategory;
import com.goonamvina.goonamvina.model.ProductSubCategory;
import com.goonamvina.goonamvina.repository.PSCRepository;
import com.goonamvina.goonamvina.repository.ProductRepository;
import com.goonamvina.goonamvina.util.SlugUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private PSCService pscService;

    @Autowired
    private ProductRepository productRepository;

    // Lấy tất cả sản phẩm
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }



    // Lấy sản phẩm theo danh mục phụ và loại hoàn thiện, dựa trên ngôn ngữ
    public List<Product> getProductsBySubPCSlugAndFinishTypeAndLang(String slug, String finishType, String lang) {
        ProductSubCategory subCategory = pscService.getPSCBySlug(slug);

        if ("vi".equals(lang)) {
            return productRepository.findByProductSubCategoryAndFinishTypeVi(subCategory, finishType);
        } else {
            return productRepository.findByProductSubCategoryAndFinishTypeEn(subCategory, finishType);
        }
    }

    // Lấy sản phẩm theo danh mục phụ và ngôn ngữ, nhưng không có `finishType`
    public List<Product> getProductsBySubPCSlugAndLang(String slug, String lang) {
        ProductSubCategory subCategory = pscService.getPSCBySlug(slug);

        if ("vi".equals(lang)) {
            // Trả về sản phẩm với loại hoàn thiện bằng tiếng Việt
            return productRepository.findByProductSubCategoryAndFinishTypeVi(subCategory, null);
        } else {
            // Trả về sản phẩm với loại hoàn thiện bằng tiếng Anh
            return productRepository.findByProductSubCategoryAndFinishTypeEn(subCategory, null);
        }
    }

    // Lưu sản phẩm, tạo slug nếu chưa có
    public void saveProduct(Product product) {
        if (product.getSlug() == null || product.getSlug().isEmpty()) {
            product.setSlug(SlugUtil.toSlug(product.getNameVi()));
            product.setSlug(SlugUtil.toSlug(product.getNameEn()));
        }
        productRepository.save(product);
    }

    // Lấy sản phẩm theo ID
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Lấy sản phẩm theo slug
    public Product getProductBySlug(String slug) {
        ProductSubCategory subCategory = pscService.getPSCBySlug(slug);
        return productRepository.findByProductSubCategory(subCategory).stream().findFirst().orElse(null);
    }

    // Xóa sản phẩm theo ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Lấy danh sách sản phẩm theo ID của ProductSubCategory
    public List<Product> getProductsBySubPCId(Long spcId) {
        ProductSubCategory subCategory = pscService.getPSCById(spcId);
        return productRepository.findByProductSubCategory(subCategory);
    }

    // Lấy danh sách sản phẩm theo slug của ProductSubCategory
    public List<Product> getProductsBySubPCSlug(String slug) {
        ProductSubCategory subCategory = pscService.getPSCBySlug(slug);
        return productRepository.findByProductSubCategory(subCategory);
    }



    // Tìm kiếm sản phẩm theo từ khóa
    public List<Product> searchProducts(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return productRepository.findAll(); // Trả về tất cả sản phẩm nếu không có từ khóa
        }
        return productRepository.findByNameViContainingOrNameEnContainingOrDescriptionViContainingOrDescriptionEnContaining(
                keyword, keyword, keyword, keyword);
    }
}
