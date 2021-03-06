package net.morlhon.exos.hash;

import net.morlhon.exos.hashmap.MyHashMap;
import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;

public class MyHashMapTest {


    @Test
    public void should_not_find_any_element() {
        MyHashMap<String, String> map = new MyHashMap<>();
        assertThat(map.get("foo")).isNull();
    }

    @Test
    public void should_add_an_element() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.add("foo", "foo");
        map.add("bar", "bar");
        map.add("qix", "qix");
        assertThat(map.get("foo")).isEqualTo("foo");
        assertThat(map.get("bar")).isEqualTo("bar");
        assertThat(map.get("qix")).isEqualTo("qix");
    }

    @Test
    public void should_add_an_element_and_support_replace() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.add("foo", "bar");
        map.add("foo", "baZ");
        assertThat(map.get("foo")).isEqualTo("baZ");
    }

    @Test
    public void should_behave_correctly_eventhough_hashcode_is_flawed() {
        MyHashMap<Flawed, String> map = new MyHashMap<>();
        map.add(new Flawed("foo"), "foo");
        map.add(new Flawed("bar"), "bar");
        map.add(new Flawed("qix"), "qix");
        assertThat(map.array()[42].size()).isEqualTo(3);
        assertThat(map.get(new Flawed("foo"))).isEqualTo("foo");
        assertThat(map.get(new Flawed("bar"))).isEqualTo("bar");
        assertThat(map.get(new Flawed("qix"))).isEqualTo("qix");
    }

    @Test
    public void should_delete_node() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.add("foo", "foo");
        map.add("bar", "bar");
        map.add("qix", "qix");
        assertThat(map.get("foo")).isEqualTo("foo");
        assertThat(map.get("bar")).isEqualTo("bar");
        assertThat(map.get("qix")).isEqualTo("qix");
        assertThat(map.delete("foo")).isTrue();
        assertThat(map.get("foo")).isNull();
        assertThat(map.get("bar")).isEqualTo("bar");
        assertThat(map.get("qix")).isEqualTo("qix");
    }


    @Test
    public void should_rehash_if_loadfactor_is_reached() {
        MyHashMap<String, String> map = new MyHashMap<>(11);
        map.setLoadFactor(.8f);
        for (int i = 0; i < 8; i++) {
            map.add("foo" + i, "foo" + i);
        }
        assertThat(map.currentMaxSize()).isEqualTo(11);
        assertThat(map.size()).isEqualTo(8);
        assertThat(map.array()[0].get(0).key).isEqualTo("foo7"); // foo7 is @ rank 0
//        System.out.println(map.toString());
        map.add("foo" + 8, "foo" + 8);
        assertThat(map.size()).isEqualTo(9);
        assertThat(map.currentMaxSize()).isEqualTo(23);
        assertThat(map.array()[0]).isNull(); // nothing is at 0 anymore
        assertThat(map.array()[11].get(0).key).isEqualTo("foo7"); // foo7 has moved to rank 11
//        System.out.println(map.toString());
    }

    @Test
    public void should_not_change_size_when_size_is_already_a_prime_number() {
        MyHashMap<String, String> map = new MyHashMap<>(11);
        assertThat(map.currentMaxSize()).isEqualTo(11);
    }

    @Test
    public void should_find_nearest_greater_prime_number_of_suggested_size() {
        MyHashMap<String, String> map = new MyHashMap<>(100);
        assertThat(map.currentMaxSize()).isEqualTo(101);
    }


    class Flawed {
        public final String string;

        public Flawed(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }

        @Override
        public int hashCode() {
            return 42;
        }

        @Override
        public boolean equals(Object obj) {
            return ((Flawed) obj).string.equals(string);
        }
    }

}