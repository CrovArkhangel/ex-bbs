package com.example.service;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.repository.ArticleRepository;
import com.example.repository.ArticleRepository.ArticleCommentDTO;
import com.example.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 記事情報を操作するサービスクラスです.
 */
@Service
@Transactional
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
        List<ArticleCommentDTO> articleCommentDTOList = articleRepository.findAllIncludeComment();
        Map<Integer, Article> articleMap = new LinkedHashMap<>();
        for (ArticleCommentDTO dto : articleCommentDTOList) {
            Article article = articleMap.get(dto.id());
            if (article == null) {
                article = new Article();
                article.setId(dto.id());
                article.setName(dto.name());
                article.setContent(dto.content());
                if(article.getCommentList() == null){
                    article.setCommentList(new ArrayList<>());
                }
                articleMap.put(dto.id(), article);
            }

            // コメントが存在する場合のみ追加
            if (dto.comId() != null) {
                Comment comment = new Comment();
                comment.setId(dto.comId());
                comment.setName(dto.comName());
                comment.setContent(dto.comContent());
                comment.setArticleId(dto.articleId());
                article.getCommentList().add(comment);
            }
        }
        return new ArrayList<>(articleMap.values());
    }

    public void deleteArticleAndComment(Integer id){
        commentRepository.deleteComment(id);
        articleRepository.deleteArticle(id);
    }
}
