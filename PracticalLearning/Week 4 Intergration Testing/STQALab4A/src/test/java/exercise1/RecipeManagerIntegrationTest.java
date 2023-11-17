package exercise1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

        //act
        Recipe chicken = recipeManager.getRecipe("Chicken");

        //assert
        assertNull(chicken, "The retrieved recipe doesnt exist");

    }

    // Exercise 1.3 - Test that duplicate recipe names retrieve the first occurrence
    /* Is it better not sometimes to fail test for example , in this the test passes as it should do be if we
    are to fail the test on purpouse : eg. we change the assertEquals to ingredients to hot sauce , is this not a
    better demonstration that the intergation is working as it should be because surely we can create a test that passes
    but doesnt actually act the way it shoould do , however also vice versa how do we know if a tests fail becuase of
    the test code we written is syntaxly/not written correctly or the way the they have written there code doesnt act they
    way it should !
     */

    @Test
    @DisplayName("Adding recipes with duplicate names should retrieve the first added recipe on search")
    void testRecipesWithDuplicateNamesShouldRetrieveFirstAdded() {
        // todo: finish implementation

        // arrange
        Recipe recipe1 = new Recipe("Fried Chicken", "flour, milk, eggs", "Mix ingredients and cook.");
        // arrange
        Recipe recipe2 = new Recipe("Fried Chicken", "Hot Sauce, milk, eggs", "Mix ingredients and cook.");

        //Act
        recipeManager.addRecipe(recipe1);
        recipeManager.addRecipe(recipe2);
        Recipe retrievedRecipe = recipeManager.getRecipe("Fried Chicken");

        //assert
        assertNotNull(retrievedRecipe, "The retrieved recipe should not be null");
        assertEquals("Fried Chicken", retrievedRecipe.getName(), "The retrieved recipe should have the correct name");
        assertEquals("flour, milk, eggs", retrievedRecipe.getIngredients(), "The retrieved recipe should have the correct ingredients");
        assertEquals("Mix ingredients and cook.", retrievedRecipe.getInstructions(), "The retrieved recipe should have the correct instructions");

    }
}