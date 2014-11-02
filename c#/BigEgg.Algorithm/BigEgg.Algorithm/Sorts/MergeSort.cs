using System;
using System.Collections;

namespace BigEgg.Algorithm.Sorts
{
    public static class MergeSort
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

        private static void SortASC(IComparable[] a)
        {
            IComparable[] aux = new IComparable[a.Length];
            SortASC(a, aux, 0, a.Length - 1);
        }

        private static void SortASC(IComparable[] a, IComparable[] aux, int lo, int hi)
        {
            if (hi <= lo) return;
            int mid = lo + (hi - lo) / 2;
            SortASC(a, aux, lo, mid);
            SortASC(a, aux, mid + 1, hi);
            MergeASC(a, aux, lo, mid, hi);
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
            IComparable[] aux = new IComparable[a.Length];
            SortDESC(a, aux, 0, a.Length - 1);
        }

        private static void SortDESC(IComparable[] a, IComparable[] aux, int lo, int hi)
        {
            if (hi <= lo) return;
            int mid = lo + (hi - lo) / 2;
            SortDESC(a, aux, lo, mid);
            SortDESC(a, aux, mid + 1, hi);
            MergeDESC(a, aux, lo, mid, hi);
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

        private static void SortASC(Object[] a, IComparer c)
        {
            Object[] aux = new Object[a.Length];
            SortASC(a, aux, c, 0, a.Length - 1);
        }

        private static void SortASC(Object[] a, Object[] aux, IComparer c, int lo, int hi)
        {
            if (hi <= lo) return;
            int mid = lo + (hi - lo) / 2;
            SortASC(a, aux, c, lo, mid);
            SortASC(a, aux, c, mid + 1, hi);
            MergeASC(a, aux, c, lo, mid, hi);
        }

        private static void MergeASC(Object[] a, Object[] aux, IComparer c, int lo, int mid, int hi)
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

        private static void SortDESC(Object[] a, IComparer c)
        {
            Object[] aux = new Object[a.Length];
            SortDESC(a, aux, c, 0, a.Length - 1);
        }

        private static void SortDESC(Object[] a, Object[] aux, IComparer c, int lo, int hi)
        {
            if (hi <= lo) return;
            int mid = lo + (hi - lo) / 2;
            SortDESC(a, aux, c, lo, mid);
            SortDESC(a, aux, c, mid + 1, hi);
            MergeDESC(a, aux, c, lo, mid, hi);
        }

        private static void MergeDESC(Object[] a, Object[] aux, IComparer c, int lo, int mid, int hi)
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
