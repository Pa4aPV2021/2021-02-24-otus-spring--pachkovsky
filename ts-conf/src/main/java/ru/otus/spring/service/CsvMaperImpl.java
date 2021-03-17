package ru.otus.spring.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Component;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.MappingStrategy;

@Component
public class CsvMaperImpl implements CsvMaper {

	public <T> List<T> csvToObjectsList(String resursCsvPath, String[] columns, Class<T> toMapType) {
		List<T> result = null;

		try (CSVReader csvReader = new CSVReader(new InputStreamReader(
				new FileInputStream(Paths.get(ClassLoader.getSystemResource(resursCsvPath).toURI()).toString())))) {

			result = new CsvToBean<T>().parse(createStrategy(toMapType, columns), csvReader);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Failed to convert object sheet from csv file: encoding is not supported", e);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Failed to convert object sheet from csv file: the string could not be parsed as a URI reference.",
					e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to convert object sheet from csv file: file not found", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to convert object sheet from CSV file: unknown error", e);
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