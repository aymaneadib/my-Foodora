package notifications;

import food.Meal;

/**
 * Observer interface for the Observer design pattern.
 * This interface will get implemented by the Customer class since it is the one to observe new meals of the weeks.
 * 
 * @author Aymane ADIB
 */
public interface Observer {
    
    /**
     * Update method to be called when the observable notifies its observers.
     * 
     * @param mealOfTheWeek The new meal of the week.
     */
    public void update(Meal mealOfTheWeek);

}
