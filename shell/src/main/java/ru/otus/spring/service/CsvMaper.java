package ru.otus.spring.service;

import java.util.List;

public interface CsvMaper {
	<T> List<T> csvToObjectsList(String resursCsvPath, String[] columns, Class<T> toMapType);
}