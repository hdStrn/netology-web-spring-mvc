package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {

    private ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);

    public List<Post> all() {
        List<Post> validPosts = posts.values().stream()
                .filter(post -> !post.isRemoved())
                .toList();
        return new ArrayList<>(validPosts);
    }

    public Optional<Post> getById(long id) {
        Post post = posts.get(id);
        if (post == null) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(post.isRemoved() ? null : post);
        }
    }

    public Optional<Post> save(Post post) {
        long postId = post.getId();
        if (postId == 0) {
            long newPostId = idCounter.getAndIncrement();
            post.setId(newPostId);
            posts.put(newPostId, post);
            return Optional.of(posts.get(newPostId));
        } else {
            if (posts.containsKey(postId) && !posts.get(postId).isRemoved()) {
                posts.put(postId, post);
                return Optional.of(posts.get(postId));
            } else {
                return Optional.empty();
            }
        }
    }

    public Optional<Post> removeById(long id) {
        Post postToRemove = posts.get(id);
        if (postToRemove != null) {
            postToRemove.setRemoved(true);
        }
        return Optional.ofNullable(postToRemove);
    }
}
