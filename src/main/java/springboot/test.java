package springboot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONObject;

import algorithm.TripleDES;

public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		String[] billCode = { "PA04PY1028290", "PK02000115244", "PA22060730936", "PK02000112439", "PA18CD0024716",
				"PK02000115265", "PA18CD0003132", "PD09000033408", "PE03000051827", "PA22060632149", "PB15070008313",
				"PD09000033511", "PD0900T008960", "PD0400T021822", "PA22060716201", "PA22040478746", "PD09000334781",
				"PA05020061715", "PK02000114204", "PD27007650612", "PD09000056409", "PA05020036070", "PD02T673237",
				"PD07000033035", "PB15070061445", "PB15070053620", "PD09000325300", "PD0900T008570", "PB15070009505",
				"PD0900T005657", "PE16000006084", "PD0900T008868", "PD0900T008426", "PD0900T008525", "PD23008161680",
				"PB04020047372", "PD09000303849", "PD09000001804", "PD09000033689", "PA22040511908", "PA18CD0013764",
				"PK10000067954", "PA18CD0017576", "PA22060632475", "PE13000042315", "PD27007668521", "PA22060720912",
				"PA22060632270", "PD27007650498", "PK08000025805", "PA22060632616", "PD09000005678", "PA22010170161",
				"PA04PL1026854", "PB02010091676", "PE10000011229", "PA23AT0008002", "PA22010169857", "PK08000061896",
				"PD30000114677" };

//		String[] arrdata = data.split("\n");
		ArrayList<String> logLine = readFileLog("./files_data/console.log.4");
		ArrayList<String> logLine1 = readFileLog("./files_data/console.log.5");
		ArrayList<String> logLine2 = readFileLog("./files_data/console.log.6");
		StringBuffer stbuff = new StringBuffer();
		for (String string : billCode) {
			if (string.isEmpty())
				continue;
			else {
				
				for (String string2 : logLine) {
					int indexToCut = string2.indexOf("Body:");
					String json = string2.substring(indexToCut+5, string2.length());
					System.out.println("xxx="+ json);
					JSONObject jsonObject = new JSONObject(json);
					JSONObject jsonObject_ = jsonObject.getJSONObject("message");
					
					String billCodeSent = jsonObject_.getString("billing_code").toString().trim();
					if (billCodeSent.equalsIgnoreCase(string)) {
						System.out.println(string+ "=>>FIND:" + jsonObject.getString("reference_code").toString());
						stbuff.append(jsonObject.getString("reference_code").toString()+"\n");
					}
				}

				for (String string2 : logLine1) {
					int indexToCut = string2.indexOf("Body:");
					String json = string2.substring(indexToCut+5, string2.length());
					
					System.out.println("xxx1="+ json);
					try {
						JSONObject jsonObject = new JSONObject(json);
						JSONObject jsonObject_ = jsonObject.getJSONObject("message");
						
						String billCodeSent = jsonObject_.getString("billing_code").toString().trim();
						if (billCodeSent.equalsIgnoreCase(string)) {
							System.out.println(string+ "=>>FIND:" + jsonObject.getString("reference_code").toString());
							stbuff.append(jsonObject.getString("reference_code").toString()+"\n");
						}	
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}

				for (String string2 : logLine2) {
					int indexToCut = string2.indexOf("Body:");
					String json = string2.substring(indexToCut+5, string2.length());
					
					System.out.println("xxx2="+ json);
					try {
						JSONObject jsonObject = new JSONObject(json);
						JSONObject jsonObject_ = jsonObject.getJSONObject("message");
						
						String billCodeSent = jsonObject_.getString("billing_code").toString().trim();
						
						if (billCodeSent.equalsIgnoreCase(string)) {
							System.out.println(string+ "=>>FIND:" + jsonObject.getString("reference_code").toString());
							stbuff.append(jsonObject.getString("reference_code").toString() +"\n");
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
//				JSONObject jsonObject = new JSONObject(string);
//				String realData = jsonObject.get("data").toString();
//
				try {
//					String clear = TripleDES.decrypt("5d7eb8e355ebb8598ffe5f6a", realData);
					FileWriter fileWriter = new FileWriter("./file.out", true);
					fileWriter.append(stbuff.toString() + "\n");
					fileWriter.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static ArrayList<String> readFileLog(String input) {
		ArrayList<String> lstString = new ArrayList<>();
		try {
			// pass the path to the file as a parameter
			File file = new File(input);
			Scanner sc = new Scanner(file);

			while (sc.hasNextLine()) {
				String logline = sc.nextLine();
				if (logline.contains("Body:{\"pr_code\":\"1010\"")) {
//					System.out.println(logline);
					lstString.add(logline);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstString;
	}
}
