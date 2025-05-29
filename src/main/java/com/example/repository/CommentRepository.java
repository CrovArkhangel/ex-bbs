package com.example.repository;

import com.example.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * commentsテーブルを操作するリポジトリーです.
 */
public interface CommentRepository  extends JpaRepository<Comment, Integer> {
}
