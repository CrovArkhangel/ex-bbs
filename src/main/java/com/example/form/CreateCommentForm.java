package com.example.form;

/**
 * コメント投稿用のフォームクラスです.
 */
public class CreateCommentForm {
    /** 投稿者名 */
    private String name;
    /** コメント内容 */
    private String content;
    /** 記事ID */
    private Integer articleId;

    @Override
    public String toString() {
        return "CreateCommentForm{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", articleId=" + articleId +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
