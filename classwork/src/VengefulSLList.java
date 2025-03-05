public class VengefulSLList<Item> extends SLList<Item> {
    private SLList<Item> deletedItems;

    public VengefulSLList() {
        deletedItems = new SLList<Item>();
    }

    @Override
    public Item removeLast() {
        Item oldBack = super.removeLast(); /*calls Superclass’s
version of removeLast() */
        deletedItems.addLast(oldBack);
        return oldBack;
    }

    public void printLostItems() {
        deletedItems.print();
    }


    public static void main(String[] args) {
        VengefulSLList<Integer> vs1 = new VengefulSLList<Integer>();
        vs1.addLast(1);
        vs1.addLast(5);
        vs1.addLast(10);
        vs1.addLast(13);      /* [1, 5, 10, 13] */
        vs1.removeLast();     /* 13 gets deleted. */
        vs1.removeLast();     /* 10 gets deleted. */
        System.out.print("The fallen are: ");
        vs1.printLostItems(); /* Should print 10 and 13. */
    }
}