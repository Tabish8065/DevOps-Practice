package com.blogging.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentInputAPIDto {

	private String name;
	private String email;
	private String body;
	
}
