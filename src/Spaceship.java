import oop.ex3.spaceship.*;

public class Spaceship {

    /** Spaceship's name. */
    public String name;

    /** Spaceship's crew IDs. */
    public int[] crewIDs;

    /** Spaceship's maximal number of lockers. */
    public int numOfLockers;

    /** Spaceship's Lockers array. */
    private Locker[] lockersArr;

    /** Spaceship's long term storage. */
    public LongTermStorage longTermStorage;

    /** Constructor. */
    public Spaceship(String name, int[] crewIDs, int numOfLockers){
        this.name = name;
        this.crewIDs = crewIDs;
        this.numOfLockers = numOfLockers;
        this.lockersArr = new Locker[numOfLockers];
        this.longTermStorage = new LongTermStorage();
    }

    /** This method returns the long-term storage object associated with that Spaceship. */
    public LongTermStorage getLongTermStorage() {
        return this.longTermStorage;
    }

    /** This method creates a locker object, and adds it as part of the spaceship's storage.
     * The new locker is associated with a crew member with the given id, and has the given capacity.
     * If the id is not valid, a new locker is not created and the method returns -1.
     * If the given capacity does not meet the locker class requirements,
     * a new locker is not created and the method returns -2.
     * If the spaceship already contains the allowed number of lockers,
     * a new locker is not created the method returns -3.
     * If a locker was created successfully, returns 0.
     */
    public int createLocker(int crewID, int capacity) {
        if (capacity < 0) {
            return -2;
        }
        if (lockersArr[numOfLockers] != null) {
            return -3;
        }
        for (int i=0; i < crewIDs.length; i++){
            if (crewIDs[i] == crewID) {
                for (int j=0; j < lockersArr.length; j++) {
                    if (lockersArr[j] == null) {
                        lockersArr[j] = new Locker(longTermStorage, capacity, ItemFactory.getConstraintPairs());
                        return 0;
                    }
                }
            }
        }
        return -3;
    }

    /** Returns an array with the crew's ids. */
    public int[] getCrewIDs() {
        return crewIDs;
    }

    /** Returns an array of Lockers, whose length is numOfLockers. */
    public Locker[] getLockers() {
        return lockersArr;
    }

}
