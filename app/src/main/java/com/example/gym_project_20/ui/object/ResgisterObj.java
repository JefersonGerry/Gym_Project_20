package com.example.gym_project_20.ui.object;

public class ResgisterObj {

    private String birthDate;
    private String height;
    private String weight;
    private String gender;

    public ResgisterObj(){

    }

    public ResgisterObj(String birthDate, String height, String weight, String gender) {
        this.birthDate = birthDate;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
