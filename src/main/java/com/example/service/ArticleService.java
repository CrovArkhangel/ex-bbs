package com.example.service;

import com.example.domain.Article;
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
    public List<Article> showList(){
        List<Article> articles = articleRepository.findAll();
        List<Article> articleList = new ArrayList<>();
        /* それぞれの記事に対して、対応するコメントリストをセットする */
        for (Article article: articles){
            article.setCommentList(commentRepository.findByArticleId(article.getId()));
            articleList.add(article);
        }
        return articleList;
    }

}
