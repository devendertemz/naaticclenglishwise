package com.englishwise.naaticclenglishwise.Modal;

public class GetMockDetailsResponseData {
    private String TestTitle,Date;
    private String TotalQuestion,AttemptQuestion;

    public String getTotalQuestion() {
        return TotalQuestion;
    }

    public void setTotalQuestion(String totalQuestion) {
        TotalQuestion = totalQuestion;
    }

    public String getAttemptQuestion() {
        return AttemptQuestion;
    }

    public void setAttemptQuestion(String attemptQuestion) {
        AttemptQuestion = attemptQuestion;
    }

    public GetMockDetailsResponseData(String testTitle, String date, String totalQuestion, String attemptQuestion) {
        TestTitle = testTitle;
        Date = date;
        TotalQuestion = totalQuestion;
        AttemptQuestion = attemptQuestion;
    }

    public GetMockDetailsResponseData(String testTitle, String date) {
        TestTitle = testTitle;
        Date = date;
    }

    public String getTestTitle() {
        return TestTitle;
    }

    public void setTestTitle(String testTitle) {
        TestTitle = testTitle;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
