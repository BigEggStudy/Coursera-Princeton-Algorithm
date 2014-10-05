using System;

namespace BigEgg.Algorithm.UnionFinds
{
    public abstract class UnionFind : IUnionFind
    {
        protected int[] id;


        public UnionFind(int N)
        {
            if (N <= 0) { throw new ArgumentException(); }

            id = new int[N];
            for (int i = 0; i < N; i++)
            {
                id[i] = i;
            }
        }
    }
}
