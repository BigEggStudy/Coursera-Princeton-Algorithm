using System;
using System.Collections.Generic;

namespace BigEgg.Algorithm.Test.Sorts
{
    public class Point : IComparable<Point>
    {
        public static IComparer<Point> X_ORDER = new XOrder();
        public static IComparer<Point> Y_ORDER = new YOrder();

        public Point(int x, int y)
        {
            X = x;
            Y = y;
        }

        public int X { get; private set; }
        public int Y { get; private set; }

        public int CompareTo(Point that)
        {
            if (this.Y < that.Y) return -1;
            if (this.Y > that.Y) return +1;
            if (this.X < that.X) return -1;
            if (this.X > that.X) return +1;
            return 0;
        }

        private class XOrder : IComparer<Point>
        {
            public int Compare(Point x, Point y)
            {
                if (x.X < y.X) return -1;
                if (x.X > y.X) return +1;
                return 0;
            }
        }

        private class YOrder : IComparer<Point>
        {
            public int Compare(Point x, Point y)
            {
                if (x.Y < y.Y) return -1;
                if (x.Y > y.Y) return +1;
                return 0;
            }
        }
    }
}
