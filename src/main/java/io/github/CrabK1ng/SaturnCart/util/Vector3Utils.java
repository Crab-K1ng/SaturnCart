package io.github.CrabK1ng.SaturnCart.util;

import com.badlogic.gdx.math.Vector3;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Vector3Utils {

    public static Stream<Vector3> getAllInBox(Vector3 corner1, Vector3 corner2) {
        int x1 = (int) Math.min(corner1.x, corner2.x);
        int y1 = (int) Math.min(corner1.y, corner2.y);
        int z1 = (int) Math.min(corner1.z, corner2.z);
        int x2 = (int) Math.max(corner1.x, corner2.x);
        int y2 = (int) Math.max(corner1.y, corner2.y);
        int z2 = (int) Math.max(corner1.z, corner2.z);

        return StreamSupport.stream(new Vector3Iterable(x1, y1, z1, x2, y2, z2).spliterator(), false);
    }

    private static class Vector3Iterable implements Iterable<Vector3> {
        private final int x1, y1, z1, x2, y2, z2;

        public Vector3Iterable(int x1, int y1, int z1, int x2, int y2, int z2) {
            this.x1 = x1;
            this.y1 = y1;
            this.z1 = z1;
            this.x2 = x2;
            this.y2 = y2;
            this.z2 = z2;
        }

        @Override
        public Iterator<Vector3> iterator() {
            return new Iterator<>() {
                private int x = x1;
                private int y = y1;
                private int z = z1;

                @Override
                public boolean hasNext() {
                    return x <= x2 && y <= y2 && z <= z2;
                }

                @Override
                public Vector3 next() {
                    Vector3 current = new Vector3(x, y, z);
                    if (++z > z2) {
                        z = z1;
                        if (++y > y2) {
                            y = y1;
                            ++x;
                        }
                    }
                    return current;
                }
            };
        }
    }

    public static boolean isInsideBox(Vector3 point, Vector3 minCorner, Vector3 maxCorner) {
        return point.x >= minCorner.x && point.x <= maxCorner.x &&
                point.y >= minCorner.y && point.y <= maxCorner.y &&
                point.z >= minCorner.z && point.z <= maxCorner.z;
    }

    public static Vector3[] getMinMaxCorners(Vector3 v1, Vector3 v2) {
        Vector3 minCorner = new Vector3(
                Math.min(v1.x, v2.x),
                Math.min(v1.y, v2.y),
                Math.min(v1.z, v2.z)
        );

        Vector3 maxCorner = new Vector3(
                Math.max(v1.x, v2.x),
                Math.max(v1.y, v2.y),
                Math.max(v1.z, v2.z)
        );

        return new Vector3[]{minCorner, maxCorner};
    }
}

