package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestListTest {
    private static final Path path = Path.of("guests.txt");

    @Test
    void shouldBeEmptyInitially() throws IOException {
        GuestList guestList = new GuestList();
        guestList.setGuests(new ArrayList<>());
        assertEquals(0, guestList.getGuests().size());
    }
    @Test
    void shouldReadSameGuestsAsWrittenBefore() throws IOException {
        GuestList guestList = new GuestList();
        guestList.setGuests(List.of("Karl", "Ute"));
        assertEquals(List.of("Karl", "Ute"), guestList.getGuests());
    }

    @Test
    void shouldWriteToFileSystem() throws IOException {
        GuestList guestList = new GuestList();
        guestList.setGuests(List.of("Theodor", "Anette"), path);
        assertTrue(Files.exists(path));

        List<String> fileContents = Files.readAllLines(path);
        assertEquals(List.of("Theodor", "Anette"), fileContents);
    }

    //TODO: add them manually before this tests, it checks if they are in already
    @Test
    void shouldReadFromFileSystem() throws IOException {
        GuestList guestList = new GuestList();
        assertEquals(List.of("Stephan", "Max"), guestList.getGuests(path));
    }

    @Test
    void readingFile_expect_Exception() {
        assertThrows(NoSuchFileException.class, ()->{Files.readAllLines(Path.of("guestsWRONG.txt"));});
    }

    @Test
    void addGuest_expect_Added() throws IOException {
        GuestList guestList = new GuestList();
        guestList.addGuest("Kristijan");
        assertTrue(Files.exists(path));
        List<String> fileContents = Files.readAllLines(path);
        assertEquals("Kristijan", fileContents.get(0));
    }
}