using System;

namespace BigEgg.Algorithm.Sorts
{
    public class Shuffle
    {

        /// <summary>
        /// Rearranges an array of objects in uniformly random order
        /// (under the assumption that <tt>Math.random()</tt> generates independent
        /// and uniformly distributed numbers between 0 and 1).
        /// </summary>
        /// <param name="a">the array to be shuffled</param>
        public static void Shuffle(Object[] a)
        {
            var random = new Random(DateTime.Now.Millisecond);

            int N = a.Length;
            for (int i = 0; i < N; i++)
            {
                int r = random.Next(i + 1);
                SortHelper.Exchange(a, r, i);
            }
        }
    }
}
