package com.recommender.customMethod.MockSimilarity;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.PreferenceInferrer;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.util.Collection;

public class MockSimilarity implements UserSimilarity {

    DataModel dataModel;

    MockSimilarity(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public double userSimilarity(long U, long V) throws TasteException {
        return Math.random();
    }

    public void setPreferenceInferrer(PreferenceInferrer preferenceInferrer) {

    }

    public void refresh(Collection<Refreshable> collection) {

    }
}
