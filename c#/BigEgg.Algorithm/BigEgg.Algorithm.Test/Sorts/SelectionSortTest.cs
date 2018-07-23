using BigEgg.Algorithm.Sorts;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;

namespace BigEgg.Algorithm.Test.Sorts
{
    [TestClass]
    public class SelectionSortTest
    {
        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void SortTest_NullArray()
        {
            SelectionSort.Sort(null);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void SortTest_ASCOrder_NullArray()
        {
            SelectionSort.Sort(null, SortOrder.ASC);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void SortTest_DESCOrder_NullArray()
        {
            SelectionSort.Sort(null, SortOrder.DESC);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void SortWithComparerTest_NullArray()
        {
            SelectionSort.Sort(null, Point.X_ORDER);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void SortWithComparerTest_NullComparer()
        {
            Point[] points = new Point[10];
            for (int i = 0; i < 10; i++)
            {
                points[i] = new Point(i, 10 - i);
            }

            SelectionSort.Sort(points, null);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void SortWithComparerTest_ASCOrder_NullArray()
        {
            SelectionSort.Sort(null, Point.X_ORDER, SortOrder.ASC);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void SortWithComparerTest_ASCOrder_NullComparer()
        {
            Point[] points = new Point[10];
            for (int i = 0; i < 10; i++)
            {
                points[i] = new Point(i, 10 - i);
            }

            SelectionSort.Sort(points, null, SortOrder.ASC);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void SortWithComparerTest_DESCOrder_NullArray()
        {
            SelectionSort.Sort(null, Point.X_ORDER, SortOrder.DESC);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void SortWithComparerTest_DESCOrder_NullComparer()
        {
            Point[] points = new Point[10];
            for (int i = 0; i < 10; i++)
            {
                points[i] = new Point(i, 10 - i);
            }

            SelectionSort.Sort(points, null, SortOrder.DESC);
        }

        [TestMethod]
        public void SortTest()
        {
            String[] a = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
            Shuffle.Do(a);

            Assert.IsFalse(SortHelper.IsSorted(a));
            SelectionSort.Sort(a);
            Assert.IsTrue(SortHelper.IsSorted(a));
        }

        [TestMethod]
        public void SortTest_ASC()
        {
            String[] a = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
            Shuffle.Do(a);

            Assert.IsFalse(SortHelper.IsSorted(a, SortOrder.ASC));
            SelectionSort.Sort(a, SortOrder.ASC);
            Assert.IsTrue(SortHelper.IsSorted(a, SortOrder.ASC));
        }

        [TestMethod]
        public void SortTest_DESC()
        {
            String[] a = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
            Shuffle.Do(a);

            Assert.IsFalse(SortHelper.IsSorted(a, SortOrder.DESC));
            SelectionSort.Sort(a, SortOrder.DESC);
            Assert.IsTrue(SortHelper.IsSorted(a, SortOrder.DESC));
        }

        [TestMethod]
        public void SortWithComparerTest()
        {
            Point[] points = new Point[10];
            for (int i = 0; i < 10; i++)
            {
                points[i] = new Point(i, 10 - i);
            }
            Shuffle.Do(points);

            Assert.IsFalse(SortHelper.IsSorted(points, Point.X_ORDER));
            SelectionSort.Sort(points, Point.X_ORDER);
            Assert.IsTrue(SortHelper.IsSorted(points, Point.X_ORDER));
        }

        [TestMethod]
        public void SortWithComparerTest_ASC()
        {
            Point[] points = new Point[10];
            for (int i = 0; i < 10; i++)
            {
                points[i] = new Point(i, 10 - i);
            }
            Shuffle.Do(points);

            Assert.IsFalse(SortHelper.IsSorted(points, Point.X_ORDER, SortOrder.ASC));
            SelectionSort.Sort(points, Point.X_ORDER, SortOrder.ASC);
            Assert.IsTrue(SortHelper.IsSorted(points, Point.X_ORDER, SortOrder.ASC));
        }

        [TestMethod]
        public void SortWithComparerTest_DESC()
        {
            Point[] points = new Point[10];
            for (int i = 0; i < 10; i++)
            {
                points[i] = new Point(i, 10 - i);
            }
            Shuffle.Do(points);

            Assert.IsFalse(SortHelper.IsSorted(points, Point.X_ORDER, SortOrder.DESC));
            SelectionSort.Sort(points, Point.X_ORDER, SortOrder.DESC);
            Assert.IsTrue(SortHelper.IsSorted(points, Point.X_ORDER, SortOrder.DESC));
        }
    }
}
