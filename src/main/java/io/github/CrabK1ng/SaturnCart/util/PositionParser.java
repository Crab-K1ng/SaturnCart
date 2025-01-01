package io.github.CrabK1ng.SaturnCart.util;

import com.badlogic.gdx.math.Vector3;

import java.util.List;

import java.util.*;
import java.util.regex.*;
import java.util.stream.*;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PositionParser {
    private List<Vector3> finishLinePositions = new ArrayList<>();
    private List<Vector3> checkpoinOnePositions = new ArrayList<>();
    private List<Vector3> checkpoinTwoPositions = new ArrayList<>();

    public void parsePositions(String input) {
        // Regex pattern to match each category and its coordinates
        String pattern = "(FinishLine|CheckpoinOne|CheckpoinTwo) \\[([^\\]]+)\\]";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);

        while (matcher.find()) {
            String category = matcher.group(1);
            String coordinates = matcher.group(2);

            // Parse the coordinates and add to the appropriate list
            List<Vector3> positions = parseCoordinates(coordinates);
            switch (category) {
                case "FinishLine":
                    finishLinePositions.addAll(positions);
                    break;
                case "CheckpoinOne":
                    checkpoinOnePositions.addAll(positions);
                    break;
                case "CheckpoinTwo":
                    checkpoinTwoPositions.addAll(positions);
                    break;
            }
        }
    }

    private List<Vector3> parseCoordinates(String coordinates) {
        List<Vector3> positions = new ArrayList<>();
        // Regex to match individual Vector3 coordinates: (x, y, z)
        String vectorPattern = "\\(([^,]+),([^,]+),([^\\)]+)\\)";
        Pattern vectorRegex = Pattern.compile(vectorPattern);
        Matcher vectorMatcher = vectorRegex.matcher(coordinates);

        while (vectorMatcher.find()) {
            float x = Float.parseFloat(vectorMatcher.group(1).trim());
            float y = Float.parseFloat(vectorMatcher.group(2).trim());
            float z = Float.parseFloat(vectorMatcher.group(3).trim());
            positions.add(new Vector3(x, y, z));
        }

        return positions;
    }

    public void printPositions() {
        System.out.println("FinishLine Positions: " + finishLinePositions);
        System.out.println("CheckpoinOne Positions: " + checkpoinOnePositions);
        System.out.println("CheckpoinTwo Positions: " + checkpoinTwoPositions);
    }

    public static void main(String[] args) {
        String input = "FinishLine [(1791.0,33.0,1844.0), (1801.0,37.0,1845.0)] "
                + "CheckpoinOne [(1762.0,33.0,1889.0), (1761.0,36.0,1899.0)] "
                + "CheckpoinTwo [(1656.0,33.0,1769.0), (1646.0,36.0,1768.0)]";

        PositionParser parser = new PositionParser();
        parser.parsePositions(input);
        parser.printPositions();
    }
}



