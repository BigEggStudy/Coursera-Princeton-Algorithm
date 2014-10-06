using System;

namespace BigEgg.Algorithm.UnionFinds
{
    public class WeightedQuickUnionUF : QuickUnionUF
    {
        private int[] sz;

        /// <summary>
        /// Initializes an empty union-find data structure with N isolated components 0 through N-1.
        /// </summary>
        /// <param name="N">The number of objects.</param>
        /// <exception cref="System.ArgumentException">N < 0</exception>
        public WeightedQuickUnionUF(int N)
            : base(N)
        {
            sz = new int[N];
            for (int i = 0; i < N; i++)
            {
                sz[i] = 1;
            }
        }

        /// <summary>
        /// Merges the component containing site <c>p</c> with the component containing site <c>q</c>.
        /// </summary>
        /// <param name="p">The integer representing one site.</param>
        /// <param name="q">The integer representing the other site.</param>
        /// <exception cref="System.IndexOutOfRangeException">Throw unless both 0 <= p < N and 0 <= q < N.</exception>
        public override void Union(int p, int q)
        {
            if (p < 0 || p >= id.Length) { throw new IndexOutOfRangeException(); }
            if (q < 0 || q >= id.Length) { throw new IndexOutOfRangeException(); }

            int pRoot = Find(p);
            int qRoot = Find(q);
            if (pRoot == qRoot) return;

            if (sz[pRoot] < sz[qRoot])
            {
                id[pRoot] = qRoot;
                sz[qRoot] += sz[pRoot];
            }
            else
            {
                id[qRoot] = pRoot;
                sz[pRoot] += sz[qRoot];
            }
            count--;
        }
    }
}
