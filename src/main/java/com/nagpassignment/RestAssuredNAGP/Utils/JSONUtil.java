package com.nagpassignment.RestAssuredNAGP.Utils;

import io.restassured.path.json.JsonPath;

public class JSONUtil {
	
	
	public static String getNodeValue(String responseBody, String nodePath ) {
		  JsonPath jsonPath = new JsonPath(responseBody);
		  return jsonPath.get(nodePath).toString();
	}

}
