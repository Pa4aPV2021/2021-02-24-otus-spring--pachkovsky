package ru.otus.spring.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

public class CsvReaderImpl implements CsvReader {
	private String defaultCsvResourcePath;

	public String getDefaultCsvResourcePath() {
		return defaultCsvResourcePath;
	}

	public void setDefaultCsvResourcePath(String defaultCsvResourcePath) {
		this.defaultCsvResourcePath = defaultCsvResourcePath;
	}

	public <T> List<T> readDefaultCsvFile(String[] columns, Class<T> toMapType)
			throws FileNotFoundException, URISyntaxException {
		CsvToBean<T> csv = new CsvToBean<T>();
		String csvFilename = Paths.get(ClassLoader.getSystemResource(defaultCsvResourcePath).toURI()).toString();
		CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
		ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategy<T>();
		strategy.setType(toMapType);
		strategy.setColumnMapping(columns);
		return csv.parse(strategy, csvReader);
	}
}