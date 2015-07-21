package thed;

/**
 * Wrapper to handle.
 *
 * @author Derek Mulvihill - Nov 11, 2013
 */
public class GLBuffer {
    private int triIndex = 0;
    private final int triangles;
    private final float[] vertices;
    private final float[] colors;

    public GLBuffer(int triangles) {
        this.triangles = triangles;
        vertices = new float[triangles * 3 * 3];
        colors = new float[triangles * 3 * 4];
    }

    public Tri t() {
        if (triIndex >= triangles) {
            return null;
        }
        Tri tri = new Tri(triIndex);
        triIndex++;
        return tri;
    }

    public GLBuffer tvcb(double x0, double y0, double x1, double y1, double x2, double y2, float r,
                         float g, float b) {
        return t().v(x0, y0, x1, y1, x2, y2).c(r, g, b).b();
    }

    public int getTriangles() {
        return triangles;
    }

    public int getVertexCount() {
        return triangles * 3;
    }

    public float[] getVertices() {
        return vertices;
    }

    public float[] getColors() {
        return colors;
    }

    public class Tri {
        protected int i;

        protected Tri(int i) {
            this.i = i;
        }

        public Tri v(double x0, double y0, double x1, double y1, double x2, double y2) {
            int n = i * 3 * 3;
            vertices[n++] = (float) x0;
            vertices[n++] = (float) y0;
            vertices[n++] = (float) 0f;

            vertices[n++] = (float) x1;
            vertices[n++] = (float) y1;
            vertices[n++] = (float) 0f;

            vertices[n++] = (float) x2;
            vertices[n++] = (float) y2;
            vertices[n++] = (float) 0f;

            return this;
        }

        public Tri c(float r, float g, float b) {
            return c(r, g, b, 1);
        }

        public Tri c(float r, float g, float b, float a) {
            int n = i * 3 * 4;

            colors[n++] = r;
            colors[n++] = g;
            colors[n++] = b;
            colors[n++] = a;

            colors[n++] = r;
            colors[n++] = g;
            colors[n++] = b;
            colors[n++] = a;

            colors[n++] = r;
            colors[n++] = g;
            colors[n++] = b;
            colors[n++] = a;

            return this;
        }

        public Tri c(GLColor color) {
            int n = i * 3 * 4;

            System.arraycopy(color.get(), 0, colors, n, 12);

            return this;
        }

        public Tri c(float[] colorers) {
            int n = i * 3 * 4;

            System.arraycopy(colorers, 0, colors, n, 12);

            return this;
        }

        public GLBuffer b() {
            return GLBuffer.this;
        }
    }
}
