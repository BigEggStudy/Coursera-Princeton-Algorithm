using BigEgg.Algorithm.UnionFinds;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;

namespace BigEgg.Algorithm.Test.UnionFinds
{
    [TestClass]
    public class QuickFindUFTest
    {
        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void testUnion_ParameterValidation_PLessThan0()
        {
            QuickFindUF quickFindUF = new QuickFindUF(10);
            quickFindUF.Union(-1, 4);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void testUnion_ParameterValidation_PEqualN()
        {
            QuickFindUF quickFindUF = new QuickFindUF(10);
            quickFindUF.Union(10, 4);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void testUnion_ParameterValidation_PLargerThanN()
        {
            QuickFindUF quickFindUF = new QuickFindUF(10);
            quickFindUF.Union(11, 4);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void testUnion_ParameterValidation_QLessThan0()
        {
            QuickFindUF quickFindUF = new QuickFindUF(10);
            quickFindUF.Union(4, -1);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void testUnion_ParameterValidation_QEqualN()
        {
            QuickFindUF quickFindUF = new QuickFindUF(10);
            quickFindUF.Union(4, 10);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void testUnion_ParameterValidation_QLargerThanN()
        {
            QuickFindUF quickFindUF = new QuickFindUF(10);
            quickFindUF.Union(4, 11);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void testConnected_ParameterValidation_PLessThan0()
        {
            QuickFindUF quickFindUF = new QuickFindUF(10);
            quickFindUF.Connected(-1, 4);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void testConnected_ParameterValidation_PEqualN()
        {
            QuickFindUF quickFindUF = new QuickFindUF(10);
            quickFindUF.Connected(10, 4);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void testConnected_ParameterValidation_PLargerThanN()
        {
            QuickFindUF quickFindUF = new QuickFindUF(10);
            quickFindUF.Connected(11, 4);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void testConnected_ParameterValidation_QLessThan0()
        {
            QuickFindUF quickFindUF = new QuickFindUF(10);
            quickFindUF.Connected(4, -1);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void testConnected_ParameterValidation_QEqualN()
        {
            QuickFindUF quickFindUF = new QuickFindUF(10);
            quickFindUF.Connected(4, 10);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void testConnected_ParameterValidation_QLargerThanN()
        {
            QuickFindUF quickFindUF = new QuickFindUF(10);
            quickFindUF.Connected(4, 11);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void testFind_ParameterValidation_PLessThan0()
        {
            QuickFindUF quickFindUF = new QuickFindUF(10);
            quickFindUF.Find(-1);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void testFind_ParameterValidation_PEqualN()
        {
            QuickFindUF quickFindUF = new QuickFindUF(10);
            quickFindUF.Find(10);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void testFind_ParameterValidation_PLargerThanN()
        {
            QuickFindUF quickFindUF = new QuickFindUF(10);
            quickFindUF.Find(11);
        }


        [TestMethod]
        public void testUnionAndConnected_NewConnection()
        {
            QuickFindUF quickFindUF = new QuickFindUF(10);
            Assert.IsFalse(quickFindUF.Connected(0, 1));
            Assert.AreEqual(10, quickFindUF.Count());
            quickFindUF.Union(0, 1);
            Assert.IsTrue(quickFindUF.Connected(0, 1));
            Assert.AreEqual(9, quickFindUF.Count());
            Assert.AreEqual(1, quickFindUF.Find(1));
            Assert.AreEqual(1, quickFindUF.Find(0));
        }

        [TestMethod]
        public void testUnionAndConnected_AlreadyConnected()
        {
            QuickFindUF quickFindUF = new QuickFindUF(10);
            quickFindUF.Union(0, 1);
            Assert.IsTrue(quickFindUF.Connected(0, 1));
            Assert.AreEqual(9, quickFindUF.Count());
            quickFindUF.Union(0, 1);
            Assert.IsTrue(quickFindUF.Connected(0, 1));
            Assert.AreEqual(9, quickFindUF.Count());
            Assert.AreEqual(1, quickFindUF.Find(1));
            Assert.AreEqual(1, quickFindUF.Find(0));
        }

        [TestMethod]
        public void testUnionAndConnected_MultiConnection()
        {
            QuickFindUF quickFindUF = new QuickFindUF(10);
            quickFindUF.Union(0, 1);
            quickFindUF.Union(2, 1);
            Assert.IsTrue(quickFindUF.Connected(0, 1));
            Assert.IsTrue(quickFindUF.Connected(2, 1));
            Assert.IsTrue(quickFindUF.Connected(2, 0));
            Assert.AreEqual(8, quickFindUF.Count());
            Assert.AreEqual(1, quickFindUF.Find(1));
            Assert.AreEqual(1, quickFindUF.Find(0));
            Assert.AreEqual(1, quickFindUF.Find(2));

            quickFindUF.Union(2, 5);
            Assert.IsTrue(quickFindUF.Connected(0, 1));
            Assert.IsTrue(quickFindUF.Connected(2, 1));
            Assert.IsTrue(quickFindUF.Connected(2, 0));
            Assert.IsTrue(quickFindUF.Connected(5, 0));
            Assert.IsTrue(quickFindUF.Connected(5, 1));
            Assert.IsTrue(quickFindUF.Connected(5, 2));
            Assert.AreEqual(7, quickFindUF.Count());
            Assert.AreEqual(5, quickFindUF.Find(1));
            Assert.AreEqual(5, quickFindUF.Find(0));
            Assert.AreEqual(5, quickFindUF.Find(2));
            Assert.AreEqual(5, quickFindUF.Find(5));
        }

        [TestMethod]
        public void testFind()
        {
            QuickFindUF quickFindUF = new QuickFindUF(10);
            for (int i = 0; i < 10; i++)
            {
                Assert.AreEqual(i, quickFindUF.Find(i));
            }
        }
    }
}
