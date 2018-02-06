package com.predictionmarketing.ItemRecommend;

import java.io.IOException;

import java.io.File;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.common.TasteException;

public class ItemRecommend {
	public static void main(String[] args) throws IOException, TasteException {
		DataModel dm = new FileDataModel(new File("data/actionratings.csv"));
		LogLikelihoodSimilarity sim = new LogLikelihoodSimilarity(dm);
		GenericItemBasedRecommender item = new GenericItemBasedRecommender(dm,
				sim);	
		PrintWriter writer = null;
		writer = new PrintWriter("/Users/varshav/Desktop/actionrecommendation", "UTF-8");

		for(  LongPrimitiveIterator movies = dm.getItemIDs(); 
                               movies.hasNext();){
				 long movieid = movies.nextLong();
			List<RecommendedItem> recommendations = item.mostSimilarItems(movieid, 5);
			for (RecommendedItem recommendation : recommendations) {
				System.out.println(movieid + "," + recommendation.getItemID() + ","
					+ recommendation.getValue());
				
				writer.println(movieid + "," + recommendation.getItemID() + ","
						+ recommendation.getValue());
				writer.flush();
			}
		}
		writer.close();
	}
}

