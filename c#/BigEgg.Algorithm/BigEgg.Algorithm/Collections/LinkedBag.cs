using System;
using System.Collections;
using System.Collections.Generic;

namespace BigEgg.Algorithm.Collections
{
    public class LinkedBag<Item> : IBag<Item>
    {
        private Node first;
        private int N;

        private class Node
        {
            public Item Item { get; set; }
            public Node Next { get; set; }
        }

        /// <summary>
        /// Initializes a new instance of the <see cref="LinkedBag{Item}"/> class.
        /// </summary>
        public LinkedBag()
        {
            first = null;
            N = 0;
        }

        /// <summary>
        /// Is this bag empty?
        /// </summary>
        /// <returns><c>True</c> if this bag is empty; <c>false</c> otherwise</returns>
        public bool IsEmpty()
        {
            return first == null;
        }

        /// <summary>
        /// Returns the number of items in the bag.
        /// </summary>
        /// <returns>The number of items in the bag.</returns>
        public int Size()
        {
            return N;
        }

        /// <summary>
        /// Adds the item to this bag.
        /// </summary>
        /// <param name="item">The item to add.</param>
        /// <exception cref="System.ArgumentNullException">Item cannot be null.</exception>
        public void Add(Item item)
        {
            if (item == null) { throw new ArgumentNullException(); }
            Node oldFirst = first;
            first = new Node()
            {
                Item = item,
                Next = oldFirst
            };
        }

        /// <summary>
        /// Returns a string representation of this bag.
        /// </summary>
        /// <returns>The sequence of items in the bag, separated by spaces.</returns>
        public override string ToString()
        {
            return String.Join(" ", this);
        }

        public IEnumerator<Item> GetEnumerator()
        {
            throw new NotImplementedException();
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            throw new NotImplementedException();
        }

        private class Enumerator : IEnumerator<Item>, IDisposable, IEnumerator
        {
            private Node first;
            private Node current;

            public Enumerator(LinkedBag<Item> bag)
            {
                first = bag.first;
                current = null;
            }

            public Item Current
            {
                get { return current.Item; }
            }

            public void Dispose()
            {
                first = null;
                current = null;
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
