using System;
using System.Collections.Generic;

namespace BigEgg.Algorithm.Sorts
{
    public static class HeapSort
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
            for (int k = N / 2; k >= 1; k--)
                SinkASC(a, k, N);
            while (N > 1)
            {
                Exchange(a, 1, N--);
                SinkASC(a, 1, N);
            }
        }

        private static void SinkASC(IComparable[] pq, int k, int N)
        {
            while (2 * k <= N)
            {
                int j = 2 * k;
                if (j < N && Less(pq, j, j + 1)) j++;
                if (!Less(pq, k, j)) break;
                Exchange(pq, k, j);
                k = j;
            }
        }

        private static void SortDESC(IComparable[] a)
        {
            int N = a.Length;
            for (int k = N / 2; k >= 1; k--)
                SinkDESC(a, k, N);
            while (N > 1)
            {
                Exchange(a, 1, N--);
                SinkDESC(a, 1, N);
            }
        }

        private static void SinkDESC(IComparable[] pq, int k, int N)
        {
            while (2 * k <= N)
            {
                int j = 2 * k;
                if (j < N && Greater(pq, j, j + 1)) j++;
                if (!Greater(pq, k, j)) break;
                Exchange(pq, k, j);
                k = j;
            }
        }

        private static void SortASC<Key>(Key[] a, IComparer<Key> c) where Key : class
        {
            int N = a.Length;
            for (int k = N / 2; k >= 1; k--)
                SinkASC(a, c, k, N);
            while (N > 1)
            {
                Exchange(a, 1, N--);
                SinkASC(a, c, 1, N);
            }
        }

        private static void SinkASC<Key>(Key[] pq, IComparer<Key> c, int k, int N) where Key : class
        {
            while (2 * k <= N)
            {
                int j = 2 * k;
                if (j < N && Less(pq, c, j, j + 1)) j++;
                if (!Less(pq, c, k, j)) break;
                Exchange(pq, k, j);
                k = j;
            }
        }

        private static void SortDESC<Key>(Key[] a, IComparer<Key> c) where Key : class
        {
            int N = a.Length;
            for (int k = N / 2; k >= 1; k--)
                SinkDESC(a, c, k, N);
            while (N > 1)
            {
                Exchange(a, 1, N--);
                SinkDESC(a, c, 1, N);
            }
        }

        private static void SinkDESC<Key>(Key[] pq, IComparer<Key> c, int k, int N) where Key : class
        {
            while (2 * k <= N)
            {
                int j = 2 * k;
                if (j < N && Greater(pq, c, j, j + 1)) j++;
                if (!Greater(pq, c, k, j)) break;
                Exchange(pq, k, j);
                k = j;
            }
        }


        private static bool Less(IComparable[] pq, int i, int j)
        {
            return SortHelper.Less(pq[i - 1], pq[j - 1]);
        }

        private static bool Less<Key>(Key[] pq, IComparer<Key> c, int i, int j) where Key : class
        {
            return SortHelper.Less<Key>(c, pq[i - 1], pq[j - 1]);
        }

        private static bool Greater(IComparable[] pq, int i, int j)
        {
            return SortHelper.Greater(pq[i - 1], pq[j - 1]);
        }

        private static bool Greater<Key>(Key[] pq, IComparer<Key> c, int i, int j) where Key : class
        {
            return SortHelper.Greater<Key>(c, pq[i - 1], pq[j - 1]);
        }

        private static void Exchange(Object[] a, int i, int j)
        {
            SortHelper.Exchange(a, i - 1, j - 1);
        }
    }
}
