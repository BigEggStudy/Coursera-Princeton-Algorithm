
namespace BigEgg.Algorithm.UnionFinds
{
    public class WeightedQuickUnionUF : QuickUnionUF
    {
        private int[] sz;

        public WeightedQuickUnionUF(int N)
            : base(N)
        {
            sz = new int[N];
            for (int i = 0; i < N; i++)
            {
                sz[i] = 1;
            }
        }

        public override void Union(int p, int q)
        {
            int i = Root(p);
            int j = Root(q);
            if (i == j) return;

            if (sz[i] < sz[j])
            {
                id[i] = j;
                sz[j] += sz[i];
            }
            else
            {
                id[j] = i;
                sz[i] += sz[j];
            }
        }

    }
}
