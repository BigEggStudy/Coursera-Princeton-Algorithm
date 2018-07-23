using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace BigEgg.Algorithm.Test
{
    [TestClass]
    public class BinarySearchTest
    {
        [TestMethod]
        public void RankTest_KeySmaller()
        {
            int[] data = new int[5];
            for (int i = 0; i < 5; i++)
            {
                data[i] = i;
            }

            int index = BinarySearch.Rank(data, -1);
            Assert.AreEqual(-1, index);
        }

        [TestMethod]
        public void RankTest_KeyLarger()
        {
            int[] data = new int[5];
            for (int i = 0; i < 5; i++)
            {
                data[i] = i;
            }

            int index = BinarySearch.Rank(data, 5);
            Assert.AreEqual(-1, index);
        }

        [TestMethod]
        public void RankTest_KeyExist()
        {
            int[] data = { 1, 5, 10, 12, 37 };

            int index = BinarySearch.Rank(data, 1);
            Assert.AreEqual(0, index);
            index = BinarySearch.Rank(data, 5);
            Assert.AreEqual(1, index);
            index = BinarySearch.Rank(data, 10);
            Assert.AreEqual(2, index);
            index = BinarySearch.Rank(data, 12);
            Assert.AreEqual(3, index);
            index = BinarySearch.Rank(data, 37);
            Assert.AreEqual(4, index);
        }

        [TestMethod]
        public void RankTest_KeyNotExist()
        {
            int[] data = { 1, 5, 10, 12, 37 };

            int index = BinarySearch.Rank(data, 20);
            Assert.AreEqual(-1, index);
            index = BinarySearch.Rank(data, 3);
            Assert.AreEqual(-1, index);
        }
    }
}
