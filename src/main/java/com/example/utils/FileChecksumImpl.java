package com.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class FileChecksumImpl implements FileChecksum {

	@Override
	public String sha1Checksum(InputStream fis) {
		MessageDigest sha1Digest = null;
		try {
			sha1Digest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String checksum = null;
		try {
			checksum = getFileChecksum(sha1Digest, fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return checksum;
	}

	@Override
	public String sha256Checksum(InputStream fis) {
		MessageDigest sha256Digest = null;
		try {
			sha256Digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String checksum = null;
		try {
			checksum = getFileChecksum(sha256Digest, fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return checksum;
	}

	@Override
	public String sha512Checksum(InputStream fis) {
		MessageDigest sha512Digest = null;
		try {
			sha512Digest = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String checksum = null;
		try {
			checksum = getFileChecksum(sha512Digest, fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return checksum;
	}

	private static String getFileChecksum(MessageDigest digest, InputStream fis) throws IOException {

		byte[] byteArray = new byte[1024];
		int bytesCount = 0;
		while ((bytesCount = fis.read(byteArray)) != -1) {
			digest.update(byteArray, 0, bytesCount);
		}
		fis.close();
		byte[] bytes = digest.digest();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}
