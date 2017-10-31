/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static org.testng.Assert.*;

/**
 *
 * @author clovis
 */
public class UserNGTest {
    
    public UserNGTest() {
    }

    @org.testng.annotations.BeforeClass
    public static void setUpClass() throws Exception {
    }

    @org.testng.annotations.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @org.testng.annotations.BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @org.testng.annotations.AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Teste de método getEmail, da classe User.
     */
    @org.testng.annotations.Test
    public void testGetEmail() {
        System.out.println("getEmail");
        User instance = new User();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(result, expResult);
        // TODO verifica o código de teste gerado e remove a chamada default para falha.
        fail("O caso de teste \u00e9 um prot\u00f3tipo.");
    }

    /**
     * Teste de método setEmail, da classe User.
     */
    @org.testng.annotations.Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        User instance = new User();
        instance.setEmail(email);
        // TODO verifica o código de teste gerado e remove a chamada default para falha.
        fail("O caso de teste \u00e9 um prot\u00f3tipo.");
    }

    /**
     * Teste de método getPassword, da classe User.
     */
    @org.testng.annotations.Test
    public void testGetPassword() {
        System.out.println("getPassword");
        User instance = new User();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(result, expResult);
        // TODO verifica o código de teste gerado e remove a chamada default para falha.
        fail("O caso de teste \u00e9 um prot\u00f3tipo.");
    }

    /**
     * Teste de método setPassword, da classe User.
     */
    @org.testng.annotations.Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        User instance = new User();
        instance.setPassword(password);
        // TODO verifica o código de teste gerado e remove a chamada default para falha.
        fail("O caso de teste \u00e9 um prot\u00f3tipo.");
    }

    /**
     * Teste de método getID, da classe User.
     */
    @org.testng.annotations.Test
    public void testGetID() {
        System.out.println("getID");
        User instance = new User();
        Long expResult = null;
        Long result = instance.getID();
        assertEquals(result, expResult);
        // TODO verifica o código de teste gerado e remove a chamada default para falha.
        fail("O caso de teste \u00e9 um prot\u00f3tipo.");
    }

    /**
     * Teste de método setID, da classe User.
     */
    @org.testng.annotations.Test
    public void testSetID() {
        System.out.println("setID");
        Long ID = null;
        User instance = new User();
        instance.setID(ID);
        // TODO verifica o código de teste gerado e remove a chamada default para falha.
        fail("O caso de teste \u00e9 um prot\u00f3tipo.");
    }
    
}
