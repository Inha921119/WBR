package com.project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.repository.ContentRepository;

@Service
public class ContentService {
	
	private ContentRepository contentRepository;
	
	@Autowired
	public ContentService(ContentRepository contentRepository) {
		this.contentRepository = contentRepository;
	}
}
