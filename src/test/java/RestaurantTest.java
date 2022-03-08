import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;

    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void beforeEachTest() {
        LocalTime openingTime = LocalTime.parse("10:00:00");
        LocalTime closingTime = LocalTime.parse("22:30:00");
        restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        LocalTime CustomTime = LocalTime.parse("15:30:00");
        Restaurant SpytimeInBetwwen = Mockito.spy(restaurant);
        Mockito.when(SpytimeInBetwwen.getCurrentTime()).thenReturn(CustomTime);
        boolean restrudentStatus = SpytimeInBetwwen.isRestaurantOpen();
        assertTrue(restrudentStatus);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //before opening time expected false
        LocalTime CustomTimeBefroe = LocalTime.parse("08:00:00");
        Restaurant SpytimeBefore = Mockito.spy(restaurant);
        Mockito.when(SpytimeBefore.getCurrentTime()).thenReturn(CustomTimeBefroe);
        boolean restrudentStatusBefore = SpytimeBefore.isRestaurantOpen();
        assertFalse(restrudentStatusBefore);

        //after opening time expected false
        LocalTime CustomTimeAfter = LocalTime.parse("23:00:00");
        Restaurant SpytimeAfter = Mockito.spy(restaurant);
        Mockito.when(SpytimeAfter.getCurrentTime()).thenReturn(CustomTimeAfter);
        boolean restrudentStatusAfter = SpytimeAfter.isRestaurantOpen();
        assertFalse(restrudentStatusAfter);

    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


}