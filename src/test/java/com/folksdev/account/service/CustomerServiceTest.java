package com.folksdev.account.service;

import com.folksdev.account.dto.CustomerDto;
import com.folksdev.account.dto.CustomerDtoConverter;
import com.folksdev.account.exception.CustomerNotFoundException;
import com.folksdev.account.model.Customer;
import com.folksdev.account.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;



import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {

    private CustomerService service;
    private CustomerRepository customerRepository;
    private CustomerDtoConverter converter;

    @BeforeEach
    public void setUp(){
        // Bu servisin dışa bağımlılıkları mocklandı
        customerRepository = Mockito.mock(CustomerRepository.class);
        converter = Mockito.mock(CustomerDtoConverter.class);
        // Test edilecke servis için bir nesne oluşturuldu.
        service =  new CustomerService(customerRepository,converter);
    }



    @Test
    public void testFindByCustomerId_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException(){

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class,
                () -> service.findCustomerById("id"));
    }

    @Test
    public void testFindByCustomerId_whenCustomerIdExist_shouldReturnCustomer(){
        // Fonksiyon başarılı çalıştığında dönmesi gereken nesnenin olusturulması.
        Customer customer = new Customer("id", "name", "surname", List.of());
        // Mockitonun tetiklenip test edilen fonksiyonun çalışması ve then Return ile fonksiyonun dönmesi gereken değerinin belirlenmesi
        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));
        // Mockitoda dönecek değer belirlendikten sonra, fonksiyonun test edilip resulta atılması.
        Customer result = service.findCustomerById("id");
        // dönmesi gereken değer ile sonucun karşılaştırılması
        assertEquals(result, customer);
    }

    @Test
    public void testGetCustomerById_whenCustomerIdExist_shouldReturnCustomer(){

        Customer customer = new Customer("id", "name", "surname", List.of());
        CustomerDto customerDto = new CustomerDto("id","name","surname",List.of());

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));
        Mockito.when(converter.convertToCustomerDto(customer)).thenReturn(customerDto);

        CustomerDto result = service.getCustomerById("id");
        assertEquals(result, customerDto);
    }

    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException(){

        //Customer customer = new Customer("id", "name", "surname", List.of());
        //CustomerDto customerDto = new CustomerDto("id","name","surname",List.of());

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());
        //Mockito.when(converter.convertToCustomerDto(customer)).thenReturn(customerDto);

        assertThrows(CustomerNotFoundException.class,
                () -> service.getCustomerById("id"));

        // converter'ın hiç bir metodu çağırılmasın çünkü hata üretildi.
        Mockito.verifyNoInteractions(converter);
    }




}