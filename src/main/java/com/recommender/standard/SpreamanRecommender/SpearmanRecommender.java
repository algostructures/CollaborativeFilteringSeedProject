package com.recommender.standard.SpreamanRecommender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.SpearmanCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;

import java.io.File;
import java.io.IOException;

public class SpearmanRecommender {
    class MyRecommenderBuilder implements RecommenderBuilder{
        public Recommender buildRecommender(DataModel dm) throws TasteException {
            UserSimilarity similarity = new SpearmanCorrelationSimilarity(dm);
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(10, similarity, dm);
            return new GenericUserBasedRecommender(dm, neighborhood, similarity);//
        }
    }
    public static void main(String arg[]) throws IOException, TasteException, ClassNotFoundException{
        File file = new File(Class.class.getResource("/datasets/preparedData/movies.csv").getFile());
        DataModel model = new FileDataModel(file);
        RandomUtils.useTestSeed();
        RecommenderEvaluator evaluator = new RMSRecommenderEvaluator();
        SpearmanRecommender ev = new SpearmanRecommender();
        RecommenderBuilder builder = ev.new MyRecommenderBuilder();
        double result = evaluator.evaluate(builder, null, model, 0.3, 1.0);
        System.out.println(result);
    }
}
