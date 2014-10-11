using System;
using System.Collections;
using System.Collections.Generic;

namespace BigEgg.Algorithm.Collections
{
    public class ResizingArrayQueue<T> : IQueue<T>
    {
        private int N;
        private int first;
        private int last;
        private T[] q;

        /// <summary>
        /// Initializes a new instance of the <see cref="ResizingArrayQueue{T}"/> class.
        /// </summary>
        public ResizingArrayQueue()
        {
            q = new T[2];
            N = 0;
            first = 0;
            last = 0;
        }

        /// <summary>
        /// Is this queue empty?
        /// </summary>
        /// <returns><c>True</c> if this queue is empty; <c>false</c> otherwise</returns>
        public bool IsEmpty()
        {
            return N == 0;
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
        /// Resize the underlying array holding the elements.
        /// </summary>
        /// <param name="capacity">The new capacity of the queue.</param>
        private void Resize(int capacity)
        {
            T[] temp = new T[capacity];
            for (int i = 0; i < N; i++)
            {
                temp[i] = q[(first + i) % q.Length];
            }
            q = temp;
            first = 0;
            last = N;
        }

        /// <summary>
        /// Adds the item to this queue.
        /// </summary>
        /// <param name="item">The item to add.</param>
        /// <exception cref="System.ArgumentNullException">Item cannot be null.</exception>
        public void Enqueue(T item)
        {
            if (item == null) { throw new ArgumentNullException(); }
            if (N == q.Length) Resize(q.Length * 2);
            q[last++] = item;
            if (last == q.Length) last = 0;
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
            T result = q[first];
            q[first] = default(T);
            N--;
            first++;
            if (first == q.Length) first = 0;
            if (N == q.Length / 4) Resize(q.Length / 2);
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
            return q[first];
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
            private int i = -1;
            private int N;
            private int first;
            private T[] q;

            public Enumerator(ResizingArrayQueue<T> queue)
            {
                first = queue.first;
                q = queue.q;
                N = queue.N;
            }

            public T Current
            {
                get { return q[(first + i) % q.Length]; }
            }

            public void Dispose()
            {
                i = N = first = 0;
                q = null;
            }

            object IEnumerator.Current
            {
                get { return q[(first + i) % q.Length]; }
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
