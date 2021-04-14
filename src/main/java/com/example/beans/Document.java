package com.example.beans;

import org.springframework.web.multipart.MultipartFile;

public class Document {
	private MultipartFile[] doc;
	private String name;
	private String size;
	private String checksum;

	public Document() {
		super();
	}

	public Document(MultipartFile[] doc, String name, String size, String checksum) {
		super();
		this.doc = doc;
		this.name = name;
		this.size = size;
		this.checksum = checksum;
	}

	public MultipartFile[] getDoc() {
		return doc;
	}

	public void setDoc(MultipartFile[] doc) {
		this.doc = doc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

}
