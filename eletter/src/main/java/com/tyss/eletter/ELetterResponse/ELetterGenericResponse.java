package com.tyss.eletter.ELetterResponse;

import lombok.Data;

@Data
public class ELetterGenericResponse {
	
	private int status;
	private String message;
	private String description;

	
}
