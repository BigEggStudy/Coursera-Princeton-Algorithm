using BigEgg.Algorithm.Sorts;
using System;
using System.Collections;

namespace BigEgg.Algorithm
{
    public static class QuickSelect
    {
        /// <summary>
        /// Rearranges the array so that a[k] contains the kth smallest key;
        /// a[0] through a[k-1] are less than (or equal to) a[k]; and
        /// a[k+1] through a[N-1] are greater than (or equal to) a[k].
        /// </summary>
        /// <param name="a">The array</param>
        /// <param name="k">Find the kth smallest</param>
        /// <returns>The kth smallest key</returns>
        public static IComparable Select(IComparable[] a, int k)
        {
            if (a == null) { throw new ArgumentNullException(); }
            if (k < 0 || k >= a.Length) { throw new ArgumentException(); }

            int lo = 0, hi = a.Length - 1;
            while (true)
            {
                int j = Partition(a, lo, hi);
                if (j < k) lo = j + 1;
                else if (j > k) hi = j - 1;
                else return a[j];
            }
        }

        /// <summary>
        /// Rearranges the array so that a[k] contains the kth smallest key;
        /// a[0] through a[k-1] are less than (or equal to) a[k]; and
        /// a[k+1] through a[N-1] are greater than (or equal to) a[k].
        /// </summary>
        /// <param name="a">The array</param>
        /// <param name="c">The comparer</param>
        /// <param name="k">Find the kth smallest</param>
        /// <returns>The kth smallest key</returns>
        public static Object Select(Object[] a, IComparer c, int k)
        {
            if (a == null) { throw new ArgumentNullException(); }
            if (c == null) { throw new ArgumentNullException(); }
            if (k < 0 || k >= a.Length) { throw new ArgumentException(); }

            int lo = 0, hi = a.Length - 1;
            while (true)
            {
                int j = Partition(a, c, lo, hi);
                if (j < k) lo = j + 1;
                else if (j > k) hi = j - 1;
                else return a[j];
            }
        }

        private static int Partition(IComparable[] a, int lo, int hi)
        {
            int i = lo, j = hi + 1;
            IComparable v = a[lo];
            while (true)
            {
                while (SortHelper.Less(a[++i], v))
                    if (i == hi) break;
                while (SortHelper.Less(v, a[--j]))
                    if (j == lo) break;
                if (i >= j) break;
                SortHelper.Exchange(a, i, j);
            }
            SortHelper.Exchange(a, lo, j);
            return j;
        }

        private static int Partition(Object[] a, IComparer c, int lo, int hi)
        {
            int i = lo, j = hi + 1;
            Object v = a[lo];
            while (true)
            {
                while (SortHelper.Less(c, a[++i], v))
                    if (i == hi) break;
                while (SortHelper.Less(c, v, a[--j]))
                    if (j == lo) break;
                if (i >= j) break;
                SortHelper.Exchange(a, i, j);
            }
            SortHelper.Exchange(a, lo, j);
            return j;
        }
    }
}
