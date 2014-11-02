using System;
using System.Collections;

namespace BigEgg.Algorithm.Sorts
{
    public class BUMergeSort
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
        public static void Sort(Object[] a, IComparer c)
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
        public static void Sort(Object[] a, IComparer c, SortOrder order)
        {
            if (a == null) { throw new ArgumentNullException("a"); }
            if (c == null) { throw new ArgumentNullException("c"); }

            if (order == SortOrder.ASC)
                SortASC(a, c);
            else
                SortDESC(a, c);
        }

        protected static void SortASC(IComparable[] a)
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
                if (i > mid) a[k] = a[j++];
                else if (j > hi) a[k] = a[i++];
                else if (SortHelper.Less(a[j], a[i])) a[k] = a[j++];
                else a[k] = a[i++];
            }
        }

        protected static void SortDESC(IComparable[] a)
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
                if (i > mid) a[k] = a[j++];
                else if (j > hi) a[k] = a[i++];
                else if (SortHelper.Greater(a[j], a[i])) a[k] = a[j++];
                else a[k] = a[i++];
            }
        }


        protected static void SortASC(Object[] a, IComparer c)
        {
            int N = a.Length;
            Object[] aux = new Object[N];
            for (int sz = 1; sz < N; sz += sz)
                for (int lo = 0; lo < N - sz; lo += sz + sz)
                    MergeASC(a, aux, c, lo, lo + sz - 1, Math.Min(lo + sz + sz - 1, N - 1));
        }

        private static void MergeASC(Object[] a, Object[] aux, IComparer c, int lo, int mid, int hi)
        {
            if (!SortHelper.Less(c, a[mid + 1], a[mid])) return;

            for (int k = lo; k <= hi; k++)
                aux[k] = a[k];

            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++)
            {
                if (i > mid) a[k] = a[j++];
                else if (j > hi) a[k] = a[i++];
                else if (SortHelper.Less(c, a[j], a[i])) a[k] = a[j++];
                else a[k] = a[i++];
            }
        }

        protected static void SortDESC(Object[] a, IComparer c)
        {
            int N = a.Length;
            Object[] aux = new Object[N];
            for (int sz = 1; sz < N; sz += sz)
                for (int lo = 0; lo < N - sz; lo += sz + sz)
                    MergeDESC(a, aux, c, lo, lo + sz - 1, Math.Min(lo + sz + sz - 1, N - 1));
        }

        private static void MergeDESC(Object[] a, Object[] aux, IComparer c, int lo, int mid, int hi)
        {
            if (!SortHelper.Greater(c, a[mid + 1], a[mid])) return;

            for (int k = lo; k <= hi; k++)
                aux[k] = a[k];

            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++)
            {
                if (i > mid) a[k] = a[j++];
                else if (j > hi) a[k] = a[i++];
                else if (SortHelper.Greater(c, a[j], a[i])) a[k] = a[j++];
                else a[k] = a[i++];
            }
        }
    }
}
