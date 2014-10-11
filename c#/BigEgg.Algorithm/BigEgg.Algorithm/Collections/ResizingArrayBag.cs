using System;
using System.Collections;
using System.Collections.Generic;

namespace BigEgg.Algorithm.Collections
{
    public class ResizingArrayBag<Item> : IBag<Item>
    {
        private Item[] a;
        private int N;

        public ResizingArrayBag()
        {
            N = 0;
            a = new Item[2];
        }

        /// <summary>
        /// Is this bag empty?
        /// </summary>
        /// <returns><c>True</c> if this bag is empty; <c>false</c> otherwise</returns>
        public bool IsEmpty()
        {
            return N == 0;
        }

        /// <summary>
        /// Returns the number of items in the bag.
        /// </summary>
        /// <returns>The number of items in the bag.</returns>
        public int Size()
        {
            return N;
        }

        private void Resize(int capacity)
        {
            Item[] temp = new Item[capacity];
            for (int i = 0; i < N; i++)
            {
                temp[i] = a[i];
            }
            a = temp;
        }

        /// <summary>
        /// Adds the item to this bag.
        /// </summary>
        /// <param name="item">The item to add.</param>
        /// <exception cref="System.ArgumentNullException">Item cannot be null.</exception>
        public void Add(Item item)
        {
            if (item == null) { throw new ArgumentNullException(); }
            if (N == a.Length) Resize(2 * a.Length);
            a[N++] = item;
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
            private int i = -1;
            private int N;
            private Item[] a;

            public Enumerator(ResizingArrayBag<Item> bag)
            {
                a = bag.a;
                N = bag.N;
            }

            public Item Current
            {
                get { return a[i]; }
            }

            public void Dispose()
            {
                a = null;
                N = 0;
            }

            object IEnumerator.Current
            {
                get { return a[i]; }
            }

            public bool MoveNext()
            {
                return ++i < N;
            }

            public void Reset()
            {
                i = -1;
            }
        }
    }
}
