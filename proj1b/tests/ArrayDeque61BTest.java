import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

    @Test
    @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
    void noNonTrivialFields() {
        List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                .toList();

        assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
    }

    @Test
    public void addFirstTest() {
        Deque61B<Integer> alist1 = new ArrayDeque61B<>();

        alist1.addFirst(3);
        alist1.addFirst(2);
        alist1.addFirst(1);

        assertThat(alist1.toList()).containsExactly(1, 2, 3);
    }

    @Test
    public void addLastTest() {
        Deque61B<Integer> alist1 = new ArrayDeque61B<>();

        alist1.addLast(1);
        alist1.addLast(2);
        alist1.addLast(3);
        assertThat(alist1.toList()).containsExactly(1, 2, 3);
    }

    @Test
    public void getTest() {
        Deque61B<String> alist1 = new ArrayDeque61B<>();

        alist1.addLast("Banana");
        alist1.addLast("Apple");
        alist1.addLast("Orange");

        assertThat(alist1.get(0)).isEqualTo("Banana");
        assertThat(alist1.get(2)).isEqualTo("Orange");
        assertThat(alist1.get(10000)).isEqualTo(null);
        assertThat(alist1.get(-10)).isEqualTo(null);
    }

    @Test
    public void sizeTest() {
        Deque61B<String> alist1 = new ArrayDeque61B<>();
        alist1.addLast("Banana");
        alist1.addLast("Apple");
        alist1.addLast("Orange");

        assertThat(alist1.size()).isEqualTo(3);

    }

    @Test
    public void emptyTest() {
        Deque61B<String> alist1 = new ArrayDeque61B<>();
        Deque61B<String> alist2 = new ArrayDeque61B<>();

        alist1.addLast("Banana");
        alist1.addLast("Apple");
        alist1.addLast("Orange");

        assertThat(alist1.isEmpty()).isFalse();
        assertThat(alist2.isEmpty()).isTrue();
    }

    @Test
    public void removeFirstTest() {
        Deque61B<String> alist1 = new ArrayDeque61B<>();

        alist1.addLast("Banana");
        alist1.addLast("Apple");
        alist1.addLast("Orange");

        alist1.removeFirst();
        assertThat(alist1.toList()).containsExactly("Apple", "Orange");

        alist1.removeFirst();
        assertThat(alist1.toList()).containsExactly("Orange");
    }

    @Test
    public void removeLastTest() {
        Deque61B<String> alist1 = new ArrayDeque61B<>();

        alist1.addLast("Banana");
        alist1.addLast("Apple");
        alist1.addLast("Orange");

        alist1.removeLast();
        assertThat(alist1.toList()).containsExactly("Banana", "Apple");

        alist1.removeLast();
        assertThat(alist1.toList()).containsExactly("Banana");
    }

    /** Check later
     * Issue with Testing, potentially in method*/
    @Test
    public void resizeTest() {
        Deque61B<String> alist1 = new ArrayDeque61B<>(64);

        alist1.addLast("Banana");
        alist1.addLast("Apple");
        alist1.addLast("Orange");
        alist1.addLast("Banana");
        alist1.addLast("Apple");
        alist1.addLast("Orange");
        alist1.addLast("Banana");
        alist1.addLast("Apple");
        alist1.addLast("Orange");
        alist1.addLast("Banana");
        alist1.addLast("Apple");
        alist1.addLast("Orange");

        alist1.removeLast();
        assertThat(alist1.getLength()).isEqualTo(8);

        Deque61B<String> alist2 = new ArrayDeque61B<>();
        alist1.addLast("Banana");
        alist1.addLast("Apple");
        alist1.addLast("Orange");
        alist1.addLast("Papaya");
        alist1.addLast("Kiwi");
        alist1.addLast("Melon");
        alist1.addLast("Pork");
        alist1.addLast("Grape");
        alist1.addLast("Mushmula");

        assertThat(alist2.getLength()).isEqualTo(16);
    }
}
