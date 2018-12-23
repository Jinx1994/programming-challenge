package de.exxcellent.challenge;

public class DataEntry {

    private int firstValue;
    private int secondValue;
    private int minDifference;
    private String entryName;

    public DataEntry(int minValue, int maxValue, int minDifference, String entryName)
    {
        this.firstValue = minValue;
        this.secondValue = maxValue;
        this.minDifference = minDifference;
        this.entryName = entryName;
    }

    public int getFirstValue()
    {
        return this.firstValue;
    }

    public int getSecondValue()
    {
        return this.secondValue;
    }

    public int getMinDifference()
    {
        return this.minDifference;
    }

    public String getEntryName()
    {
        return this.entryName;
    }

    public String getWeatherDataEntry()
    {
        return "Day: " + this.entryName + ", " +
                "Mininum Value: " + this.firstValue + ", " +
                "Maximum value: " + this.secondValue + ", " +
                "Difference: " + this.minDifference;
    }

    public String getFootballDataEntry()
    {
        return "Team: " + this.entryName + ", " +
                "Goals Scored: " + this.firstValue + ", " +
                "Goals Allowed: " + this.secondValue + ", " +
                "Difference: " + this.minDifference;
    }
}
