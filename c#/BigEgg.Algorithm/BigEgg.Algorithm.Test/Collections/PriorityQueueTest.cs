using BigEgg.Algorithm.Collections;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;

namespace BigEgg.Algorithm.Test.Collections
{
    [TestClass]
    public class PriorityQueueTest
    {
        [TestMethod]
        public void ConstructorTest()
        {
            MockPriorityQueue<int> pq = new MockPriorityQueue<int>();
            Assert.AreEqual(0, pq.Size());
            Assert.IsTrue(pq.IsEmpty());
        }

        [TestMethod]
        public void ConstructorTest_WithKeys()
        {
            int[] a = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

            MockPriorityQueue<int> pq = new MockPriorityQueue<int>(a);
            Assert.AreEqual(10, pq.Size());
            Assert.IsFalse(pq.IsEmpty());
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void InsertTest_NullItem()
        {
            MockPriorityQueue<String> pq = new MockPriorityQueue<String>();
            pq.Insert(null);
        }

        [TestMethod]
        public void InsertTest()
        {
            MockPriorityQueue<int> pq = new MockPriorityQueue<int>();
            pq.Insert(1);
            Assert.AreEqual(1, pq.Size());
            Assert.IsFalse(pq.IsEmpty());
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void FirstTest_Empty()
        {
            MockPriorityQueue<int> pq = new MockPriorityQueue<int>();
            pq.First();
        }

        [TestMethod]
        public void FirstTest()
        {
            MockPriorityQueue<int> pq = new MockPriorityQueue<int>();
            pq.Insert(1);
            Assert.AreEqual(1, pq.Size());
            Assert.IsFalse(pq.IsEmpty());
            int result = pq.First();
            Assert.AreEqual(1, result);
            Assert.AreEqual(1, pq.Size());
            Assert.IsFalse(pq.IsEmpty());
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void DeleteTest_Empty()
        {
            MockPriorityQueue<int> pq = new MockPriorityQueue<int>();
            pq.Delete();
        }

        [TestMethod]
        public void DeleteTest()
        {
            MockPriorityQueue<int> pq = new MockPriorityQueue<int>();
            pq.Insert(1);
            Assert.AreEqual(1, pq.Size());
            Assert.IsFalse(pq.IsEmpty());
            int result = pq.Delete();
            Assert.AreEqual(1, result);
            Assert.AreEqual(0, pq.Size());
            Assert.IsTrue(pq.IsEmpty());
        }


        private class MockPriorityQueue<Key> : PriorityQueue<Key>
        {
            public MockPriorityQueue()
                : base()
            { }

            public MockPriorityQueue(int initCapacity)
                : base(initCapacity)
            { }

            public MockPriorityQueue(IComparer<Key> comparer)
                : base(comparer)
            { }

            public MockPriorityQueue(int initCapacity, IComparer<Key> comparer)
                : base(initCapacity, comparer)
            { }

            public MockPriorityQueue(Key[] keys)
                : this(keys, null)
            { }

            public MockPriorityQueue(Key[] keys, IComparer<Key> comparer)
                : base(keys, comparer)
            { }

            public override IEnumerator<Key> GetEnumerator()
            {
                throw new NotImplementedException();
            }

            protected override void Sink(int k)
            { }

            protected override void Swim(int k)
            { }
        }
    }
}
