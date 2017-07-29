package com.wlezien.json;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.FileWriter;

public class JSONMain {

	public static void main(String[] args) throws IOException {
		
	}
		
		/*
		String filePath;

		if(args.length>0){
			filePath = args[0];
		}

		else{
			System.out.println("setting file name to default (input.txt).\n\n");
			filePath = "../input.txt";
		}

		Map<String,TestJSON> jsons = new HashMap<>();
		
		File input = new File(filePath);
		Scanner scan = new Scanner(input);
		scan.useDelimiter("###---###---###---###---");
		//scan.useDelimiter("(?<={\\s*)-----(?=\\s*})");
		
		while(scan.hasNext()){
			
			final String next=scan.next();
			System.out.println(next);
			System.out.println("-----");
			
			jsons.put(next, new TestJSON() {
				
				public void run(JSONRunner runner,int count) throws IOException {
					
					JSON json = runner.getParser().parseInput(next);
					
					FileWriter output = new FileWriter("output"+count+".json");

					String toString = json.toString();

					output.write(toString);

					output.close();
						
				}
				
			});
			
		}
		
		int count =1;
		for(TestJSON test : jsons.values()){
			test.run(new JSONRunner() {
				
				public JSONParser getParser(){
					
					return new JSONParser();
					
				}
			}   , count );

			count++;
		}
		
	}
	*/

}
