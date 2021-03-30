package com.englishwise.naaticclenglishwise.Modal;

public class VocabularyModel {

    String Englis, SuportedWord;

    public VocabularyModel(String englis, String suportedWord) {
        Englis = englis;
        SuportedWord = suportedWord;
    }

    public String getEnglis() {
        return Englis;
    }

    public void setEnglis(String englis) {
        Englis = englis;
    }

    public String getSuportedWord() {
        return SuportedWord;
    }

    public void setSuportedWord(String suportedWord) {
        SuportedWord = suportedWord;
    }
}
