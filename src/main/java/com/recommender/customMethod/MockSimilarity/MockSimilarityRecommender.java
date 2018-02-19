package com.recommender.customMethod.MockSimilarity;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;

import java.io.File;
import java.io.IOException;

public class MockSimilarityRecommender {
    class MyRecommenderBuilder implements RecommenderBuilder {
        public Recommender buildRecommender(DataModel dm) throws TasteException {
            UserSimilarity similarity = new MockSimilarity(dm);
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(100, similarity, dm);
            return new GenericUserBasedRecommender(dm, neighborhood, similarity);//
            //return null;
        }
    }
    public static void main(String arg[]) throws IOException, TasteException, ClassNotFoundException{
        File file = new File(Class.class.getResource("/datasets/preparedData/movies.csv").getFile());
        DataModel model = new FileDataModel(file);
        RandomUtils.useTestSeed();
        RecommenderEvaluator evaluator = new RMSRecommenderEvaluator();
        MockSimilarityRecommender ev = new MockSimilarityRecommender();
        RecommenderBuilder builder = ev.new MyRecommenderBuilder();
        double result = evaluator.evaluate(builder, null, model, 0.3, 1.0);

        System.out.println(result);
    }
}
