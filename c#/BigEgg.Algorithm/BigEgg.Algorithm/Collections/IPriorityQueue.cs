using System.Collections.Generic;

namespace BigEgg.Algorithm.Collections
{
    public interface IPriorityQueue<Key> : IEnumerable<Key>
    {
        /// <summary>
        /// Is the priority queue empty?
        /// </summary>
        /// <returns><c>True</c> if the priority queue is empty; <c>false</c> otherwise.</returns>
        bool IsEmpty();

        /// <summary>
        /// Returns the number of keys on the priority queue.
        /// </summary>
        /// <returns>The number of keys on the priority queue.</returns>
        int Size();

        /// <summary>
        /// Returns the first key on the priority queue.
        /// </summary>
        /// <returns>The first key on the priority queue.</returns>
        /// <exception cref="System.ArgumentOutOfRangeException">Queue is empty.</exception>
        Key First();

        /// <summary>
        /// Adds a new key to the priority queue.
        /// </summary>
        /// <param name="x">The new key to add to the priority queue.</param>
        /// <exception cref="System.ArgumentNullException">Item cannot be null.</exception>
        void Insert(Key x);

        /// <summary>
        /// Removes and returns a first key on the priority queue.
        /// </summary>
        /// <returns>The first key on the priority queue.</returns>
        /// <exception cref="System.ArgumentOutOfRangeException">Queue is empty.</exception>
        Key Delete();
    }
}
