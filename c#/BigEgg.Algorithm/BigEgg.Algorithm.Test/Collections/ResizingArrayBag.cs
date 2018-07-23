using BigEgg.Algorithm.Collections;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;

namespace BigEgg.Algorithm.Test.Collections
{
    [TestClass]
    public class ResizingArrayBag
    {
        [TestMethod]
        public void ConstructorTest()
        {
            IBag<int> bag = new ResizingArrayBag<int>();
            Assert.IsTrue(bag.IsEmpty());
            Assert.AreEqual(0, bag.Size());
            Assert.IsTrue(string.IsNullOrWhiteSpace(bag.ToString()));
        }

        [TestMethod]
        public void AddTest_ValueType()
        {
            IBag<int> bag = new ResizingArrayBag<int>();
            bag.Add(1);
            Assert.IsFalse(bag.IsEmpty());
            Assert.AreEqual(1, bag.Size());
        }

        [TestMethod]
        public void AddTest_ReferenceType()
        {
            IBag<String> bag = new ResizingArrayBag<String>();
            bag.Add("1");
            Assert.IsFalse(bag.IsEmpty());
            Assert.AreEqual(1, bag.Size());
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void AddTest_NullItem()
        {
            IBag<String> bag = new ResizingArrayBag<String>();
            bag.Add(null);
        }

        [TestMethod]
        public void ManyAddTest()
        {
            IBag<int> bag = new ResizingArrayBag<int>();
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
            IBag<int> bag = new ResizingArrayBag<int>();
            for (int i = 0; i < 10; i++)
            {
                bag.Add(i);
            }
            Assert.AreEqual("0 1 2 3 4 5 6 7 8 9", bag.ToString());
        }
    }
}
