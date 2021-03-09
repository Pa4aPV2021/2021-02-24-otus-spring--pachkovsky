package ru.otus.spring.service;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

public interface CsvMaperService {
	<T> List<T> rowToObject(String resursCsvPath, String[] columns, Class<T> toMapType)
			throws FileNotFoundException, URISyntaxException;
}