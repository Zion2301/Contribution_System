package org.example;

public class ContributionParams {
    private String Firstname;
    private double contribution;
    private String Lastname;

    public ContributionParams(String firstname, double contribution, String lastname) {
        Firstname = firstname;
        this.contribution = contribution;
        Lastname = lastname;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public double getContribution() {
        return contribution;
    }

    public void setContribution(double contribution) {
        this.contribution = contribution;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    @Override
    public String toString() {
        return "ContributionParams{" +
                "firstname='" + Firstname + '\'' +
                ", contribution=" + contribution +
                ", lastname='" + Lastname + '\'' +
                '}';
    }
}
