package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository implements IRepository<Post> {

    private ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);

    @Override
    public List<Post> getAll() {
        List<Post> validPosts = posts.values().stream()
                .filter(post -> !post.isRemoved())
                .toList();
        return new ArrayList<>(validPosts);
    }

    @Override
    public Optional<Post> getById(long id) {
        Post post = posts.get(id);
        if (post == null) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(post.isRemoved() ? null : post);
        }
    }

    @Override
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

    @Override
    public Optional<Post> removeById(long id) {
        Post postToRemove = posts.get(id);
        if (postToRemove == null || postToRemove.isRemoved()) {
            return Optional.empty();
        }
        postToRemove.setRemoved(true);
        return Optional.of(postToRemove);
    }
}
