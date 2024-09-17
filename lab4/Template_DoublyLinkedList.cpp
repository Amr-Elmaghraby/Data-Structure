#include <iostream>
using namespace std;

// Node structure for doubly linked list
template<typename T>
struct Node {
    T data;         // Data contained in the node
    Node* prev;     // Pointer to the previous node
    Node* next;     // Pointer to the next node
    
    // Constructor to initialize a new node
    Node(T val) : data(val), prev(nullptr), next(nullptr) {}
};

template<typename T>
class DoublyLinkedList {
private:
    Node<T>* head;  // Pointer to the head of the list
    Node<T>* tail;  // Pointer to the tail of the list
    int size;       // Size of the list

    // Method to get the node just before a given index
    Node<T>* get_prev_Node(int index) {
        Node<T>* current;

        // Decide whether to traverse from head or tail
        if (index <= size / 2) {
            current = head;
            // Traverse from the head to the node before the desired index
            for (int i = 0; i < index - 1; ++i) {
                current = current->next;
            }
        } else {
            current = tail;
            // Traverse from the tail to the node before the desired index
            for (int i = size; i > index; --i) {
                current = current->prev;
            }
        }
        return current;
    }

public:
    // Constructor to initialize an empty list
    DoublyLinkedList() : head(nullptr), tail(nullptr), size(0) {}

    // Method to insert a node at the front of the list
    void insertFront(T val) {
        Node<T>* newNode = new Node(val);
        if (!head) {
            head = tail = newNode;  // List is empty, new node is both head and tail
        } else {
            newNode->next = head;   // Insert new node before the current head
            head->prev = newNode;
            head = newNode;         // Update head to new node
        }
        size++;
    }

    // Method to insert a node at the end of the list
    void insertEnd(T val) {
        Node<T>* newNode = new Node(val);
        if (!head) {
            head = tail = newNode;  // List is empty, new node is both head and tail
        } else {
            tail->next = newNode;   // Insert new node after the current tail
            newNode->prev = tail;
            tail = newNode;         // Update tail to new node
        }
        size++;
    }

    // Method to insert a node at a specific index
    void insertAt(int index, T val) {
        if (index < 0 || index > size) {
            throw out_of_range("Index out of range"); // Handle invalid index
        }
        if (index == 0) {
            insertFront(val);  // Insert at the beginning
        } else if (index == size) {
            insertEnd(val);    // Insert at the end
            //Note: if added at the end it's index will be +1
        } else {
            Node<T>* newNode = new Node(val);
            Node<T>* current = get_prev_Node(index);

            // Insert the new node
            newNode->next = current->next;  // Link new node to the next node
            newNode->prev = current;        // Link new node to the previous node
            current->next->prev = newNode;  // Update the previous node of the next node
            current->next = newNode;        // Update the next node of the current node

            size++;
        }
    }

    // Method to get the current size of the list
    int getSize() {
        return size;
    }

    // Method to remove and return the node at the front of the list
    T popFront() {
        if (!head) {
            throw runtime_error("List is empty!"); // Handle empty list case
        }
        T val = head->data;
        Node<T>* tmp = head;
        head = head->next;
        if (head) {
            head->prev = nullptr;
        } else {
            tail = nullptr; // List is empty after removal
        }
        delete tmp;
        size--;
        return val;
    }

    // Method to remove and return the node at the end of the list
    T popBack() {
        if (!tail) {
            throw runtime_error("List is empty!"); // Handle empty list case
        }
        T val = tail->data;
        Node<T>* tmp = tail;
        tail = tail->prev;
        if (tail) {
            tail->next = nullptr;
        } else {
            head = nullptr; // List is empty after removal
        }
        delete tmp;
        size--;
        return val;
    }

    // Method to get the value at a specific index
    T getValAt(int index) {
        if (index < 0 || index >= size) {
            throw out_of_range("Index out of range"); // Handle invalid index
        }
        if (index == 0) {
            return head->data;  // Return value of the head node
        }
        Node<T>* current = get_prev_Node(index);
        current = current->next;
        if (current) {
            return current->data;  // Return value of the node at the index
        }
        return 0;  // Default return value if node is not found (should not happen)
    }

    // Method to delete the first occurrence of a value in the list
    void deleteVal(T val) {
        Node<T>* current = head;
        while (current) {
            if (current->data == val) {
                // Deleting head node
                if (current == head) {
                    head = head->next;
                    if (head) {
                        head->prev = nullptr;
                    } else {
                        tail = nullptr; // List is empty
                    }
                }
                // Deleting tail node
                else if (current == tail) {
                    tail = tail->prev;
                    if (tail) {
                        tail->next = nullptr;
                    } else {
                        head = nullptr; // List is empty
                    }
                }
                // Deleting a middle node
                else {
                    current->prev->next = current->next;
                    current->next->prev = current->prev;
                }
                delete current;
                size--;
                return;
            }
            current = current->next;
        }
    }

    // Method to print the list from head to tail
    void printForward() {
        for (Node<T>* current = head; current; current = current->next) {
            cout << current->data << " ";
        }
        cout << endl;
    }

    // Method to print the list from tail to head
    void printBackward() {
        for (Node<T>* current = tail; current; current = current->prev) {
            cout << current->data << " ";
        }
        cout << endl;
    }

    // Destructor to clean up all nodes
    ~DoublyLinkedList() {
        Node<T>* current = head;
        while (current) {
            Node<T>* tmp = current;
            current = current->next;
            delete tmp;
        }
    }
};

// Main function for testing the DoublyLinkedList class
int main() {
    // Create a DoublyLinkedList for integers
    DoublyLinkedList<int> dll;

    // Append values to the list
    dll.insertEnd(10);
    dll.insertEnd(20);
    dll.insertEnd(30);

    // Prepend values to the list
    dll.insertFront(5);
    dll.insertFront(1);

    // Print the list in forward order
    cout << "List forward: ";
    dll.printForward();

    // Print the list size
    cout << "List size: " << dll.getSize() << endl;

    // Pop from the front
    cout << "Pop from front: " << dll.popFront() << endl;
    cout << "List forward after popFront: ";
    dll.printForward();

    // Pop from the back
    cout << "Pop from back: " << dll.popBack() << endl;
    cout << "List forward after popBack: ";
    dll.printForward();

    // Print the final size of the list
    cout << "Final size of the list: " << dll.getSize() << endl;

    // Insert more values and test insertAt method
    dll.insertFront(5);
    dll.insertFront(2);
    dll.insertFront(10);
    dll.insertFront(11);
    dll.insertFront(9);
    dll.insertFront(0);
    dll.insertFront(3);
    dll.insertAt(9, 99);  // Insert 99 at index 8
    dll.printForward();
    // Print value at chosen index
    cout << dll.getValAt(9) << endl;

    // Print the list in backward order
    dll.printBackward();
    dll.printForward();

    return 0;
}
