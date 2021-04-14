package com.example.utils;

import java.io.InputStream;

public interface FileChecksum {
	public String sha1Checksum(InputStream fis);

	public String sha256Checksum(InputStream fis);

	public String sha512Checksum(InputStream fis);
}
