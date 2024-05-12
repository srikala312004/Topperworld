import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OnlineVotingSystem {
    private static Map<String, Integer> candidates = new HashMap<>();
    private static Map<String, Boolean> voters = new HashMap<>();

    public static void main(String[] args) {
        initializeCandidates();
        boolean votingOpen = true;
        
        while (votingOpen) {
            System.out.println("Welcome to the Online Voting System");
            System.out.println("1. Cast your vote");
            System.out.println("2. View Results");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    castVote();
                    break;
                case 2:
                    viewResults();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    votingOpen = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeCandidates() {
        candidates.put("Candidate 1", 0);
        candidates.put("Candidate 2", 0);
        // Add more candidates as needed
    }

    private static void castVote() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("List of Candidates:");
        for (String candidate : candidates.keySet()) {
            System.out.println(candidate);
        }
        System.out.print("Enter the name of the candidate you want to vote for: ");
        String candidateName = scanner.nextLine();

        if (candidates.containsKey(candidateName)) {
            if (!voters.containsKey(candidateName)) {
                candidates.put(candidateName, candidates.get(candidateName) + 1);
                voters.put(candidateName, true);
                System.out.println("Thank you for casting your vote for " + candidateName);
            } else {
                System.out.println("You have already voted for this candidate.");
            }
        } else {
            System.out.println("Invalid candidate name.");
        }
    }

    private static void viewResults() {
        System.out.println("Voting Results:");
        for (Map.Entry<String, Integer> entry : candidates.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " votes");
        }
    }
}
