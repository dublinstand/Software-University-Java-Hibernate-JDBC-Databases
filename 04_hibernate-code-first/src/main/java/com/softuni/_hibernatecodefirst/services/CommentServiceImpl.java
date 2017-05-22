package com.softuni._hibernatecodefirst.services;

import com.softuni._hibernatecodefirst.dao.CommentDao;
import com.softuni._hibernatecodefirst.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentDao commentDao;

    @Override
    public void create(Comment comment) {
        this.commentDao.saveAndFlush(comment);
    }
}
