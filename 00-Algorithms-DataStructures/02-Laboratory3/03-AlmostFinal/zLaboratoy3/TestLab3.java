package zLaboratoy3;

public class TestLab3 {
    
    public static void main(String[] args) {
       
        Sensor point1 = new Sensor("Root", 8);
        Sensor point2 = new Sensor("A", 10);
        Sensor point3 = new Sensor("B", 3);
        Sensor point4 = new Sensor("C", 1);
        Sensor point5 = new Sensor("D", 6);
        Sensor point6 = new Sensor("E", 9);
        Sensor point7 = new Sensor("F", 7);
        Sensor point8 = new Sensor("G", 14);
        
        SortedBinaryTree<Sensor> myTree = new SortedBinaryTree<Sensor>(point1);
        myTree.insert(point2);
        myTree.insert(point3);
        myTree.insert(point4);
        myTree.insert(point5);
        myTree.insert(point6);
        myTree.insert(point7);
        myTree.insert(point8);
        
        myTree.printGrid();
        
        System.out.println("=== TEST RESULTS ===");
        
        Node minNode = myTree.min();
        System.out.println("MIN Value: " + (minNode != null ? minNode.getKey() : "null") + " (Expected: 1)");

        Node maxNode = myTree.max();
        System.out.println("MAX Value: " + (maxNode != null ? maxNode.getKey() : "null") + " (Expected: 14)");

        int searchKey = 6;
        Node foundNode = myTree.find(new Sensor("dummy", searchKey));
        System.out.println("FIND(" + searchKey + "): " + (foundNode != null ? "Found Node with Key " + foundNode.getKey() : "Not Found"));

        int missingKey = 99;
        Node missingNode = myTree.find(new Sensor("dummy", missingKey));
        System.out.println("FIND(" + missingKey + "): " + (missingNode != null ? "Found " + missingNode.getKey() : "Correctly returned null"));
        
        testSucc(myTree, 6); // Expected 7
        testSucc(myTree, 8); // Expected 9
        testSucc(myTree, 9); // Expected 10
        testSucc(myTree, 14); // Expected null (is max)
        
//		Iterator<Node> it = myTree.iterator();
//		
//		while (it.hasNext()) {
//			Node n = it.next();
//			System.out.println(n.getKey());
//		}
    }
    
    // Helper for printing successor tests clearly
    private static void testSucc(SortedBinaryTree<Sensor> tree, int key) {
        Node input = new Sensor("temp", key);
        Node s = tree.succ(input);
        System.out.println("SUCC(" + key + "): " + (s != null ? s.getKey() : "null"));
    }
}