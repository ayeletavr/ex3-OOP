import oop.ex3.spaceship.*;

import java.util.HashMap;
import java.util.Map;

public class Locker {

    /** Locker's long-term storage. */
    public LongTermStorage lts;

    /** Locker's capacity. */
    public int capacity;

    /** Locker's constraints. */
    public Item[][] constraints;

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

    private int percentageOfCapacity;

    /** Consturctor. */
    public Locker(LongTermStorage lts, int capacity, Item[][] constraints) {
        this.lts = lts;
        this.capacity = capacity;
        this.constraints = constraints;
        this.totalSpace = capacity;
        this.baseballBats = 0;
        this.helmets1 = 0;
        this.helmets2 = 0;
        this.helmets3 = 0;
        this.sporesEngines = 0;
        this.football = 0;
        this.percentageOfCapacity = 0;
    }

    /**
     * Adds n items to the Locker.
     * @param item
     * @param n
     * @return If the addition was successful and doesn't cause Items to be moves to long-term storage, returns 0.
     * If item cannot be added to the locker, returns -1.
     * If this action causes n* items to be moved to long-term storage and it can accomodate all n* items, returns 1.
     */
    public int addItem(Item item, int n) {
        int space_needed = item.getVolume() * n;
        if ((item.getType().equals("baseball bat") && this.football > 0) || (item.getType() == "football" && this.baseballBats > 0)) {
            System.out.println("Error: Your request cannot be completed at this time. Problem: the locker cannot contain" +
                    " items of type " + item.getType() + ", as it contains a contradicting item.");
        }
        if (space_needed <= totalSpace) { // there is enough space in locker
            if (item.getType().equals("baseball bat")) {
                this.baseballBats += n;
                this.totalSpace -= n;
                double percentageOfCapacity = ((double)this.baseballBats * item.getVolume() / this.capacity) * 100;
                if (percentageOfCapacity > 50) {
                    int twentyPercent_n = (int) (capacity * 0.2 / item.getVolume());
                    int rest = n - twentyPercent_n;
                    this.baseballBats = this.baseballBats-n +twentyPercent_n;
                    this.totalSpace = this.totalSpace + n -twentyPercent_n * item.getVolume();
                    lts.addItem(item, rest);
                    return 1;
                }
                return 0;
            }
            if (item.getType().equals("helmet, size 1")) {
                this.helmets1 += n;
                this.totalSpace -= n;
                double percentageOfCapacity =((double) this.helmets1 * item.getVolume() / this.capacity)  * 100;
                if (percentageOfCapacity > 50) {
                    int twentyPercent_n = (int) (this.capacity * 0.2 / item.getVolume());
                    int rest = n - twentyPercent_n;
                    this.helmets1 = this.helmets1-n+twentyPercent_n;
                    this.totalSpace = this.totalSpace + n -twentyPercent_n * item.getVolume();
                    lts.addItem(item, rest);
                    return 1;
                }
                return 0;
            }
            if (item.getType().equals("helmet, size 2")) {
                this.helmets2 += n;
                this.totalSpace -= n;
                double percentageOfCapacity = ((double)this.helmets2 * item.getVolume() / this.capacity) * 100;
                if (percentageOfCapacity > 50) {
                    int twentyPercent_n = (int) (this.capacity * 0.2 / item.getVolume());
                    int rest = n - twentyPercent_n;
                    this.helmets2 = this.helmets2-n+twentyPercent_n;
                    this.totalSpace = this.totalSpace + n -twentyPercent_n * item.getVolume();
                    lts.addItem(item, rest);
                    return 1;
                }
                return 0;
            }
            if (item.getType().equals("helmet, size 3")) {
                this.helmets3 += n;
                this.totalSpace -= n;
                double percentageOfCapacity =  ((double) this.helmets3 * item.getVolume() / this.capacity) * 100;
                if (percentageOfCapacity > 50) {
                    int twentyPercent_n = (int) (this.capacity * 0.2 / item.getVolume());
                    int rest = n - twentyPercent_n;
                    this.helmets3 = this.helmets3-n+twentyPercent_n;
                    this.totalSpace = this.totalSpace + n -twentyPercent_n * item.getVolume();
                    lts.addItem(item, rest);
                    return 1;
                }
                return 0;
            }
            if (item.getType().equals("spores engine")) {
                this.sporesEngines += n;
                this.totalSpace -= n;
                 double percentageOfCapacity =  ( (double) this.sporesEngines * item.getVolume() / this.capacity) * 100;
                if (percentageOfCapacity > 50) {
                    int twentyPercent_n = (int) (this.capacity * 0.2 / item.getVolume());
                    int rest = n - twentyPercent_n;
                    this.sporesEngines = this.sporesEngines-n +twentyPercent_n;
                    this.totalSpace = this.totalSpace + n -twentyPercent_n * item.getVolume();
                    lts.addItem(item, rest);
                    return 1;
                }
                return 0;
            }
            if (item.getType().equals("football")) {
                this.football += n;
                this.totalSpace -= n;
                double percentageOfCapacity =  ((double) this.football * item.getVolume() / this.capacity) * 100;
                if (percentageOfCapacity > 50) {
                    int twentyPercent_n = (int) (capacity * 0.2 / item.getVolume());
                    int rest = n - twentyPercent_n;
                    this.football = this.football-n+twentyPercent_n;
                    this.totalSpace = this.totalSpace + n -twentyPercent_n * item.getVolume();
                    lts.addItem(item, rest);
                    return 1;
                }
                return 0;
            }

            int partial_space_to_locker = space_needed - totalSpace;
            int partial_space_to_storage = space_needed - partial_space_to_locker;
            int n_toLocker = space_needed / partial_space_to_locker;
            int n_toStorage = space_needed / partial_space_to_storage;
            if (partial_space_to_storage <= lts.totalSpace) { // can be stored in lts.
                totalSpace -= partial_space_to_locker;
                lts.totalSpace -= partial_space_to_storage;
                if (item.getType().equals("baseball bat")) {
                    lts.addItem(item, n_toStorage);
                    this.baseballBats += n_toLocker;
                }
                if (item.getType().equals("helmet, size 1")) {
                    lts.addItem(item, n_toStorage);
                    this.helmets1 += n_toLocker;
                }
                if (item.getType().equals("helmet, size 2")) {
                    lts.addItem(item, n_toStorage);
                    this.helmets2 += n_toLocker;
                }
                if (item.getType().equals("helmet, size 3")) {
                    lts.addItem(item, n_toStorage);
                    this.helmets3 += n_toLocker;
                }
                if (item.getType().equals("spores engine")) {
                    lts.addItem(item, n_toStorage);
                    this.sporesEngines += n_toLocker;
                }
                if (item.getType().equals("football")) {
                    lts.addItem(item, n_toStorage);
                    this.football += n_toLocker;
                }
                System.out.println("Warning: Action successful, but has caused items to be moved to storage");
                return 1;
            }

            System.out.println("Error: Your request cannot be completed at this time. Problem: no room for " + n_toStorage + " items of type " + item.getType());
            return -1;
        }
        return -1;
    }


    /** Removes n items from the locker.
     * @param item
     * @param n
     * @return if the action is successful, returns 0.
     * else, returns -1.
     */
    public int removeItem(Item item, int n) {
        if (n < 0) {
            System.out.println("Error: Your request cannot be completed at this time. Problem: cannot remove a negative number of items of type " + item.getType());
            return -1;
        }
        if (item.getType().equals("baseball bat")) {
            if (this.baseballBats >= n) {
                this.baseballBats -= n;
                return 0;
            }
        }
        if (item.getType().equals("helmet, size 1")) {
            if (this.helmets1 >= n) {
                this.helmets1 -= n;
                return 0;
            }
        }
        if (item.getType().equals("helmet, size 2")) {
            if (this.helmets2 >= n) {
                this.helmets2 -= n;
                return 0;
            }
        }
        if (item.getType().equals("helmet, size 3")) {
            if (this.helmets3 >= n) {
                this.helmets3 -= n;
                return 0;
            }
        }
        if (item.getType().equals("spores engine")) {
            if (this.sporesEngines >= n) {
                this.sporesEngines -= n;
                return 0;
            }
        }
        if (item.getType().equals("football")) {
            if (this.football >= n) {
                this.football -= n;
                return 0;
            }
        }
        System.out.println("Error: Your request cannot be completed at this time. Problem: the locker does not contain " +n+ " items of type " +item.getType());
        return -1;
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
    public Map<String,Integer> getInventory() {
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

    /** Returns the locker's total capacity. */
    public int getCapacity() {
        return this.capacity;
    }

    /** Returns the locker's available capacity. */
    public int getAvailableCapacity() {
        return this.totalSpace;
    }
}