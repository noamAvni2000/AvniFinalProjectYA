package com.example.avnisofshana;

public class Question {

    private final int left;
    private final int right;

    //the key to the function is this
    //-1  →  left <  right
    //0  →  left = right
    //+1  →  left >  right
    private final int correctRelation;

    public Question(int left, int right) {
        this.left  = left;
        this.right = right;
        this.correctRelation = Integer.compare(left, right);//constructor for Question returns the value with the key in the note above
    }

    public boolean isCorrect(int userChoice)
    {
        return userChoice == correctRelation;
    }//function that retruns true if the user answer is correct and false if not

    //getters for all the variables in the class
    public int getLeft()
    {
        return left;
    }
    public int getRight()
    {
        return right;
    }
    public int getCorrectRelation()
    {
        return correctRelation;
    }
}

