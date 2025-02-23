import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import deque.*;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

     // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    /** Checks both for empty and non-empty deques */
    public void isEmptyTest() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();
         assertThat(lld1.isEmpty()).isTrue();

         Deque61B<String> lld2 = new LinkedListDeque61B<>();
         lld2.addLast("SomeString");
         assertThat(lld2.isEmpty()).isFalse();
    }

    @Test
    public void sizeTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.size()).isEqualTo(0);

        Deque61B<String> lld2 = new LinkedListDeque61B<>();
        lld2.addLast("SomeString");
        lld2.addLast("OtherStr");
        assertThat(lld2.size()).isEqualTo(2);
    }

    @Test
    public void getTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(5);
        lld1.addLast(7);
        lld1.addLast(9);
        lld1.addLast(11);
        assertThat(lld1.get(1)).isEqualTo(7);
        assertThat(lld1.get(3)).isEqualTo(11);
        assertWithMessage("Index out of bounds")
                .that(lld1.get(2830))
                .isEqualTo(null);
        assertWithMessage("Cannot use negative indexes")
                .that(lld1.get(-10))
                .isEqualTo(null);
    }

    @Test
    public void getRecursiveTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(5);
        lld1.addLast(7);
        lld1.addLast(9);
        lld1.addLast(11);
        assertThat(lld1.getRecursive(1)).isEqualTo(7);
        assertThat(lld1.getRecursive(3)).isEqualTo(11);
        assertWithMessage("Index out of bounds")
                .that(lld1.getRecursive(2830))
                .isEqualTo(null);
        assertWithMessage("Cannot use negative indexes")
                .that(lld1.getRecursive(-10))
                .isEqualTo(null);
    }

    @Test
    public void removeFirstTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("Apple");
        lld1.addLast("Banana");
        lld1.addLast("Orange");

        String actual = lld1.removeFirst();
        assertThat(actual).isEqualTo("Apple");
        assertThat(lld1.toList()).containsExactly("Banana", "Orange").inOrder();

        Deque61B<String> lld2 = new LinkedListDeque61B<>();
        assertThat(lld2.removeFirst()).isEqualTo(null);
    }

    @Test
    public void removeLastTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("Apple");
        lld1.addLast("Banana");
        lld1.addLast("Orange");

        String actual = lld1.removeLast();
        assertThat(actual).isEqualTo("Orange");
        assertThat(lld1.toList()).containsExactly("Apple", "Banana").inOrder();

        Deque61B<String> lld2 = new LinkedListDeque61B<>();
        assertThat(lld2.removeLast()).isEqualTo(null);
    }
}