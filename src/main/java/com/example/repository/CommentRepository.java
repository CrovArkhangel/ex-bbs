package com.example.repository;

import com.example.domain.Comment;
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
 * commentsテーブルを操作するリポジトリーです.
 */
@Repository
@Transactional
public class CommentRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
        Comment comment = new Comment();
        comment.setId(rs.getInt("id"));
        comment.setName(rs.getString("name"));
        comment.setContent(rs.getString("content"));
        comment.setArticleId(rs.getInt("article_id"));
        return comment;
    };

    /**
     * 記事Idが一致するコメントを全件取得する.
     *
     * @param articleId 記事Id
     * @return 記事Idに対応するコメント一覧
     */
    public List<Comment> findByArticleId(Integer articleId){
        String sql =  """
                select id, name, content, article_id from comments where article_id = :articleId order by id desc;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
        return template.query(sql, param, COMMENT_ROW_MAPPER);
    }

    /**
     * コメントを投稿する.
     *
     * @param comment 投稿するコメント情報
     */
    public void insertComment(Comment comment){
        SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
        String sql = """
                insert into comments(name, content, article_id) values(:name, :content, :articleId);
                """;
        template.update(sql, param);
    }

    /**
     * 削除した記事に紐づくコメントを削除する.
     *
     * @param articleId 削除する記事id
     */
    public void deleteComment(Integer articleId){
        SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
        String sql = """
                DELETE FROM comments WHERE article_id = :articleId;
                """;
        template.update(sql, param);
    }
}
