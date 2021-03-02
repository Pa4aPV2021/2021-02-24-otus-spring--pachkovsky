package ru.otus.spring.service;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

public interface CsvReader {
	<T> List<T> readDefaultCsvFile(String[] columns, Class<T> toMapType) throws FileNotFoundException, URISyntaxException;
}