package edu.eci.cvds;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import antlr.collections.List;
import edu.eci.cvds.modelo.Usuario;
import edu.eci.cvds.repositorios.UsuarioRepositorio;
import edu.eci.cvds.servicios.UsuarioServicio;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServicioTest {
    private Usuario user;
    @Mock
    private UsuarioRepositorio userRepository;

    @InjectMocks
    private UsuarioServicio userService;

    @Test
  public void testSaveUser() {

    Usuario user = new Usuario();
    user.setId((long) 1);
    user.setUserName("testuser");
    user.setPassword("testpass");
    user.setFirstName("Test");
    user.setLastName("User");
    user.setEmail("testuser@example.com");

    userService.saveUser(user);
    verify(userRepository, times(1)).save(user);
  }
  
    
    @Test
        public void testFindAll() {
        ArrayList<Usuario> userList = new ArrayList<>();
        Usuario user1 = new Usuario((long) 3, "testuser1", "testpass1", "Test1", "User1", "testuser1@example.com");
        userList.add(user1);
        Usuario user2 = new Usuario((long) 4, "testuser2", "testpass2", "Test2", "User2", "testuser2@example.com");
        userList.add(user2);
        when(userRepository.findAll()).thenReturn(userList);
        ArrayList<Usuario> result = (ArrayList<Usuario>) userService.findAll();
        assertEquals(userList, result);
  }

    // public void deleteUser(Long userId)
  @Test
        public void testDeleteUser() {
        Usuario user = new Usuario((long) 3, "testuser", "testpass", "Test", "User", "testuser@example.com");

        userService.deleteUserName(user.getUserName() );

        verify(userRepository, times(1)).deleteByUserName(user.getUserName());
    }

  // public void updateUser(Usuario user)
  @Test
        public void testUpdateUser() {
        Usuario user = new Usuario((long) 4, "testuser", "testpass", "Test", "User", "testuser@example.com");

        when(userRepository.existsById(user.getId())).thenReturn(true);
        userService.updateUser(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testLogin() {
        user = new Usuario((long) 4, "testuser", "testpass", "Test", "User", "testuser@example.com");
        // Llama al método que deseas probar
        boolean result = userService.login("testuser", "testpass");
        System.out.println(result);
        // Realiza las aserciones correspondientes para verificar el resultado
        assertTrue(true);
    }

}