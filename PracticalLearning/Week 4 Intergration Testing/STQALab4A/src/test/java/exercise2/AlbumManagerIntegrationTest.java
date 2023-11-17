package exercise2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNull;

class AlbumManagerIntegrationTest {

    private AlbumManager albumManager;

    @BeforeEach
    void setUp() throws IOException {
        albumManager = new AlbumManager("src/test/java/resources/albums.json");
    }

    // Exercise 2.1 - Test that getting an album that doesn't exist returns null as expected
    @Test
    @DisplayName("Test retrieval of non-existent album")
    void testRetrievalOfNonExistentAlbumShouldReturnNull() {
        // todo: finish implementation
    }

    // Exercise 2.2 - Test that getting an album by name is case-insensitive
    @Test
    @DisplayName("Test getAlbum method is case insensitive")
    void testGetAlbumIsCaseInsensitiveShouldReturnCorrectAlbum() {
        // todo: finish implementation
    }

    // Exercise 2.3 - Test that getting an album that doesn't exist returns null as expected
    @Test
    @DisplayName("Test AlbumManager's constructor correctly loads albums")
    void testConstructor_ShouldLoadAlbums() {
        // todo: finish implementation
    }

    // Exercise 2.4 - Test that giving the loadAlbums method a file that doesn't exist gives us the correct exception
    @Test
    @DisplayName("Test Constructor with invalid file path/name")
    void testConstructorWithInvalidFilePathShouldThrowRuntimeException() {
        // todo: finish implementation
    }

    // Exercise 2.5 - Test that successive calls to the loadAlbumsFromJSON method do not keep adding the albums to the ArrayList
    @Test
    @DisplayName("testLoadAlbumsFromJSONShouldClearPreviousAlbums")
    void testLoadAlbumsFromJSONShouldClearPreviousAlbums() {
        // todo: finish implementation
    }


}