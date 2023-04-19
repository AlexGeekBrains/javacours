package less1;

import org.example.less1.person.Person;
import org.example.less1.shape.Circle;
import org.example.less1.shape.Shape;
import org.example.less1.shape.Square;
import org.example.less1.shape.Triangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ShapeTest {
    private Shape triangle;
    private Shape square;
    private Shape circle;
    @BeforeEach
    public void setUp() {
       triangle = new Triangle(15,20);
       square = new Square(20);
       circle = new Circle(30);
    }

    @Test
    public void testGetArea() {
       assertEquals(150,triangle.getArea());
       assertEquals(400,square.getArea());
       assertEquals(2827.4333882308138,circle.getArea());
    }

}
