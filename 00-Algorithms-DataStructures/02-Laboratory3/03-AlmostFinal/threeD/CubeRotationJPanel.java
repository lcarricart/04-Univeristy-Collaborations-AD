package threeD;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

import files.SensorData;

public class CubeRotationJPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final SensorData sensorData;
    private int dataIndex = 0;

    // 3D cube definition
    private Point3D[] vertices;
    
    // Define the 6 faces (sides) of the cube
    // Each inner array lists the vertex indices for one face
    private final int[][] faces = {
        {0, 1, 2, 3}, // Bottom face
        {4, 5, 6, 7}, // Top face
        {0, 1, 5, 4}, // Front face
        {2, 3, 7, 6}, // Back face
        {1, 2, 6, 5}, // Right face
        {0, 3, 7, 4}  // Left face
    };

    // Define a color for each of the 6 faces
    private final Color[] faceColors = {
        new Color(255, 0, 0, 150),   // Bottom (Red)
        new Color(0, 255, 0, 150),   // Top (Green)
        new Color(0, 0, 255, 150),   // Front (Blue)
        new Color(255, 255, 0, 150), // Back (Yellow)
        new Color(255, 0, 255, 150), // Right (Magenta)
        new Color(0, 255, 255, 150)  // Left (Cyan)
    };

    // Store total rotation angles (in radians)
    private double totalRotationX = 0;
    private double totalRotationY = 0;
    private double totalRotationZ = 0;

    // Store the last timestamp to calculate delta-time (dt)
    private double lastTimestamp = 0;

    public CubeRotationJPanel(SensorData sensorData) {
        this.sensorData = sensorData;
        
        // Define the 8 vertices of the cube (unchanged)
        vertices = new Point3D[8];
        vertices[0] = new Point3D(-1, -1, -1);
        vertices[1] = new Point3D( 1, -1, -1);
        vertices[2] = new Point3D( 1,  1, -1);
        vertices[3] = new Point3D(-1,  1, -1);
        vertices[4] = new Point3D(-1, -1,  1);
        vertices[5] = new Point3D( 1, -1,  1);
        vertices[6] = new Point3D( 1,  1,  1);
        vertices[7] = new Point3D(-1,  1,  1);

        if (sensorData != null && !sensorData.isEmpty()) {
            lastTimestamp = sensorData.getTimestamps().get(0);
        }
    }

    // This makes sorting easy
    private class Face implements Comparable<Face> {
        Polygon polygon;
        Color color;
        double avgZ;

        Face(Polygon p, Color c, double z) {
            this.polygon = p;
            this.color = c;
            this.avgZ = z;
        }

        @Override
        public int compareTo(Face other) {
            // Sort from largest Z (farthest) to smallest Z (nearest)
            return Double.compare(other.avgZ, this.avgZ);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set high-quality rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Set origin to the center of the panel
        g2d.translate(getWidth() / 2, getHeight() / 2);

        // 1. Project all vertices and store rotated versions
        Point[] projectedPoints = new Point[vertices.length];
        Point3D[] rotatedVertices = new Point3D[vertices.length];
        
        for (int i = 0; i < vertices.length; i++) {
            rotatedVertices[i] = rotatePoint(vertices[i], totalRotationX, totalRotationY, totalRotationZ);
            projectedPoints[i] = projectPoint(rotatedVertices[i]);
        }

        // 2. Create a list of faces
        List<Face> facesToDraw = new ArrayList<>();
        
        for (int i = 0; i < faces.length; i++) {
            int[] faceVertexIndices = faces[i];
            Polygon polygon = new Polygon();
            double zSum = 0.0;

            for (int vertexIndex : faceVertexIndices) {
                // Add the 2D projected point to the polygon
                polygon.addPoint(projectedPoints[vertexIndex].x, projectedPoints[vertexIndex].y);
                
                // Add the 3D rotated Z-depth for sorting
                zSum += rotatedVertices[vertexIndex].z;
            }
            
            // Calculate average Z-depth
            double avgZ = zSum / faceVertexIndices.length;
            
            // Add the face to our list
            facesToDraw.add(new Face(polygon, faceColors[i], avgZ));
        }

        // 3. Sort the faces (Painter's Algorithm)
        // This sorts them from farthest to nearest
        Collections.sort(facesToDraw);

        // 4. Draw the faces in sorted order
        for (Face face : facesToDraw) {
            // Fill the face with its color
            g2d.setColor(face.color);
            g2d.fill(face.polygon);
            
            // Draw a black outline
            g2d.setColor(Color.BLACK);
            g2d.draw(face.polygon);
        }
    }

    private Point3D rotatePoint(Point3D p, double rotX, double rotY, double rotZ) {
        // This method is unchanged
        // Apply Z-axis rotation
        double cosZ = Math.cos(rotZ);
        double sinZ = Math.sin(rotZ);
        double x1 = p.x * cosZ - p.y * sinZ;
        double y1 = p.x * sinZ + p.y * cosZ;
        double z1 = p.z;

        // Apply Y-axis rotation
        double cosY = Math.cos(rotY);
        double sinY = Math.sin(rotY);
        double x2 = x1 * cosY + z1 * sinY;
        double y2 = y1;
        double z2 = -x1 * sinY + z1 * cosY;

        // Apply X-axis rotation
        double cosX = Math.cos(rotX);
        double sinX = Math.sin(rotX);
        double x3 = x2;
        double y3 = y2 * cosX - z2 * sinX;
        double z3 = y2 * sinX + z2 * cosX;

        return new Point3D(x3, y3, z3);
    }

    private Point projectPoint(Point3D p) {
        // This method is unchanged
        // Distance from camera to projection plane (focal length)
        double focalLength = 2;
        // Distance from object to camera
        double distance = 5;
        
        // Perspective projection formula
        double scale = focalLength / (distance + p.z);
        
        // Scale to make it visible (e.g., 100 pixels)
        int screenX = (int) (p.x * scale * 100);
        int screenY = (int) (p.y * scale * 100);

        return new Point(screenX, screenY);
    }

	public void setTimestamp(int newIndex) {
        // This method is unchanged
		totalRotationX = 0;
        totalRotationY = 0;
        totalRotationZ = 0;

        if (sensorData == null || sensorData.isEmpty()) {
            return;
        }
        
        // Get all the data lists
        List<Double> timestamps = sensorData.getTimestamps();
        List<Double> gyroX = sensorData.getColumnData("gyroX");
        List<Double> gyroY = sensorData.getColumnData("gyroY");
        List<Double> gyroZ = sensorData.getColumnData("gyroZ");
        
        // Ensure newIndex is valid
        if (newIndex < 0) newIndex = 0;
        if (newIndex >= sensorData.getSize()) newIndex = sensorData.getSize() - 1;

        // We need a local "last timestamp" for our loop
        double localLastTimestamp = timestamps.get(0);
        
        // Loop from the start (index 0) up to the new index
        for (int i = 0; i <= newIndex; i++) {
            double currentTimestamp = timestamps.get(i);
            
            // Calculate dt in seconds
            double dt = (currentTimestamp - localLastTimestamp) / 1000.0;
            
            // dt should always be positive. If it's not (e.g., at index 0),
            // we'll just use 0, so no rotation is added.
            if (dt < 0) {
                dt = 0;
            }

            // Get angular velocity (in degrees per second)
            double velX_deg = gyroX.get(i);
            double velY_deg = gyroY.get(i);
            double velZ_deg = gyroZ.get(i);

            // Convert to radians per second
            double velX_rad = Math.toRadians(velX_deg);
            double velY_rad = Math.toRadians(velY_deg);
            double velZ_rad = Math.toRadians(velZ_deg);

            // Integrate velocity: angle = angle + (velocity * time)
            totalRotationX += velX_rad * dt;
            totalRotationY += velY_rad * dt;
            totalRotationZ += velZ_rad * dt;

            // Update the local "last timestamp" for the next iteration
            localLastTimestamp = currentTimestamp;
        }
        
        // Store the current index
        this.dataIndex = newIndex;
	}
}