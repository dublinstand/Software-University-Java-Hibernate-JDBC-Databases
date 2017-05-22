package com.softuni._hibernatecodefirst.dao;


import com.softuni._hibernatecodefirst.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CommentDao extends JpaRepository<Comment, Long> {
}
