/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.Arrays;

public class GraphByMatrix {

    private Vertex[] vertices;
    private double[][] distance;
    private double[][] cost;
    private double[][] time;
    private boolean[] free;
    private static final double MAX_VALUE = Math.pow(10, 5);


    public GraphByMatrix(Vertex[] vertices) {
        this.vertices = vertices;
        int len = vertices.length;
        distance = new double[len][len];
        cost = new double[len][len];
        time = new double[len][len];
        Arrays.stream(distance).forEach(w -> Arrays.fill(w, MAX_VALUE));
        Arrays.stream(cost).forEach(w -> Arrays.fill(w, MAX_VALUE));
        Arrays.stream(time).forEach(w -> Arrays.fill(w, MAX_VALUE));
        free = new boolean[len];
    }

    public int searchVertex(String id) {
        for (int i = 0; i < vertices.length; i++) {
            Vertex vertex = vertices[i];
            if (vertex.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void addEdge(String id1, String id2, double distance, double cost, double time) {
        int i = searchVertex(id1);
        int j = searchVertex(id2);
        if (i == -1 || j == -1) {
            System.out.println("Cannot add!");
            return;
        }
        this.distance[i][j] = distance;
        this.cost[i][j] = cost;
        this.time[i][j] = time;
    }

    public double shotrestPath(String id1, String id2, String message) {
        int len = vertices.length;
        int startIndex = searchVertex(id1);
        int endIndex = searchVertex(id2);
        if (startIndex == -1 || endIndex == -1) {
            return MAX_VALUE;
        }
        double[] label = new double[len];
        Arrays.fill(label, MAX_VALUE);
        Arrays.fill(free, true);
        label[startIndex] = 0;
        int[] trace = new int[len];
        while (true) {
            double min = MAX_VALUE + 1;
            int imin = -1;
            for (int i = 0; i < len; i++) {
                if (free[i] && label[i] < min) {
                    min = label[i];
                    imin = i;
                }
            }
            free[imin] = false;
            if (imin == endIndex || imin == -1) {
                break;
            }
            for (int i = 0; i < len; i++) {
                if (message.equals("shortest distance")) {
                    if (free[i] && label[i] > min + distance[imin][i]) {
                        label[i] = min + distance[imin][i];
                        trace[i] = imin;
                    }
                } else if (message.equals("minimum time")) {
                    if (free[i] && label[i] > min + time[imin][i]) {
                        label[i] = min + time[imin][i];
                        trace[i] = imin;
                    }
                } else {
                    if (free[i] && label[i] > min + cost[imin][i]) {
                        label[i] = min + cost[imin][i];
                        trace[i] = imin;
                    }
                }
            }
        }
        double result = label[endIndex];
        if (result == MAX_VALUE) {
            return MAX_VALUE;
        }
        else{
            showMinimumRoad(trace, startIndex, endIndex);
        }
        return result;
    }
    public static String convertTime(int time){
        String Time="";
        Time += String.valueOf(time/60+"h");
        Time+= String.valueOf(time%60+"p");
        return Time;
    }

    @Override
    public String toString() {
        String result = "";
        result += "Vertices:\n" + Arrays.toString(vertices) + "\n" + "Weight:\n";
        for (double[] w : distance) {
            result += Arrays.toString(w) + "\n";
        }
        return result.trim();
    }

    private void showMinimumRoad(int[] trace, int startIndex, int endIndex) {
        int u = endIndex;
        System.out.print("Follow the path: ");
        while (true) {
            System.out.print(vertices[u].getName() + "<-");
            u = trace[u];
            if (u == startIndex) {
                System.out.print(vertices[u].getName());
                break;
            }
        }
        System.out.println("");
    }

    public void display() {
        System.out.print("GraphByMatrix\n" + "Vertices:\n");
        System.out.println(Arrays.toString(vertices));
        System.out.print("Weight:\n");
        displayMatrix(vertices);
        int i = 0;
        for (double[] w : cost) {
            System.out.printf("%-12s", vertices[i].getName()
            );
            displayWeight(w);
            i++;
        }
    }

    private void displayMatrix(Vertex[] vertices) {
        System.out.printf("%-12s[", "");
        for (int i = 0; i < vertices.length; i++) {
            Vertex vertice = vertices[i];
            System.out.printf("%-10s", vertice.getName());
            if (i != vertices.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    private void displayWeight(double[] w) {
        System.out.print("[");
        for (int i = 0; i < vertices.length; i++) {
            double weigh = w[i];
            System.out.printf("%-10s", weigh);
            if (i != vertices.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

}
