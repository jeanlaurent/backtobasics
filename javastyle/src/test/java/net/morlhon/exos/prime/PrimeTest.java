package net.morlhon.exos.prime;

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
        assertThat(new Prime().nearestGreaterOf(3)).isEqualTo(5);
        assertThat(new Prime().nearestGreaterOf(7)).isEqualTo(11);
        assertThat(new Prime().nearestGreaterOf(11)).isEqualTo(13);
        assertThat(new Prime().nearestGreaterOf(13)).isEqualTo(17);
        assertThat(new Prime().nearestGreaterOf(17)).isEqualTo(19);
        assertThat(new Prime().nearestGreaterOf(19)).isEqualTo(23);
    }

}