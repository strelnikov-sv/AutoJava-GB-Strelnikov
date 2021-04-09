package org.example;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {
    private static Logger logger = LoggerFactory.getLogger(TriangleTest.class);

    @Nested
    @DisplayName("Тесты проверки треугольника:")
    class UnitTests {

        @Test
        @DisplayName("Треугольник со сторонами 0,0,0 нельзя создать")
        void zeroAreaTriangleCanNotBeCreated() {
            assertThrows(IllegalArgumentException.class,() -> new Triangle(0,0,0));
        }

        @Test
        @DisplayName("Треугольник со сторонами -3,-100,2 нельзя создать")
        void negativeLengthSideTriangleCanNotBeCreated() {
            assertThrows(IllegalArgumentException.class,() -> new Triangle(-3,-100,2));
        }

        @Test
        @DisplayName("Треугольник со сторонами 3,4,5 создается без ошибок")
        void validTriangleCanBeCreated() {
            new Triangle(3,4,5);
        }

        @Test
        @DisplayName("Площадь треугольника со сторонами 3,4,5 расщитывается без ошибок")
        void areaCalculation() {
            Triangle triangle = new Triangle(3,4,5);
            assertEquals(triangle.area(),6);
            logger.info("Test is successful");
        }
    }
}
