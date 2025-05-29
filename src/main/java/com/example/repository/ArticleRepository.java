package com.example.repository;

import com.example.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * articlesテーブルを操作するリポジトリーです.
 */
@Repository
public class ArticleRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    /** articles left outer join comment dto */
    public record ArticleCommentDTO(
            Integer id,
            String name,
            String content,
            Integer comId,
            String comName,
            String comContent,
            Integer articleId
    ) {}

    private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
        Article article = new Article();
        article.setId(rs.getInt("id"));
        article.setName(rs.getString("name"));
        article.setContent(rs.getString("content"));
        return article;
    };

    private static final RowMapper<ArticleCommentDTO> ARTICLE_COMMENT_DTO_ROW_MAPPER = (rs, i) ->
            new ArticleCommentDTO(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("content"),
                rs.getObject("com_id", Integer.class),
                rs.getString("com_name"),
                rs.getString("com_content"),
                rs.getObject("article_id", Integer.class)
            );

    /**
     * 全記事情報とそれに紐づくコメントを取得する.
     *
     * @return 全記事情報とそれに紐づくコメント
     */
    public List<ArticleCommentDTO> findAllIncludeComment(){
        String sql = """
                select a.id as id, a.name as name, a.content as content, c.id as com_id, c.name as com_name, c.content as com_content, c.article_id as article_id
                from articles as a left outer join comments as c
                on a.id = c.article_id order by a.id desc, c.id desc;
                """;
        return template.query(sql, ARTICLE_COMMENT_DTO_ROW_MAPPER);
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
     * 記事を削除する.
     *
     * @param articleId 削除する記事id
     */
    public void deleteArticle(Integer articleId){
        SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
        String sql = """
                DELETE FROM articles WHERE id = :articleId;
                """;
        template.update(sql, param);
    }
}
