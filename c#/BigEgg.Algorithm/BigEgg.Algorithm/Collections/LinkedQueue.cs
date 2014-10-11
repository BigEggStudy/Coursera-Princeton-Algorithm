using System;
using System.Collections;
using System.Collections.Generic;

namespace BigEgg.Algorithm.Collections
{
    public class LinkedQueue<T> : IQueue<T>
    {
        private int N;
        private Node first;
        private Node last;

        private class Node
        {
            public T Item { get; set; }
            public Node Next { get; set; }
        }

        /// <summary>
        /// Initializes a new instance of the <see cref="LinkedQueue{T}"/> class.
        /// </summary>
        public LinkedQueue()
        {
            N = 0;
            first = null;
            last = null;
        }

        /// <summary>
        /// Is this queue empty?
        /// </summary>
        /// <returns><c>True</c> if this queue is empty; <c>false</c> otherwise</returns>
        public bool IsEmpty()
        {
            return first == null;
        }

        /// <summary>
        /// Returns the number of items in this queue.
        /// </summary>
        /// <returns>The number of items in this queue.</returns>
        public int Size()
        {
            return N;
        }

        /// <summary>
        /// Adds the item to this queue.
        /// </summary>
        /// <param name="item">The item to add.</param>
        /// <exception cref="System.ArgumentNullException">Item cannot be null.</exception>
        public void Enqueue(T item)
        {
            if (item == null) { throw new ArgumentNullException(); }
            Node oldLast = last;
            last = new Node();
            last.Item = item;
            last.Next = null;
            if (IsEmpty()) first = last;
            else oldLast.Next = last;
            N++;
        }

        /// <summary>
        /// Removes and returns the item on this queue that was least recently added.
        /// </summary>
        /// <returns>The item on this queue that was least recently added.</returns>
        /// <exception cref="System.ArgumentOutOfRangeException">Queue is empty.</exception>
        public T Dequeue()
        {
            if (IsEmpty()) { throw new ArgumentOutOfRangeException(); }
            T result = first.Item;
            first = first.Next;
            if (IsEmpty()) last = null;
            N--;
            return result;
        }

        /// <summary>
        /// Returns (but does not remove) the item least recently added to this queue.
        /// </summary>
        /// <returns>The item least recently added to this queue.</returns>
        /// <exception cref="System.ArgumentOutOfRangeException">Queue is empty.</exception>
        public T Peek()
        {
            if (IsEmpty()) { throw new ArgumentOutOfRangeException(); }
            return first.Item;
        }

        /// <summary>
        /// Returns a string representation of this queue.
        /// </summary>
        /// <returns>The sequence of items in the queue in FIFO order, separated by spaces.</returns>
        public override string ToString()
        {
            return String.Join(" ", this);
        }

        /// <summary>
        /// Returns an enumerator that iterates through the items in this queue in FIFO order.
        /// </summary>
        /// <returns>
        /// A <see cref="T:System.Collections.Generic.IEnumerator`1" /> that can be used to iterate through the items in this queue in FIFO order.
        /// </returns>
        public IEnumerator<T> GetEnumerator()
        {
            return new Enumerator(this);
        }

        /// <summary>
        /// Returns an enumerator that iterates through the items in this queue in FIFO order.
        /// </summary>
        /// <returns>
        /// An <see cref="T:System.Collections.IEnumerator" /> object that can be used to iterate through the items in this queue in FIFO order.
        /// </returns>
        IEnumerator IEnumerable.GetEnumerator()
        {
            return new Enumerator(this);
        }

        private class Enumerator : IEnumerator<T>, IDisposable, IEnumerator
        {
            private Node current;
            private Node first;

            public Enumerator(LinkedQueue<T> queue)
            {
                current = null;
                first = queue.first;
            }

            public T Current
            {
                get { return current.Item; }
            }

            public void Dispose()
            {
                current = null;
                first = null;
            }

            object IEnumerator.Current
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
