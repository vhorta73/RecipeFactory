package core.tables.unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import core.tables.impl.PrivilegeImpl;
import core.tables.interfaces.Privilege;

/**
 * Testing the Privilege implementation.
 * 
 * @author Vasco
 *
 */
public class TestPrivilegeImpl {
    /**
     * The auto-increment id.
     */
    private final int ID = 23;
    
    /**
     * The privilege name.
     */
    private final String NAME = "Privilege name";
    
    /**
     * The privilege status.
     */
    private final int STATUS = 1;
    
    /**
     * The privilege description.
     */
    private final String DESCRIPTION = "This is a description.";

    /**
     * The show flag.
     */
    private final boolean SHOW = true;

    /**
     * The deleted flag.
     */
    private final boolean DELETED = false;

    /**
     * The username that created this privilege.
     */
    private final String CREATED_BY = "snoopy";
    
    /**
     * The date and time this record was created.
     */
    private final Timestamp CREATED_DATE = Timestamp.valueOf("2015-09-05 12:15:18");
    
    /**
     * The username that last updated this record.
     */
    private final String LAST_UPDATED_BY = "garfield";
    
    /**
     * The date and time this record was last updated.
     */
    private final Timestamp LAST_UPDATED_DATE = Timestamp.valueOf("2015-09-05 12:15:19");

    /**
     * The Privilege handler.
     */
    private Privilege privilege;

    /**
     * Initialisations.
     */
    @Before
    public void before() {
        privilege = new PrivilegeImpl(ID, NAME, STATUS, DESCRIPTION, SHOW, DELETED,
                CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);
    }
    
    /**
     * Test id.
     */
    @Test
    public void testId() {
        assertTrue(ID == privilege.getId());
    }

    /**
     * Test name.
     */
    @Test
    public void testName() {
        assertEquals(NAME, privilege.getDisplayName());
    }

    /**
     * Test status.
     */
    @Test
    public void testStatus() {
        assertEquals(STATUS, privilege.getStatusId());
    }

    /**
     * Test description.
     */
    @Test
    public void testDescription() {
        assertEquals(DESCRIPTION, privilege.getDescription());
    }

    /**
     * Test show.
     */
    @Test
    public void testShow() {
        assertEquals(SHOW, privilege.isShow());
    }

    /**
     * Test deleted.
     */
    @Test
    public void testDeleted() {
        assertEquals(DELETED, privilege.isDeleted());
    }

    /**
     * Test created by
     */
    @Test
    public void testCreatedBy() {
        assertEquals(CREATED_BY, privilege.getCreatedBy());
    }

    /**
     * Test created date
     */
    @Test
    public void testCreatedDate() {
        assertEquals(CREATED_DATE, privilege.getCreatedDate());
    }

    /**
     * Test last updated by
     */
    @Test
    public void testLastUpdatedBy() {
        assertEquals(LAST_UPDATED_BY, privilege.getLastUpdatedBy());
    }

    /**
     * Test last updated date
     */
    @Test
    public void testLastUpdatedDate() {
        assertEquals(LAST_UPDATED_DATE, privilege.getLastUpdatedDate());
    }
}
