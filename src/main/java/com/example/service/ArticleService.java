package com.example.service;

import com.example.domain.Article;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 記事情報を操作するサービスクラスです.
 */
@Service
@Transactional
public class ArticleService {
    /** articlesテーブルを操作するリポジトリー */
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
    }

    /**
     * 記事とコメントを全件取得する.
     *
     * @return 記事とそれに紐づくコメントのリスト
     */
    public List<Article> showList() {
        return articleRepository.findAllWithComments();
    }

    /**
     * 記事とそれに紐づくコメントを一括削除する.
     *
     * @param id 記事Id
     */
    public void deleteArticleAndComment(Integer id){
        articleRepository.deleteById(id);
    }
}
