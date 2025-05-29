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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
     * @param createArticleForm 記事投稿フォーム
     * @param createArticleResult バリデーション結果
     * @param createCommentForm コメント投稿フォーム
     * @param model モデル
     * @return 記事投稿画面
     */
    @PostMapping("/articlePost")
    public String articlePost(@Validated CreateArticleForm createArticleForm, BindingResult createArticleResult, CreateCommentForm createCommentForm, Model model){
        if (createArticleResult.hasErrors()) {
            List<Article> articleList = articleService.showList();
            model.addAttribute("articleList", articleList);
            model.addAttribute("createCommentForm", createCommentForm);
            return "index"; // エラーメッセージ表示用のビュー
        }
        Article article = new Article();
        article.setName(createArticleForm.getArticleName());
        article.setContent(createArticleForm.getArticleContent());
        articleRepository.create(article);
        return "redirect:/bbs";
    }

    /**
     * コメントを投稿する.
     *
     * @param createCommentForm コメント投稿用フォーム
     * @param createCommentResult バリデーション結果
     * @param createArticleForm 記事投稿フォーム
     * @param model モデル
     * @return 記事投稿画面
     */
    @PostMapping("/commentPost")
    public String commentPost(@Validated CreateCommentForm createCommentForm, BindingResult createCommentResult, CreateArticleForm createArticleForm, Model model){
        if (createCommentResult.hasErrors()) {
            List<Article> articleList = articleService.showList();
            model.addAttribute("articleList", articleList);
            model.addAttribute("createArticleForm", createArticleForm);
            return "index"; // エラーメッセージ表示用のビュー
        }
        Comment comment = new Comment();
        BeanUtils.copyProperties(createCommentForm, comment);
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
        articleService.deleteArticleAndComment(articleId);
        return "redirect:/bbs";
    }
}
