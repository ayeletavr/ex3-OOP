import oop.ex3.spaceship.*;

import java.util.HashMap;
import java.util.Map;

public class LongTermStorage{ //extends Locker {

    private static final int ltsCapacity = 1000;

    /** Locker's capacity. */
    public int capacity;

    /** Locker's current left space. */
    public int totalSpace;

    /** Represents the quantity of baseball bats the locker currently holds. */
    public int baseballBats;

    /** Represents the quantity of helmets size 1 the locker currently holds. */
    public int helmets1;

    /** Represents the quantity of helmets size 2 the locker currently holds. */
    public int helmets2;

    /** Represents the quantity of helmets size 3 the locker currently holds. */
    public int helmets3;

    /** Represents the quantity of spores engines the locker currently holds. */
    public int sporesEngines;

    /** Represents the quantity of footballs the locker currently holds. */
    public int football;


    /** Consturctor. */
    public LongTermStorage() {
        this.capacity = ltsCapacity;
        this.totalSpace = capacity;
        this.baseballBats = 0;
        this.helmets1 = 0;
        this.helmets2 = 0;
        this.helmets3 = 0;
        this.sporesEngines = 0;
        this.football = 0;
    }

    /**
     * adds n items of the given type to the long-term storage unit.
     * @param item
     * @param n
     * @return If the action is successful, this method should return 0.
     * if n items cannot be added to the locker at this time, returns -1.
     */
    public int addItem(Item item, int n) {
        int space_needed = item.getVolume() * n;
        if (space_needed <= totalSpace) { // there is enough space in storage.
            this.totalSpace -= n;
            if (item.getType().equals("baseball bat")) {
                this.baseballBats += n;
            }
            if (item.getType().equals("helmet, size 1")) {
                this.helmets1 += n;
            }
            if (item.getType().equals("helmet, size 2")) {
                this.helmets2 += n;
            }
            if (item.getType().equals("helmet, size 3")) {
                this.helmets3 += n;
            }
            if (item.getType().equals("spores engine")) {
                this.sporesEngines += n;
            }
            if (item.getType().equals("football")) {
                this.football += n;
            }
            return 0;
        }
        System.out.println("Error: Your request cannot be completed at this time. Problem: no room for " +n+ " items of type " +item.getType());
        return -1;
    }

    /** resets the long-term storage's inventory. */
    public void resetInventory() {
        this.baseballBats = 0;
        this.helmets1 = 0;
        this.helmets2 = 0;
        this.helmets3 = 0;
        this.sporesEngines = 0;
        this.football = 0;
    }
    /** Returns the number of items of type the locker contains. */
    public int getItemCount (String type) {
        if (type.equals("baseball bat")) {
            return this.baseballBats;
        }
        if (type.equals("helmet, size 1")) {
            return this.helmets1;
        }
        if (type.equals("helmet, size 2")) {
            return this.helmets2;
        }
        if (type.equals("helmet, size 3")) {
            return this.helmets3;
        }
        if (type.equals("spores engine")) {
            return this.sporesEngines;
        }
        if (type.equals("football")) {
            return this.football;
        }
        System.out.println("Error: Your request cannot be completed at this time.");
        return -1;
        }

    /** Returns a map of all item types contained in the locker, and their respective quantities. */
    public Map<String, Integer> getInventory() {
        Map <String,Integer> inventory = new HashMap<>();
        if (this.baseballBats > 0) {
            inventory.put("baseball bat", this.baseballBats);
        }
        if (this.helmets1 > 0) {
            inventory.put("helmet, size 1", this.helmets1);
        }
        if (this.helmets2 > 0) {
            inventory.put("helmet, size 2", this.helmets2);
        }
        if (this.helmets3 > 0) {
            inventory.put("helmet, size 3", this.helmets3);
        }
        if (this.sporesEngines > 0) {
            inventory.put("spores engine", this.sporesEngines);
        }
        if (this.football > 0) {
            inventory.put("football", this.football);
        }
        return inventory;
    }

    /** Returns the storage's total capacity. */
    public int getCapacity() {
        return capacity;
    }

    /** Returns the storage's available capacity. */
    public int getAvailableCapacity() {
        return totalSpace;
    }
}
