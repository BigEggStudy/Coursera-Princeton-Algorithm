using System.Collections.Generic;

namespace BigEgg.Algorithm.Collections
{
    public interface IQueue<Item> : IEnumerable<Item>
    {
        /// <summary>
        /// Is this queue empty?
        /// </summary>
        /// <returns><c>True</c> if this queue is empty; <c>false</c> otherwise</returns>
        bool IsEmpty();

        /// <summary>
        /// Returns the number of items in this queue.
        /// </summary>
        /// <returns>The number of items in this queue.</returns>
        int Size();

        /// <summary>
        /// Adds the item to this queue.
        /// </summary>
        /// <param name="item">The item to add.</param>
        /// <exception cref="System.ArgumentNullException">Item cannot be null.</exception>
        void Enqueue(Item item);

        /// <summary>
        /// Removes and returns the item on this queue that was least recently added.
        /// </summary>
        /// <returns>The item on this queue that was least recently added.</returns>
        /// <exception cref="System.ArgumentOutOfRangeException">Queue is empty.</exception>
        Item Dequeue();

        /// <summary>
        /// Returns (but does not remove) the item least recently added to this queue.
        /// </summary>
        /// <returns>The item least recently added to this queue.</returns>
        /// <exception cref="System.ArgumentOutOfRangeException">Queue is empty.</exception>
        Item Peek();
    }
}
