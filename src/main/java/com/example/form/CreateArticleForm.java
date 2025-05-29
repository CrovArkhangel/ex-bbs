package com.example.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 記事投稿用のフォームクラスです.
 */
public class CreateArticleForm {
    /** 投稿者名 */
    @NotBlank(message = "名前を入力してください")
    @Size(max = 50, message = "名前は50文字以内で入力してください")
    private String articleName;
    /** 投稿内容 */
    @NotBlank(message = "内容を入力してください")
    @Size(max = 200, message = "内容は200文字以内で入力してください")
    private String articleContent;

    @Override
    public String toString() {
        return "CreateArticleForm{" +
                "articleName='" + articleName + '\'' +
                ", articleContent='" + articleContent + '\'' +
                '}';
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
}
