package org.example;

import java.util.List;

public interface ContributionInterface {
     boolean addConribution (String name, String Lastname, double amount);
     List<ContributionParams> viewContributions();
     void deleteContribution(String LastName);
     ContributionParams searchContribution( String LastName);

}
