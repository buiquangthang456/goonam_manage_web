package com.goonamvina.goonamvina.service;
import com.goonamvina.goonamvina.model.News;
import com.goonamvina.goonamvina.repository.NewsRepository;
import com.goonamvina.goonamvina.util.SlugUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public void saveNews(News news) {
        if (news.getSlug() == null || news.getSlug().isEmpty()) {
            news.setSlug(SlugUtil.toSlug(news.getTitleVi()));  // Create slug based on Vietnamese title
        }
        newsRepository.save(news);
    }

    public News getNewsById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }
    public News getNewsBySlug(String slug) {
        return newsRepository.findBySlug(slug).orElse(null);
    }
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }
    public List<News> findRandomNews(Long excludeNewsId, int limit) {
        List<News> allNews = newsRepository.findAllByIdNot(excludeNewsId);
        Collections.shuffle(allNews); // Xáo trộn danh sách tin tức
        return allNews.stream().limit(limit).collect(Collectors.toList()); // Giới hạn số lượng tin tức
    }
    public List<News> searchNews(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return newsRepository.findAll();
        }
        return newsRepository.findByTitleViContainingOrTitleEnContainingOrShortContentViContainingOrShortContentEnContaining(
                keyword, keyword, keyword, keyword);
    }
}
