using BigEgg.Algorithm.UnionFinds;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;

namespace BigEgg.Algorithm.Test.UnionFinds
{
    [TestClass]
    public class UnionFindTest
    {
        [TestMethod]
        [ExpectedException(typeof(ArgumentException))]
        public void testConstructor_ParameterValidation_NEqual0()
        {
            new MockUnionFind(0);
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentException))]
        public void ConstructorTest_ParameterValidation_NLessThan0()
        {
            new MockUnionFind(-1);
        }

        [TestMethod]
        public void ConstructorTest()
        {
            MockUnionFind unionFind = new MockUnionFind(5);
            Assert.AreEqual(5, unionFind.Count());
        }

        private class MockUnionFind : UnionFind
        {
            public MockUnionFind(int N)
                : base(N)
            { }

            public override void Union(int p, int q)
            {
                throw new NotImplementedException();
            }

            public override bool Connected(int p, int q)
            {
                throw new NotImplementedException();
            }

            public override int Find(int p)
            {
                throw new NotImplementedException();
            }
        }
    }
}
