using System;

namespace BigEgg.Algorithm.UnionFinds
{
    public abstract class UnionFind : IUnionFind
    {
        protected int[] id;
        protected int count;

        /// <summary>
        /// Initializes an empty union-find data structure with N isolated components 0 through N-1.
        /// </summary>
        /// <param name="N">The number of objects.</param>
        /// <exception cref="System.ArgumentException">N < 0</exception>
        public UnionFind(int N)
        {
            if (N <= 0) { throw new ArgumentException("N < 0"); }

            id = new int[N];
            for (int i = 0; i < N; i++)
            {
                id[i] = i;
            }
            count = N;
        }

        /// <summary>
        /// Merges the component containing site <c>p</c> with the component containing site <c>q</c>.
        /// </summary>
        /// <param name="p">The integer representing one site.</param>
        /// <param name="q">The integer representing the other site.</param>
        /// <exception cref="System.IndexOutOfRangeException">Throw unless both 0 <= p < N and 0 <= q < N.</exception>
        public abstract void Union(int p, int q);

        /// <summary>
        /// Are the two sites <c>p</c> and <c>q</c> in the same component?
        /// </summary>
        /// <param name="p">The integer representing one site.</param>
        /// <param name="q">The integer representing the other site.</param>
        /// <returns><c>True</c> if the two sites <c>p</c> and <c>q</c> are in the same component, and <c>false</c> otherwise.</returns>
        /// <exception cref="System.IndexOutOfRangeException">Throw unless both 0 <= p < N and 0 <= q < N.</exception>
        public abstract bool Connected(int p, int q);

        /// <summary>
        /// Returns the number of components.
        /// </summary>
        /// <returns>
        /// The number of components (between 1 and N).
        /// </returns>
        public int Count()
        {
            return count;
        }

        /// <summary>
        /// Returns the component identifier for the component containing site <c>p</c>.
        /// </summary>
        /// <param name="p">The integer representing one site.</param>
        /// <returns>The component identifier for the component containing site <c>p</c>.</returns>
        /// <exception cref="System.IndexOutOfRangeException">Throw unless both 0 <= p < N.</exception>
        public abstract int Find(int p);
    }
}
