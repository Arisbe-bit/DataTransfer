package com.neoris.tcl.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.primefaces.model.file.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelReader<T> {

	private final static Logger LOG = LoggerFactory.getLogger(ExcelReader.class);
	private Class<T> clazz;

	public ExcelReader(Class<T> clazz) {
		this.clazz = clazz;
	}

	/**
	 * 
	 * @param excelFile
	 * @return
	 */
	public List<T> readFile(InputStream excelFile) {
		List<T> lstRetval = new ArrayList<>();

		try {
			Workbook workbook = WorkbookFactory.create(excelFile);

			if (workbook.getNumberOfSheets() > 0) {
				lstRetval = processSheet(workbook.getSheetAt(0));
			}

		} catch (EncryptedDocumentException e) {
			LOG.error("** EncryptedDocumentException **: {}", e.getMessage());
		} catch (IOException e) {
			LOG.error("** IOException **: {}", e.getMessage());
		}

		return lstRetval;
	}

	public List<T> readFile(UploadedFile excelFile) {
		List<T> lstRetval = null;
		LOG.info("[readFile] Entering to read file:{}", excelFile.getFileName());
		try {
			lstRetval = readFile(excelFile.getInputStream());
		} catch (IOException e) {
			LOG.error("** IOException **: {}", e.getMessage());
			lstRetval = new ArrayList<>();
		}
		return lstRetval;
	}

	/**
	 * 
	 * @param excelFile
	 */
	public List<T> readFile(File excelFile) {

		List<T> lstRetval = null;
		LOG.info("[readFile] Entering to read file:{}", excelFile.getName());

		try {
			lstRetval = readFile(new FileInputStream(excelFile));
		} catch (FileNotFoundException e) {
			LOG.error("** IOException **: {}", e.getMessage());
			lstRetval = new ArrayList<>();
		}
		return lstRetval;
	}

	/**
	 * Process a given sheet and create a List of <T> elements.
	 * 
	 * @param sheet
	 */
	private List<T> processSheet(Sheet sheet) {

		List<T> lstRetval = new ArrayList<>();
		List<String> lstHeaders = new ArrayList<>();

		Row rowHerader = sheet.getRow(0);

		// Get the header of the sheet table. It will helps to populate the desired
		// bean.
		rowHerader.forEach(cell -> lstHeaders.add(readCellValue(cell).toLowerCase()));

		// Gets the data from the sheet
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row currRow = sheet.getRow(i);
			try {
				T bean = getInstanceOfT();
				for (int j = 0; j < lstHeaders.size(); j++) {
					BeanUtils.setProperty(bean, lstHeaders.get(j), readCellValueAsObject(currRow.getCell(j)));
				}
				lstRetval.add(bean);
			} catch (InstantiationException | IllegalAccessException e) {
				LOG.error("Exception creating bean: {}", e.getMessage());
			} catch (InvocationTargetException e) {
				LOG.error("InvocationTargetException populating bean: {}", e.getMessage());
			}
		}

		return lstRetval;
	}

	/**
	 * Return the String value of a given cell.
	 * 
	 * @param cell
	 * @return Cell value as String.
	 */
	private String readCellValue(Cell cell) {
		return String.valueOf(readCellValueAsObject(cell));
	}

	/**
	 * Read the value of a given Cell and get its value as Object.
	 * 
	 * @param cell
	 * @return Object with the value of cell.
	 */
	private Object readCellValueAsObject(Cell cell) {
		Object retval;
		if (cell == null) {
			return "";
		}
		switch (cell.getCellType()) {
		case BOOLEAN:
			retval = new Boolean(cell.getBooleanCellValue());
			break;
		case STRING:
			retval = cell.getStringCellValue();
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				retval = cell.getDateCellValue();
			} else {
				retval = cell.getNumericCellValue();
			}
			break;
		case FORMULA:
			retval = cell.getCellFormula();
			break;
		case BLANK:
			retval = "";
			break;
		default:
			retval = "";
		}
		return retval;
	}

	/**
	 * Creates a instance of the given <T> generic type parameter
	 * 
	 * @return A new Instance of given generic class.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private T getInstanceOfT() throws InstantiationException, IllegalAccessException {
		return clazz.newInstance();
	}
}
