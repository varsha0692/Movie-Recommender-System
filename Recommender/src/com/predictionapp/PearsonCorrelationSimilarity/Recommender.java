package com.predictionapp.PearsonCorrelationSimilarity;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;

public class Recommender {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, TasteException {
		// Loading the file into the Datamodel
		DataModel dm = new FileDataModel(new File(
				"data/actionratings.csv"));
		// Grouping users with similarity
		UserSimilarity sim = new PearsonCorrelationSimilarity(dm);

		// Find the neighbourhood
		UserNeighborhood neighbourhood = new NearestNUserNeighborhood(5, sim,
				dm);

		// recommender
		int n;
		PrintWriter writer = null;
		writer = new PrintWriter("/Users/varshav/Desktop/actionratingsPearson.dat", "UTF-8");
		GenericUserBasedRecommender recommender = new GenericUserBasedRecommender(
				dm, neighbourhood, sim);
		for (n = 1; n <= 72000; n++) {
			List<RecommendedItem> recommendations =
			recommender.recommend(n, 5);
			for (RecommendedItem recommendation : recommendations) {
				System.out.println("UserID:"+n + " might like the movie with ID: "+ recommendation.getItemID() + ","
						+ "Pearson Correlation Similarity: "+ recommendation.getValue());
				writer.println("UserID:"+n + " might like the movie with ID: "+ recommendation.getItemID() + ","
						+ "Pearson Correlation Similarity: "+ recommendation.getValue());
				writer.flush();
			}
		}
		writer.flush();
		writer.close();
	}
}
