package ru.otus.spring.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.MappingStrategy;

@Service
public class CsvMaperServiceImpl implements CsvMaperService {

	public <T> List<T> rowToObject(String resursCsvPath, String[] columns, Class<T> toMapType)
			throws URISyntaxException, FileNotFoundException {
		List<T> result = null;
		try {
			CSVReader csvReader = new CSVReader(new InputStreamReader(
					new FileInputStream(Paths.get(ClassLoader.getSystemResource(resursCsvPath).toURI()).toString()),
					"UTF-8"));
			result = new CsvToBean<T>().parse(createStrategy(toMapType, columns), csvReader);
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	private <T> MappingStrategy<T> createStrategy(Class<T> toMapType, String[] columns) {
		ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategy<T>();
		strategy.setType(toMapType);
		strategy.setColumnMapping(columns);
		return strategy;
	}

}