using System;
using System.Collections;
using System.Collections.Generic;

namespace BigEgg.Algorithm.Collections
{
    public class ResizingArrayStack<Item> : IStack<Item>
    {
        private Item[] a;
        private int N;

        /// <summary>
        /// Initializes a new instance of the <see cref="ResizingArrayStack{Item}"/> class.
        /// </summary>
        public ResizingArrayStack()
        {
            N = 0;
            a = new Item[2];
        }

        /// <summary>
        /// Is this stack empty?
        /// </summary>
        /// <returns><c>True</c> if this stack is empty; <c>false</c> otherwise</returns>
        public bool IsEmpty()
        {
            return N == 0;
        }

        /// <summary>
        /// Returns the number of items in the stack.
        /// </summary>
        /// <returns>The number of items in the stack.</returns>
        public int Size()
        {
            return N;
        }

        /// <summary>
        /// Resize the underlying array holding the elements.
        /// </summary>
        /// <param name="capacity">The new capacity of the stack.</param>
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
        /// Adds the item to this stack.
        /// </summary>
        /// <param name="item">The item to add.</param>
        /// <exception cref="System.ArgumentNullException">Item cannot be null.</exception>
        public void Push(Item item)
        {
            if (item == null) { throw new ArgumentNullException("item"); }
            if (N == a.Length) Resize(a.Length * 2);
            a[N++] = item;
        }

        /// <summary>
        /// Removes and returns the item most recently added to this stack.
        /// </summary>
        /// <returns>The item most recently added.</returns>
        /// <exception cref="System.ArgumentOutOfRangeException">Stack is empty.</exception>
        public Item Pop()
        {
            if (IsEmpty()) { throw new ArgumentOutOfRangeException(); }
            var result = a[N - 1];
            a[N - 1] = default(Item);
            N--;
            if (N == a.Length / 4) Resize(a.Length / 2);
            return result;
        }

        /// <summary>
        /// Returns (but does not remove) the item most recently added to this stack.
        /// </summary>
        /// <returns>The item most recently added to this stack.</returns>
        /// <exception cref="System.ArgumentOutOfRangeException">Stack is empty.</exception>
        public Item Peek()
        {
            if (IsEmpty()) { throw new ArgumentOutOfRangeException(); }
            return a[N - 1];
        }

        /// <summary>
        /// Returns a string representation of this stack.
        /// </summary>
        /// <returns>The sequence of items in the stack in LIFO order, separated by spaces.</returns>
        public override string ToString()
        {
            return String.Join(" ", this);
        }

        /// <summary>
        /// Returns an enumerator that iterates through the items in LIFO order.
        /// </summary>
        /// <returns>
        /// A <see cref="T:System.Collections.Generic.IEnumerator`1" /> that can be used to iterate through the items in LIFO order.
        /// </returns>
        public IEnumerator<Item> GetEnumerator()
        {
            return new Enumerator(this);
        }

        /// <summary>
        /// Returns an enumerator that iterates through a items in LIFO order.
        /// </summary>
        /// <returns>
        /// An <see cref="T:System.Collections.IEnumerator" /> object that can be used to iterate through the items in LIFO order.
        /// </returns>
        IEnumerator IEnumerable.GetEnumerator()
        {
            return new Enumerator(this);
        }

        private struct Enumerator : IEnumerator<Item>, IDisposable, IEnumerator
        {
            private Item[] a;
            private int i;
            private int N;

            public Enumerator(ResizingArrayStack<Item> stack)
            {
                a = stack.a;
                i = stack.N;
                N = stack.N;
            }


            public Item Current
            {
                get { return a[i - 1]; }
            }

            public void Dispose()
            {
                a = null;
                N = 0;
            }

            object System.Collections.IEnumerator.Current
            {
                get { return a[i - 1]; }
            }

            public bool MoveNext()
            {
                i--;
                return i > 0;
            }

            public void Reset()
            {
                i = N;
            }
        }
    }
}
