package com.goonamvina.goonamvina.Controller;

import com.goonamvina.goonamvina.model.Product;
import com.goonamvina.goonamvina.model.ProductCategory;
import com.goonamvina.goonamvina.model.ProductSubCategory;
import com.goonamvina.goonamvina.model.ProjectCategory;
import com.goonamvina.goonamvina.service.*;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;
import java.util.Locale;

@Controller
public class ProductController {

    @Autowired
    private PCService pcService;

    @Autowired
    private PSCService pscService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProjectCategoryService projectCategoryService;
    @Autowired
    private LocaleResolver localeResolver;
    @Autowired
    private ProjectService projectService;
   @GetMapping("/products-list")
    public String products_list(HttpServletRequest request, Model model){
       Locale locale = localeResolver.resolveLocale(request);
       String lang = locale.getLanguage(); // Lấy mã ngôn ngữ hiện tại (vi/en)

       List<ProductCategory> productCategoryList = pcService.getAllPC();
       model.addAttribute("pcList",pcService.getAllPC());
       model.addAttribute("productCategoryList", productCategoryList);

       List<ProjectCategory> projectCategoryList = projectCategoryService.getAllProjectCategory();
       model.addAttribute("projectCategoryList", projectCategoryList);

       return"products-list/products-list";
   }
    @GetMapping("/subproductcategory/{slug}")
    public String subProductList(@PathVariable String slug, Model model, HttpServletRequest request) {
        Locale locale = localeResolver.resolveLocale(request);
        String lang = locale.getLanguage();

        List<ProductCategory> productCategoryList = pcService.getAllPC();
        model.addAttribute("productCategoryList", productCategoryList);

        List<ProjectCategory> projectCategoryList = projectCategoryService.getAllProjectCategory();
        model.addAttribute("projectCategoryList", projectCategoryList);
        ProductCategory productCategory = pcService.getProductCategoryBySlug(slug);
        model.addAttribute("productCategory", productCategory);

        List<ProductSubCategory> spcList = pscService.getSubProductCategoriesByPCId(productCategory.getId());
        model.addAttribute("spcList", spcList);



        return "products-list/subproducts-list";
    }

    @GetMapping("/products/{slug}")
    public String productList(@PathVariable String slug, Model model, HttpServletRequest request) {
        Locale locale = localeResolver.resolveLocale(request);
        String lang = locale.getLanguage(); // Lấy mã ngôn ngữ hiện tại (vi/en)

        // Truyền lang vào model để sử dụng trong template
        model.addAttribute("lang", lang);

        List<ProductCategory> productCategoryList = pcService.getAllPC();
        model.addAttribute("productCategoryList", productCategoryList);

        List<ProjectCategory> projectCategoryList = projectCategoryService.getAllProjectCategory();
        model.addAttribute("projectCategoryList", projectCategoryList);

        ProductSubCategory subCategory = pscService.getPSCBySlug(slug);
        model.addAttribute("subProductCategory", subCategory);

        // Đặt tên cho các hạng mục theo ngôn ngữ
        String sonDonMau = "vi".equals(lang) ? "Sơn đơn màu" : "Solid Color Paint";
        String sonGiaGo = "vi".equals(lang) ? "Sơn giả gỗ" : "Wood Grain Paint";
        String danLaminate = "vi".equals(lang) ? "Dán laminate" : "Laminate Coating";

        // Truyền tên các hạng mục vào model để sử dụng trong template
        model.addAttribute("sonDonMau", sonDonMau);
        model.addAttribute("sonGiaGo", sonGiaGo);
        model.addAttribute("danLaminate", danLaminate);

        if ("cua-thep-cua-thep-chong-chay".equals(subCategory.getSlug())) {
            List<Product> sonDoProducts = productService.getProductsBySubPCSlugAndFinishTypeAndLang(slug, sonDonMau, lang);
            List<Product> sonGiaGoProducts = productService.getProductsBySubPCSlugAndFinishTypeAndLang(slug, sonGiaGo, lang);
            List<Product> danLaminateProducts = productService.getProductsBySubPCSlugAndFinishTypeAndLang(slug, danLaminate, lang);

            model.addAttribute("sonDoProducts", sonDoProducts);
            model.addAttribute("sonGiaGoProducts", sonGiaGoProducts);
            model.addAttribute("danLaminateProducts", danLaminateProducts);
        } else {
            List<Product> productList = productService.getProductsBySubPCSlugAndLang(slug, lang);
            model.addAttribute("productList", productList);
        }



        return "products-list/products";
    }
}
