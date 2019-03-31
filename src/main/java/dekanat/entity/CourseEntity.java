package dekanat.entity;

import java.io.Serializable;

public class CourseEntity implements Serializable {
    private int id;
    private String name;
    private int numberOfLectures;
    private int numberOfPractices;
    private String summarize;
    private boolean isNormative;
    private String isNormativeString;
    private int numberOfCredits;
    private int cathedraId;
    private String cathedraName;

    public String getIsNormativeString() {
        return isNormativeString;
    }

    public void setIsNormativeString(String isNormativeString) {
        this.isNormativeString = isNormativeString;
    }

    public String getCathedraName() {
        return cathedraName;
    }

    public void setCathedraName(String cathedraName) {
        this.cathedraName = cathedraName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfLectures() {
        return numberOfLectures;
    }

    public void setNumberOfLectures(int numberOfLectures) {
        this.numberOfLectures = numberOfLectures;
    }

    public int getNumberOfPractices() {
        return numberOfPractices;
    }

    public void setNumberOfPractices(int numberOfPractices) {
        this.numberOfPractices = numberOfPractices;
    }

    public String getSummarize() {
        return summarize;
    }

    public void setSummarize(String summarize) {
        this.summarize = summarize;
    }

    public boolean isNormative() {
        return isNormative;
    }

    public void setNormative(boolean normative) {
        isNormative = normative;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public int getCathedraId() {
        return cathedraId;
    }

    public void setCathedraId(int cathedraId) {
        this.cathedraId = cathedraId;
    }

    @Override
    public String toString() {
        return name;
    }
}
