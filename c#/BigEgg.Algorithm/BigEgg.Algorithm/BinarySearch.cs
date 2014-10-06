namespace BigEgg.Algorithm
{
    public static class BinarySearch
    {
        /// <summary>
        /// Prevents a default instance of the <see cref="BinarySearch"/> class from being created.
        /// </summary>
        private BinarySearch() { }

        /// <summary>
        /// Searches for the integer key in the sorted array a[].
        /// </summary>
        /// <param name="a">The array of integers, must be sorted in ascending order.</param>
        /// <param name="key">The search key.</param>
        /// <returns>Index of key in array a[] if present; -1 if not present.</returns>
        public int Rank(int[] a, int key)
        {
            int lo = 0;
            int hi = a.Length - 1;
            while (lo <= hi)
            {
                int mid = lo + (hi - lo) / 2;
                if (key < a[mid]) hi = mid - 1;
                else if (key > a[mid]) lo = mid + 1;
                else return mid;
            }
            return -1;
        }
    }
}
