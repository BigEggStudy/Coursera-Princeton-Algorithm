using BigEgg.Algorithm.Sorts;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;

namespace BigEgg.Algorithm.Test.Sorts
{
    [TestClass]
    public class ShuffleTest
    {
        [TestMethod]
        public void DoTest()
        {
            String[] a = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
            Assert.IsTrue(SortHelper.IsSorted(a));
            Shuffle.Do(a);
            Assert.IsFalse(SortHelper.IsSorted(a));
        }

        [TestMethod]
        public void DoTest_Different()
        {
            String[] a1 = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
            String[] a2 = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

            Shuffle.Do(a1);
            Shuffle.Do(a2);
            Assert.IsFalse(SortHelper.IsSorted(a1));
            Assert.IsFalse(SortHelper.IsSorted(a2));

            var same = true;
            for (int i = 0; i < 10; i++)
                if (a1[i] != a2[i])
                {
                    same = false;
                    break;
                }
            Assert.IsFalse(same);
        }

        [TestMethod]
        public void DoTest_Different_Multi()
        {
            for (int i = 0; i < 10; i++)
            {
                DoTest_Different();
            }
        }
    }
}
