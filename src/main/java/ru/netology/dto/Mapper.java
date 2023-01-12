package ru.netology.dto;

import org.springframework.stereotype.Component;
import ru.netology.model.Post;

@Component
public class Mapper {

    public PostDTO mapToPostDTO(Post post) {
        return new PostDTO(post.getId(), post.getContent());
    }
}
