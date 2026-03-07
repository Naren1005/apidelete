package utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class dataProvider {

	
	@DataProvider
	public Object[][] excelDataProvider() throws IOException {
		String path = ".\\testdata\\orders_excel_data.xlsx";
		Excelutils xl = new Excelutils(path, "Sheet1");

		int rownum = xl.getRowCount();
		int colcount = xl.getCellCount(1);

		Object dataArray[][] = new Object[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				dataArray[i - 1][j] = xl.getCellData(i, j);
			}
		}
		return dataArray;
}
	
	@DataProvider
	public Object[][] jsonDataProvider() throws IOException {
		// Path to your JSON file
		String filePath = ".\\testdata\\orders_json_data.json";

		// Read JSON file and map it to a List of Maps
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, String>> dataList = objectMapper.readValue(new File(filePath),
				new TypeReference<List<Map<String, String>>>() {
				});

		// Convert List<Map<String, String>> to Object[][]
		Object[][] dataArray = new Object[dataList.size()][];
		for (int i = 0; i < dataList.size(); i++) {
			dataArray[i] = new Object[] { dataList.get(i) };
		}

		return dataArray;
	}
	
}
