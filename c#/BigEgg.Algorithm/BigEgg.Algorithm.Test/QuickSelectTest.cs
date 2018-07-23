using BigEgg.Algorithm.Sorts;
using BigEgg.Algorithm.Test.Sorts;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;

namespace BigEgg.Algorithm.Test
{
    [TestClass]
    public class QuickSelectTest
    {
        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void SelectTest_NullArray()
        {
            QuickSelect.Select(null, 1);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentException))]
        public void SelectTest_KLessThan0()
        {
            String[] a = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
            QuickSelect.Select(a, -1);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentException))]
        public void SelectTest_KGreaterOrEqualThanN()
        {
            String[] a = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
            QuickSelect.Select(a, 10);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void SelectWithComparerTest_NullArray()
        {
            QuickSelect.Select(null, Point.X_ORDER, 1);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void SelectWithComparerTest_NullComparer()
        {
            Point[] points = new Point[10];
            for (int i = 0; i < 10; i++)
            {
                points[i] = new Point(i, 10 - i);
            }

            QuickSelect.Select(points, null, 1);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentException))]
        public void SelectWithComparerTest_KLessThan0()
        {
            Point[] points = new Point[10];
            for (int i = 0; i < 10; i++)
            {
                points[i] = new Point(i, 10 - i);
            }
            QuickSelect.Select(points, Point.X_ORDER, -1);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentException))]
        public void SelectWithComparerTest_KGreaterOrEqualThanN()
        {
            Point[] points = new Point[10];
            for (int i = 0; i < 10; i++)
            {
                points[i] = new Point(i, 10 - i);
            }
            QuickSelect.Select(points, Point.X_ORDER, 10);
        }

        [TestMethod]
        public void SelectTest()
        {
            String[] a = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
            Shuffle.Do(a);

            String v = QuickSelect.Select(a, 3) as String;
            Assert.IsNotNull(v);
            Assert.AreEqual("3", v);
            for (int i = 0; i < 3; i++)
                Assert.IsTrue(SortHelper.Less(a[i], v));
            Assert.AreEqual(a[3], v);
        }

        [TestMethod]
        public void SelectWithComparerTest()
        {
            Point[] points = new Point[10];
            for (int i = 0; i < 10; i++)
            {
                points[i] = new Point(i, 10 - i);
            }
            Shuffle.Do(points);

            Point v = QuickSelect.Select(points, Point.X_ORDER, 3) as Point;
            Assert.IsNotNull(v);
            Assert.AreEqual(3, v.X);
            Assert.AreEqual(7, v.Y);
            for (int i = 0; i < 3; i++)
                Assert.IsTrue(SortHelper.Less(Point.X_ORDER, points[i], v));
            Assert.AreEqual(points[3], v);
        }
    }
}
