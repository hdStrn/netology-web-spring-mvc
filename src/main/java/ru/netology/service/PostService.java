package ru.netology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.IRepository;

import java.util.List;

@Service
public class PostService implements IService<Post> {
    private final IRepository<Post> repository;

    @Autowired
    public PostService(IRepository<Post> repository) {
        this.repository = repository;
    }

    @Override
    public List<Post> getAll() {
        return repository.getAll();
    }

    @Override
    public Post getById(long id) {
        return repository.getById(id).orElseThrow(() ->
                new NotFoundException("There is no post with ID " + id));
    }

    @Override
    public Post save(Post post) {
        return repository.save(post).orElseThrow(() ->
                new NotFoundException("There is no post with ID " + post.getId()));
    }

    @Override
    public String removeById(long id) {
        repository.removeById(id).orElseThrow(() ->
                new NotFoundException("There is no post with ID " + id));
        return "Post with ID " + id + " successfully deleted!";
    }
}

