import java.io.*;
import java.util.*;

class Player {
    private final String name, team, role;
    private final int matchesPlayed, totalRuns, wicketsTaken;
    private final double averageRuns;
    private final double strikeRate;

    Player(String name, String team, String role, int matches, int runs, int wickets, double average, double strikeRate) {
        this.name = name;
        this.team = team;
        this.role = role;
        this.matchesPlayed = matches;
        this.totalRuns = runs;
        this.wicketsTaken = wickets;
        this.averageRuns = average;
        this.strikeRate = strikeRate;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public String getRole() {
        return role;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public double getAverageRuns() {
        return averageRuns;
    }

    public void viewProfile() {
        System.out.println("----------------------------------");
        System.out.println("Name: " + name + "\t;\t" + "Plays for: " + team);
        System.out.println("Role: " + role + "\t;\t" + "Matches played: " + matchesPlayed);
        System.out.println("Total runs: " + totalRuns + "\t;\t" + "Wickets taken: " + wicketsTaken);
        System.out.println("Average runs: " + averageRuns + "\t;\t" + "Strike rate: " + strikeRate);
        System.out.println("----------------------------------");
    }
}

class Team {
    private final String name, homeGround;
    private final int teamId;
    private final ArrayList<Player> players, bowlers, batsmen, wicketKeepers, allRounders;

    public String getName() {
        return name;
    }

    public String getHomeGround() {
        return homeGround;
    }

    public int getTeamId() {
        return teamId;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    Team(int id, String name, String ground) {
        this.name = name;
        this.homeGround = ground;
        this.teamId = id;
        bowlers = new ArrayList<>();
        batsmen = new ArrayList<>();
        wicketKeepers = new ArrayList<>();
        allRounders = new ArrayList<>();
        players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if (!player.getTeam().equals(this.name)) {
            System.out.println("Player belongs to a different team ( " + player.getTeam() + " )");
            return;
        }
        players.add(player);
        switch (player.getRole()) {
            case "BATSMAN" -> batsmen.add(player);
            case "BOWLER" -> bowlers.add(player);
            case "WICKET KEEPER" -> wicketKeepers.add(player);
            default -> allRounders.add(player);
        }
    }

    public ArrayList<Player> getPlayersWithWickets(int bound) {
        // Returns a list of players with wickets taken greater than or equal to bound
        ArrayList<Player> filteredPlayers = new ArrayList<>();
        for (Player player : bowlers) {
            if (player.getWicketsTaken() >= bound)
                filteredPlayers.add(player);
        }
        return filteredPlayers;
    }

    public Player getHighestWicketTaker() {
        Player highestWicketTaker = players.get(0);
        for (Player player : players) {
            if (player.getWicketsTaken() > highestWicketTaker.getWicketsTaken()) {
                highestWicketTaker = player;
            }
        }
        return highestWicketTaker;
    }

    public Player getHighestRunScorer() {
        Player highestRunScorer = players.get(0);
        for (Player player : players) {
            if (player.getTotalRuns() > highestRunScorer.getTotalRuns()) {
                highestRunScorer = player;
            }
        }
        return highestRunScorer;
    }

}

class SortByTotalRuns implements Comparator<Player> {
    @Override
    public int compare(Player A, Player B) {
        /*
        Criteria:
        Runs Scored
         */
        return B.getTotalRuns() - A.getTotalRuns();

    }
}

class SortByAverageRuns implements Comparator<Player> {
    @Override
    public int compare(Player A, Player B) {
        /*
        Criteria:
        Average Runs
         */
        return Double.compare(B.getAverageRuns(), A.getAverageRuns());
    }
}

class SortByByTotalWickets implements Comparator<Player> {
    @Override
    public int compare(Player A, Player B) {
        /*
        Criteria:
        Wickets Taken
         */
        return B.getWicketsTaken() - A.getWicketsTaken();
    }
}

class SortByAllRounderCriteria implements Comparator<Player> {
    @Override
    public int compare(Player A, Player B) {
        /*
        Criteria:
        Average Runs + Wickets
         */
//        System.out.println(Double.compare(B.getAverageRuns() + B.getWicketsTaken(), A.getAverageRuns() + A.getAverageWickets()));
        return Double.compare(B.getAverageRuns() + B.getWicketsTaken(), A.getAverageRuns() + A.getWicketsTaken());
    }
}

class Match {
    Team homeTeam, awayTeam;
    int matchId;
    String ground;

    Match(Team home, Team away, String ground) {
        this.homeTeam = home;
        this.awayTeam = away;
        this.ground = ground;
    }
}

class Tournament {
    private final ArrayList<Team> teams;
    private final ArrayList<Match> matches;
    private final ArrayList<Player> allPlayers;
    private final ArrayList<Player> allBowlers;
    private final ArrayList<Player> allBatsmen;
    private final ArrayList<Player> allAllRounders;


    Tournament(ArrayList<Player> players, ArrayList<Team> teams) {
        this.teams = teams;
        matches = new ArrayList<>();
        allBowlers = new ArrayList<>();
        allAllRounders = new ArrayList<>();
        allBatsmen = new ArrayList<>();
        ArrayList<Player> allWicketKeepers = new ArrayList<>();
        allPlayers = new ArrayList<>();

        for (Player player : players) {
            allPlayers.add(player);
            switch (player.getRole()) {
                case "BATSMAN" -> allBatsmen.add(player);
                case "BOWLER" -> allBowlers.add(player);
                case "WICKET KEEPER" -> allWicketKeepers.add(player);
                default -> allAllRounders.add(player);
            }
        }

        fixSchedule();
        Processor.writeToCSV(matches);
    }

    public void fixSchedule() {
        for (Team homeTeam : teams) {
            for (Team awayTeam : teams) {
                if (homeTeam.getTeamId() == awayTeam.getTeamId())
                    continue;
                Match match = new Match(homeTeam, awayTeam, homeTeam.getHomeGround());
                matches.add(match);
            }
        }
        Collections.shuffle(matches);
        int identifier = 1;
        for (Match match : matches) {
            match.matchId = identifier;
            identifier++;
        }
    }

    public boolean isTeamPresent(String teamName) {
        teamName = teamName.toLowerCase();
        for (Team team : teams) {
            if (team.getName().toLowerCase().equals(teamName)) {
                return true;
            }
        }
        return false;
    }

    public Team getTeam(String teamName) {
        teamName = teamName.toLowerCase();
        Team result = null;
        for (Team team : teams) {
            if (team.getName().toLowerCase().equals(teamName)) {
                result = team;
                break;
            }
        }
        return result;
    }

    public void showSchedule() {
        for (Match match : matches) {
            System.out.println("Match: " + match.matchId + ", Ground: " + match.ground);
            System.out.println("Home: " + match.homeTeam.getName() + ", " + "Away: " + match.awayTeam.getName());
            System.out.println();
        }
    }

    public ArrayList<Player> getTopThreeBatsmen() {
        /*
        Criteria:
        Runs Scored -> Average Runs -> Strike Rate
         */
        allBatsmen.sort(new SortByTotalRuns());
        ArrayList<Player> top3Batsmen = new ArrayList<>();
        top3Batsmen.add(allBatsmen.get(0));
        top3Batsmen.add(allBatsmen.get(1));
        top3Batsmen.add(allBatsmen.get(2));
        return top3Batsmen;
    }

    public ArrayList<Player> getTopThreeBowlers() {
        /*
        Criteria:
        Wicket Taken
         */
        allBowlers.sort(new SortByByTotalWickets());
        ArrayList<Player> top3Bowler = new ArrayList<>();
        top3Bowler.add(allBowlers.get(0));
        top3Bowler.add(allBowlers.get(1));
        top3Bowler.add(allBowlers.get(2));
        return top3Bowler;
    }

    public ArrayList<Player> getTopThreeAllRounders() {
        /*
        Criteria:
        Runs Scored -> Average Runs -> Strike Rate
         */
        allAllRounders.sort(new SortByAllRounderCriteria());
        ArrayList<Player> top3AllRounders = new ArrayList<>();
        top3AllRounders.add(allAllRounders.get(0));
        top3AllRounders.add(allAllRounders.get(1));
        top3AllRounders.add(allAllRounders.get(2));
        return top3AllRounders;
    }

    public void getTopTwoScoringTeams() {
        for (Team team : teams) {
            team.getPlayers().sort(new SortByAverageRuns());
        }
        HashMap<String, Integer> totalAverageRuns = new HashMap<>();
        for (Team team : teams) {
            int total = 0;
            for (int i = 0; i < 11; i++) {
                total += Math.floor(team.getPlayers().get(i).getAverageRuns());
            }
            totalAverageRuns.put(team.getName(), total);
        }
        int maxRuns = 0, secondMaxRuns = 0;
        String maxRunTeam = "", secondMaxRunTeam = "";
        for (Map.Entry<String, Integer> entry : totalAverageRuns.entrySet()) {
            if (entry.getValue() > maxRuns) {
                secondMaxRuns = maxRuns;
                secondMaxRunTeam = maxRunTeam;
                maxRuns = entry.getValue();
                maxRunTeam = entry.getKey();
            } else if (entry.getValue() > secondMaxRuns) {
                secondMaxRuns = entry.getValue();
                secondMaxRunTeam = entry.getKey();
            }
        }
        System.out.println("Max Scoring team: " + maxRunTeam + " , Predicted score: " + maxRuns);
        System.out.println("Second max Scoring team: " + secondMaxRunTeam + " , Predicted score: " + secondMaxRuns);
    }

    public HashMap<Team, ArrayList<Player>> getNextGenBatsmen() {
        /*
            Matches played <= 100
            Average Runs >= 30
         */
        HashMap<Team, ArrayList<Player>> nextGenBatsmen = new HashMap<>();
        for (Team team : teams) {
            nextGenBatsmen.put(team, new ArrayList<>());
            for (Player player : team.getPlayers()) {
                if (player.getMatchesPlayed() <= 100 && player.getAverageRuns() >= 30) {
                    nextGenBatsmen.get(team).add(player);
                }
            }
        }
        return nextGenBatsmen;
    }

    public HashMap<Team, ArrayList<Player>> getNextGenBowler() {
        /*
            Matches played <= 100
            Wickets Taken >= 50
         */
        HashMap<Team, ArrayList<Player>> nextGenBatsmen = new HashMap<>();
        for (Team team : teams) {
            nextGenBatsmen.put(team, new ArrayList<>());
            for (Player player : team.getPlayers()) {
                if (player.getMatchesPlayed() <= 100 && player.getWicketsTaken() >= 50) {
                    nextGenBatsmen.get(team).add(player);
                }
            }
        }
        return nextGenBatsmen;
    }

    public ArrayList<Player> searchPlayer(String searchTerm) {
        ArrayList<Player> result = new ArrayList<>();
        for (Player player : allPlayers) {
            searchTerm = searchTerm.toLowerCase();
            String name = player.getName().toLowerCase();
            if (searchTerm.length() > name.length())
                continue;
            for (int i = 0; i < name.length(); i++) {
                if (name.startsWith(searchTerm, i)) {
                    result.add(player);
                    break;
                }
            }
        }
        return result;
    }

}

class Processor {

    public static HashMap<String, String> homeGroundForTeams = new HashMap<>();

    public static void assignHomeGround() {
        // Hard-coding the home grounds of teams
        Processor.homeGroundForTeams.put("RCB", "M. Chinnaswamy Stadium");
        Processor.homeGroundForTeams.put("PBKS", "PCA Stadium");
        Processor.homeGroundForTeams.put("DC", "Arun Jaitley Ground");
        Processor.homeGroundForTeams.put("KKR", "Eden Gardens");
        Processor.homeGroundForTeams.put("MI", "Wankhede Stadium");
        Processor.homeGroundForTeams.put("RR", "Sawai Mansingh Stadium");
        Processor.homeGroundForTeams.put("SRH", "Rajiv Gandhi International Cricket Stadium");
        Processor.homeGroundForTeams.put("CSK", "M. A. Chidambaram Stadium");
    }

    public static ArrayList<String[]> readFromCSV() {
        ArrayList<String[]> inputData = new ArrayList<>();
        try {
            FileReader file = new FileReader("IPL_2021_data.csv");
            BufferedReader csvReader = new BufferedReader(file);
            csvReader.readLine();
            while (true) {
                String currentTuple = csvReader.readLine();
                if (currentTuple == null)
                    break;
                String[] data = currentTuple.split(",");
                inputData.add(data);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return inputData;
    }

    public static void writeToCSV(ArrayList<Match> matches) {
        try {
            File file = new File("schedule.csv");
            PrintWriter writer = new PrintWriter(file);
            StringBuilder currentRow = new StringBuilder();
            currentRow.append("Match Number,Team home,Team away,Ground\n");
            for (Match match : matches) {
                currentRow.append(match.matchId).append(",").append(match.homeTeam.getName()).append(",").append(match.awayTeam.getName()).append(",").append(match.ground).append('\n');
            }
            writer.write(currentRow.toString());
            writer.close();
            System.out.println("done");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static ArrayList<Player> processPlayers() {
        ArrayList<String[]> data = readFromCSV();
        ArrayList<Player> players = new ArrayList<>();
        for (String[] row : data) {
            String name = row[0];
            String team = row[1];
            String role = row[2];
            int matchCount = Integer.parseInt(row[3]);
            int runsScored = Integer.parseInt(row[4]);
            double averageRuns = Double.parseDouble(row[5]);
            double strikeRate = Double.parseDouble(row[6]);
            int wicketsTaken = Integer.parseInt(row[7]);
            Player player = new Player(name, team, role, matchCount, runsScored, wicketsTaken, averageRuns, strikeRate);
            players.add(player);
        }
        return players;
    }

    public static ArrayList<Team> processTeams(ArrayList<Player> players) {
        ArrayList<Team> teams = new ArrayList<>();
        TreeMap<String, ArrayList<Player>> uniqueTeams = new TreeMap<>();
        for (Player player : players) {
            if (!uniqueTeams.containsKey(player.getTeam())) {
                uniqueTeams.put(player.getTeam(), new ArrayList<>());
            }
            uniqueTeams.get(player.getTeam()).add(player);
        }
        int id = 1;
        for (Map.Entry<String, ArrayList<Player>> pair : uniqueTeams.entrySet()) {
            Team newTeam = new Team(id, pair.getKey(), homeGroundForTeams.get(pair.getKey()));
            for (Player player : pair.getValue()) {
                newTeam.addPlayer(player);
            }
            teams.add(newTeam);
            id++;
        }
        return teams;
    }

}

public class Main {

    public static void main(String[] args) {

        Processor.assignHomeGround();
        ArrayList<Player> players = Processor.processPlayers();
        ArrayList<Team> teams = Processor.processTeams(players);

        Tournament IPL = new Tournament(players, teams);

        Scanner sc = new Scanner(System.in).useDelimiter("\n");

        while (true) {
            System.out.println("1. Find which bowlers of a team have taken at least 40 wickets");
            System.out.println("2. Search for a player");
            System.out.println("3. Find the highest wicket-taker of a team");
            System.out.println("4. Find the highest run-scorer of a team");
            System.out.println("5. Find the top 3 batsmen of the tournament");
            System.out.println("6. Find the top 3 bowlers of the tournament");
            System.out.println("7. Find the top 3 all-rounders of the tournament");
            System.out.println("8. Find the top 2 scoring teams");
            System.out.println("9. Find the next gen batsmen");
            System.out.println("10. Find the next gen bowlers");
            System.out.println("11. Exit");
            System.out.println("Enter an option: ");
            int option;
            try {
                option = sc.nextInt();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return;
            }
            switch (option) {
                case 1:
                    System.out.println("Enter team name: ");
                    String teamName = sc.next();
                    if (IPL.isTeamPresent(teamName)) {
                        ArrayList<Player> result = IPL.getTeam(teamName).getPlayersWithWickets(40);
                        System.out.println("The bowlers are: ");
                        for (Player player : result) {
                            player.viewProfile();
                        }
                    } else {
                        System.out.println("No such team");
                    }
                    break;
                case 2:
                    System.out.println("Enter player's name: ");
                    String searchName = sc.next();
                    ArrayList<Player> result = IPL.searchPlayer(searchName);
                    if (result.isEmpty()) {
                        System.out.println("No player with its name containing the given string is found");
                    } else {
                        System.out.println("These are the player(s) we found: ");
                        for (Player player : result)
                            player.viewProfile();
                    }
                    break;
                case 3:
                    System.out.println("Enter team name: ");
                    teamName = sc.next();
                    if (IPL.isTeamPresent(teamName)) {
                        IPL.getTeam(teamName).getHighestWicketTaker().viewProfile();
                    } else {
                        System.out.println("No such team");
                    }
                    break;
                case 4:
                    System.out.println("Enter team name: ");
                    teamName = sc.next();
                    if (IPL.isTeamPresent(teamName)) {
                        IPL.getTeam(teamName).getHighestRunScorer().viewProfile();
                    } else {
                        System.out.println("No such team");
                    }
                    break;
                case 5:
                    result = IPL.getTopThreeBatsmen();
                    System.out.println("The batsmen are: ");
                    for (Player player : result) {
                        player.viewProfile();
                    }
                    break;
                case 6:
                    result = IPL.getTopThreeBowlers();
                    System.out.println("The bowlers are: ");
                    for (Player player : result) {
                        player.viewProfile();
                    }
                    break;
                case 7:
                    result = IPL.getTopThreeAllRounders();
                    System.out.println("The all rounders are: ");
                    for (Player player : result) {
                        player.viewProfile();
                    }
                    break;
                case 8:
                    IPL.getTopTwoScoringTeams();
                    break;
                case 9:
                    HashMap<Team, ArrayList<Player>> mp = IPL.getNextGenBatsmen();
                    for (Map.Entry<Team, ArrayList<Player>> entry : mp.entrySet()) {
                        System.out.println(entry.getKey().getName() + ": ");
                        for (Player player : entry.getValue()) {
                            player.viewProfile();
                        }
                    }
                    break;
                case 10:
                    mp = IPL.getNextGenBowler();
                    for (Map.Entry<Team, ArrayList<Player>> entry : mp.entrySet()) {
                        System.out.println(entry.getKey().getName() + ": ");
                        for (Player player : entry.getValue()) {
                            player.viewProfile();
                        }
                    }
                    break;
                case 11:
                    return;
                default:
                    System.out.println("Enter Valid option");
            }
            System.out.println("Do you want to continue?");
            System.out.println("Enter YES or NO");
            String toContinue = sc.next();
            while (!toContinue.equalsIgnoreCase("yes") && !toContinue.equalsIgnoreCase("no")) {
                System.out.println("Enter valid input");
                toContinue = sc.next();
            }
            if (toContinue.equalsIgnoreCase("no"))
                break;
        }

    }
}
