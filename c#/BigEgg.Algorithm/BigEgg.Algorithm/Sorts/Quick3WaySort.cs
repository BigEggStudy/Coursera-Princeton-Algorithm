using System;
using System.Collections.Generic;

namespace BigEgg.Algorithm.Sorts
{
    public static class Quick3WaySort
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
            int lt = lo, gt = hi;
            int i = lo;
            IComparable v = a[lo];
            while (i <= gt)
            {
                int cmp = a[i].CompareTo(v);
                if (cmp < 0) SortHelper.Exchange(a, lt++, i++);
                else if (cmp > 0) SortHelper.Exchange(a, i, gt--);
                else i++;
            }
            SortASC(a, lo, lt - 1);
            SortASC(a, gt + 1, hi);
        }

        private static void SortDESC(IComparable[] a)
        {
            Shuffle.Do(a);
            SortDESC(a, 0, a.Length - 1);
        }

        private static void SortDESC(IComparable[] a, int lo, int hi)
        {
            if (hi <= lo) return;
            int lt = lo, gt = hi;
            int i = lo;
            IComparable v = a[lo];
            while (i <= gt)
            {
                int cmp = a[i].CompareTo(v);
                if (cmp > 0) SortHelper.Exchange(a, lt++, i++);
                else if (cmp < 0) SortHelper.Exchange(a, i, gt--);
                else i++;
            }
            SortDESC(a, lo, lt - 1);
            SortDESC(a, gt + 1, hi);
        }



        private static void SortASC<Key>(Key[] a, IComparer<Key> c) where Key : class
        {
            Shuffle.Do(a);
            SortASC(a, c, 0, a.Length - 1);
        }

        private static void SortASC<Key>(Key[] a, IComparer<Key> c, int lo, int hi) where Key : class
        {
            if (hi <= lo) return;
            int lt = lo, gt = hi;
            int i = lo;
            Key v = a[lo];
            while (i <= gt)
            {
                int cmp = c.Compare(a[i], v);
                if (cmp < 0) SortHelper.Exchange(a, lt++, i++);
                else if (cmp > 0) SortHelper.Exchange(a, i, gt--);
                else i++;
            }
            SortASC(a, c, lo, lt - 1);
            SortASC(a, c, gt + 1, hi);
        }

        private static void SortDESC<Key>(Key[] a, IComparer<Key> c) where Key : class
        {
            Shuffle.Do(a);
            SortDESC(a, c, 0, a.Length - 1);
        }

        private static void SortDESC<Key>(Key[] a, IComparer<Key> c, int lo, int hi) where Key : class
        {
            if (hi <= lo) return;
            int lt = lo, gt = hi;
            int i = lo;
            Key v = a[lo];
            while (i <= gt)
            {
                int cmp = c.Compare(a[i], v);
                if (cmp > 0) SortHelper.Exchange(a, lt++, i++);
                else if (cmp < 0) SortHelper.Exchange(a, i, gt--);
                else i++;
            }
            SortDESC(a, c, lo, lt - 1);
            SortDESC(a, c, gt + 1, hi);
        }
    }
}
