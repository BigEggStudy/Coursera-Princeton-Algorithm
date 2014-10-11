using BigEgg.Algorithm.Collections;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BigEgg.Algorithm.Test.Collections
{
    [TestClass]
    public class ResizingArrayStack
    {
        [TestMethod]
        public void ConstructorTest()
        {
            IStack<int> stack = new ResizingArrayStack<int>();
            Assert.IsTrue(stack.IsEmpty());
            Assert.AreEqual(0, stack.Size());
            Assert.IsTrue(string.IsNullOrWhiteSpace(stack.ToString()));
        }

        [TestMethod]
        public void PushAndPopTest_ValueType()
        {
            IStack<int> stack = new ResizingArrayStack<int>();
            stack.Push(1);
            Assert.IsFalse(stack.IsEmpty());
            Assert.AreEqual(1, stack.Peek());
            Assert.AreEqual(1, stack.Size());
            int value = stack.Pop();
            Assert.IsTrue(stack.IsEmpty());
            Assert.AreEqual(0, stack.Size());
            Assert.AreEqual(1, value);
        }

        [TestMethod]
        public void PushAndPopTest_ReferenceType()
        {
            IStack<String> stack = new ResizingArrayStack<String>();
            stack.Push("1");
            Assert.IsFalse(stack.IsEmpty());
            Assert.AreEqual("1", stack.Peek());
            Assert.AreEqual(1, stack.Size());
            String value = stack.Pop();
            Assert.IsTrue(stack.IsEmpty());
            Assert.AreEqual(0, stack.Size());
            Assert.AreEqual("1", value);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentNullException))]
        public void PushTest_NullItem()
        {
            IStack<String> stack = new ResizingArrayStack<String>();
            stack.Push(null);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void PopTest_EmptyStack()
        {
            IStack<int> stack = new ResizingArrayStack<int>();
            stack.Pop();
        }

        [TestMethod]
        public void ManyPushTest()
        {
            IStack<int> stack = new ResizingArrayStack<int>();
            for (int i = 0; i < 10; i++)
            {
                stack.Push(i);
                Assert.IsFalse(stack.IsEmpty());
                Assert.AreEqual(i + 1, stack.Size());
                Assert.AreEqual(i, stack.Peek());
            }
        }

        [TestMethod]
        public void ManyPopTest()
        {
            IStack<int> stack = new ResizingArrayStack<int>();
            for (int i = 0; i < 10; i++)
            {
                stack.Push(i);
            }
            for (int i = 0; i < 10; i++)
            {
                int value = stack.Pop();
                Assert.AreEqual(10 - i - 1, stack.Size());
                Assert.AreEqual(10 - i - 1, value);
            }
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void PeekTest_EmptyStack()
        {
            IStack<int> stack = new ResizingArrayStack<int>();
            stack.Peek();
        }

        [TestMethod]
        public void ToStringTest()
        {
            IStack<int> stack = new ResizingArrayStack<int>();
            for (int i = 0; i < 10; i++)
            {
                stack.Push(i);
            }
            Assert.AreEqual("9 8 7 6 5 4 3 2 1 0", stack.ToString());
        }
    }
}
