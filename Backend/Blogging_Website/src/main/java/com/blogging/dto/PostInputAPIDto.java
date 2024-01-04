package com.blogging.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostInputAPIDto {

	@NotEmpty
	@Size(min = 2, message = "Title should be of atleast length 2")
	private String title;

	@NotEmpty
	@Size(min = 2, message = "Description should be of atleast length 2")
	private String description;

	@NotEmpty
	@Size(min = 2, message = "Content should be of atleast length 2")
	private String content;

	private int categoryId;
	
}
