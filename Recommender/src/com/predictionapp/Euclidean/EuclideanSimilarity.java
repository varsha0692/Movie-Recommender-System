package com.predictionapp.Euclidean;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;

public class EuclideanSimilarity {

	public static void main(String[] args) throws IOException, TasteException {
		DataModel model = new FileDataModel(new File("data/childrenratings.csv"));
	    //Compute the similarity between users, according to their preferences
	    UserSimilarity similarity = new EuclideanDistanceSimilarity(model);
	    //Group the users with similar preferences
	    UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1,similarity,model);
	    //Create a recommender
	    UserBasedRecommender recommender = new GenericUserBasedRecommender(model,neighborhood,similarity);
	    //For the uesr with id 1 get two recommendations
	    List<RecommendedItem> recommendations = recommender.recommend(1, 2);
	    for(RecommendedItem recommendation : recommendations) {
	    System.out.println("User 1 might like the movie with ID: "
	            + recommendation.getItemID() + "(predicted Euclidean Distance similarity : "
	            + recommendation.getValue() + ")");
	}

	}

}
