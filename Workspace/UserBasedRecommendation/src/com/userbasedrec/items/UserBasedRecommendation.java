package com.userbasedrec.items;


import java.io.BufferedReader;
import java.io.FileReader;


public class UserBasedRecommendation {
	
public static void readMovies() {
	BufferedReader br = null;
	//MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	//MongoDatabase database = mongoClient.getDatabase("movieLens");
	String line;
	try {

	br = new BufferedReader(new FileReader("data/Userbasedreco.csv"));

}
	
	catch (Exception e) {
		e.printStackTrace();
	}
	try {

	    while ((line = br.readLine()) != null) {

			String data[]= line.split(",");
		   
		   String userID = data[0];
		   String MovieName = data[1];
}		
	}
	
	catch (Exception e) {
		e.printStackTrace();
	}
}






}

