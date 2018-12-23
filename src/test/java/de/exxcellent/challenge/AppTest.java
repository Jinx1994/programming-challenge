package de.exxcellent.challenge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Example JUnit4 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public class AppTest {

    private String successLabel = "not successful";

    @BeforeEach
    public void setUp() {
        successLabel = "successful";
    }

    @Test
    public void aPointlessTest() {
        assertEquals("successful", successLabel, "My expectations were not met");
    }

    @Test
    public void runFootball() {
        App.main("--football", "football.csv");
    }

    @Test
    public void openFootballCSVTest()
    {
        File testFile = App.openFileChooser();
        assertEquals("csv", App.getFileExtension(testFile));
        assertEquals("football", App.getFileNameWithoutExtension(testFile) );
    }

    @Test
    public void openWeatherCSVTest()
    {
        File testFile = App.openFileChooser();
        assertEquals("csv", App.getFileExtension(testFile));
        assertEquals("weather", App.getFileNameWithoutExtension(testFile) );
    }

    @Test
    public void openExampleJSONTest()
    {
        File testFile = App.openFileChooser();
        assertEquals("json", App.getFileExtension(testFile));
        assertEquals("example", App.getFileNameWithoutExtension(testFile) );
    }

    @Test
    public void dataEntryWeatherCSVTest()
    {
        File selectedFile = App.openFileChooser();
        DataEntry dataEntry = new DataEntry(59,61,2,"14");
        assertEquals(dataEntry.getFirstValue(), App.getMinDifferenceFromWeatherCSVFile(selectedFile) .getFirstValue());
        assertEquals(dataEntry.getSecondValue(), App.getMinDifferenceFromWeatherCSVFile(selectedFile).getSecondValue());
        assertEquals(dataEntry.getMinDifference(), App.getMinDifferenceFromWeatherCSVFile(selectedFile).getMinDifference());
        assertEquals(dataEntry.getEntryName(), App.getMinDifferenceFromWeatherCSVFile(selectedFile).getEntryName());
    }

    @Test
    public void dataEntryFootballCSVTest()
    {
        File selectedFile = App.openFileChooser();
        DataEntry dataEntry = new DataEntry(46,47,1,"Aston_Villa");
        assertEquals(dataEntry.getFirstValue(), App.getAbsMinDifferenceFromFootballCSVFile(selectedFile) .getFirstValue());
        assertEquals(dataEntry.getSecondValue(), App.getAbsMinDifferenceFromFootballCSVFile(selectedFile).getSecondValue());
        assertEquals(dataEntry.getMinDifference(), App.getAbsMinDifferenceFromFootballCSVFile(selectedFile).getMinDifference());
        assertEquals(dataEntry.getEntryName(), App.getAbsMinDifferenceFromFootballCSVFile(selectedFile).getEntryName());
    }

    @Test
    public void readJSONFileTest()
    {
        File selectedFile = App.openFileChooser();
        Assertions.assertTrue(App.readJSONFile(selectedFile), "JSON file reading process successful!");
    }
}