using System.Collections.Generic;

namespace BigEgg.Algorithm
{
    public interface IStack<Item> : IEnumerable<Item>
    {
        /// <summary>
        /// Is this stack empty?
        /// </summary>
        /// <returns><c>True</c> if this stack is empty; <c>false</c> otherwise</returns>
        bool IsEmpty();

        /// <summary>
        /// Returns the number of items in the stack.
        /// </summary>
        /// <returns>The number of items in the stack.</returns>
        int Size();

        /// <summary>
        /// Adds the item to this stack.
        /// </summary>
        /// <param name="item">The item to add.</param>
        /// <exception cref="System.ArgumentNullException">Item cannot be null.</exception>
        void Push(Item item);

        /// <summary>
        /// Removes and returns the item most recently added to this stack.
        /// </summary>
        /// <returns>The item most recently added.</returns>
        /// <exception cref="System.ArgumentOutOfRangeException">Stack is empty.</exception>
        Item Pop();

        /// <summary>
        /// Returns (but does not remove) the item most recently added to this stack.
        /// </summary>
        /// <returns>The item most recently added to this stack.</returns>
        /// <exception cref="System.ArgumentOutOfRangeException">Stack is empty.</exception>
        Item Peek();
    }
}
