using BigEgg.Algorithm.Sorts;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;

namespace BigEgg.Algorithm.Test.Sorts
{
    [TestClass]
    public class BUMergeSortTest
    {
        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void SortTest_NullArray()
        {
            BUMergeSort.Sort(null);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void SortTest_ASCOrder_NullArray()
        {
            BUMergeSort.Sort(null, SortOrder.ASC);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void SortTest_DESCOrder_NullArray()
        {
            BUMergeSort.Sort(null, SortOrder.DESC);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void SortTestWithComparer_NullArray()
        {
            BUMergeSort.Sort(null, Point.X_ORDER);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void SortTestWithComparer_NullComparer()
        {
            Point[] points = new Point[10];
            for (int i = 0; i < 10; i++)
            {
                points[i] = new Point(i, 10 - i);
            }

            BUMergeSort.Sort(points, null);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void SortTestWithComparer_ASCOrder_NullArray()
        {
            BUMergeSort.Sort(null, Point.X_ORDER, SortOrder.ASC);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void SortTestWithComparer_ASCOrder_NullComparer()
        {
            Point[] points = new Point[10];
            for (int i = 0; i < 10; i++)
            {
                points[i] = new Point(i, 10 - i);
            }

            BUMergeSort.Sort(points, null, SortOrder.ASC);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void SortTestWithComparer_DESCOrder_NullArray()
        {
            BUMergeSort.Sort(null, Point.X_ORDER, SortOrder.DESC);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void SortTestWithComparer_DESCOrder_NullComparer()
        {
            Point[] points = new Point[10];
            for (int i = 0; i < 10; i++)
            {
                points[i] = new Point(i, 10 - i);
            }

            BUMergeSort.Sort(points, null, SortOrder.DESC);
        }

        [TestMethod]
        public void SortTest()
        {
            String[] a = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
            Shuffle.Do(a);

            Assert.IsFalse(SortHelper.IsSorted(a));
            BUMergeSort.Sort(a);
            Assert.IsTrue(SortHelper.IsSorted(a));
        }

        [TestMethod]
        public void SortTest_ASC()
        {
            String[] a = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
            Shuffle.Do(a);

            Assert.IsFalse(SortHelper.IsSorted(a, SortOrder.ASC));
            BUMergeSort.Sort(a, SortOrder.ASC);
            Assert.IsTrue(SortHelper.IsSorted(a, SortOrder.ASC));
        }

        [TestMethod]
        public void SortTest_DESC()
        {
            String[] a = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
            Shuffle.Do(a);

            Assert.IsFalse(SortHelper.IsSorted(a, SortOrder.DESC));
            BUMergeSort.Sort(a, SortOrder.DESC);
            Assert.IsTrue(SortHelper.IsSorted(a, SortOrder.DESC));
        }

        [TestMethod]
        public void SortTestWithComparer()
        {
            Point[] points = new Point[10];
            for (int i = 0; i < 10; i++)
            {
                points[i] = new Point(i, 10 - i);
            }
            Shuffle.Do(points);

            Assert.IsFalse(SortHelper.IsSorted(points, Point.X_ORDER));
            BUMergeSort.Sort(points, Point.X_ORDER);
            Assert.IsTrue(SortHelper.IsSorted(points, Point.X_ORDER));
        }

        [TestMethod]
        public void SortTestWithComparer_ASC()
        {
            Point[] points = new Point[10];
            for (int i = 0; i < 10; i++)
            {
                points[i] = new Point(i, 10 - i);
            }
            Shuffle.Do(points);

            Assert.IsFalse(SortHelper.IsSorted(points, Point.X_ORDER, SortOrder.ASC));
            BUMergeSort.Sort(points, Point.X_ORDER, SortOrder.ASC);
            Assert.IsTrue(SortHelper.IsSorted(points, Point.X_ORDER, SortOrder.ASC));
        }

        [TestMethod]
        public void SortTestWithComparer_DESC()
        {
            Point[] points = new Point[10];
            for (int i = 0; i < 10; i++)
            {
                points[i] = new Point(i, 10 - i);
            }
            Shuffle.Do(points);

            Assert.IsFalse(SortHelper.IsSorted(points, Point.X_ORDER, SortOrder.DESC));
            BUMergeSort.Sort(points, Point.X_ORDER, SortOrder.DESC);
            Assert.IsTrue(SortHelper.IsSorted(points, Point.X_ORDER, SortOrder.DESC));
        }
    }
}
