package com.example.msntest.core.model;

public class AvgScoreAndModusEmotionResult {
    public String name;
    public String created;
    public double averageScore;
    public String modusEmotion;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public String getModusEmotion() {
        return modusEmotion;
    }

    public void setModusEmotion(String modusEmotion) {
        this.modusEmotion = modusEmotion;
    }
}
