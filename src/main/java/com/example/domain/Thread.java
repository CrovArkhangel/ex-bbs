package com.example.domain;

import java.util.List;

/**
 * 記事とそれに紐づくコメントを表すドメインです.
 */
public class Thread {
    /** 記事id */
    private Integer articleId;
    /** 記事の投稿者名 */
    private String articleName;
    /** 記事内容 */
    private String articleContent;
    /** 紐づくコメントリスト */
    private List<Comment> commentList;

    @Override
    public String toString() {
        return "Thread{" +
                "articleId=" + articleId +
                ", articleName='" + articleName + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", commentList=" + commentList +
                '}';
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
