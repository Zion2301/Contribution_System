package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ContributionImpl implements ContributionInterface {
    private final List<ContributionParams> thelist = new ArrayList<>();
    private final ExecutorService executors = Executors.newFixedThreadPool(5);

    @Override
    public boolean addConribution(String Firstname, String Lastname, double contribution) {
        executors.execute(() -> {
            synchronized (thelist) {
                if (contribution <= 0) {
                    System.out.println("Contribution must be greater than zero");
                }
                thelist.add(new ContributionParams(Firstname, contribution, Lastname));
            }
        });
        return true;
    }

    public List<ContributionParams> viewContributions() {
        Future<List<ContributionParams>> future = executors.submit(new Callable<List<ContributionParams>>() {
            @Override
            public List<ContributionParams> call() {
                synchronized (thelist) {
                    // Create a new list to return
                    List<ContributionParams> result = new ArrayList<>(thelist);
                    System.out.println("Viewing contributions");
                    return result; // Return the new list of contributions
                }
            }
        });

        // Get the result from the Future
        try {
            return future.get(); // This will block until the task is complete
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Record is not here");
            return new ArrayList<>(); // Return an empty list in case of error
        }
    }

    @Override
    public void deleteContribution(String LastName) {
        executors.execute(() -> {
            synchronized (thelist) {
                for (int i = 0; i < thelist.size(); i++) {
                    ContributionParams contribute = thelist.get(i);
                    if (contribute.getLastname().equals(LastName)) {
                        thelist.remove(i);
                        System.out.println("Removed contribution from " + LastName);
                        break;
                    }
                }
            }
        });
    }

    @Override
    public ContributionParams searchContribution(String lastName) {
        synchronized (thelist) {
            for (ContributionParams contribute : thelist) {
                if (contribute.getLastname().equalsIgnoreCase(lastName)) { // Use equalsIgnoreCase for case-insensitive comparison
                    System.out.println("Found contribution from " + lastName);
                    return contribute; // Return the found contribution directly
                }
            }
        }
        // If no contribution was found, return null
        System.out.println("Contribution not found for " + lastName);
        return null;
    }


    public void shutdown() {
        executors.shutdown();
        System.out.println("Executors service shut down");
    }
}
