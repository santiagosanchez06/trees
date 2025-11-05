public class BinarySearchTreeDemo {

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    static class BinarySearchTree {
        TreeNode root;

        public void insert(int value) {
            root = insertNode(root, null, value);
        }

        private TreeNode insertNode(TreeNode current, TreeNode parent, int value) {
            if (current == null) {
                TreeNode newNode = new TreeNode(value);
                newNode.parent = parent;
                return newNode;
            }

            if (value < current.value) {
                current.left = insertNode(current.left, current, value);
            } else if (value > current.value) {
                current.right = insertNode(current.right, current, value);
            }
            // No insertamos duplicados
            return current;
        }

        public TreeNode find(int target) {
            return findNode(root, target);
        }

        private TreeNode findNode(TreeNode current, int target) {
            if (current == null) return null;
            if (current.value == target) return current;
            if (target < current.value) {
                return findNode(current.left, target);
            } else {
                return findNode(current.right, target);
            }
        }

        // Recorrido in-order: izquierda → raíz → derecha
        public void inorder() {
            System.out.print("Recorrido in-order: ");
            inorderRec(root);
            System.out.println();
        }

        private void inorderRec(TreeNode node) {
            if (node != null) {
                inorderRec(node.left);
                System.out.print(node.value + " ");
                inorderRec(node.right);
            }
        }

        // Recorrido pre-order: raíz → izquierda → derecha
        public void preorder() {
            System.out.print("Recorrido pre-order: ");
            preorderRec(root);
            System.out.println();
        }

        private void preorderRec(TreeNode node) {
            if (node != null) {
                System.out.print(node.value + " ");
                preorderRec(node.left);
                preorderRec(node.right);
            }
        }

        // Recorrido post-order: izquierda → derecha → raíz
        public void postorder() {
            System.out.print("Recorrido post-order: ");
            postorderRec(root);
            System.out.println();
        }

        private void postorderRec(TreeNode node) {
            if (node != null) {
                postorderRec(node.left);
                postorderRec(node.right);
                System.out.print(node.value + " ");
            }
        }

        public void delete(int value) {
            root = deleteNode(root, value);
        }

        private TreeNode deleteNode(TreeNode current, int value) {
            if (current == null) return null;

            if (value < current.value) {
                current.left = deleteNode(current.left, value);
            } else if (value > current.value) {
                current.right = deleteNode(current.right, value);
            } else {
                // Caso 1: nodo hoja
                if (current.left == null && current.right == null) {
                    return null;
                }
                // Caso 2: un solo hijo
                else if (current.left == null) {
                    return current.right;
                } else if (current.right == null) {
                    return current.left;
                }
                // Caso 3: dos hijos
                else {
                    TreeNode successor = findMin(current.right);
                    current.value = successor.value;
                    current.right = deleteNode(current.right, successor.value);
                }
            }
            return current;
        }

        private TreeNode findMin(TreeNode node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        System.out.println("Creando árbol binario de búsqueda...");

        int[] values = {8, 3, 10, 1, 6, 14, 4, 7, 13};
        for (int v : values) {
            tree.insert(v);
        }

        System.out.println("\nResultados:");
        tree.inorder();
        tree.preorder();
        tree.postorder();

        System.out.println("\nEliminando el nodo con valor 6...");
        tree.delete(6);

        System.out.println("Árbol después de eliminar 6:");
        tree.inorder();
    }
}