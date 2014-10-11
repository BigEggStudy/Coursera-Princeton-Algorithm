using System;

namespace BigEgg.Algorithm.UnionFinds
{
    public class QuickUnionUF : UnionFind
    {
        /// <summary>
        /// Initializes an empty union-find data structure with N isolated components 0 through N-1.
        /// </summary>
        /// <param name="N">The number of objects.</param>
        /// <exception cref="System.ArgumentException">N < 0</exception>
        public QuickUnionUF(int N)
            : base(N)
        { }

        /// <summary>
        /// Merges the component containing site <c>p</c> with the component containing site <c>q</c>.
        /// </summary>
        /// <param name="p">The integer representing one site.</param>
        /// <param name="q">The integer representing the other site.</param>
        /// <exception cref="System.ArgumentOutOfRangeException">Throw unless both 0 <= p < N and 0 <= q < N.</exception>
        public override void Union(int p, int q)
        {
            if (p < 0 || p >= id.Length) { throw new ArgumentOutOfRangeException(); }
            if (q < 0 || q >= id.Length) { throw new ArgumentOutOfRangeException(); }

            int pRoot = Find(p);
            int qRoot = Find(q);
            if (pRoot == qRoot) return;

            id[pRoot] = qRoot;
            count--;
        }

        /// <summary>
        /// Are the two sites <c>p</c> and <c>q</c> in the same component?
        /// </summary>
        /// <param name="p">The integer representing one site.</param>
        /// <param name="q">The integer representing the other site.</param>
        /// <returns><c>True</c> if the two sites <c>p</c> and <c>q</c> are in the same component, and <c>false</c> otherwise.</returns>
        /// <exception cref="System.ArgumentOutOfRangeException">Throw unless both 0 <= p < N and 0 <= q < N.</exception>
        public override bool Connected(int p, int q)
        {
            if (p < 0 || p >= id.Length) { throw new ArgumentOutOfRangeException(); }
            if (q < 0 || q >= id.Length) { throw new ArgumentOutOfRangeException(); }

            return Find(p) == Find(q);
        }

        /// <summary>
        /// Returns the component identifier for the component containing site <c>p</c>.
        /// </summary>
        /// <param name="p">The integer representing one site.</param>
        /// <returns>The component identifier for the component containing site <c>p</c>.</returns>
        /// <exception cref="System.ArgumentOutOfRangeException">Throw unless both 0 <= p < N.</exception>
        public override int Find(int p)
        {
            if (p < 0 || p >= id.Length) { throw new ArgumentOutOfRangeException(); }

            while (p != id[p])
            {
                id[p] = id[id[p]];
                p = id[p];
            }
            return p;
        }
    }
}
