package ru.netology.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.netology.dto.Mapper;
import ru.netology.dto.PostDTO;
import ru.netology.model.Post;
import ru.netology.service.IService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController implements IController<PostDTO, Post> {

    private final IService<Post> service;
    private final Mapper mapper;

    @Autowired
    public PostController(IService<Post> service, Mapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<PostDTO> getAll() {
        return service.getAll().stream()
                .map(mapper::mapToPostDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO getById(long id) {
        return mapper.mapToPostDTO(service.getById(id));
    }

    @Override
    public PostDTO save(Post post) {
        return mapper.mapToPostDTO(service.save(post));
    }

    @Override
    public String removeById(long id) {
        return service.removeById(id);
    }
}
