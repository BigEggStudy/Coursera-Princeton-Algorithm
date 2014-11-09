using System;
using System.Collections.Generic;

namespace BigEgg.Algorithm.Collections
{
    public class MinPriorityQueue<Key> : PriorityQueue<Key>
    {
        /// <summary>
        /// Initializes an empty priority queue.
        /// </summary>
        public MinPriorityQueue()
            : base()
        { }

        /// <summary>
        /// Initializes an empty priority queue with the given initial capacity.
        /// </summary>
        /// <param name="initCapacity">The initial capacity of the priority queue</param>
        public MinPriorityQueue(int initCapacity)
            : base(initCapacity)
        { }

        /// <summary>
        /// Initializes an empty priority queue using the given comparator.
        /// </summary>
        /// <param name="comparer">The order in which to compare the keys.</param>
        public MinPriorityQueue(IComparer<Key> comparer)
            : base(comparer)
        { }

        /// <summary>
        /// Initializes an empty priority queue with the given initial capacity,
        /// using the given comparator.
        /// </summary>
        /// <param name="initCapacity">The initial capacity of the priority queue.</param>
        /// <param name="comparer">The order in which to compare the keys.</param>
        public MinPriorityQueue(int initCapacity, IComparer<Key> comparer)
            : base(initCapacity, comparer)
        { }

        /// <summary>
        /// Initializes a priority queue from the array of keys.
        /// Takes time proportional to the number of keys, using sink-based heap construction.
        /// </summary>
        /// <param name="keys">The array of keys</param>
        public MinPriorityQueue(Key[] keys)
            : this(keys, null)
        { }

        /// <summary>
        /// Initializes a priority queue from the array of keys.
        /// Takes time proportional to the number of keys, using sink-based heap construction.
        /// </summary>
        /// <param name="keys">The array of keys</param>
        /// <param name="comparer">The order in which to compare the keys.</param>
        public MinPriorityQueue(Key[] keys, IComparer<Key> comparer)
            : base(keys, comparer)
        { }


        /// <summary>
        /// Returns the smallest key on the priority queue.
        /// </summary>
        /// <returns>The smallest key on the priority queue</returns>
        /// <exception cref="System.ArgumentOutOfRangeException">Queue is empty.</exception>
        public Key Min()
        {
            return First();
        }

        /// <summary>
        /// Removes and returns the smallest key on the priority queue.
        /// </summary>
        /// <returns>The smallest key on the priority queue.</returns>
        /// <exception cref="System.ArgumentOutOfRangeException">Queue is empty.</exception>
        public Key DelMin()
        {
            return Delete();
        }

        /// <summary>
        /// Returns an enumerator that iterates over the keys on the priority queue
        /// in ascending order.
        /// </summary>
        /// <returns>
        /// A <see cref="T:System.Collections.Generic.IEnumerator`1" /> that can be used to iterate over the keys.
        /// </returns>
        public override IEnumerator<Key> GetEnumerator()
        {
            return new HeapEnumerator(this);
        }


        protected override void Sink(int k)
        {
            while (2 * k <= N)
            {
                int j = 2 * k;
                if (j < N && Greater(j, j + 1)) j++;
                if (!Greater(k, j)) break;
                Exchange(k, j);
                k = j;
            }
        }

        protected override void Swim(int k)
        {
            while (k > 1 && Greater(k / 2, k))
            {
                Exchange(k / 2, k);
                k = k / 2;
            }
        }


        private bool Greater(int i, int j)
        {
            if (comparer == null)
                return ((IComparable)pq[i]).CompareTo(pq[j]) > 0;
            else
                return comparer.Compare(pq[i], pq[j]) > 0;
        }

        private class HeapEnumerator : IEnumerator<Key>
        {
            private MinPriorityQueue<Key> copy;

            public HeapEnumerator(MinPriorityQueue<Key> pq)
            {
                if (pq.comparer == null)
                    copy = new MinPriorityQueue<Key>(pq.Size());
                else
                    copy = new MinPriorityQueue<Key>(pq.Size(), pq.comparer);
                for (int i = 1; i <= pq.N; i++)
                    copy.Insert(pq.pq[i]);
            }


            public Key Current
            {
                get { return copy.DelMin(); }
            }

            public void Dispose()
            {
                copy = null;
            }

            object System.Collections.IEnumerator.Current
            {
                get { return Current; }
            }

            public bool MoveNext()
            {
                return !copy.IsEmpty();
            }

            public void Reset()
            {
                throw new NotImplementedException();
            }
        }

    }
}
