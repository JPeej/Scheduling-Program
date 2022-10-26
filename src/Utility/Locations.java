package Utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;

public class Locations {

    public static ObservableList<String> countries = FXCollections.observableArrayList("U.S", "UK", "Canada");

    public static ObservableList<String> usDivList = FXCollections.observableArrayList("Alabama", "Alaska",
            "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "District of Columbia",
            "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana",
            "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana",
            "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina",
            "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina",
            "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia",
            "Wisconsin", "Wyoming");

    public static  ObservableList<String> ukDivList  = FXCollections.observableArrayList("England", "Wales",
            "Scotland", "Northern Ireland");

    public static  ObservableList<String> canadaList  = FXCollections.observableArrayList("British Columbia",
            "Manitoba", "New Brunswick", "Newfoundland and Labrador", "Northwest Territories", "Nova Scotia", "Nunavut",
            "Ontario", "Prince Edward Island", "Québec", "Saskatchewan", "Yukon");

}
