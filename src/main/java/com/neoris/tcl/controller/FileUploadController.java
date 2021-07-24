package com.neoris.tcl.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.neoris.tcl.models.HfmAccEntriesDet;
import com.neoris.tcl.services.IHfmAccEntriesDetService;
import com.neoris.tcl.utils.ExcelReader;
import com.neoris.tcl.utils.Functions;

@Controller(value = "fileUpload")
@Scope(scopeName = org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST)
public class FileUploadController {

	private final static Logger LOG = LoggerFactory.getLogger(FileUploadController.class);
	private UploadedFile file;

	@Autowired
	private IHfmAccEntriesDetService servicedet;

	public FileUploadController() {

	}

	@PostConstruct
	public void init() {
		LOG.info("FileUploadController Post constructor");
	}

	@PostMapping("/uploadHfmAccEntriesDet")
	public String handlePostHfmAccEntriesDetFile(@RequestParam("filexls") MultipartFile file) {
		LOG.info("[handlePostHfmAccEntriesDetFile] recibo archivo:{}", file.getOriginalFilename());
		try {
			ExcelReader<HfmAccEntriesDet> reader = new ExcelReader<>(HfmAccEntriesDet.class);
			List<HfmAccEntriesDet> lstHfmAccEntriesDet = reader.readFile(file.getInputStream());
			lstHfmAccEntriesDet.forEach(i -> LOG.info(i.toString()));
			servicedet.saveAll(lstHfmAccEntriesDet);
		} catch (IOException e) {
			LOG.error("** IOException **: {}", e.getMessage(), e);
		}
		return "fileUpload.xhtml";
	}

	public void upload() {
		String message;
		if (file != null) {
			message = "Successful. " + file.getFileName() + " is uploaded.";
			Functions.addInfoMessage("File Upload", message);
		} else {
			message = "Error! File couldn't be uploaded!!";
			Functions.addErrorMessage("Error", message);
		}
		LOG.info(message);
	}

	public void handleFileUpload(FileUploadEvent event) {
		file = event.getFile();
		String message = "Successful. " + file.getFileName() + " is uploaded.";
		Functions.addInfoMessage("File Upload", message);
		LOG.info(message);
	}

	public UploadedFile getFile() {
		LOG.info("[setFile] get file:{}", file);
		return file;
	}

	public void setFile(UploadedFile file) {
		LOG.info("[setFile] recibo file:{}", file.getFileName());
		this.file = file;
	}

	public String getButton1() {
		return "Choose File";
	}

	public String getContextPath() {
		return Functions.getContextPath();
	}

}
