using System;
using System.Collections;

namespace BigEgg.Algorithm.Sorts
{
    internal static class SortHelper
    {
        /// <summary>
        /// Is v < w ?
        /// </summary>
        /// <returns><c>True</c> if v < w, otherwise <c>false</c>.</returns>
        public static bool Less(IComparable v, IComparable w)
        {
            return v.CompareTo(w) < 0;
        }

        /// <summary>
        /// Is v < w ?
        /// </summary>
        /// <returns><c>True</c> if v < w, otherwise <c>false</c>.</returns>
        public static bool Less(IComparer c, Object v, Object w)
        {
            return c.Compare(v, w) < 0;
        }

        /// <summary>
        /// Is v > w ?
        /// </summary>
        /// <returns><c>True</c> if v > w, otherwise <c>false</c>.</returns>
        public static bool Greater(IComparable v, IComparable w)
        {
            return v.CompareTo(w) > 0;
        }

        /// <summary>
        /// Is v > w ?
        /// </summary>
        /// <returns><c>True</c> if v > w, otherwise <c>false</c>.</returns>
        public static bool Greater(IComparer c, Object v, Object w)
        {
            return c.Compare(v, w) > 0;
        }

        /// <summary>
        /// Exchange a[i] and a[j]  (for indirect sort)
        /// </summary>
        /// <param name="a">The array</param>
        /// <param name="i">The index1.</param>
        /// <param name="j">The index2.</param>
        public static void Exchange(Object[] a, int i, int j)
        {
            Object swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }

        /// <summary>
        /// Check if array is sorted in ascending order, using the natural order.
        /// </summary>
        /// <param name="a">The array</param>
        /// <returns><c>True</c> if array is sorted, otherwise <c>false</c>.</returns>
        public static bool IsSorted(IComparable[] a)
        {
            return IsSorted(a, SortOrder.ASC);
        }

        /// <summary>
        /// Check if array is sorted in specific order, using the natural order.
        /// </summary>
        /// <param name="a">The array</param>
        /// <param name="order">The sort order</param>
        /// <returns><c>True</c> if array is sorted, otherwise <c>false</c>.</returns>
        public static bool IsSorted(IComparable[] a, SortOrder order)
        {
            return IsSorted(a, 0, a.Length, order);
        }

        /// <summary>
        /// Is the array sorted in specific order from a[lo] to a[hi], using the natural order.
        /// </summary>
        /// <param name="a">The array</param>
        /// <param name="lo">The low index</param>
        /// <param name="hi">The high index</param>
        /// <param name="order">The sort order</param>
        /// <returns><c>True</c> if array is sorted, otherwise <c>false</c>.</returns>
        public static bool IsSorted(IComparable[] a, int lo, int hi, SortOrder order)
        {
            if (order == SortOrder.ASC)
            {
                for (int i = lo + 1; i < hi; i++)
                    if (Less(a[i], a[i - 1])) return false;
            }
            else
            {
                for (int i = lo + 1; i < hi; i++)
                    if (Greater(a[i], a[i - 1])) return false;
            }
            return true;
        }

        /// <summary>
        /// Check if array is sorted in ascending order, using a comparer
        /// </summary>
        /// <param name="a">The array</param>
        /// <param name="c">The comparer.</param>        
        /// <returns><c>True</c> if array is sorted, otherwise <c>false</c>.</returns>
        public static bool IsSorted(Object[] a, IComparer c)
        {
            return IsSorted(a, c, SortOrder.ASC);
        }

        /// <summary>
        /// Check if array is sorted in specific order, using the natural order.
        /// </summary>
        /// <param name="a">The array</param>
        /// <param name="c">The comparer.</param>
        /// <param name="order">The sort order</param>
        /// <returns><c>True</c> if array is sorted, otherwise <c>false</c>.</returns>
        public static bool IsSorted(Object[] a, IComparer c, SortOrder order)
        {
            return IsSorted(a, c, 0, a.Length, order);
        }

        /// <summary>
        /// Is the array sorted in specific order from a[lo] to a[hi], using the natural order.
        /// </summary>
        /// <param name="a">The array</param>
        /// <param name="c">The comparer.</param>
        /// <param name="lo">The low index</param>
        /// <param name="hi">The high index</param>
        /// <param name="order">The sort order</param>
        /// <returns><c>True</c> if array is sorted, otherwise <c>false</c>.</returns>
        public static bool IsSorted(Object[] a, IComparer c, int lo, int hi, SortOrder order)
        {
            if (order == SortOrder.ASC)
            {
                for (int i = lo + 1; i < hi; i++)
                    if (Less(c, a[i], a[i - 1])) return false;
            }
            else
            {
                for (int i = lo + 1; i < hi; i++)
                    if (Greater(c, a[i], a[i - 1])) return false;
            }
            return true;
        }
    }
}
