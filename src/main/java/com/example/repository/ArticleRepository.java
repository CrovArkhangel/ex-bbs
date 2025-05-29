package com.example.repository;

import com.example.domain.Article;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * articlesテーブルを操作するリポジトリーです.
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    /**
     * 記事とコメントを全件取得する.
     *
     * @return 全記事とコメント情報
     */
    @EntityGraph(attributePaths = "commentList")
    @Query("SELECT a FROM Article a LEFT OUTER JOIN a.commentList c ORDER BY a.id DESC, c.id DESC")
    List<Article> findAllWithComments();
}
