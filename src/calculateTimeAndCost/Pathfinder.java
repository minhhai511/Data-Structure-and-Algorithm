/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculateTimeAndCost;

import graph.GraphByMatrix;
import graph.Vertex;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author win
 */
public class Pathfinder {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static final String FILE_NAME = "map.txt";
    private static final String VALID_NUMBER = "^[+-]?[0-9]+[.]?[0-9]*$";
    private static final double MAX_VALUE = Math.pow(10, 5);

    public static void main(String[] args) {
        Vertex[] listVertices = getVertices();
        GraphByMatrix myGraph = new GraphByMatrix(listVertices);
        addEdges(myGraph);
        System.out.println("MAP:");
        System.out.println("1.Son La \t2. Ha Noi  \t3.Thai Nguyen ");
        System.out.println("4.Yen Bai \t5. Ha Nam \t6. Bac Giang \t7. Ha Giang");
        String id1 = String.valueOf(getValidOption(1, 7, "From: "));
        String id2 = String.valueOf(getValidOption(1, 7, "To: "));
        System.out.println("*** Result ***");
        double shortestDistance = myGraph.shotrestPath(id1, id2, "shortest distance");
        if(shortestDistance!= MAX_VALUE)System.out.println("=> Shortest distance: " + shortestDistance + "km");
        double minimumTime = myGraph.shotrestPath(id1, id2, "minimum time");
        if(minimumTime!=MAX_VALUE)System.out.println("=> Minimum time: "+minimumTime+"p =" + myGraph.convertTime((int)minimumTime));
        double minimumCost = myGraph.shotrestPath(id1, id2, "minimum cost");
        if(minimumCost!=MAX_VALUE)System.out.println("=> Minimum cost: " + minimumCost + "$");
        String name1 = listVertices[Integer.parseInt(id1) - 1].getName();
        String name2 = listVertices[Integer.parseInt(id2) - 1].getName();
        if (shortestDistance == MAX_VALUE || minimumTime == MAX_VALUE || minimumCost == MAX_VALUE) {
            System.out.println("There are no suitable path from " + name1 + " to " + name2);
            return;
        }

    }

    public static int getValidOption(int min, int max, String message) {
        String optionStr = "";
        while (true) {
            try {
                System.out.print(message);
                optionStr = in.readLine().trim();
                /* Checking when user input nothing */
                if (optionStr.isEmpty()) {
                    throw new IOException("You must enter an option!");
                }
                /* Checking when user input invalid character or a real number */
                if (!optionStr.matches(VALID_NUMBER) || optionStr.contains(".")) {
                    throw new IOException("Your option must be an integer number!");
                }
                int option = Integer.parseInt(optionStr);
                /* Checking when user enter a choice which is out of valid range
                 min - max */
                if (option < min || option > max) {
                    throw new IOException("You must choose an option between "
                            + min + " - " + max);
                }
                return option;
            } catch (IOException io) {
                System.out.println(io.getMessage());
            } catch (NumberFormatException n) {
                System.out.println("You must choose an option between "
                        + min + " - " + max);
            }
        }
    }

    private static Vertex[] getVertices() {
        ArrayList<Vertex> listVertices = new ArrayList<>();
        File file = new File(FILE_NAME);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.ready()) {
                String data = reader.readLine();
                String[] separatedData = data.split("\\|");
                if (separatedData.length > 2) {
                    break;
                }
                String id = separatedData[0];
                String name = separatedData[1];
                Vertex vertex = new Vertex(id, name);
                listVertices.add(vertex);
            }
        } catch (Exception e) {
        }
        Vertex[] vertices = new Vertex[listVertices.size()];
        for (int i = 0; i < listVertices.size(); i++) {
            vertices[i] = listVertices.get(i);
        }
        return vertices;
    }

    private static void addEdges(GraphByMatrix myGraph) {
        File file = new File(FILE_NAME);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.ready()) {
                String data = reader.readLine();
                String[] separatedData = data.split("\\|");
                if (separatedData.length == 5) {
                    String id_1 = separatedData[0];
                    String id_2 = separatedData[1];
                    double distance = Double.parseDouble(separatedData[2]);
                    double time = Double.parseDouble(separatedData[3]);
                    double cost = Double.parseDouble(separatedData[4]);
                    myGraph.addEdge(id_1, id_2, distance, cost, time);
                }
            }
        } catch (Exception e) {
        }
    }

}
