using System;
using System.Collections.Generic;

namespace BigEgg.Algorithm.Sorts
{
    public class QuickSort
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
            Shuffle.Do(a);
            SortASC(a, 0, a.Length - 1);
        }

        private static void SortASC(IComparable[] a, int lo, int hi)
        {
            if (hi <= lo) return;
            int j = PartitionASC(a, lo, hi);
            SortASC(a, lo, j - 1);
            SortASC(a, j + 1, hi);
        }

        private static int PartitionASC(IComparable[] a, int lo, int hi)
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

        private static void SortDESC(IComparable[] a)
        {
            Shuffle.Do(a);
            SortDESC(a, 0, a.Length - 1);
        }

        private static void SortDESC(IComparable[] a, int lo, int hi)
        {
            if (hi <= lo) return;
            int j = PartitionDESC(a, lo, hi);
            SortDESC(a, lo, j - 1);
            SortDESC(a, j + 1, hi);
        }

        private static int PartitionDESC(IComparable[] a, int lo, int hi)
        {
            int i = lo, j = hi + 1;
            IComparable v = a[lo];
            while (true)
            {
                while (SortHelper.Greater(a[++i], v))
                    if (i == hi) break;
                while (SortHelper.Greater(v, a[--j]))
                    if (j == lo) break;
                if (i >= j) break;
                SortHelper.Exchange(a, i, j);
            }
            SortHelper.Exchange(a, lo, j);
            return j;
        }

        private static void SortASC<Key>(Key[] a, IComparer<Key> c) where Key : class
        {
            Shuffle.Do(a);
            SortASC(a, c, 0, a.Length - 1);
        }

        private static void SortASC<Key>(Key[] a, IComparer<Key> c, int lo, int hi) where Key : class
        {
            if (hi <= lo) return;
            int j = PartitionASC(a, c, lo, hi);
            SortASC(a, c, lo, j - 1);
            SortASC(a, c, j + 1, hi);
        }

        private static int PartitionASC<Key>(Key[] a, IComparer<Key> c, int lo, int hi) where Key : class
        {
            int i = lo, j = hi + 1;
            Key v = a[lo];
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

        private static void SortDESC<Key>(Key[] a, IComparer<Key> c) where Key : class
        {
            Shuffle.Do(a);
            SortDESC(a, c, 0, a.Length - 1);
        }

        private static void SortDESC<Key>(Key[] a, IComparer<Key> c, int lo, int hi) where Key : class
        {
            if (hi <= lo) return;
            int j = PartitionDESC(a, c, lo, hi);
            SortDESC(a, c, lo, j - 1);
            SortDESC(a, c, j + 1, hi);
        }

        private static int PartitionDESC<Key>(Key[] a, IComparer<Key> c, int lo, int hi) where Key : class
        {
            int i = lo, j = hi + 1;
            Key v = a[lo];
            while (true)
            {
                while (SortHelper.Greater(c, a[++i], v))
                    if (i == hi) break;
                while (SortHelper.Greater(c, v, a[--j]))
                    if (j == lo) break;
                if (i >= j) break;
                SortHelper.Exchange(a, i, j);
            }
            SortHelper.Exchange(a, lo, j);
            return j;
        }
    }
}
