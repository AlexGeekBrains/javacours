import org.example.less1.person.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    private Person personMonica;
    private Person personJack;

    @BeforeEach
    public void setUp() {
        personMonica = new Person.Builder()
                .setAddress("Rome")
                .setCountry("Italy")
                .setGender("Female")
                .setAge(25)
                .setFirstName("Monica")
                .setLastName("Bellucci")
                .setMiddleName("Anna Maria")
                .setPhone("7777777")
                .build();
        personJack = new Person.Builder()
                .build();
    }

    @Test
    public void testPersonBuilder() {
        assertEquals("Rome", personMonica.getAddress());
        assertEquals("Italy", personMonica.getCountry());
        assertEquals("Female", personMonica.getGender());
        assertEquals(25, personMonica.getAge());
        assertEquals("Monica", personMonica.getFirstName());
        assertEquals("Bellucci", personMonica.getLastName());
        assertEquals("Anna Maria", personMonica.getMiddleName());
        assertEquals("7777777", personMonica.getPhone());
        assertEquals(0, personJack.getAge());
        assertNull(personJack.getGender());
        assertNull(personJack.getPhone());
        assertNull(personJack.getCountry());
        assertNull(personJack.getAddress());
        assertNull(personJack.getLastName());
        assertNull(personJack.getFirstName());
        assertNull(personJack.getMiddleName());
    }
}
