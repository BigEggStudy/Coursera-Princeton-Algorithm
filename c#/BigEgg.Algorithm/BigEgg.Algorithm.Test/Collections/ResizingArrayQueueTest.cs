using BigEgg.Algorithm.Collections;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;

namespace BigEgg.Algorithm.Test.Collections
{
    [TestClass]
    public class ResizingArrayQueueTest
    {
        [TestMethod]
        public void ConstructorTest()
        {
            IQueue<int> stack = new ResizingArrayQueue<int>();
            Assert.IsTrue(stack.IsEmpty());
            Assert.AreEqual(0, stack.Size());
            Assert.IsTrue(string.IsNullOrWhiteSpace(stack.ToString()));
        }

        [TestMethod]
        public void EnqueueAndDequeueTest_ValueType()
        {
            IQueue<int> stack = new ResizingArrayQueue<int>();
            stack.Enqueue(1);
            Assert.IsFalse(stack.IsEmpty());
            Assert.AreEqual(1, stack.Peek());
            Assert.AreEqual(1, stack.Size());
            int value = stack.Dequeue();
            Assert.IsTrue(stack.IsEmpty());
            Assert.AreEqual(0, stack.Size());
            Assert.AreEqual(1, value);
        }

        [TestMethod]
        public void EnqueueAndDequeueTest_ReferenceType()
        {
            IQueue<String> stack = new ResizingArrayQueue<String>();
            stack.Enqueue("1");
            Assert.IsFalse(stack.IsEmpty());
            Assert.AreEqual("1", stack.Peek());
            Assert.AreEqual(1, stack.Size());
            String value = stack.Dequeue();
            Assert.IsTrue(stack.IsEmpty());
            Assert.AreEqual(0, stack.Size());
            Assert.AreEqual("1", value);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void EnqueueTest_NullItem()
        {
            IQueue<String> stack = new ResizingArrayQueue<String>();
            stack.Enqueue(null);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void DequeueTest_EmptyStack()
        {
            IQueue<int> stack = new ResizingArrayQueue<int>();
            stack.Dequeue();
        }

        [TestMethod]
        public void ManyEnqueueTest()
        {
            IQueue<int> stack = new ResizingArrayQueue<int>();
            for (int i = 0; i < 10; i++)
            {
                stack.Enqueue(i);
                Assert.IsFalse(stack.IsEmpty());
                Assert.AreEqual(i + 1, stack.Size());
                Assert.AreEqual(0, stack.Peek());
            }
        }

        [TestMethod]
        public void ManyDequeueTest()
        {
            IQueue<int> stack = new ResizingArrayQueue<int>();
            for (int i = 0; i < 10; i++)
            {
                stack.Enqueue(i);
            }
            for (int i = 0; i < 10; i++)
            {
                int value = stack.Dequeue();
                Assert.AreEqual(10 - i - 1, stack.Size());
                Assert.AreEqual(i, value);
            }
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void PeekTest_EmptyStack()
        {
            IQueue<int> stack = new ResizingArrayQueue<int>();
            stack.Peek();
        }

        [TestMethod]
        public void ToStringTest()
        {
            IQueue<int> stack = new ResizingArrayQueue<int>();
            for (int i = 0; i < 10; i++)
            {
                stack.Enqueue(i);
            }
            Assert.AreEqual("0 1 2 3 4 5 6 7 8 9", stack.ToString());
        }
    }
}
