using System;
using System.Collections;
using System.Collections.Generic;

namespace BigEgg.Algorithm.Collections
{
    public class LinkedStack<T> : IStack<T>
    {
        private int N;
        private Node first;

        private class Node
        {
            public T Item { get; set; }
            public Node Next { get; set; }
        }

        /// <summary>
        /// Initializes a new instance of the <see cref="LinkedStack{T}"/> class.
        /// </summary>
        public LinkedStack()
        {
            N = 0;
            first = null;
        }

        /// <summary>
        /// Is this stack empty?
        /// </summary>
        /// <returns><c>True</c> if this stack is empty; <c>false</c> otherwise</returns>
        public bool IsEmpty()
        {
            return first == null;
        }

        /// <summary>
        /// Returns the number of items in the stack.
        /// </summary>
        /// <returns>The number of items in the stack.</returns>
        public int Size()
        {
            return N;
        }

        /// <summary>
        /// Adds the item to this stack.
        /// </summary>
        /// <param name="item">The item to add.</param>
        /// <exception cref="System.ArgumentNullException">Item cannot be null.</exception>
        public void Push(T item)
        {
            if (item == null) { throw new ArgumentNullException("item"); }
            var oldFirst = first;
            first = new Node();
            first.Item = item;
            first.Next = oldFirst;
            N++;
        }

        /// <summary>
        /// Removes and returns the item most recently added to this stack.
        /// </summary>
        /// <returns>The item most recently added.</returns>
        /// <exception cref="System.ArgumentOutOfRangeException">Stack is empty.</exception>
        public T Pop()
        {
            if (IsEmpty()) { throw new ArgumentOutOfRangeException(); }
            var result = first.Item;
            first = first.Next;
            N--;
            return result;
        }

        /// <summary>
        /// Returns (but does not remove) the item most recently added to this stack.
        /// </summary>
        /// <returns>The item most recently added to this stack.</returns>
        /// <exception cref="System.ArgumentOutOfRangeException">Stack is empty.</exception>
        public T Peek()
        {
            if (IsEmpty()) { throw new ArgumentOutOfRangeException(); }
            return first.Item;
        }

        /// <summary>
        /// Returns a string representation of this stack.
        /// </summary>
        /// <returns>The sequence of items in the stack in LIFO order, separated by spaces.</returns>
        public override string ToString()
        {
            return String.Join(" ", this);
        }

        /// <summary>
        /// Returns an enumerator that iterates through the items in LIFO order.
        /// </summary>
        /// <returns>
        /// A <see cref="T:System.Collections.Generic.IEnumerator`1" /> that can be used to iterate through the items in LIFO order.
        /// </returns>
        public IEnumerator<T> GetEnumerator()
        {
            return new Enumerator(this);
        }

        /// <summary>
        /// Returns an enumerator that iterates through the items in LIFO order.
        /// </summary>
        /// <returns>
        /// An <see cref="T:System.Collections.IEnumerator" /> object that can be used to iterate through the items in LIFO order.
        /// </returns>
        IEnumerator IEnumerable.GetEnumerator()
        {
            return new Enumerator(this);
        }

        private struct Enumerator : IEnumerator<T>, IDisposable, IEnumerator
        {
            private Node current;
            private Node first;

            public Enumerator(LinkedStack<T> stack)
            {
                current = null;
                first = stack.first;
            }


            public T Current
            {
                get { return current.Item; }
            }

            public void Dispose()
            {
                first = current = null;
            }

            object System.Collections.IEnumerator.Current
            {
                get { return current.Item; }
            }

            public bool MoveNext()
            {
                if (first == null) return false;
                else
                    if (current == null) current = first;
                    else current = current.Next;
                return current != null;
            }

            public void Reset()
            {
                current = null;
            }
        }
    }
}
