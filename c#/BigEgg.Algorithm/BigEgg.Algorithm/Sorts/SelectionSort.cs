using System;
using System.Collections;

namespace BigEgg.Algorithm.Sorts
{
    public static class SelectionSort
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

        /// <summary>
        /// Rearranges the array in ascending order, using the natural order.
        /// </summary>
        /// <param name="a">The array to be sorted</param>
        private static void SortASC(IComparable[] a)
        {
            int N = a.Length;

            for (int i = 0; i < N; i++)
            {
                int min = i;
                for (int j = i + 1; j < N; j++)
                    if (SortHelper.Less(a[j], a[min]))
                        min = j;
                SortHelper.Exchange(a, min, i);
            }
        }

        /// <summary>
        /// Rearranges the array in ascending order, using a comparer.
        /// </summary>
        /// <param name="a">The array to be sorted</param>
        /// <param name="c">the comparer that specifying the order</param>
        private static void SortASC(Object[] a, IComparer c)
        {
            int N = a.Length;

            for (int i = 0; i < N; i++)
            {
                int min = i;
                for (int j = i + 1; j < N; j++)
                    if (SortHelper.Less(c, a[j], a[min]))
                        min = j;
                SortHelper.Exchange(a, i, min);
            }
        }

        /// <summary>
        /// Rearranges the array in descending order, using the natural order.
        /// </summary>
        /// <param name="a">The array to be sorted</param>
        private static void SortDESC(IComparable[] a)
        {
            int N = a.Length;

            for (int i = 0; i < N; i++)
            {
                int max = i;
                for (int j = i + 1; j < N; j++)
                    if (SortHelper.Greater(a[j], a[max]))
                        max = j;
                SortHelper.Exchange(a, max, i);
            }
        }

        /// <summary>
        /// Rearranges the array in descending order, using a comparer.
        /// </summary>
        /// <param name="a">The array to be sorted</param>
        /// <param name="c">the comparer that specifying the order</param>
        private static void SortDESC(Object[] a, IComparer c)
        {
            int N = a.Length;

            for (int i = 0; i < N; i++)
            {
                int max = i;
                for (int j = i + 1; j < N; j++)
                    if (SortHelper.Greater(c, a[j], a[max]))
                        max = j;
                SortHelper.Exchange(a, i, max);
            }
        }
    }
}
