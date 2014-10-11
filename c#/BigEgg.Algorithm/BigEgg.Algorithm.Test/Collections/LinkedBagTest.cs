using BigEgg.Algorithm.Collections;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;

namespace BigEgg.Algorithm.Test.Collections
{
    [TestClass]
    public class LinkedBagTest
    {
        [TestMethod]
        public void ConstructorTest()
        {
            IBag<int> bag = new LinkedBag<int>();
            Assert.IsTrue(bag.IsEmpty());
            Assert.AreEqual(0, bag.Size());
            Assert.IsTrue(string.IsNullOrWhiteSpace(bag.ToString()));
        }

        [TestMethod]
        public void AddTest_ValueType()
        {
            IBag<int> bag = new LinkedBag<int>();
            bag.Add(1);
            Assert.IsFalse(bag.IsEmpty());
            Assert.AreEqual(1, bag.Size());
        }

        [TestMethod]
        public void AddTest_ReferenceType()
        {
            IBag<String> bag = new LinkedBag<String>();
            bag.Add("1");
            Assert.IsFalse(bag.IsEmpty());
            Assert.AreEqual(1, bag.Size());
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void AddTest_NullItem()
        {
            IBag<String> bag = new LinkedBag<String>();
            bag.Add(null);
        }

        [TestMethod]
        public void ManyAddTest()
        {
            IBag<int> bag = new LinkedBag<int>();
            for (int i = 0; i < 10; i++)
            {
                bag.Add(i);
                Assert.IsFalse(bag.IsEmpty());
                Assert.AreEqual(i + 1, bag.Size());
            }
        }

        [TestMethod]
        public void ToStringTest()
        {
            IBag<int> bag = new LinkedBag<int>();
            for (int i = 0; i < 10; i++)
            {
                bag.Add(i);
            }
            Assert.AreEqual("9 8 7 6 5 4 3 2 1 0", bag.ToString());
        }
    }
}
