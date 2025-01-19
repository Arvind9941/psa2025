package in.arvind.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String title;
    private String content;
    @OneToMany(mappedBy = "post" ,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comments;
}
