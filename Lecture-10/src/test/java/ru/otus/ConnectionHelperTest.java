package ru.otus;

import org.junit.Test;
import ru.otus.dataset.UserDataSet;
import ru.otus.executor.Executor;

import java.sql.Connection;

import static org.junit.Assert.*;

public class ConnectionHelperTest {
    @Test
    public void connectionIsNotNull() throws Exception {
        Connection connection = ConnectionHelper.getConnection();
        assertNotNull(connection);
    }

    //@Test
    public void saveUserToDataBase() throws Exception {
        UserDataSet userDataSet = new UserDataSet();
        userDataSet.setName("user2");
        userDataSet.setAge(25);

        Executor executor = new Executor(ConnectionHelper.getConnection());
        executor.save(userDataSet);
    }

    @Test
    public void loadUserFromDataBase() throws Exception {
        Executor executor = new Executor(ConnectionHelper.getConnection());
        UserDataSet user = executor.load(1, UserDataSet.class);

        assertNotNull(user);
        assertEquals(user.getName(), "user1");
        assertEquals(user.getAge(), 20);

        user = executor.load(2, UserDataSet.class);
        assertNotNull(user);
        assertEquals(user.getName(), "user2");
        assertEquals(user.getAge(), 25);
    }
}