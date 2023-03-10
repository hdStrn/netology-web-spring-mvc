package ru.netology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
  private final PostRepository repository;

  @Autowired
  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public List<Post> all() {
    return repository.all();
  }

  public Post getById(long id) {
    return repository.getById(id).orElseThrow(() ->
            new NotFoundException("There is no post with ID " + id));
  }

  public Post save(Post post) {
    return repository.save(post).orElseThrow(() ->
            new NotFoundException("There is no post with ID " + post.getId()));
  }

  public void removeById(long id) {
    repository.removeById(id).orElseThrow(() ->
            new NotFoundException("There is no post with ID " + id));
  }
}

