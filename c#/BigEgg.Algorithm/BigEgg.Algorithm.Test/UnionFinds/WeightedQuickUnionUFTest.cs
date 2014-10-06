using BigEgg.Algorithm.UnionFinds;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;

namespace BigEgg.Algorithm.Test.UnionFinds
{
    [TestClass]
    public class WeightedQuickUnionUFTest
    {
        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testUnion_ParameterValidation_PLessThan0()
        {
            WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
            weightedQuickUnionUF.Union(-1, 4);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testUnion_ParameterValidation_PEqualN()
        {
            WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
            weightedQuickUnionUF.Union(10, 4);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testUnion_ParameterValidation_PLargerThanN()
        {
            WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
            weightedQuickUnionUF.Union(11, 4);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testUnion_ParameterValidation_QLessThan0()
        {
            WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
            weightedQuickUnionUF.Union(4, -1);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testUnion_ParameterValidation_QEqualN()
        {
            WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
            weightedQuickUnionUF.Union(4, 10);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testUnion_ParameterValidation_QLargerThanN()
        {
            WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
            weightedQuickUnionUF.Union(4, 11);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testConnected_ParameterValidation_PLessThan0()
        {
            WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
            weightedQuickUnionUF.Connected(-1, 4);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testConnected_ParameterValidation_PEqualN()
        {
            WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
            weightedQuickUnionUF.Connected(10, 4);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testConnected_ParameterValidation_PLargerThanN()
        {
            WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
            weightedQuickUnionUF.Connected(11, 4);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testConnected_ParameterValidation_QLessThan0()
        {
            WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
            weightedQuickUnionUF.Connected(4, -1);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testConnected_ParameterValidation_QEqualN()
        {
            WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
            weightedQuickUnionUF.Connected(4, 10);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testConnected_ParameterValidation_QLargerThanN()
        {
            WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
            weightedQuickUnionUF.Connected(4, 11);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testFind_ParameterValidation_PLessThan0()
        {
            WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
            weightedQuickUnionUF.Find(-1);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testFind_ParameterValidation_PEqualN()
        {
            WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
            weightedQuickUnionUF.Find(10);
        }

        [TestMethod]
        [ExpectedException(typeof(IndexOutOfRangeException))]
        public void testFind_ParameterValidation_PLargerThanN()
        {
            WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
            weightedQuickUnionUF.Find(11);
        }


        [TestMethod]
        public void testUnionAndConnected_NewConnection()
        {
            WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
            Assert.IsFalse(weightedQuickUnionUF.Connected(0, 1));
            Assert.AreEqual(10, weightedQuickUnionUF.Count());
            weightedQuickUnionUF.Union(0, 1);
            Assert.IsTrue(weightedQuickUnionUF.Connected(0, 1));
            Assert.AreEqual(9, weightedQuickUnionUF.Count());
            Assert.AreEqual(0, weightedQuickUnionUF.Find(1));
            Assert.AreEqual(0, weightedQuickUnionUF.Find(0));
        }

        [TestMethod]
        public void testUnionAndConnected_AlreadyConnected()
        {
            WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
            weightedQuickUnionUF.Union(0, 1);
            Assert.IsTrue(weightedQuickUnionUF.Connected(0, 1));
            Assert.AreEqual(9, weightedQuickUnionUF.Count());
            weightedQuickUnionUF.Union(0, 1);
            Assert.IsTrue(weightedQuickUnionUF.Connected(0, 1));
            Assert.AreEqual(9, weightedQuickUnionUF.Count());
            Assert.AreEqual(0, weightedQuickUnionUF.Find(1));
            Assert.AreEqual(0, weightedQuickUnionUF.Find(0));
        }

        [TestMethod]
        public void testUnionAndConnected_MultiConnection()
        {
            WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
            weightedQuickUnionUF.Union(0, 1);
            weightedQuickUnionUF.Union(2, 1);
            Assert.IsTrue(weightedQuickUnionUF.Connected(0, 1));
            Assert.IsTrue(weightedQuickUnionUF.Connected(2, 1));
            Assert.IsTrue(weightedQuickUnionUF.Connected(2, 0));
            Assert.AreEqual(8, weightedQuickUnionUF.Count());
            Assert.AreEqual(0, weightedQuickUnionUF.Find(1));
            Assert.AreEqual(0, weightedQuickUnionUF.Find(0));
            Assert.AreEqual(0, weightedQuickUnionUF.Find(2));

            weightedQuickUnionUF.Union(2, 5);
            Assert.IsTrue(weightedQuickUnionUF.Connected(0, 1));
            Assert.IsTrue(weightedQuickUnionUF.Connected(2, 1));
            Assert.IsTrue(weightedQuickUnionUF.Connected(2, 0));
            Assert.IsTrue(weightedQuickUnionUF.Connected(5, 0));
            Assert.IsTrue(weightedQuickUnionUF.Connected(5, 1));
            Assert.IsTrue(weightedQuickUnionUF.Connected(5, 2));
            Assert.AreEqual(7, weightedQuickUnionUF.Count());
            Assert.AreEqual(0, weightedQuickUnionUF.Find(1));
            Assert.AreEqual(0, weightedQuickUnionUF.Find(0));
            Assert.AreEqual(0, weightedQuickUnionUF.Find(2));
            Assert.AreEqual(0, weightedQuickUnionUF.Find(5));
        }

        [TestMethod]
        public void testFind()
        {
            WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
            for (int i = 0; i < 10; i++)
            {
                Assert.AreEqual(i, weightedQuickUnionUF.Find(i));
            }
        }
    }
}
