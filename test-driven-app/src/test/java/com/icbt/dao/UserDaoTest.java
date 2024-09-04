package com.icbt.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.icbt.model.User;

public class UserDaoTest {

	private UserDao userDao;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @Before
    public void setUp() throws Exception {
        userDao = new UserDao();
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);
    }

    @Test
    public void testGetUserByUsernameAndPassword() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("user_id")).thenReturn(1);
        when(mockResultSet.getString("username")).thenReturn("testuser");
        when(mockResultSet.getString("password")).thenReturn("testpass");
        when(mockResultSet.getString("role")).thenReturn("customer");

        UserDao userDaoSpy = Mockito.spy(userDao);
        doReturn(mockConnection).when(userDaoSpy).getConnection();

        User user = userDaoSpy.getUserByUsernameAndPassword("testuser", "testpass");

        assertNotNull(user);
        assertEquals(1, user.getUserId());
        assertEquals("testuser", user.getUsername());
        assertEquals("testpass", user.getPassword());
        assertEquals("customer", user.getRole());

        verify(mockPreparedStatement).setString(1, "testuser");
        verify(mockPreparedStatement).setString(2, "testpass");
    }

    
}
