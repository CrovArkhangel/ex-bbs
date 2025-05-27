package com.example.controller;

import com.example.domain.Article;
import com.example.form.CreateArticleForm;
import com.example.repository.ArticleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 記事情報を操作するコントローラーです.
 */
@Controller
@RequestMapping("/bbs")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    /**
     * 記事投稿画面に遷移する.
     *
     * @param model モデル
     * @return 記事投稿画面
     */
    @GetMapping("")
    public String index(CreateArticleForm form, Model model){
        List<Article> articleList = articleRepository.findAll();
        model.addAttribute("articleList", articleList);
        return "index";
    }

    /**
     * 記事を投稿する.
     *
     * @param form フォーム
     * @return 記事投稿画面
     */
    @PostMapping("/articlePost")
    public String articlePost(CreateArticleForm form){
        Article article = new Article();
        BeanUtils.copyProperties(form, article);
        articleRepository.create(article);
        return "redirect:/bbs";
    }
}
