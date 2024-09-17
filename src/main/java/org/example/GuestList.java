package org.example;

import lombok.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class GuestList {
   private Optional<List<String>> guests = Optional.of(new ArrayList<String>());

   public void setGuests (List<String> guests) {
       this.guests = Optional.of(guests);
   }
    public void setGuests(List<String> guests, Path path) throws IOException {
        this.guests = Optional.of(guests);
        Files.write(path, guests);
    }
    public List<String> getGuests() {
        return guests.get();
    }
    public List<String> getGuests(Path path) throws IOException {
        return Files.readAllLines(path);
    }
    public void addGuest(String guest) throws IOException {
        Files.write(Path.of("guests.txt"), List.of(guest));
    }
}
