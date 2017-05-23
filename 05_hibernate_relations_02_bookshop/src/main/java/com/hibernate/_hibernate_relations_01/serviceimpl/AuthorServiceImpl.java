package com.hibernate._hibernate_relations_01.serviceimpl;


import com.hibernate._hibernate_relations_01.dao.AuthorDao;
import com.hibernate._hibernate_relations_01.domain.Author;
import com.hibernate._hibernate_relations_01.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorDao authorDao;


    @Override
    public void create(Author author) {
        this.authorDao.save(author);
    }

    @Override
    public void delete(Author author) {
        this.authorDao.delete(author);
    }

    @Override
    public void delete(Long id) {
        this.authorDao.delete(id);
    }

    @Override
    public Author findAuthor(Long id) {
        return this.authorDao.findOne(id);
    }

    @Override
    public Iterable<Author> findAuthors() {
        return this.authorDao.findAll();
    }
}
