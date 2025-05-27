package com.example.repository;

import com.example.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * articlesテーブルを操作するリポジトリーです.
 */
@Repository
public class ArticleRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
        Article article = new Article();
        article.setId(rs.getInt("id"));
        article.setName(rs.getString("name"));
        article.setContent(rs.getString("content"));
        return article;
    };

    /**
     * 全記事情報を取得する.
     *
     * @return 全記事情報
     */
    public List<Article> findAll(){
        String sql = """
                select id, name, content from articles order by id desc;
                """;
        return template.query(sql, ARTICLE_ROW_MAPPER);
    }
}
