package com.example.utils;

import java.io.FileInputStream;

public interface FileChecksum {
	public String sha1Checksum(FileInputStream fis);

	public String sha256Checksum(FileInputStream fis);

	public String sha512Checksum(FileInputStream fis);
}
