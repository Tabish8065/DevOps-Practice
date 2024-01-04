package com.blogging.dto;

import java.util.Set;

import lombok.Data;

@Data
public class PostAPIOutputDto{

    private String title;
    private String description;
    private String content;
    private CategoryDto category;

    private Set<CommentInputAPIDto> comments;

}