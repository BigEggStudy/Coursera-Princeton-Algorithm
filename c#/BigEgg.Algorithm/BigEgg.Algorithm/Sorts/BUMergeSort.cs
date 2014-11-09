using System;
using System.Collections.Generic;

namespace BigEgg.Algorithm.Sorts
{
    public static class BUMergeSort
    {
        /// <summary>
        /// Rearranges the array in ascending order, using the natural order.
        /// </summary>
        /// <param name="a">The array to be sorted</param>
        public static void Sort(IComparable[] a)
        {
            Sort(a, SortOrder.ASC);
        }

        /// <summary>
        /// Rearranges the array in ascending order, using a comparer.
        /// </summary>
        /// <param name="a">The array to be sorted</param>
        /// <param name="c">the comparer that specifying the order</param>
        public static void Sort<Key>(Key[] a, IComparer<Key> c) where Key : class
        {
            Sort(a, c, SortOrder.ASC);
        }

        /// <summary>
        /// Rearranges the array in specific order, using the natural order.
        /// </summary>
        /// <param name="a">The array to be sorted</param>
        /// <param name="order">The sort order.</param>
        public static void Sort(IComparable[] a, SortOrder order)
        {
            if (a == null) { throw new ArgumentNullException("a"); }

            if (order == SortOrder.ASC)
                SortASC(a);
            else
                SortDESC(a);
        }

        /// <summary>
        /// Rearranges the array in specific order, using a comparer.
        /// </summary>
        /// <param name="a">The array to be sorted</param>
        /// <param name="c">the comparer that specifying the order</param>
        /// <param name="order">The sort order.</param>
        public static void Sort<Key>(Key[] a, IComparer<Key> c, SortOrder order) where Key : class
        {
            if (a == null) { throw new ArgumentNullException("a"); }
            if (c == null) { throw new ArgumentNullException("c"); }

            if (order == SortOrder.ASC)
                SortASC(a, c);
            else
                SortDESC(a, c);
        }

        private static void SortASC(IComparable[] a)
        {
            int N = a.Length;
            IComparable[] aux = new IComparable[N];
            for (int sz = 1; sz < N; sz += sz)
                for (int lo = 0; lo < N - sz; lo += sz + sz)
                    MergeASC(a, aux, lo, lo + sz - 1, Math.Min(lo + sz + sz - 1, N - 1));
        }

        private static void MergeASC(IComparable[] a, IComparable[] aux, int lo, int mid, int hi)
        {
            if (!SortHelper.Less(a[mid + 1], a[mid])) return;

            for (int k = lo; k <= hi; k++)
                aux[k] = a[k];

            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++)
            {
                if (i > mid) a[k] = aux[j++];
                else if (j > hi) a[k] = aux[i++];
                else if (SortHelper.Less(aux[j], aux[i])) a[k] = aux[j++];
                else a[k] = aux[i++];
            }
        }

        private static void SortDESC(IComparable[] a)
        {
            int N = a.Length;
            IComparable[] aux = new IComparable[N];
            for (int sz = 1; sz < N; sz += sz)
                for (int lo = 0; lo < N - sz; lo += sz + sz)
                    MergeDESC(a, aux, lo, lo + sz - 1, Math.Min(lo + sz + sz - 1, N - 1));
        }

        private static void MergeDESC(IComparable[] a, IComparable[] aux, int lo, int mid, int hi)
        {
            if (!SortHelper.Greater(a[mid + 1], a[mid])) return;

            for (int k = lo; k <= hi; k++)
                aux[k] = a[k];

            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++)
            {
                if (i > mid) a[k] = aux[j++];
                else if (j > hi) a[k] = aux[i++];
                else if (SortHelper.Greater(aux[j], aux[i])) a[k] = aux[j++];
                else a[k] = aux[i++];
            }
        }


        private static void SortASC<Key>(Key[] a, IComparer<Key> c) where Key : class
        {
            int N = a.Length;
            Key[] aux = new Key[N];
            for (int sz = 1; sz < N; sz += sz)
                for (int lo = 0; lo < N - sz; lo += sz + sz)
                    MergeASC(a, aux, c, lo, lo + sz - 1, Math.Min(lo + sz + sz - 1, N - 1));
        }

        private static void MergeASC<Key>(Key[] a, Key[] aux, IComparer<Key> c, int lo, int mid, int hi) where Key : class
        {
            if (!SortHelper.Less(c, a[mid + 1], a[mid])) return;

            for (int k = lo; k <= hi; k++)
                aux[k] = a[k];

            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++)
            {
                if (i > mid) a[k] = aux[j++];
                else if (j > hi) a[k] = aux[i++];
                else if (SortHelper.Less(c, aux[j], aux[i])) a[k] = aux[j++];
                else a[k] = aux[i++];
            }
        }

        private static void SortDESC<Key>(Key[] a, IComparer<Key> c) where Key : class
        {
            int N = a.Length;
            Key[] aux = new Key[N];
            for (int sz = 1; sz < N; sz += sz)
                for (int lo = 0; lo < N - sz; lo += sz + sz)
                    MergeDESC(a, aux, c, lo, lo + sz - 1, Math.Min(lo + sz + sz - 1, N - 1));
        }

        private static void MergeDESC<Key>(Key[] a, Key[] aux, IComparer<Key> c, int lo, int mid, int hi) where Key : class
        {
            if (!SortHelper.Greater(c, a[mid + 1], a[mid])) return;

            for (int k = lo; k <= hi; k++)
                aux[k] = a[k];

            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++)
            {
                if (i > mid) a[k] = aux[j++];
                else if (j > hi) a[k] = aux[i++];
                else if (SortHelper.Greater(c, aux[j], aux[i])) a[k] = aux[j++];
                else a[k] = aux[i++];
            }
        }
    }
}
