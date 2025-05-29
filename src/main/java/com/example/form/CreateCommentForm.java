package com.example.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * コメント投稿用のフォームクラスです.
 */
public class CreateCommentForm {
    /** 投稿者名 */
    @NotBlank(message = "名前を入力してください")
    @Size(max = 50, message = "名前は50文字以内で入力してください")
    private String name;
    /** コメント内容 */
    @NotBlank(message = "内容を入力してください")
    @Size(max = 200, message = "内容は200文字以内で入力してください")
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
