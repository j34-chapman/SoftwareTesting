package exercise1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RecipeManagerIntegrationTest {

    RecipeManager recipeManager;

    @BeforeEach
    void setUp() {
        recipeManager = new RecipeManager();
    }

    // Exercise 1.1 - Test that a recipe can be added and retrieved from the recipe manager object
    @Test
    @DisplayName("Test add and retrieval of new recipe")
    void testAddAndGetRecipe_ShouldReturnAddedRecipe() {
        // todo: finish implementation
        // arrange
        Recipe recipe1 = new Recipe("Pancakes", "flour, milk, eggs", "Mix ingredients and cook.");

        // act
        //In this we are using the object
        recipeManager.addRecipe(recipe1);
        Recipe retrievedRecipe = recipeManager.getRecipe("Pancakes");


        // Assert
        assertNotNull(retrievedRecipe, "The retrieved recipe should not be null");
        assertEquals("Pancakes", retrievedRecipe.getName(), "The retrieved recipe should have the correct name");
        assertEquals("flour, milk, eggs", retrievedRecipe.getIngredients(), "The retrieved recipe should have the correct ingredients");
        assertEquals("Mix ingredients and cook.", retrievedRecipe.getInstructions(), "The retrieved recipe should have the correct instructions");
    }

    // Exercise 1.2 - Test the retrieval of a recipe that doesn't exist
    @Test
    @DisplayName("Test retrieval of non-existent recipe")
    void testRetrievalOfNonExistentRecipe_ShouldReturnNull() {
        // todo: finish implementation
    }

    // Exercise 1.3 - Test that duplicate recipe names retrieve the first occurrence
    @Test
    @DisplayName("Adding recipes with duplicate names should retrieve the first added recipe on search")
    void testRecipesWithDuplicateNamesShouldRetrieveFirstAdded() {
        // todo: finish implementation
    }
}