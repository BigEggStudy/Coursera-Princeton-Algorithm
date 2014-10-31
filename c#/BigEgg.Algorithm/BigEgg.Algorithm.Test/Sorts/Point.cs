using System;
using System.Collections;
using System.Collections.Generic;

namespace BigEgg.Algorithm.Test.Sorts
{
    public class Point : IComparable<Point>
    {
        public static IComparer X_ORDER = new XOrder();
        public static IComparer Y_ORDER = new YOrder();

        public Point(int x, int y)
        {
            X = x;
            Y = y;
        }

        public int X { private get; set; }
        public int Y { private get; set; }

        public int CompareTo(Point that)
        {
            if (this.Y < that.Y) return -1;
            if (this.Y > that.Y) return +1;
            if (this.X < that.X) return -1;
            if (this.X > that.X) return +1;
            return 0;
        }

        private class XOrder : IComparer, IComparer<Point>
        {
            public int Compare(Point x, Point y)
            {
                if (x.X < y.X) return -1;
                if (x.X > y.X) return +1;
                return 0;
            }

            public int Compare(object x, object y)
            {
                return Compare(x as Point, y as Point);
            }
        }

        private class YOrder : IComparer, IComparer<Point>
        {
            public int Compare(Point x, Point y)
            {
                if (x.Y < y.Y) return -1;
                if (x.Y > y.Y) return +1;
                return 0;
            }

            public int Compare(object x, object y)
            {
                return Compare(x as Point, y as Point);
            }
        }
    }
}
