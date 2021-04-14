package com.example.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.beans.Document;
import com.example.utils.FileChecksum;

@Controller
public class MainController {

	@Autowired
	FileChecksum fcs;

	@GetMapping("/")
	public String getHome(Model model) {
		model.addAttribute("document", new Document());
		return "home";
	}

	@PostMapping("/check")
	public String postHome(@RequestParam("file") MultipartFile multipartFile, @RequestParam("algo") String algo,
			Model model) {
		if (algo == null) {
			algo = "sha-1";
		}
		try {
			FileInputStream fis = (FileInputStream) multipartFile.getInputStream();
			String checksum = null;
			switch (algo) {
			case "sha-1":
				checksum = fcs.sha1Checksum(fis);
				break;
			case "sha-256":
				checksum = fcs.sha256Checksum(fis);
				break;
			case "sha-512":
				checksum = fcs.sha512Checksum(fis);
				break;
			default:
				checksum = fcs.sha1Checksum(fis);
			}
			Document document = new Document();
			document.setName(multipartFile.getOriginalFilename());
			document.setChecksum(checksum);
			document.setSize(bytesToHuman(multipartFile.getSize()));
			model.addAttribute("document", document);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "home";
	}

	@GetMapping("/check")
	public String fallback(Model model) {
		model.addAttribute("document", new Document());
		return "home";
	}

	private static String floatForm(double d) {
		return new DecimalFormat("#.##").format(d);
	}

	private static String bytesToHuman(long size) {
		long Kb = 1 * 1024;
		long Mb = Kb * 1024;
		long Gb = Mb * 1024;
		long Tb = Gb * 1024;

		if (size < Kb)
			return floatForm(size) + " byte";
		if (size >= Kb && size < Mb)
			return floatForm((double) size / Kb) + " Kb";
		if (size >= Mb && size < Gb)
			return floatForm((double) size / Mb) + " Mb";
		if (size >= Gb && size < Tb)
			return floatForm((double) size / Gb) + " Gb";

		return "Invalid file size";
	}

}
