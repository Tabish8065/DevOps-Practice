package com.blogging.Model;

import java.util.HashSet;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Schema(
    description = "The is the Post Model for the database"
)
public class PostModel {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(
        description = "The primary key for the post in the database. The key is auto-generated."
    )
    private int id;
    
    @Schema(
        description = "The title for the post."
    )
    private String title;

    @Schema(
        description = "Description for the post."
    )
    private String description;
    
    @Schema(
        description = "The content of the post"
    )
    private String content;

    @Schema(
        description = "The comment associated wwith the post. Comment should be added using comment API after post has been created in the database."
    )
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CommentModel> comments = new HashSet<CommentModel>();

    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryModel category;

}
