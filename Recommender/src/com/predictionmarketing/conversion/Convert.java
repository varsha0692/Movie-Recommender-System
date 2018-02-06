package com.predictionmarketing.conversion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Convert {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("data/Userbasedreco.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("data/Userbasedreco.csv"));
		String line;
		while((line = br.readLine()) != null) {
			String[] values = line.split("\\t",-1);
			bw.write(values[0] + "," + values[1]+"\n");
		
		
		}
		br.close();
		bw.close();
		System.out.println("converted! pls refresh");
	}

}
