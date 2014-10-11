using BigEgg.Algorithm.Collections;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;

namespace BigEgg.Algorithm.Test.Collections
{
    [TestClass]
    public class LinkedQueueTest
    {
        [TestMethod]
        public void ConstructorTest()
        {
            IQueue<int> queue = new LinkedQueue<int>();
            Assert.IsTrue(queue.IsEmpty());
            Assert.AreEqual(0, queue.Size());
            Assert.IsTrue(string.IsNullOrWhiteSpace(queue.ToString()));
        }

        [TestMethod]
        public void EnqueueAndDequeueTest_ValueType()
        {
            IQueue<int> queue = new LinkedQueue<int>();
            queue.Enqueue(1);
            Assert.IsFalse(queue.IsEmpty());
            Assert.AreEqual(1, queue.Peek());
            Assert.AreEqual(1, queue.Size());
            int value = queue.Dequeue();
            Assert.IsTrue(queue.IsEmpty());
            Assert.AreEqual(0, queue.Size());
            Assert.AreEqual(1, value);
        }

        [TestMethod]
        public void EnqueueAndDequeueTest_ReferenceType()
        {
            IQueue<String> queue = new LinkedQueue<String>();
            queue.Enqueue("1");
            Assert.IsFalse(queue.IsEmpty());
            Assert.AreEqual("1", queue.Peek());
            Assert.AreEqual(1, queue.Size());
            String value = queue.Dequeue();
            Assert.IsTrue(queue.IsEmpty());
            Assert.AreEqual(0, queue.Size());
            Assert.AreEqual("1", value);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void EnqueueTest_NullItem()
        {
            IQueue<String> queue = new LinkedQueue<String>();
            queue.Enqueue(null);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void DequeueTest_Emptyqueue()
        {
            IQueue<int> queue = new LinkedQueue<int>();
            queue.Dequeue();
        }

        [TestMethod]
        public void ManyEnqueueTest()
        {
            IQueue<int> queue = new LinkedQueue<int>();
            for (int i = 0; i < 10; i++)
            {
                queue.Enqueue(i);
                Assert.IsFalse(queue.IsEmpty());
                Assert.AreEqual(i + 1, queue.Size());
                Assert.AreEqual(0, queue.Peek());
            }
        }

        [TestMethod]
        public void ManyDequeueTest()
        {
            IQueue<int> queue = new LinkedQueue<int>();
            for (int i = 0; i < 10; i++)
            {
                queue.Enqueue(i);
            }
            for (int i = 0; i < 10; i++)
            {
                int value = queue.Dequeue();
                Assert.AreEqual(10 - i - 1, queue.Size());
                Assert.AreEqual(i, value);
            }
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void PeekTest_Emptyqueue()
        {
            IQueue<int> queue = new LinkedQueue<int>();
            queue.Peek();
        }

        [TestMethod]
        public void ToStringTest()
        {
            IQueue<int> queue = new LinkedQueue<int>();
            for (int i = 0; i < 10; i++)
            {
                queue.Enqueue(i);
            }
            Assert.AreEqual("0 1 2 3 4 5 6 7 8 9", queue.ToString());
        }
    }
}
