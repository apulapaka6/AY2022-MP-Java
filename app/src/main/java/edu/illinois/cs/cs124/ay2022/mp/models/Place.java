package edu.illinois.cs.cs124.ay2022.mp.models;

import java.util.ArrayList;
import java.util.List;

/*
 * Model storing information about a place retrieved from the backend server.
 *
 * You will need to understand some of the code in this file and make changes starting with MP1.
 */
@SuppressWarnings("unused")
public final class Place {
  /*
   * The Jackson JSON serialization library that we are using requires an empty constructor.
   * So don't remove this!
   */
  public Place() {}

  public Place(
      final String setId,
      final String setName,
      final double setLatitude,
      final double setLongitude,
      final String setDescription) {
    id = setId;
    name = setName;
    latitude = setLatitude;
    longitude = setLongitude;
    description = setDescription;
  }

  // ID of the place
  private String id;

  public String getId() {
    return id;
  }

  // Name of the person who submitted this favorite place
  private String name;

  public String getName() {
    return name;
  }

  // Latitude and longitude of the place
  private double latitude = 100000;

  public double getLatitude() {
    return latitude;
  }

  private double longitude = 100000;

  public double getLongitude() {
    return longitude;
  }

  // Description of the place
  private String description;

  public String getDescription() {
    return description;
  }

  public static List<Place> search(final List<Place> places, final String inp) {
    if (places == null || inp == null) {
      throw new IllegalArgumentException();
    }
    if (places.size() == 0 || inp.trim().equals("")) {
      return places;
    }
    List<Place> output = new ArrayList<>();
    for (int i = 0; i < places.size(); i++) {
      String des = places.get(i).getDescription();
      for (int j = 0; j < des.length(); j++) {
        if (des.charAt(j) == '.' || des.charAt(j) == '!' || des.charAt(j) == '?'
            || des.charAt(j) == ',' || des.charAt(j) == ':' || des.charAt(j) == ';'
            || des.charAt(j) == '/' || des.charAt(j) == ' ') {
          des = des.replace(des.charAt(j), ' ');
        } else if (Character.isDigit(des.charAt(j))) {
          break;
        } else if (!(Character.isAlphabetic(des.charAt(j)))) {
          des = des.replace(Character.toString(des.charAt(j)), "");
        }
      }
      String[] temp = des.split(" ");
      for (int j = 0; j < temp.length; j++) {
        if (temp[j].equalsIgnoreCase(inp.trim())) {
          output.add(places.get(i));
          break;
        }
      }
    }
    return output;
  }
}
