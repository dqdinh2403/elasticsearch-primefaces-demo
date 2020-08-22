package com.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonUtil {
	
	private static Logger logger = Logger.getLogger(CommonUtil.class);
	
	public static String readJsonFile(String fileName) {
		try {
			FileReader fileReader = new FileReader(fileName);
			JSONParser jsonParser = new JSONParser();
			Object object = jsonParser.parse(fileReader);
			
			JSONObject candidate = (JSONObject) object;
			return candidate.toJSONString();
		} catch (FileNotFoundException e) {
			logger.error("Read file: " + e.getMessage());
		} catch (IOException | ParseException e) {
			logger.error("Parsing: " + e.getMessage());
		}
		return StringUtils.EMPTY;
	}

}
