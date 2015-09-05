package net.morlhon.exos;

import net.morlhon.exos.pair.ArrayPairSum;
import net.morlhon.exos.pair.Pair;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayPairSumTest {
    int[] input = new int[]{10, 2, 8, 7, 5, 0, 4, 9, 1, 6};
    int target = 10;


    @Test
    public void should_find_proper_result() {
        List<Pair> pairs = new ArrayPairSum().find(input, target);
        assertThat(pairs).hasSize(4);
        assertThat(pairs).containsOnly(new Pair(10,0), new Pair(2,8), new Pair(4,6), new Pair(9,1));
    }


    @Test
    public void should_find2_proper_result() {
        List<Pair> pairs = new ArrayPairSum().find2(input, target);
        assertThat(pairs).hasSize(4);
        assertThat(pairs).containsOnly(new Pair(10, 0), new Pair(2, 8), new Pair(4, 6), new Pair(9, 1));
    }


    @Test
    public void should_find3_proper_result() {
        List<Pair> pairs = new ArrayPairSum().find3(input, target);
        assertThat(pairs).hasSize(4);
        assertThat(pairs).containsOnly(new Pair(10,0), new Pair(2,8), new Pair(4,6), new Pair(9,1));
    }

}
