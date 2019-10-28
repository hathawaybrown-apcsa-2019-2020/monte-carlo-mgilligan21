/**
 * This class simulates 100000 flights and determines the average number of 
 * seats filled and number of times overbooked based on the number of tickets
 * sold, the number of seats, and the no-show probability.
 *
 * @author Maxine Gilligan
 * @version 24 Oct 2019
 */
public class AirlineMonteCarlo
{
    private final int NUM_SIMULATIONS = 100000;
    private int ticketsSold;
    private int seats;
    private double noShowProbability;
    private int overBooked;
    private double average;
    private double percent;
    
    /**
     * Constructor for object of class AirlineMonteCarlo
     * @param ticketsSold         Number of tickets sold
     * @param seats               Number of seats
     * @param noShowProbability   No Show Probability
     */
    public AirlineMonteCarlo(int t, int s, double ns)
    {
        this.ticketsSold = t;
        this.seats = s;
        this.noShowProbability = ns;
    }
    
    /**
     * Simulates a flight by calculating and returning the number of seats filled
     * @param seatsFilled         Number of seats filled
     * @return                    The number of seats filled 
     */
    public int simulateFlight()
    {
        int seatsFilled = 0;
        for(int n = 1; n <= ticketsSold; n++)
        {
            if(Math.random() > noShowProbability)
            {
                seatsFilled = seatsFilled + 1;
            }
        }
        return seatsFilled;
    }
    
    /**
     * Runs simulations to calculate the average number of seats filled, the number of times overbooked, and the percentage 
     * of overbooked flights.
     */
    public void runSimulation()
    {
        int total = 0;
        for (int n = 1; n <= NUM_SIMULATIONS; n++)
        {
            int seatsFilled = simulateFlight();
            total = total + seatsFilled;
            if (seatsFilled > seats)
            {
                overBooked = overBooked + 1;
            }
            
        }
        average = (double)(total) / NUM_SIMULATIONS;
        percent = (double)overBooked * 100 / NUM_SIMULATIONS;
    }
    
    /**
    * Prints the tickets sold, number of seats, no-show probability, number of simulations, average seats filled, and number and 
    * percentage of times overbooked. 
    */ 
    public void reportResults()
    {
        System.out.println("Simulation: " + ticketsSold + " tickets sold for " + seats + " seats; no-show probability = " + noShowProbability);
        System.out.println("Based on " + NUM_SIMULATIONS + " simulations:");
        System.out.println("Average seats filled: " + average);
        System.out.println("Number of times overbooked: " + overBooked + " (" + percent + " percent)");
    }
    
    /**
     * One instance of a simulation with 140 tickets sold, 136 seats, and a no show probability of 0.04.
     */
    public static void main(String[] args)
    {
        AirlineMonteCarlo mySim = new AirlineMonteCarlo(140,136,0.04);
        mySim.runSimulation();
        mySim.reportResults();
    }
}