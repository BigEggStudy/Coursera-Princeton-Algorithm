using BigEgg.Algorithm.Collections;
using BigEgg.Algorithm.Sorts;
using BigEgg.Algorithm.Test.Sorts;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace BigEgg.Algorithm.Test.Collections
{
    [TestClass]
    public class MaxPriorityQueueTest
    {
        [TestMethod]
        public void MaxPriorityQueueTest_InitWithKey()
        {
            int[] a = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
            Shuffle.Do(a);

            MaxPriorityQueue<int> pq = new MaxPriorityQueue<int>(a);
            Assert.IsFalse(pq.IsEmpty());
            Assert.AreEqual(10, pq.Size());

            for (int i = 0; i < 10; i++)
            {
                int result = pq.Max();
                Assert.AreEqual(10 - i - 1, result);
                result = pq.DelMax();
                Assert.AreEqual(10 - i - 1, result);
            }
            Assert.IsTrue(pq.IsEmpty());
            Assert.AreEqual(0, pq.Size());
        }

        [TestMethod]
        public void MaxPriorityQueueTest_Insert()
        {
            int[] a = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
            Shuffle.Do(a);

            MaxPriorityQueue<int> pq = new MaxPriorityQueue<int>();
            foreach (var value in a)
            {
                pq.Insert(value);
            }
            Assert.IsFalse(pq.IsEmpty());
            Assert.AreEqual(10, pq.Size());

            for (int i = 0; i < 10; i++)
            {
                int result = pq.Max();
                Assert.AreEqual(10 - i - 1, result);
                result = pq.DelMax();
                Assert.AreEqual(10 - i - 1, result);
            }
            Assert.IsTrue(pq.IsEmpty());
            Assert.AreEqual(0, pq.Size());
        }

        [TestMethod]
        public void MaxPriorityQueueTest_Enumerator()
        {
            int[] a = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
            Shuffle.Do(a);

            MaxPriorityQueue<int> pq = new MaxPriorityQueue<int>();
            foreach (var value in a)
            {
                pq.Insert(value);
            }
            Assert.IsFalse(pq.IsEmpty());
            Assert.AreEqual(10, pq.Size());

            int expectedValue = 9;
            foreach (var value in pq)
            {
                Assert.AreEqual(expectedValue--, value);
            }
            Assert.IsFalse(pq.IsEmpty());
            Assert.AreEqual(10, pq.Size());
        }

        [TestMethod]
        public void MaxPriorityQueueTest_Comparer()
        {
            Point[] points = new Point[10];
            for (int i = 0; i < 10; i++)
            {
                points[i] = new Point(i, 10 - i);
            }
            Shuffle.Do(points);

            MaxPriorityQueue<Point> pq = new MaxPriorityQueue<Point>(Point.X_ORDER);
            foreach (var value in points)
            {
                pq.Insert(value);
            }
            Assert.IsFalse(pq.IsEmpty());
            Assert.AreEqual(10, pq.Size());

            Point[] expectPoints = new Point[10];
            for (int i = 0; i < 10; i++)
            {
                expectPoints[i] = new Point(i, 10 - i);
            }
            Shuffle.Do(expectPoints);
            HeapSort.Sort(expectPoints, Point.X_ORDER, SortOrder.DESC);

            for (int i = 0; i < 10; i++)
            {
                var result = pq.Max();
                Assert.AreEqual(expectPoints[i].X, result.X);
                Assert.AreEqual(expectPoints[i].Y, result.Y);
                result = pq.DelMax();
                Assert.AreEqual(expectPoints[i].X, result.X);
                Assert.AreEqual(expectPoints[i].Y, result.Y);
            }
            Assert.IsTrue(pq.IsEmpty());
            Assert.AreEqual(0, pq.Size());
        }

        [TestMethod]
        public void MaxPriorityQueueTest_Comparer_Enumerator()
        {
            Point[] points = new Point[10];
            for (int i = 0; i < 10; i++)
            {
                points[i] = new Point(i, 10 - i);
            }
            Shuffle.Do(points);

            MaxPriorityQueue<Point> pq = new MaxPriorityQueue<Point>(Point.X_ORDER);
            foreach (var value in points)
            {
                pq.Insert(value);
            }
            Assert.IsFalse(pq.IsEmpty());
            Assert.AreEqual(10, pq.Size());

            Point[] expectPoints = new Point[10];
            for (int i = 0; i < 10; i++)
            {
                expectPoints[i] = new Point(i, 10 - i);
            }
            Shuffle.Do(expectPoints);
            HeapSort.Sort(expectPoints, Point.X_ORDER, SortOrder.DESC);

            int expectedIndex = 0;
            foreach (var result in pq)
            {
                Assert.AreEqual(expectPoints[expectedIndex].X, result.X);
                Assert.AreEqual(expectPoints[expectedIndex].Y, result.Y);
                expectedIndex++;
            }
            Assert.IsFalse(pq.IsEmpty());
            Assert.AreEqual(10, pq.Size());
        }
    }
}
