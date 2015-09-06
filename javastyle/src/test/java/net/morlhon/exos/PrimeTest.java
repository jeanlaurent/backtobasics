package net.morlhon.exos;

import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;

public class PrimeTest {


    @Test
    public void should_identify_prime_number() {
        assertThat(new Prime().is(1)).isTrue();
        assertThat(new Prime().is(2)).isTrue();
        assertThat(new Prime().is(3)).isTrue();
        assertThat(new Prime().is(4)).isFalse();
        assertThat(new Prime().is(5)).isTrue();
        assertThat(new Prime().is(6)).isFalse();
        assertThat(new Prime().is(7)).isTrue();
        assertThat(new Prime().is(8)).isFalse();
        assertThat(new Prime().is(9)).isFalse();
        assertThat(new Prime().is(10)).isFalse();
        assertThat(new Prime().is(11)).isTrue();
    }

    @Test
    public void should_find_nearest_prime_number() {
        assertThat(new Prime().nearestOf(3)).isEqualTo(5);
        assertThat(new Prime().nearestOf(7)).isEqualTo(11);
        assertThat(new Prime().nearestOf(11)).isEqualTo(13);
        assertThat(new Prime().nearestOf(13)).isEqualTo(17);
        assertThat(new Prime().nearestOf(17)).isEqualTo(19);
        assertThat(new Prime().nearestOf(19)).isEqualTo(23);
    }

}