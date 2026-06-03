package com.goonamvina.goonamvina.Controller;

import com.goonamvina.goonamvina.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Controller
public class SitemapController {

    private static final String BASE = "https://goonamvina.com";

    @Autowired private PCService pcService;
    @Autowired private PSCService pscService;
    @Autowired private MetalCategoryService metalCategoryService;
    @Autowired private ProjectCategoryService projectCategoryService;
    @Autowired private ProjectService projectService;
    @Autowired private NewsService newsService;

    @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String sitemap() {
        StringBuilder sb = new StringBuilder(4096);
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");
        String today = LocalDate.now().toString();

        String[] staticUrls = {"/", "/history", "/collective", "/main-role",
                "/products-list", "/projects", "/news", "/contact", "/googenmetal"};
        for (String u : staticUrls) {
            addUrl(sb, u, today, "weekly", "/".equals(u) ? "1.0" : "0.8");
        }
        metalCategoryService.getAllMetalCategory().forEach(mc -> {
            if (mc.getSlug() != null) addUrl(sb, "/googenmetal/" + mc.getSlug(), today, "monthly", "0.7");
        });
        pcService.getAllPC().forEach(pc -> {
            if (pc.getSlug() != null) addUrl(sb, "/subproductcategory/" + pc.getSlug(), today, "monthly", "0.7");
        });
        pscService.getAllPSC().forEach(psc -> {
            if (psc.getSlug() != null) addUrl(sb, "/products/" + psc.getSlug(), today, "monthly", "0.7");
        });
        projectCategoryService.getAllProjectCategory().forEach(pc -> {
            if (pc.getSlug() != null) addUrl(sb, "/projects/" + pc.getSlug(), today, "monthly", "0.7");
        });
        projectService.getAllProject().forEach(p -> {
            if (p.getId() != null) addUrl(sb, "/projects/detail/" + p.getId(), today, "monthly", "0.65");
        });
        newsService.getAllNews().forEach(n -> {
            if (n.getSlug() != null) addUrl(sb, "/news/" + n.getSlug(), today, "weekly", "0.6");
        });

        sb.append("</urlset>\n");
        return sb.toString();
    }

    private void addUrl(StringBuilder sb, String path, String lastmod, String changefreq, String priority) {
        sb.append("  <url>\n");
        sb.append("    <loc>").append(BASE).append(path).append("</loc>\n");
        sb.append("    <lastmod>").append(lastmod).append("</lastmod>\n");
        sb.append("    <changefreq>").append(changefreq).append("</changefreq>\n");
        sb.append("    <priority>").append(priority).append("</priority>\n");
        sb.append("  </url>\n");
    }
}
