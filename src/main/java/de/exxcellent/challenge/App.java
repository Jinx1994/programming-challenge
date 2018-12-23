package de.exxcellent.challenge;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.Math;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {
        File selectedFile = openFileChooser();
        DataEntry dataEntry = new DataEntry(0,0, 0, "");

        if(getFileExtension(selectedFile).equals("csv"))
        {
            switch(getFileNameWithoutExtension(selectedFile))
            {
                case "football":
                    dataEntry = readFootballFile(selectedFile);
                    String teamWithSmallestGoalSpread = dataEntry.getFootballDataEntry(); // Your goal analysis function call …
                    System.out.printf("Team with smallest goal spread: %s%n", teamWithSmallestGoalSpread);
                    break;
                case "weather":
                    dataEntry = readWeatherFile(selectedFile);
                    String dayWithSmallestTempSpread = dataEntry.getWeatherDataEntry(); // Your day analysis function call …
                    System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);
                    break;
                default:
                    break;
            }
        }
        else if(getFileExtension(selectedFile).equals("json"))
        {
            switch(getFileNameWithoutExtension(selectedFile))
            {
                case "example":
                    readJSONFile(selectedFile);
                    break;
                default:
                    break;
            }
        }
        // Your preparation code …
    }

    public static DataEntry readWeatherFile(File file)
    {
        String weatherFile = file.getAbsolutePath();
        BufferedReader bufferedReader = null;
        String line = "";
        String csvSplitBy = ",";
        int minValue = -1;
        int maxValue = -1;
        int tempMinValue = -1;
        int tempMaxValue = -1;
        int minimumDifference = -1;
        int tempDifference = -1;
        String tempEntryName = "";
        String entryName = "";

        try
        {
            bufferedReader = new BufferedReader(new FileReader(weatherFile));
            while ((line = bufferedReader.readLine()) != null)
            {
                String[] weatherData = line.split(csvSplitBy);

                if(weatherData[1].matches("[0-9]+") && weatherData[2].matches("[0-9]+"))
                {
                    tempMinValue = Integer.parseInt(weatherData[2]);
                    tempMaxValue = Integer.parseInt(weatherData[1]);
                    tempDifference = tempMaxValue - tempMinValue;
                    tempEntryName = weatherData[0];
                }

                if(minimumDifference == -1 || tempDifference < minimumDifference)
                {
                    minimumDifference = tempDifference;
                    minValue = tempMinValue;
                    maxValue = tempMaxValue;
                    entryName = tempEntryName;
                }
            }

            if (bufferedReader != null)
            {
                bufferedReader.close();
            }

            DataEntry dataEntry = new DataEntry(minValue, maxValue, minimumDifference, entryName);
            return dataEntry;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static DataEntry readFootballFile(File file)
    {
        String footBallFile = file.getAbsolutePath();
        BufferedReader bufferedReader = null;
        String line = "";
        String csvSplitBy = ",";
        int goalsScored = -1;
        int goalsAllowed = -1;
        int tempGoalsScored = -1;
        int tempGoalsAllowed = -1;
        int minimumAbsoluteDifference = -1;
        int tempMinimumAbsoluteDifference = -1;
        String tempEntryName = "";
        String entryName = "";

        try
        {
            bufferedReader = new BufferedReader(new FileReader(footBallFile));
            while ((line = bufferedReader.readLine()) != null)
            {
                String[] footBallData = line.split(csvSplitBy);

                if (footBallData[5].matches("[0-9]+") && footBallData[6].matches("[0-9]+"))
                {
                    tempGoalsScored = Integer.parseInt(footBallData[5]);
                    tempGoalsAllowed = Integer.parseInt(footBallData[6]);
                    tempMinimumAbsoluteDifference = Math.abs(tempGoalsScored - tempGoalsAllowed);
                    tempEntryName = footBallData[0];
                }

                if (minimumAbsoluteDifference == -1 || tempMinimumAbsoluteDifference < minimumAbsoluteDifference)
                {
                    minimumAbsoluteDifference = tempMinimumAbsoluteDifference;
                    goalsScored = tempGoalsScored;
                    goalsAllowed = tempGoalsAllowed;
                    entryName = tempEntryName;
                }
            }

            if (bufferedReader != null)
            {
                bufferedReader.close();
            }

            DataEntry dataEntry = new DataEntry(goalsScored, goalsAllowed, minimumAbsoluteDifference, entryName);
            return dataEntry;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void readJSONFile(File file)
    {
        JSONParser parser = new JSONParser();

        try
        {
            Object obj = parser.parse(new FileReader(file.getAbsolutePath()));

            JSONObject jsonObject = (JSONObject) obj;

            String name = (String) jsonObject.get("Name");
            String author = (String) jsonObject.get("Organization");
            JSONArray companyList = (JSONArray) jsonObject.get("Company List");

            System.out.println("Name: " + name);
            System.out.println("Organization: " + author);
            System.out.println("\nCompany List:");
            Iterator<String> iterator = companyList.iterator();
            while (iterator.hasNext())
            {
                System.out.println(iterator.next());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String getFileExtension(File file)
    {
        String fileExtension = "";
        int indexWhereFileExtensionBegins = file.getName().lastIndexOf('.');
        if(indexWhereFileExtensionBegins >= 0)
        {
            fileExtension = file.getName().substring(indexWhereFileExtensionBegins + 1);
            return fileExtension;
        }

        return fileExtension;
    }

    public static String getFileNameWithoutExtension(File file)
    {
        String fileNameWithoutExtension = "";
        int indexWhereFileExtensionBegins = file.getName().lastIndexOf('.');
        if(indexWhereFileExtensionBegins == -1)
        {
            return fileNameWithoutExtension;
        }

        fileNameWithoutExtension = file.getName().substring(0, indexWhereFileExtensionBegins);
        return fileNameWithoutExtension;
    }

    public static File openFileChooser()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON and CSV files", "json", "csv");
        fileChooser.addChoosableFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(null);

        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile;
        }

        JOptionPane.showMessageDialog(null, "No file was selected!", "Warning", JOptionPane.WARNING_MESSAGE);
        return null;
    }
}
