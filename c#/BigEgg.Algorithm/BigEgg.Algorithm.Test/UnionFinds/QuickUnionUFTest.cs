using BigEgg.Algorithm.UnionFinds;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;

namespace BigEgg.Algorithm.Test.UnionFinds
{
    [TestClass]
    public class QuickUnionUFTest
    {
        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testUnion_ParameterValidation_PLessThan0()
        {
            QuickUnionUF quickUnionUF = new QuickUnionUF(10);
            quickUnionUF.Union(-1, 4);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testUnion_ParameterValidation_PEqualN()
        {
            QuickUnionUF quickUnionUF = new QuickUnionUF(10);
            quickUnionUF.Union(10, 4);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testUnion_ParameterValidation_PLargerThanN()
        {
            QuickUnionUF quickUnionUF = new QuickUnionUF(10);
            quickUnionUF.Union(11, 4);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testUnion_ParameterValidation_QLessThan0()
        {
            QuickUnionUF quickUnionUF = new QuickUnionUF(10);
            quickUnionUF.Union(4, -1);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testUnion_ParameterValidation_QEqualN()
        {
            QuickUnionUF quickUnionUF = new QuickUnionUF(10);
            quickUnionUF.Union(4, 10);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testUnion_ParameterValidation_QLargerThanN()
        {
            QuickUnionUF quickUnionUF = new QuickUnionUF(10);
            quickUnionUF.Union(4, 11);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testConnected_ParameterValidation_PLessThan0()
        {
            QuickUnionUF quickUnionUF = new QuickUnionUF(10);
            quickUnionUF.Connected(-1, 4);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testConnected_ParameterValidation_PEqualN()
        {
            QuickUnionUF quickUnionUF = new QuickUnionUF(10);
            quickUnionUF.Connected(10, 4);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testConnected_ParameterValidation_PLargerThanN()
        {
            QuickUnionUF quickUnionUF = new QuickUnionUF(10);
            quickUnionUF.Connected(11, 4);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testConnected_ParameterValidation_QLessThan0()
        {
            QuickUnionUF quickUnionUF = new QuickUnionUF(10);
            quickUnionUF.Connected(4, -1);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testConnected_ParameterValidation_QEqualN()
        {
            QuickUnionUF quickUnionUF = new QuickUnionUF(10);
            quickUnionUF.Connected(4, 10);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testConnected_ParameterValidation_QLargerThanN()
        {
            QuickUnionUF quickUnionUF = new QuickUnionUF(10);
            quickUnionUF.Connected(4, 11);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testFind_ParameterValidation_PLessThan0()
        {
            QuickUnionUF quickUnionUF = new QuickUnionUF(10);
            quickUnionUF.Find(-1);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testFind_ParameterValidation_PEqualN()
        {
            QuickUnionUF quickUnionUF = new QuickUnionUF(10);
            quickUnionUF.Find(10);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testFind_ParameterValidation_PLargerThanN()
        {
            QuickUnionUF quickUnionUF = new QuickUnionUF(10);
            quickUnionUF.Find(11);
        }


        [TestMethod]
        public void testUnionAndConnected_NewConnection()
        {
            QuickUnionUF quickUnionUF = new QuickUnionUF(10);
            Assert.IsFalse(quickUnionUF.Connected(0, 1));
            Assert.AreEqual(10, quickUnionUF.Count());
            quickUnionUF.Union(0, 1);
            Assert.IsTrue(quickUnionUF.Connected(0, 1));
            Assert.AreEqual(9, quickUnionUF.Count());
            Assert.AreEqual(1, quickUnionUF.Find(1));
            Assert.AreEqual(1, quickUnionUF.Find(0));
        }

        [TestMethod]
        public void testUnionAndConnected_AlreadyConnected()
        {
            QuickUnionUF quickUnionUF = new QuickUnionUF(10);
            quickUnionUF.Union(0, 1);
            Assert.IsTrue(quickUnionUF.Connected(0, 1));
            Assert.AreEqual(9, quickUnionUF.Count());
            quickUnionUF.Union(0, 1);
            Assert.IsTrue(quickUnionUF.Connected(0, 1));
            Assert.AreEqual(9, quickUnionUF.Count());
            Assert.AreEqual(1, quickUnionUF.Find(1));
            Assert.AreEqual(1, quickUnionUF.Find(0));
        }

        [TestMethod]
        public void testUnionAndConnected_MultiConnection()
        {
            QuickUnionUF quickUnionUF = new QuickUnionUF(10);
            quickUnionUF.Union(0, 1);
            quickUnionUF.Union(2, 1);
            Assert.IsTrue(quickUnionUF.Connected(0, 1));
            Assert.IsTrue(quickUnionUF.Connected(2, 1));
            Assert.IsTrue(quickUnionUF.Connected(2, 0));
            Assert.AreEqual(8, quickUnionUF.Count());
            Assert.AreEqual(1, quickUnionUF.Find(1));
            Assert.AreEqual(1, quickUnionUF.Find(0));
            Assert.AreEqual(1, quickUnionUF.Find(2));

            quickUnionUF.Union(2, 5);
            Assert.IsTrue(quickUnionUF.Connected(0, 1));
            Assert.IsTrue(quickUnionUF.Connected(2, 1));
            Assert.IsTrue(quickUnionUF.Connected(2, 0));
            Assert.IsTrue(quickUnionUF.Connected(5, 0));
            Assert.IsTrue(quickUnionUF.Connected(5, 1));
            Assert.IsTrue(quickUnionUF.Connected(5, 2));
            Assert.AreEqual(7, quickUnionUF.Count());
            Assert.AreEqual(5, quickUnionUF.Find(1));
            Assert.AreEqual(5, quickUnionUF.Find(0));
            Assert.AreEqual(5, quickUnionUF.Find(2));
            Assert.AreEqual(5, quickUnionUF.Find(5));
        }

        [TestMethod]
        public void testFind()
        {
            QuickUnionUF quickUnionUF = new QuickUnionUF(10);
            for (int i = 0; i < 10; i++)
            {
                Assert.AreEqual(i, quickUnionUF.Find(i));
            }
        }
    }
}
