package com.example.service;

import com.example.domain.Article;
import com.example.domain.Thread;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 記事情報を操作するサービスクラスです.
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    /**
     * 記事とコメントを全件取得する.
     *
     * @return 記事とそれに紐づくコメントのリスト
     */
    public List<Thread> showList(){
        List<Article> articleList = articleRepository.findAll();
        List<Thread> threadList = new ArrayList<>();
        for (Article article: articleList){
            Thread thread = new Thread();
            thread.setArticleId(article.getId());
            thread.setArticleName(article.getName());
            thread.setArticleContent(article.getContent());
            thread.setCommentList(commentRepository.findByArticleId(article.getId()));
            threadList.add(thread);
        }
        return threadList;
    }

}
