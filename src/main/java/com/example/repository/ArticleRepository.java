package com.example.repository;

import com.example.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * articlesテーブルを操作するリポジトリーです.
 */
@Repository
@Transactional
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

    /**
     * 記事情報を登録する.
     *
     * @param article 登録する記事情報
     */
    public void create(Article article){
        SqlParameterSource param = new BeanPropertySqlParameterSource(article);
        String sql = """
                insert into articles(name, content) values(:name, :content);
                """;
        template.update(sql, param);
    }

    /**
     * 記事とそれに紐づくコメントを削除する.
     *
     * @param articleId 削除する記事id
     */
    public void deleteArticleAndComment(Integer articleId){
        SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
        String sql = """
                DELETE FROM comments WHERE article_id = :articleId;
                DELETE FROM articles WHERE id = :articleId;
                """;
        template.update(sql, param);
    }

//    結合のSQL:select * from articles join comments on articles.id = comments.article_id order by articles.id;
}
