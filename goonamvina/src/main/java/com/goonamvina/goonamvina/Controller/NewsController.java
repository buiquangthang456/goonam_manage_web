package com.goonamvina.goonamvina.Controller;

import com.goonamvina.goonamvina.model.News;
import com.goonamvina.goonamvina.model.ProductCategory;
import com.goonamvina.goonamvina.model.ProjectCategory;
import com.goonamvina.goonamvina.service.NewsService;
import com.goonamvina.goonamvina.service.PCService;
import com.goonamvina.goonamvina.service.ProjectCategoryService;
import com.goonamvina.goonamvina.service.ProjectService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private PCService pcService;
    @Autowired
    private ProjectCategoryService projectCategoryService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("")
    public String news_list(Model model, HttpServletRequest request) {
        Locale locale = RequestContextUtils.getLocale(request);  // Lấy ngôn ngữ từ LocaleResolver
        String lang = locale.getLanguage();   // Lấy mã ngôn ngữ ('vi' hoặc 'en')   // Lấy mã ngôn ngữ ('vi' hoặc 'en')

        List<ProductCategory> productCategoryList = pcService.getAllPC();
        model.addAttribute("productCategoryList", productCategoryList);

        List<ProjectCategory> projectCategoryList = projectCategoryService.getAllProjectCategory();
        model.addAttribute("projectCategoryList", projectCategoryList);

        List<News> newsList = newsService.getAllNews();
        model.addAttribute("newsList", newsList);
        model.addAttribute("lang", lang);  // Add the language to the model

        return "news/news";
    }
    @GetMapping("/{slug}")
    public String viewNews(@PathVariable("slug") String slug, Model model, HttpServletRequest request) {
        Locale locale = RequestContextUtils.getLocale(request);  // Lấy ngôn ngữ từ LocaleResolver
        String lang = locale.getLanguage();   // Lấy mã ngôn ngữ ('vi' hoặc 'en')   // Lấy mã ngôn ngữ ('vi' hoặc 'en')


        List<ProductCategory> productCategoryList = pcService.getAllPC();
        model.addAttribute("productCategoryList", productCategoryList);

        List<ProjectCategory> projectCategoryList = projectCategoryService.getAllProjectCategory();
        model.addAttribute("projectCategoryList", projectCategoryList);

        News news = newsService.getNewsBySlug(slug);
        if (news == null) {
            return "error/404";  // Return error page if news not found
        }
        model.addAttribute("news", news);
        model.addAttribute("lang", lang);  // Add the language to the model

        List<News> relatedNewsList = newsService.findRandomNews(news.getId(), 5);
        model.addAttribute("relatedNewsList", relatedNewsList);

        return "news/details-news";
    }
}
