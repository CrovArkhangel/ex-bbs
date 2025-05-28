package com.example.controller;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.CreateArticleForm;
import com.example.form.CreateCommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;
import com.example.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleService articleService;

    /**
     * 記事投稿画面に遷移する.
     *
     * @param model モデル
     * @return 記事投稿画面
     */
    @GetMapping("")
    public String index(CreateArticleForm createArticleForm, CreateCommentForm createCommentForm, Model model){
        List<Article> articleList = articleService.showList();
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

    /**
     * コメントを投稿する.
     *
     * @param form フォーム
     * @return 記事投稿画面
     */
    @PostMapping("/commentPost")
    public String commentPost(CreateCommentForm form){
        Comment comment = new Comment();
        BeanUtils.copyProperties(form, comment);
        commentRepository.insertComment(comment);
        return "redirect:/bbs";
    }

    /**
     * 記事と関連するコメントを削除
     *
     * @param articleId 記事Id
     * @return 記事投稿画面
     */
    @GetMapping("/delete/{articleId}")
    public String delete(@PathVariable Integer articleId){
        articleRepository.deleteArticleAndComment(articleId);
        return "redirect:/bbs";
    }
}
