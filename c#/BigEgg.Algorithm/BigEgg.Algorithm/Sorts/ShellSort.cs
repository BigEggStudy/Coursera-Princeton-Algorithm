using System;
using System.Collections;

namespace BigEgg.Algorithm.Sorts
{
    public static class ShellSort
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

            int h = 1;
            while (h < N / 3) h = h * 3 + 1;

            while (h >= 1)
            {
                for (int i = h; i < N; i++)
                    for (int j = i; j >= h && SortHelper.Less(a[j], a[j - h]); j -= h)
                        SortHelper.Exchange(a, j, j - h);
                h /= 3;
            }
        }

        /// <summary>
        /// Rearranges the array in ascending order, using a comparer.
        /// </summary>
        /// <param name="a">The array to be sorted</param>
        /// <param name="c">The comparer that specifying the order</param>
        private static void SortASC(Object[] a, IComparer c)
        {
            int N = a.Length;

            int h = 1;
            while (h < N / 3) h = h * 3 + 1;

            while (h >= 1)
            {
                for (int i = h; i < N; i++)
                    for (int j = i; j >= h && SortHelper.Less(c, a[j], a[j - h]); j -= h)
                        SortHelper.Exchange(a, j, j - h);
                h /= 3;
            }
        }

        /// <summary>
        /// Rearranges the array in descending order, using the natural order.
        /// </summary>
        /// <param name="a">The array to be sorted</param>
        private static void SortDESC(IComparable[] a)
        {
            int N = a.Length;

            int h = 1;
            while (h < N / 3) h = h * 3 + 1;

            while (h >= 1)
            {
                for (int i = h; i < N; i++)
                    for (int j = i; j >= h && SortHelper.Greater(a[j], a[j - h]); j -= h)
                        SortHelper.Exchange(a, j, j - h);
                h /= 3;
            }
        }

        /// <summary>
        /// Rearranges the array in descending order, using a comparer.
        /// </summary>
        /// <param name="a">The array to be sorted</param>
        /// <param name="c">The comparer that specifying the order</param>
        private static void SortDESC(Object[] a, IComparer c)
        {
            int N = a.Length;

            int h = 1;
            while (h < N / 3) h = h * 3 + 1;

            while (h >= 1)
            {
                for (int i = h; i < N; i++)
                    for (int j = i; j >= h && SortHelper.Greater(c, a[j], a[j - h]); j -= h)
                        SortHelper.Exchange(a, j, j - h);
                h /= 3;
            }
        }
    }
}
