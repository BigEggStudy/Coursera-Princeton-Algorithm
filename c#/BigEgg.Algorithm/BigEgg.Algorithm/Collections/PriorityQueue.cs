using System;
using System.Collections.Generic;

namespace BigEgg.Algorithm.Collections
{
    public abstract class PriorityQueue<Key> : IPriorityQueue<Key>
    {
        private Key[] pq;
        private int N;
        private IComparer<Key> comparer;

        /// <summary>
        /// Initializes an empty priority queue.
        /// </summary>
        public PriorityQueue()
            : this(1)
        { }

        /// <summary>
        /// Initializes an empty priority queue with the given initial capacity.
        /// </summary>
        /// <param name="initCapacity">The initial capacity of the priority queue</param>
        public PriorityQueue(int initCapacity)
            : this(initCapacity, null)
        { }

        /// <summary>
        /// Initializes an empty priority queue using the given comparator.
        /// </summary>
        /// <param name="comparer">The order in which to compare the keys.</param>
        public PriorityQueue(IComparer<Key> comparer)
            : this(1, comparer)
        { }

        /// <summary>
        /// Initializes an empty priority queue with the given initial capacity,
        /// using the given comparator.
        /// </summary>
        /// <param name="initCapacity">The initial capacity of the priority queue.</param>
        /// <param name="comparer">The order in which to compare the keys.</param>
        public PriorityQueue(int initCapacity, IComparer<Key> comparer)
        {
            this.pq = new Key[initCapacity + 1];
            this.N = 0;
            this.comparer = comparer;
        }

        /// <summary>
        /// Initializes a priority queue from the array of keys.
        /// Takes time proportional to the number of keys, using sink-based heap construction.
        /// </summary>
        /// <param name="keys">The array of keys</param>
        public PriorityQueue(Key[] keys)
            : this(keys, null)
        { }

        /// <summary>
        /// Initializes a priority queue from the array of keys.
        /// Takes time proportional to the number of keys, using sink-based heap construction.
        /// </summary>
        /// <param name="keys">The array of keys</param>
        /// <param name="comparer">The order in which to compare the keys.</param>
        public PriorityQueue(Key[] keys, IComparer<Key> comparer)
        {
            this.N = keys.Length;
            this.pq = new Key[N + 1];
            this.comparer = comparer;
            for (int i = 0; i < N; i++)
                pq[i + 1] = keys[i];
            for (int k = N / 2; k >= 1; k--)
                Sink(k);
        }


        /// <summary>
        /// Is the priority queue empty?
        /// </summary>
        /// <returns><c>True</c> if the priority queue is empty; <c>false</c> otherwise</returns>
        public bool IsEmpty()
        {
            return N == 0;
        }

        /// <summary>
        /// Returns the number of keys on the priority queue.
        /// </summary>
        /// <returns>The number of keys on the priority queue</returns>
        public int Size()
        {
            return N;
        }

        /// <summary>
        /// Returns the first key on the priority queue.
        /// </summary>
        /// <returns>The first key on the priority queue.</returns>
        /// <exception cref="System.ArgumentOutOfRangeException">Queue is empty.</exception>
        public Key First()
        {
            if (IsEmpty()) { throw new ArgumentOutOfRangeException(); }
            return pq[1];
        }

        /// <summary>
        /// Adds a new key to the priority queue.
        /// </summary>
        /// <param name="x">The new key to add to the priority queue.</param>
        /// <exception cref="System.ArgumentNullException">Item cannot be null.</exception>
        public void Insert(Key x)
        {
            if (x == null) { throw new ArgumentNullException(); }
            if (N >= pq.Length - 1)
                Resize(pq.Length * 2);

            pq[++N] = x;
            Swim(N);
        }

        /// <summary>
        /// Removes and returns a first key on the priority queue.
        /// </summary>
        /// <returns>The first key on the priority queue.</returns>
        /// <exception cref="System.ArgumentOutOfRangeException">Queue is empty.</exception>
        public Key Delete()
        {
            if (IsEmpty()) { throw new ArgumentOutOfRangeException(); }

            var result = pq[1];
            Exch(1, N--);
            Sink(1);
            pq[N + 1] = default(Key);
            if (N > 0 && N == (pq.Length - 1) / 4)
                Resize(pq.Length / 2);

            return result;
        }


        /// <summary>
        /// Returns an enumerator that iterates over the keys on the priority queue
        /// </summary>
        /// <returns>
        /// A <see cref="T:System.Collections.Generic.IEnumerator`1" /> that can be used to iterate over the keys.
        /// </returns>
        public abstract IEnumerator<Key> GetEnumerator();

        /// <summary>
        /// Returns an enumerator that iterates over the keys on the priority queue
        /// </summary>
        /// <returns>
        /// A <see cref="T:System.Collections.Generic.IEnumerator`1" /> that can be used to iterate over the keys.
        /// </returns>
        System.Collections.IEnumerator System.Collections.IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }


        protected abstract void Sink(int k);

        protected abstract void Swim(int k);

        protected void Exch(int i, int j)
        {
            var temp = pq[i];
            pq[i] = pq[j];
            pq[j] = temp;
        }


        private void Resize(int capacity)
        {
            var temp = new Key[capacity];
            for (int i = 1; i <= N; i++)
                temp[i] = pq[i];

            pq = temp;
        }
    }
}
