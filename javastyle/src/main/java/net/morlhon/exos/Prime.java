package net.morlhon.exos;

public class Prime {

    public boolean is(int target) {
        if (target - 1 > 2) {
            for (int divisor = (int)Math.floor( Math.sqrt(target) + 1); divisor >= 2 ; divisor--) {
                if ((target % divisor) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int nearestGreaterOf(int number) {
        int someNumber = number + 1;
        while(!is(someNumber)) {
            someNumber++;
        }
        return someNumber;
    }

}
