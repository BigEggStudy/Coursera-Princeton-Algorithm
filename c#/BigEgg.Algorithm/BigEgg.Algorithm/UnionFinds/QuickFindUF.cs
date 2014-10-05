namespace BigEgg.Algorithm.UnionFinds
{
    public class QuickFindUF : UnionFind
    {
        public QuickFindUF(int N)
            : base(N)
        { }

        public void Union(int p, int q)
        {
            int pid = id[p];
            int qid = id[q];

            for (int i = 0; i < id.Length; i++)
                if (id[i] == pid) id[i] = qid;
        }

        public bool Connected(int p, int q)
        {
            return id[p] == id[q];
        }
    }
}
