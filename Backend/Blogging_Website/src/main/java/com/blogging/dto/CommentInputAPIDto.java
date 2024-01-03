package com.blogging.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentInputAPIDto {

	@NotEmpty
	@Size(min = 2, message = "Name must be of minimum 2 character")
	private String name;

	@NotEmpty
	@Email(message = "The email must be in \"abc@xyz.com\"")
	private String email;

	@NotEmpty
	@Size(min = 2, message = "The body should be of atleast size 2")
	private String body;
	
}
