namespace BigEgg.Algorithm.UnionFinds
{
    public class QuickUnionUF : UnionFind
    {
        public QuickUnionUF(int N)
            : base(N)
        { }

        public override void Union(int p, int q)
        {
            int i = Root(p);
            int j = Root(q);
            id[i] = j;
        }

        public override bool Connected(int p, int q)
        {
            return Root(p) == Root(q);
        }

        protected int Root(int i)
        {
            while (i != id[i]) i = id[i];
            return i;
        }
    }
}
