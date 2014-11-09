using System;
using System.Collections.Generic;

namespace BigEgg.Algorithm.Collections
{
    public class MaxPriorityQueue<Key> : PriorityQueue<Key>
    {
        /// <summary>
        /// Initializes an empty priority queue.
        /// </summary>
        public MaxPriorityQueue()
            : base()
        { }

        /// <summary>
        /// Initializes an empty priority queue with the given initial capacity.
        /// </summary>
        /// <param name="initCapacity">The initial capacity of the priority queue</param>
        public MaxPriorityQueue(int initCapacity)
            : base(initCapacity)
        { }

        /// <summary>
        /// Initializes an empty priority queue using the given comparator.
        /// </summary>
        /// <param name="comparer">The order in which to compare the keys.</param>
        public MaxPriorityQueue(IComparer<Key> comparer)
            : base(comparer)
        { }

        /// <summary>
        /// Initializes an empty priority queue with the given initial capacity,
        /// using the given comparator.
        /// </summary>
        /// <param name="initCapacity">The initial capacity of the priority queue.</param>
        /// <param name="comparer">The order in which to compare the keys.</param>
        public MaxPriorityQueue(int initCapacity, IComparer<Key> comparer)
            : base(initCapacity, comparer)
        { }

        /// <summary>
        /// Initializes a priority queue from the array of keys.
        /// Takes time proportional to the number of keys, using sink-based heap construction.
        /// </summary>
        /// <param name="keys">The array of keys</param>
        public MaxPriorityQueue(Key[] keys)
            : this(keys, null)
        { }

        /// <summary>
        /// Initializes a priority queue from the array of keys.
        /// Takes time proportional to the number of keys, using sink-based heap construction.
        /// </summary>
        /// <param name="keys">The array of keys</param>
        /// <param name="comparer">The order in which to compare the keys.</param>
        public MaxPriorityQueue(Key[] keys, IComparer<Key> comparer)
            : base(keys, comparer)
        { }


        /// <summary>
        /// Returns the largest key on the priority queue.
        /// </summary>
        /// <returns>The largest key on the priority queue</returns>
        /// <exception cref="System.ArgumentOutOfRangeException">Queue is empty.</exception>
        public Key Max()
        {
            return First();
        }

        /// <summary>
        /// Removes and returns the largest key on the priority queue.
        /// </summary>
        /// <returns>The largest key on the priority queue.</returns>
        /// <exception cref="System.ArgumentOutOfRangeException">Queue is empty.</exception>
        public Key DelMax()
        {
            return Delete();
        }

        /// <summary>
        /// Returns an enumerator that iterates over the keys on the priority queue
        /// in descending order.
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
                if (j < N && Less(j, j + 1)) j++;
                if (!Less(k, j)) break;
                Exch(k, j);
                k = j;
            }
        }

        protected override void Swim(int k)
        {
            while (k > 1 && Less(k / 2, k))
            {
                Exch(k / 2, k);
                k = k / 2;
            }
        }


        private bool Less(int i, int j)
        {
            if (comparer == null)
                return ((IComparable)pq[i]).CompareTo(pq[j]) < 0;
            else
                return comparer.Compare(pq[i], pq[j]) < 0;
        }

        private class HeapEnumerator : IEnumerator<Key>
        {
            MaxPriorityQueue<Key> copy;

            public HeapEnumerator(MaxPriorityQueue<Key> pq)
            {
                if (pq.comparer == null)
                    copy = new MaxPriorityQueue<Key>(pq.Size());
                else
                    copy = new MaxPriorityQueue<Key>(pq.Size(), pq.comparer);
                for (int i = 1; i <= pq.N; i++)
                {
                    copy.Insert(pq.pq[i]);
                }
            }


            public Key Current
            {
                get { return copy.DelMax(); }
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
