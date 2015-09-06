package net.morlhon.exos;

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
        assertThat(map.array[42].size()).isEqualTo(3);
        assertThat(map.get(new Flawed("foo"))).isEqualTo("foo");
        assertThat(map.get(new Flawed("bar"))).isEqualTo("bar");
        assertThat(map.get(new Flawed("qix"))).isEqualTo("qix");
    }

    @Test
    public void should_rehash_if_loadfactor_is_reached() {
        MyHashMap<String, String> map = new MyHashMap<>(11, .8f);
        for (int i = 0; i < 8; i++) {
            map.add("foo" + i, "foo" + i);
        }
        assertThat(map.array.length).isEqualTo(11);
        assertThat(map.getLoad()).isEqualTo(8);
        System.out.println(map.toString());
        map.add("foo" + 8, "foo" + 8);
        assertThat(map.getLoad()).isEqualTo(9);
        assertThat(map.array.length).isEqualTo(23);
        System.out.println(map.toString());

    }


    class Flawed {
        public final String string;

        public Flawed(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string.toString();
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