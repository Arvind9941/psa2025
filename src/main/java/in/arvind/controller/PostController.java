package in.arvind.controller;

import in.arvind.entity.Comment;
import in.arvind.entity.Post;
import in.arvind.repo.CommentRepo;
import in.arvind.repo.PostRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class PostController {
    private PostRepo postRepo;
    private CommentRepo comment;

    public PostController(PostRepo postRepo, CommentRepo comment) {
        this.postRepo = postRepo;
        this.comment = comment;
    }

    @PostMapping("/post")
    public String createPost(@RequestBody Post post, @RequestBody Comment comment) {
        Post p=new Post();
        p.setComments(Arrays.asList(comment));
        Comment c=new Comment();
        c.setPost(p);
        postRepo.save(p);
        return "Post created successfully!";
    }
}
